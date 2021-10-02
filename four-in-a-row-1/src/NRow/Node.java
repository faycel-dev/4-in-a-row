package NRow;

public abstract class Node extends TreeNode{

    protected TreeNode[] children;
    protected int opponentID;
    //constructor
    public Node(int playerId, int opponent,Board board){
        playerID = playerId;
        currentState = board;
        opponentID=opponent;
        children = new TreeNode[board.width];
    }


//    public TreeNode[] children(){
//        for (int i = 0; i < currentState.width; i++) {
//            Board state = currentState.getNewBoard(i, playerID);
////            if (isLeaf(state)) {
////                children.add(new LeafNode(playerID, state));
////            } else {
////                //children[i] = new MinNode(playerID, opponentID, state);
////            }
//        }
//    }

    protected  boolean isLeaf(Board state){
        return false;
    }

    protected abstract TreeNode[] getChildren();
    protected abstract TreeNode getNextMaxNode(Board board);

}
