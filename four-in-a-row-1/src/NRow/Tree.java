package NRow;

public class Tree {
    private TreeNode root;
    private TreeNode curentState;
  public Tree (int playerid , int w, int h){
      if(playerid==1){

          root=new MaxNode(playerid,2, new Board(w,h));

      }else{
          root=new MinNode(playerid,1,new Board(w,h));
      }

      curentState=root;
  }
    public void updatecurentState(Board board){
        if(curentState instanceof Node)
            curentState = ((Node) curentState).getNextMaxNode(board);
    }
    public TreeNode getCurrentNode() {
        return curentState;
    }

    public TreeNode getRoot() {
        return root;
    }


}
