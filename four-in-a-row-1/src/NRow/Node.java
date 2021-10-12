package NRow;

import java.util.ArrayList;
public  class Node {
    Board board;
    public int value;
    public Node parent;
    public ArrayList<Node> children;

    public Node(Board board) {
      this.board = board;
    }
    public Node(Board board, Node parent) {
      this.board = board;
      this.parent = parent;
    }

    public Board getBoard() {
      return board;
    }
    public int getValue() {
      return value;
    }
    public void setValue(int i) {
      this.value = i;
    }
    public int getDepth(){
      if (this.parent == null) {
        return 0;
      }
      else {
        return this.parent.getDepth() + 1;
      }
    }
}
