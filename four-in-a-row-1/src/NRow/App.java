package NRow;
import NRow.Heuristics.SimpleHeuristic;
import NRow.Players.MinMaxPlayer;
import NRow.Players.PlayerController;

/********************************************************************
    Assignment 1 - AI: Teqniques and Principles - Four in a row
    Names: Elin Lemón and
    Student numbers: s1086861 and
********************************************************************/


public class App {
    public static void main(String[] args) throws Exception {
        int gameN = 4;
        int boardWidth = 7;
        int boardHeight = 6;

        PlayerController[] players = getPlayers(gameN);

        Game game = new Game(gameN, boardWidth, boardHeight, players);
        game.startGame();
    }

    /**
     * Determine the players for the game
     * @param n
     * @return an array of size 2 with two Playercontrollers
     */
    private static PlayerController[] getPlayers(int n) {
        SimpleHeuristic heuristic1 = new SimpleHeuristic(n);
        //SimpleHeuristic heuristic2 = new SimpleHeuristic(n);

       //PlayerController human = new HumanPlayer(1, n, heuristic1);
      //PlayerController human2 = new HumanPlayer(2,n, heuristic1);


        //TODO: Implement other PlayerControllers (MinMax, AlphaBeta)
        MinMaxPlayer  minmax1= new MinMaxPlayer (2,n, 5, heuristic1);
        MinMaxPlayer  minmax2 = new MinMaxPlayer (1, n, 5, heuristic1);
        System.out.println("Two MinMaxPlayers with depth 5 and MiniMax");

        PlayerController[] players = { minmax2, minmax1}; //decide what kind of player it is

        return players;
    }
}
