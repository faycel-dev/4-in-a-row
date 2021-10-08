package NRow;

import NRow.Heuristics.Heuristic;

public class Tree {

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
                    Node child = buildTree(node, playerId, gameN, depth - 1, heuristic, newBoard,alpha,beta);
                    node.children.add(child);
                }
            }
        }
        return node;
    }
    private Node constractTree(Node parent, int playerId, int gameN, int depth, Heuristic heuristic, Board board) {

    Node node;
    int nextPlayer;
    int winner=Game.winning(board.getBoardState(), gameN);
    int value=heuristic.evaluateBoard(playerId,board);
    node=new Node(board,playerId,value);
    if(!(winner!=0|| depth==0)){
        for(int i=0;i<board.width;i++){
            Board newBoard = new Board(board);
            if(newBoard.play(i,playerId)){
                if(playerId==1){
                    nextPlayer=2;
                }else{
                    nextPlayer=1;
                }
                Node child =constractTree(node, playerId, gameN,  depth-1, heuristic, newBoard);
                node.children.add(child);
            }
        }
    }

 return node;
 }

 public void showTree(){
    showTree(this.root);
 }
  public void showTree(Node node){
    System.out.println(node.board);
    System.out.println(node.value);
    for(Node child :node.children){
        showTree(child);
    }
  }

}

