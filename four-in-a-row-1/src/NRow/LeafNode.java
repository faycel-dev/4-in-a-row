package NRow;

import NRow.Heuristics.Heuristic;

public class LeafNode extends TreeNode{
    public LeafNode(int playerID, Board board) {
        this.playerID = playerID;
        this.currentState = board;
    }

    @Override
    public void updateValue(Heuristic heuristic, int depth) {
        value=heuristic.evaluateBoard(playerID,currentState);
    }
}
