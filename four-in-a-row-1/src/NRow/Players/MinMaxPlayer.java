package NRow.Players;

import NRow.Board;
import NRow.Heuristics.Heuristic;
import NRow.Node;
import NRow.Tree;

public class MinMaxPlayer extends PlayerController {
    private final int depth;

    public MinMaxPlayer(int playerId, int gameN, int depth, Heuristic heuristic ) {
        super(playerId, gameN, heuristic);
        this.depth=depth;
        //You can add functionality which runs when the player is first created (before the game starts)
    }

    /**
   * Implement this method yourself!
   * @param board the current board
   * @return column integer the player chose
   */
    @Override
    public int makeMove(Board board) {
        // TODO: implement minmax player!
        // HINT: use the functions on the 'board' object to produce a new board given a specific move
        // HINT: use the functions on the 'heuristic' object to produce evaluations for the different board states!


        // FROM NOW ON WE WILL HAVE TO CREATE THE MIN-MAX ALGORITHM
  
        // for each node calculate the heuristic
        // call the function evaluate in SimpleHeuristic
        // assign that value to the node of the last level
        // have to start filling in the tree, but keep in mind alpha beta pruning -> plan how to do that!

        // create a function that checks the depth. if not max depth -> keep calling itself, otherwise the evaluate function from heuristic
        // return the max of children
        // keep track of what action that leads to that node
        // inside the node class, variable action, the i of getNewBoard
        // need to know playerId - min/max player

        // Example: 
        int maxValue = Integer.MIN_VALUE;
        int maxMove = 0;
        for(int i = 0; i < board.width; i++) { //for each of the possible moves
            if(board.isValid(i)) { //if the move is valid
            Board newBoard = board.getNewBoard(i, playerId); // Get a new board resulting from that move
               Tree tree=new Tree( playerId,gameN,depth, heuristic,newBoard);
                int value =minMax(tree); //evaluate that new board to get a heuristic value from it
                if(value > maxValue) {
                    maxValue = value;
                    maxMove = i;

                }
            }
        }
        // This returns the same as:
      //  heuristic.getBestAction(playerId, board); // Very useful helper function!
        

        /*
        This is obviously not enough (this is depth 1)
        Your assignment is to create a data structure (tree) to store the gameboards such that you can evaluate a higher depths.
        Then, use the minmax algorithm to search through this tree to find the best move/action to take!
        */

        return maxMove;
    }

    private int minMax(Tree tree) {
        return minMax(tree.root);
    }

    private int minMax(Node node) {
        if(node.children.isEmpty()){ //is Leaf
            return node.value;
        }else{
            if(node.player ==1){
                int maxValue = Integer.MIN_VALUE;
                for(Node child:node.children){
                    int value=minMax(child);
                    maxValue=Math.max(maxValue,value);
                   // System.out.println(child.value );

                }
                return maxValue;
            }else{
                int minValue=Integer.MAX_VALUE;
                for(Node child:node.children){
                    int value=minMax(child);
                    minValue=Math.min(minValue,value);

                }
                return minValue;
            }
        }
    }

}
