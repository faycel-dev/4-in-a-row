package NRow;

import java.util.Arrays;

import NRow.Heuristics.Heuristic;

public class MaxNode extends Node{
    public MaxNode(int playerID, int opponentID, Board board) {
        super(playerID, opponentID, board);
    }

    @Override
    protected TreeNode[] getChildren() {
        for (int i = 0; i < currentState.width; i++) {
            Board newState = currentState.getNewBoard(i, playerID);
            if (isLeaf(newState)) {

                children[i]= new LeafNode(playerID, newState);
            } else {
                children[i] = new MinNode(playerID, opponentID, newState);
            }
        }
        return children;
    }

    @Override
    protected TreeNode getNextMaxNode(Board board) {
        for (TreeNode minNode : getChildren()) {
            if (minNode instanceof Node) {
                for (TreeNode maxNode : ((Node) minNode).getChildren()) {
                    if (maxNode.getCurrentState().equals(board)) {
                        return maxNode;
                    }
                }
            }
        }
        return null;
    }


    @Override
    public void updateValue(Heuristic heuristic, int depth) {
        if (depth <= 0) {
            value = heuristic.evaluateBoard(playerID, currentState);
            return;
        }
        for (TreeNode n : getChildren()) {
            n.updateValue(heuristic, depth - 1);
        }
        value = Arrays.stream(getChildren()).mapToInt(node -> node.getValue()).max().getAsInt();

    }
}
