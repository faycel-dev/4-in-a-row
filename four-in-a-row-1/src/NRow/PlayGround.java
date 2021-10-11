package NRow;
import NRow.Heuristics.*;

import java.util.ArrayList;
import java.util.*;


public class PlayGround {

    public static void main(String[] args){
    int depth = 2;
    int playerId = 2;
    Board board = new Board(7,6);
     //hard code an initial board state
     board = board.getNewBoard(1, 2);
     board = board.getNewBoard(1, 2);
     board = board.getNewBoard(2, 1);
     System.out.println(board);
     Node rootNode = new Node(board);
     rootNode.children = createChildren(board, depth,playerId); 
     int bestMove = minimax(rootNode, playerId, depth);
     System.out.println("best move " + bestMove);
    }


    public static ArrayList<Node> createChildren(Board board, int depth, int playerId){
        ArrayList<Node> nodes = new ArrayList();
        if (depth>0){
            for(int i = 0; i < board.width; i++){
                Node child = new Node(board.getNewBoard(i, playerId)); 
                nodes.add(child);
                if (playerId == 1) {
                    child.children = createChildren(child.getBoard(), depth - 1, 2); // swap to playerId 0
                }
                else {
                    child.children = createChildren(child.getBoard(), depth - 1, 1 ); // swap to playerId 1
                }
                 //create children for all the childnodes recursively
            }
        }
        if (depth == 0){ // reaches leaf node, time to add heuristic
            for(int i = 0; i < board.width; i++) { //for each of the possible moves
                if(board.isValid(i)) { //if the move is valid
                    Board newBoard = board.getNewBoard(i, playerId); // Get a new board resulting from that move, 1 = playerID
                    //System.out.println(newBoard);
                    Heuristic heuristic = new SimpleHeuristic(4);
                    int childValue=heuristic.evaluateBoard(playerId,newBoard);
                    //System.out.println(childValue);
                    Node child = new Node(newBoard); //not sure if it is correct
                    child.setValue(childValue);
                    nodes.add(child); //FRÅGA OM IMORGON            
                    }
            }
        }   
        return(nodes);
    }

    static int minimax(Node node, int player, int depth){
        int[] values = new int[node.getBoard().width];
        //int bestValue = 0;
        int bestMove = 0;
        int maxValue = -1000;
        int minValue = 1000;
        if (depth == 0){ //at leaf level
            if (node.children.size()>0){ 
                for (int i=0; i<node.children.size(); i++){
                values[i] = node.getChild(i).getValue();
            }
            } 
            if (player == 1){ //maxplayer 
                for (int i = 0; i<values.length; i++){
                    if(values[i] > maxValue) {
                        maxValue = values[i];
                        bestMove = i+1;
                    }
                }
            }
            else { //minplayer
                for (int i = 0; i<values.length; i++){
                    //best = Math.min(best, values[i]);
                    if(values[i] < minValue) {
                        minValue = values[i];
                        System.out.println("MinValue: " + minValue);
                        bestMove = i+1;
                    }
                }      
            }  
        }
        else {
            for (int i=0; i<node.children.size(); i++){
                bestMove = minimax(node.getChild(i),3-player,depth-1);
        }
        }
        // realize that it will never go to the min player
      /*   else if (depth % 2 == 0){ // even is minplayer
            for (int i=0; i<node.children.size(); i++){
                    bestMove = minimax(node.getChild(i),3-player,depth-1);
                    //System.out.println("depth even " + depth);
            }
        }
        else { // odd is maxplayer
            for (int i=0; i<node.children.size(); i++){
                bestMove = minimax(node.getChild(i),3-player,depth-1);
                //System.out.println("depth odd " + depth);

            }
        } */

        return bestMove;
        }  

    }


