import java.util.ArrayList;
import java.util.Stack;

public class Game extends ArrayList<Player> {
    private Stack<Card> discardPile;
    private Deck drawPile = new Deck();
    public Game(int numberOfPlayers) {
        drawPile.shuffle();
        
        for (int i = 0; i < numberOfPlayers; i++) {
            ArrayList<Card> startingHand = new ArrayList<>();
            for (int d=0; d<5; d++){
                startingHand.add(drawPile.pop());
            }
            Opponent X = new Opponent(startingHand);
            this.add(X);
        }
        ArrayList<Card>playerHand = new ArrayList<>();
        for (int i=0; i<5; i++){
            playerHand.add(drawPile.pop());
        }
        Player X = new Player(playerHand);
        this.add(X);
    }

}
