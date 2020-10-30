import java.util.ArrayList;
import java.util.Stack;
import java.util.Scanner;

public class Player {
    private ArrayList<Card> hand;
    private boolean isPlayer = true;
    private Scanner in = new Scanner(System.in);

    public Player() {
    }

    public boolean isPlayer() {
        return isPlayer;
    }

    public Player(ArrayList<Card> startingHand) {
        this.hand = startingHand;
    }

    public void makeMove(Deck drawPile, Stack<Card> discardPile) {
        System.out.println("Your cards:");
        for (Card N : this.hand) {
            System.out.println("* " + N.toString());
        }

        if (!discardPile.empty()) {
            System.out.println("The card on top of the discard pile is " + discardPile.peek().toString());
            System.out.println(
                    "Do you want to pick up the " + discardPile.peek().toString() + "(1) or draw from the deck (2)?");
            int choice = in.nextInt();
            if (choice == 1) {
                hand.add(discardPile.pop());
            } else if (choice == 2) {
                System.out.println("You picked up the " + drawPile.peek().toString());
                hand.add(drawPile.pop());
            }
        } else {
            System.out.println("The discard pile is empty, you must draw from the deck.");
            System.out.println("You drew the " + drawPile.peek().toString());
            hand.add(drawPile.pop());
        }

        System.out.println("Now your cards are:");
        for (int i = 0; i < hand.size(); i++) {
            System.out.println(i + ": " + hand.get(i).toString());

        }
        System.out.print("Which one would you like to discard? (0-5) ");
        int discardChoice = in.nextInt();
        for (int i = 0; i < hand.size(); i++) {
            if (i == discardChoice) {
                hand.remove(i);
            }
        }
        // check if i have 4 cards of the same number.
        int num = 0;
        Card X = new Card();
        for (int i=0; i<hand.size(); i++){
            if(hand.get(i) == X){
                num++;
            }
            X = hand.get(i);
        }
        int numberOfLikeCard = 0;
        for (int i = 0; i < hand.size()-1; i++) {
            if (hand.get(i).getValue() == hand.get(i + 1).getValue()) {
                numberOfLikeCard++;
            }
        }
        if (num == 4) {
            System.out.println("You win!");
            System.exit(0);
        }
    }
}
