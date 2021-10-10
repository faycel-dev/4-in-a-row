package NRow;

import java.util.ArrayList;
import java.util.List;

public  class Node {
    Board board;
    public int player;
    public int value;
    public int move;
    public int alpha;
    public int beta ;
    public Node parent;
    public ArrayList<Node> children;
    public Node(Board board) {
        this.board = board;
    }
    public Node(Board board, Node parent) {
      this.board = board;
      this.parent = parent;
  }
  /*   Node(Board board, int player, int value,int alpha,int beta ) {
        this.board = board;
        this.player = player;
        this.value = value;
        children = new ArrayList<>();
    } */

    public Board getBoard() {
        return board;
      }
    public int getValue() {
        return value;
      }
    public void setValue(int i){
        this.value = i;
    }
    public void setMove(int i){
      this.value = i;
  }
    public int getMove(){
    return move;
}
    public Node getChild(int i){
          return this.children.get(i);
      }
    public int getDepth(){
      if (this.parent == null){
          return 0;
      }
      else {
        return this.parent.getDepth() + 1;
      }
    }
}
