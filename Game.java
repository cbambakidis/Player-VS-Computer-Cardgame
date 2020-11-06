import java.util.ArrayList;
import java.util.Stack;

public class Game extends ArrayList<Player> {
    private static final long serialVersionUID = 1L;
    private Stack<Card> discardPile = new Stack<>();
    private Deck drawPile = new Deck();
    private boolean isGameOver = false;

    /*
    * Constructs a new Game with the number of players obtained in main class.
    * Makes all opponents then a player object.
    * Makes new Deck and deals starting cards.
    */

    public Game(int numberOfPlayers) {
        ArrayList<Card> playerHand = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            playerHand.add(drawPile.pop());
        }
        Player D = new Player(playerHand, drawPile, discardPile);
        this.add(D);

        drawPile.shuffle();
        for (int i = 0; i < numberOfPlayers; i++) {
            ArrayList<Card> startingHand = new ArrayList<>();
            for (int d = 0; d < 4; d++) {
                startingHand.add(drawPile.pop());
            }
            Player X = new Opponent(startingHand, discardPile, drawPile, i + 1);
            this.add(X);
        }

        while (!isGameOver) {
            for (Player X : this) {
                if(drawPile.size() == 0){ //If we run out of cards in the deck, add discard cards. This happened one time during testing.
                    drawPile.addAll(discardPile);
                    discardPile.clear();
                    discardPile.push(drawPile.pop());
                }
                X.makeMove();
                System.out.println();
            }
        }
    }

}
