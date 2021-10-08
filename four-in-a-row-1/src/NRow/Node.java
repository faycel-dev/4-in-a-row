package NRow;

import java.util.ArrayList;
import java.util.List;

public  class Node {
    Board board;
    public List<Node> children;
    public int player;
    public int value;
    public int alpha;
    public int beta ;
    Node(Board board, int player, int value) {
        this.board = board;
        this.player = player;
        this.value = value;
        children = new ArrayList<>();
    }
    Node(Board board, int player, int value,int alpha,int beta ) {
        this.board = board;
        this.player = player;
        this.value = value;
        children = new ArrayList<>();
    }


}
