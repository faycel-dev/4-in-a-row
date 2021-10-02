package NRow;

import java.util.Arrays;

import NRow.Heuristics.Heuristic;

public class MinNode extends Node{

    public MinNode(int playerID, int opponentID, Board board) {
        super(playerID, opponentID, board);
    }



    @Override
    protected TreeNode[] getChildren() {
        for (int i = 0; i < currentState.width; i++) {
            Board state = currentState.getNewBoard(i, playerID);
            if (isLeaf(state)) {

                children[i]=new LeafNode(playerID, state);
            } else {
                children[i] = new MaxNode(playerID, opponentID, state);
            }
        }
        return children;
    }

    @Override
    protected TreeNode getNextMaxNode(Board board) {
        for (TreeNode maxNode : getChildren()) {
            if (maxNode.getCurrentState().equals(board)) {
                return maxNode;
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
        for (TreeNode node : getChildren()) {
            node.updateValue(heuristic, depth - 1);
        }
        value = Arrays.stream(getChildren()).mapToInt(n -> n.getValue()).min().getAsInt();
    }
}
