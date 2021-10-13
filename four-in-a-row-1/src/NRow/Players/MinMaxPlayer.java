package NRow.Players;
import NRow.Board;
import NRow.Node;
import NRow.Heuristics.Heuristic;

public class MinMaxPlayer extends PlayerController {
    private final int depth;

    public MinMaxPlayer(int playerId, int gameN, int depth, Heuristic heuristic) {
        super(playerId, gameN, heuristic);
        this.depth = depth;
        //You can add functionality which runs when the player is first created (before the game starts)
    }

    /**
     * @param board the current board
     * @return column integer the player chose
     */
    @Override
    public int makeMove(Board board) {
        Node root = new Node(board);
        // Decide if you want to do MiniMax or Alpha-Beta pruning by commenting/uncommenting the following two lines
        //int bestMove = getBestAlphaBetaPruningAction(root);
        int bestMove = getBestMinMaxAction(root);
        if (!board.isValid(bestMove)) { //Check for invalid moves, which may happen if the game is unwinnable
            for (int i = 0; i < board.width; i++) {
                if (board.isValid(i)) return i;
            }
        }
        return bestMove;
    }
    /**
     * 
     * @param node
     * @return the column integer the player should chose based on the minimax algorithm
     */
    public int getBestMinMaxAction(Node node) {
        int bestVal = 0;
        int bestMove = 0;
        int nextPlayer = 0;
        if (node.getDepth() % 2 == 0) { // even level, means that we are maximizing player
            nextPlayer = playerId;
            bestVal = Integer.MIN_VALUE;
        } else {
            nextPlayer = 3 - playerId;
            bestVal = Integer.MAX_VALUE;
        }
        if (node.getDepth() == depth) {
            node.setValue(heuristic.evaluateBoard(playerId, node.getBoard())); //set heuristic for the leaf nodes
        } else {
            for (int i = 0; i < node.getBoard().width; i++) {
                if (node.getBoard().isValid(i)) { //if the move is valid                    
                    Node child = new Node(node.getBoard().getNewBoard(i, nextPlayer), node); //create a child with the node as parent
                    getBestMinMaxAction(child); //call the function recursively to add children or set heuristic
                    if (child.getValue() < bestVal && nextPlayer != playerId) { //maximize 
                        bestVal = child.getValue();
                        bestVal = Math.min(child.getValue(), bestVal);
                        bestMove = i;
                    } else if (child.getValue() > bestVal && nextPlayer == playerId) { //minimize
                        bestVal = child.getValue();
                        bestVal = Math.max(child.getValue(), bestVal);
                        bestMove = i;
                    }
                }
            }
            node.setValue(bestVal);
        }
        return bestMove;
    }

    /**
     * 
     * @param node
     * @return the column integer the player should chose based on alpha beta pruning
     */
    public int getBestAlphaBetaPruningAction(Node node) {
        int bestVal = 0;
        int bestMove = 0;
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;
        int nextPlayer = 0;
        if (node.getDepth() % 2 == 0) { // even level, means that we are maximizing player
            nextPlayer = playerId;
            bestVal = Integer.MIN_VALUE;
        } else {
            nextPlayer = 3 - playerId; // minimizing 
            bestVal = Integer.MAX_VALUE;
        }
        if (node.getDepth() == depth) {
            node.setValue(heuristic.evaluateBoard(playerId, node.getBoard())); //set heuristic for the leaf nodes
        } else {
            for (int i = 0; i < node.getBoard().width; i++) {
                if (node.getBoard().isValid(i)) { //if the move is valid                    
                    Node child = new Node(node.getBoard().getNewBoard(i, nextPlayer), node); //create a child with the node as parent
                    getBestAlphaBetaPruningAction(child); //call the function recursively to add children or set heuristic
                    if (child.getValue() < bestVal && nextPlayer != playerId) { //maximize 
                        bestVal = Math.min(child.getValue(), bestVal);
                        bestMove = i;
                        alpha = Math.min(alpha, bestVal);
                        // Alpha Beta Pruning
                        if (alpha <= beta)
                            break;

                    } else if (child.getValue() > bestVal && nextPlayer == playerId) { //minimize
                        bestVal = Math.max(child.getValue(), bestVal);
                        bestMove = i;
                        beta = Math.min(beta, bestVal);
                        // Alpha Beta Pruning
                        if (beta <= alpha)
                            break;
                    }
                }
            }
            node.setValue(bestVal);
        }
        return bestMove;
    }
}