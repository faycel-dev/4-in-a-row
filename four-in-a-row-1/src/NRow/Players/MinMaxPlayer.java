package NRow.Players;

import NRow.Board;
import NRow.Node;
import NRow.Heuristics.Heuristic;


public class MinMaxPlayer extends PlayerController {
    private final int depth;

    public MinMaxPlayer(int playerId, int gameN, int depth, Heuristic heuristic ) {
        super(playerId, gameN, heuristic);
        this.depth=depth;
        //You can add functionality which runs when the player is first created (before the game starts)
    }

    /**
   * @param board the current board
   * @return column integer the player chose
   */
    @Override
    public int makeMove(Board board) {   
        Node root = new Node(board);
        int bestMove = getBestMinMaxAction(root);
        if(!board.isValid(bestMove)) { //Check for invalid moves, which may happen if the game is unwinnable
            for(int i = 0; i < board.width; i++) {
                if(board.isValid(i)) return i;
            }
        }
        return bestMove;
    }
/**
 * 
 * @param node
 * @return the column integer the player should chose
 */
    public int getBestMinMaxAction(Node node){
        int bestVal = 0;
        int bestMove = 0;
        int nextPlayer = 0;
        if (node.getDepth() % 2 == 0){ // even level, means that we are maximizing player
            nextPlayer = playerId;
            bestVal = Integer.MIN_VALUE;
        }
        else {
            nextPlayer = 3-playerId;
            bestVal = Integer.MAX_VALUE;
        }
        if (node.getDepth()==depth){
            node.setValue(heuristic.evaluateBoard(playerId, node.getBoard())); //set heuristic for the leaf nodes
        } else {
            for (int i = 0; i < node.getBoard().width ; i ++){
                if(node.getBoard().isValid(i)) { //if the move is valid                    
                    Node child = new Node(node.getBoard().getNewBoard(i, nextPlayer),node); //create a child with the node as parent
                    getBestMinMaxAction(child); //call the function recursively to add children or set heuristic
                    if(child.getValue()<bestVal && nextPlayer != playerId){ //maximize 
                        bestVal = child.getValue();
                        bestMove = i;
                    }
                    else if (child.getValue()>bestVal && nextPlayer == playerId){ //minimize
                        bestVal = child.getValue();
                        bestMove = i;
                    }
                }
            }
            node.setValue(bestVal);
        }
        return bestMove;
    }
}
