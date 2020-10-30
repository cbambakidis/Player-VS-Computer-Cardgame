
import java.util.*;

public class Opponent extends Player {
    private ArrayList<Card> hand;
    private boolean isPlayer = false;

    public Opponent() {
    }

    public Opponent(ArrayList<Card> startingHand) {
        hand = startingHand;
    }

    public boolean isPlayer() {
        return isPlayer();
    }

    // Hand will have cards the opponent won't want to play. MakeMove should figure
    // this out.
    public void makeMove(Deck drawPile, Stack<Card> discardPile, Object Collections) {
        boolean hasIdealCard;
        boolean turnEnded = false;
        int idealCardValue;
        int[] cardValuesToCount = new int[4];
        for (int i = 0; i < hand.size() - 1; i++) {
            cardValuesToCount[i] = hand.get(i).getValue();
        }
        idealCardValue = cardValuesToCount[count(cardValuesToCount)]; // Returns index of cards we have the most of.
        if (count(cardValuesToCount) == 1) {
            hasIdealCard = false;
        } else {
            hasIdealCard = true;
        }

        // Check discard for ideal card. if it has it, take it. if it doesn't check if
        // it has any card we have. if it doesn't, draw from deck.
        if (hasIdealCard) {
            if (discardPile.peek().getValue() == idealCardValue) {
                hand.add(discardPile.peek());
                System.out.println("Opponent took " + discardPile.peek() + " from discard.");
                discardPile.pop();
            }
            // Discard card i have the least of.

        } else { // If we don't have a top number card, check if discard has any card value of
                 // ours.
                 // if true, then take it and discard other.
            for (Card N : hand) {
                if (discardPile.peek().getValue() == N.getValue()) {
                    System.out.println("Opponent took " + discardPile.peek() + " from discard.");
                    hand.add(discardPile.pop());
                    turnEnded = true;
                    break;
                }
            }
            if (!turnEnded) {
                System.out.println("Opponent drew from draw pile.");
                hand.add(drawPile.pop());
            }

        }
        // Discard phase. recount cards and discard amount we have least of. if all
        // even, discard random.
        int[] discardValues = new int[5];
        for (int i = 0; i < hand.size(); i++) {
            discardValues[i] = hand.get(i).getValue();
        }
        if (count(discardValues) == 1) {
            hasIdealCard = false;
        } else {
            hasIdealCard = true;
        }

        if (!hasIdealCard) {
            Random x = new Random();
            discardPile.add(hand.get(x.nextInt(6)));
        } else {
            int newIdealCardValue = discardValues[count(discardValues)];
            for (Card N : hand) {
                if (N.getValue() != newIdealCardValue) {
                    discardPile.add(N);
                    hand.remove(N);
                }
            }
        }

        // check if i have 4 cards of the same number.
        int numberOfLikeCard = 0;
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getValue() == hand.get(i + 1).getValue()) {
                numberOfLikeCard++;
            }
        }
        if (numberOfLikeCard == 4) {
            System.out.println("I win!");
            System.exit(0);
        }

        ArrayList<Card> handWithDiscard = hand;
        handWithDiscard.add(discardPile.peek());
    }

    // Return a card value to look for.
    public int count(int[] cardValues) {
        int[] numTotal = { 0, 0, 0, 0 };
        for (int i = 0; i <= hand.size(); i++) {
            if (hand.get(i).getValue() == cardValues[1]) {
                numTotal[1]++;
            }
            if (hand.get(i).getValue() == cardValues[2]) {
                numTotal[2]++;
            }
            if (hand.get(i).getValue() == cardValues[3]) {
                numTotal[3]++;
            }
            if (hand.get(i).getValue() == cardValues[4]) {
                numTotal[4]++;
            }
        } 
          // At this point we will have our array of card values to count, and an array of
          // the quantities of each card.
          // We will now sort through and find the index with the greatest value, and
          // return the corresponding index of the card we have the most of.
          // Number in the original array.
        int biggestIndex = 0;
        for (int i = 0; i < numTotal.length; i++) {
            if (numTotal[i] > numTotal[biggestIndex]) {
                biggestIndex = i;
            }
        }

        return biggestIndex;
    }

}
