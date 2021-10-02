package NRow;

import NRow.Heuristics.Heuristic;

public abstract class TreeNode {
    protected int playerID;
    protected Board currentState;
    protected int value;

    public Board getCurrentState() {
        return currentState;
    }
    public int getValue() {
        return value;
    }
    public abstract void updateValue(Heuristic heuristic, int depth);
}
