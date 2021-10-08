package NRow;
import NRow.Heuristics.*;

import java.util.ArrayList;
import java.util.*;


public class PlayGround {

    public static void main(String[] args){
    int depth = 1;
    Board board = new Board(7,6);
     //hard code an initial board state
     board = board.getNewBoard(1, 2);
     board = board.getNewBoard(1, 2);

     Node rootNode = new Node(board,2);
     rootNode.children = createChildren(board, depth); 
     int best = minimax(rootNode, 2, 2);
     System.out.print("Best value is " + best);
    }

   //Create all children
   //rtrun a list of children
    public static ArrayList<Node> createChildren(Board board, int depth){
        ArrayList<Node> nodes = new ArrayList();
        if (depth>0){
            for(int i = 0; i < board.width; i++){
                Node child = new Node(board.getNewBoard(i, 1), 1); 
                nodes.add(child);
                child.children = createChildren(child.getBoard(), depth -1 ); //create children for all the childnodes recursively
            }
        }
        if (depth == 0){ // reaches leaf node, time to add heuristic
            for(int i = 0; i < board.width; i++) { //for each of the possible moves
                if(board.isValid(i)) { //if the move is valid
                    Board newBoard = board.getNewBoard(i, 2); // Get a new board resulting from that move, 1 = playerID
                    //System.out.println(newBoard);
                    Heuristic heuristic = new SimpleHeuristic(4);
                    int childValue=heuristic.evaluateBoard(2,newBoard);
                    //System.out.println(childValue);
                       Node child = new Node(newBoard, 1); 
                       child.setValue(childValue);
                       nodes.add(child);
                       System.out.println(childValue);
                    }
            }
        }   
        return(nodes);
    }


//minimax-algorithm

static int minimax(Node node, int player, int depth){
    int[] values = new int[node.getBoard().width];
    int best = 0;
    if (depth == 0){ //at leaf level
        //System.out.println("In leaf");
        if (node.children.size()>0){ 
            for (int i=0; i<node.children.size(); i++){
            values[i] = node.getChild(i).getValue();
        }
        } 
        if (player == 2){ //maxplayer
            //System.out.println("in player =1");
            best = -1000;
            for (int i = 0; i<values.length; i++){
                System.out.println(values[i]);
                best = Math.max(best, values[i]);
            }
        }
        else { //minplayer
            //System.out.println("in player =2");
            best = 1000;
            for (int i = 0; i<values.length; i++){
                best = Math.min(best, values[i]);
            }
        }
    }
    else if (depth % 2 == 0){ // even is maxplayer
        for (int i=0; i<node.children.size(); i++){
            //System.out.println("even");
            minimax(node.getChild(i),1,depth-1);
        }
    }
    else { // odd is minplayer
        for (int i=0; i<node.children.size(); i++){
            //System.out.println("odd");
            //System.out.println(depth);
            minimax(node.getChild(i),2,depth-1);
        }
    }
    return best;
}   
}
  

