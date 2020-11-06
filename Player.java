import java.util.ArrayList;
import java.util.Stack;
import java.util.Scanner;

public class Player {
    private ArrayList<Card> hand;
    private Scanner in = new Scanner(System.in);
    private Stack<Card> discardPile;
    private Deck drawPile;

    public Player() {
    }

    public Player(ArrayList<Card> startingHand, Deck drawPile, Stack<Card>discardPile) {
        this.hand = startingHand;
        this.discardPile = discardPile;
        this.drawPile = drawPile;
    }

    //Print the players cards, move based on their choice.
    public void makeMove() {
        System.out.println("Your cards:");
        for (Card N : this.hand) {
            System.out.println("* " + N.toString());
        }

        if (!discardPile.empty()) {
            System.out.println("The card on top of the discard pile is " + discardPile.peek().toString());
            System.out.println(
                    "Do you want to pick up the " + discardPile.peek().toString() + "(1) or draw from the deck (2)?");
            int choice = getSanitizedInput(1,2);
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
            System.out.println((i + 1) + ": " + hand.get(i).toString());
        }
        System.out.print("Which one would you like to discard? (1-5) ");
        int discardChoice = getSanitizedInput(1, 5) - 1;
        for (int i = 0; i < hand.size(); i++) {
            if (i == discardChoice) {
                discardPile.push(hand.get(i));
                hand.remove(i);
            }
        }
        checkWinStatus();
    }

    //Gets sanitized user input for moves.
    private int getSanitizedInput(int leftBound, int rightBound){
        int returnValue = 0;
        boolean hasInput = false;
        while (!hasInput) {
            String input = in.next();
            try {
                 returnValue = Integer.parseInt(input);
                if (returnValue <= rightBound && returnValue >= leftBound) {
                    hasInput = true;
                }
                else{
                    System.out.println("Please enter a number between " + leftBound + " and " + rightBound);
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
        return returnValue;
    }

    //If we have 4 cards of the same number, we win!
    private void checkWinStatus() {
        int numberOfLikeCard = 1;
        for (int i = 0; i < hand.size() - 1; i++) {
            if (hand.get(i).getValue() == hand.get(i + 1).getValue()) {
                numberOfLikeCard++;
            }
        }
        if (numberOfLikeCard == 4) {
            System.out.println("I win!");
            System.exit(0);
        }
    }
}
