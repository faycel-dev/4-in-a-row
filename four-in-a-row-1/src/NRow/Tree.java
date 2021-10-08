package NRow;

import NRow.Heuristics.Heuristic;

public class Tree {

// Initival values of alpha and beta
static int max = 1000;
static int min = -1000;

public Node root;
public Tree(int playerId, int gameN, int depth, Heuristic heuristic,Board board ){
    this.root=constractTree( null,playerId,gameN,depth,heuristic,board);
}
    public Tree(int playerId, int gameN, int depth, Heuristic heuristic,Board board,int alpha,int beta) {

        this.root = buildTree(null,playerId,gameN,depth,heuristic,board, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private Node buildTree(Object o, int playerId, int gameN, int depth, Heuristic heuristic, Board board, int alpha, int beta) {
        Node node;
        int nextPlayer;
        int winner = Game.winning(board.getBoardState(), gameN);
        int value = heuristic.evaluateBoard(playerId, board);
        node = new Node(board, playerId, value, alpha, beta);
        if (!(winner != 0 || depth == 0)) {
            for (int i = 0; i < board.width; i++) {
                Board newBoard = new Board(board);
                if (newBoard.play(i, playerId)) {
                    if (playerId == 1) {
                        nextPlayer = 2;
                    } else {
                        nextPlayer = 1;
                    }
                    Node child = buildTree(node, playerId, gameN, depth - 1, heuristic, newBoard, alpha, beta);
                    node.children.add(child);
                }
            }
        }
        return node;
    }

// Returns optimal value for
// current player (Initially called
// for root and maximizer)
static int minimax(int depth, int nodeIndex,
    int player,
    int values[], int alpha,
    int beta)
    {
    // Terminating condition. i.e
    // leaf node is reached
    if (depth == 3)
        return values[nodeIndex];

    if (player == 1)
    {
        int best = min;

    // Recur for left and
    // right children
    for (int i = 0; i < 2; i++)
    {
        int val = minimax(depth + 1, nodeIndex * 2 + i,
                2, values, alpha, beta);
        best = Math.max(best, val);
        alpha = Math.max(alpha, best);

    // Alpha Beta Pruning
    if (beta <= alpha)
        break;
        }
        return best;
    }
    else
    {
        int best = max;

    // Recur for left and
    // right children
    for (int i = 0; i < 2; i++)
    {

        int val = minimax(depth + 1, nodeIndex * 2 + i,
                1, values, alpha, beta);
        best = Math.min(best, val);
        beta = Math.min(beta, best);

    // Alpha Beta Pruning
    if (beta <= alpha)
        break;
    }
        return best;
    }
    
}
        private Node constractTree(Node parent, int playerId, int gameN, int depth, Heuristic heuristic, Board board) {

        Node node;
        int nextPlayer;
        int winner=Game.winning(board.getBoardState(), gameN);
        int value=heuristic.evaluateBoard(playerId,board);
        int[] values = new int[board.width];
        node=new Node(board,playerId);
        if(!(winner!=0|| depth==0)){
            for(int i=0;i<board.width;i++){
                Board newBoard = new Board(board);
                if(newBoard.play(i,playerId)){
                    if(playerId==1){
                        nextPlayer=2;
                    }else{
                        nextPlayer=1;
                    }
                    Node child = constractTree(node, nextPlayer, gameN,  depth-1, heuristic, newBoard);
                    //node.children.add(child);

                    values[i] = child.value;

                }
            }
        }
        node.value = minimax(depth, depth, playerId, values, min, max);
        for (int i=0; i<board.width; i++){
            System.out.println(values[i]);
        }
        showTree(node);
    return node;
    
 }

 public void showTree(){
    showTree(this.root);
 }
  public void showTree(Node node){
    System.out.println(node.board);
    System.out.println(node.value);
  }

}

