package NRow;
import java.util.*;  

import NRow.Board;
import NRow.Players.*;

public class Node {

    protected LinkedList<Node> children;
    private int playerId;
    private Board boardstate;
    private Node parent;

    //constructor
    public Node(int playerId, Board boardstate){
        this.playerId = playerId;
        this.boardstate = boardstate;
        children = new LinkedList<Node>();

    }
    public Node(int playerId, Board boardstate, Node parent){
        this.playerId = playerId;
        this.boardstate = boardstate;
        this.parent = parent;
        children = new LinkedList<Node>();
    }
  
    public void addChild(Node n){
        children.add(n);
    }
}
