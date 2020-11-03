import java.util.ArrayList;
import java.util.Stack;

public class Game extends ArrayList<Player> {
    private Stack<Card> discardPile = new Stack<>();
    private Deck drawPile = new Deck();
    private boolean isGameOver = false;

    public Game(int numberOfPlayers) {
        ArrayList<Card> playerHand = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            playerHand.add(drawPile.pop());
        }
        Player D = new Player(playerHand);
        this.add(D);

        drawPile.shuffle();
        for (int i = 0; i < numberOfPlayers; i++) {
            ArrayList<Card> startingHand = new ArrayList<>();
            for (int d = 0; d < 4; d++) {
                startingHand.add(drawPile.pop());
            }
            Player X = new Opponent(startingHand, discardPile);
            this.add(X);
        }
        while (!isGameOver) {
            for (Player X : this) {
                System.out.println();
                X.makeMove(drawPile, discardPile);
                System.out.println();
            }
        }
    }

}
