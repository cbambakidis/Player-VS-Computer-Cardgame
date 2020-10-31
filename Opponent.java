
import java.util.*;

public class Opponent extends Player {
    private ArrayList<Card> hand;
    private boolean hasIdealCard;
    private int idealCardValue;
    private int idealCardQuantity;
    private int[] indexQuantityAndValue2;

    public Opponent(ArrayList<Card> startingHand) {
        this.hand = startingHand;
    }

    public void makeMove(Deck drawPile, Stack<Card> discardPile) {
        // See if we have more than one of a kind of card.
        indexQuantityAndValue2 = count();
        idealCardValue = indexQuantityAndValue2[2];
        idealCardQuantity = indexQuantityAndValue2[1];

        // If we do, we have an ideal card to look for in discard pile.
        if (idealCardQuantity <= 1) {
            hasIdealCard = false;
        } else {
            hasIdealCard = true;
        }

        // Check if the discard pile has a matching card, to any card in our hand.
        boolean discardHasMatchingCard = false;
        if (!discardPile.empty()) {
            for (Card N : hand) {
                if (discardPile.peek().getValue() == N.getValue()) {
                    discardHasMatchingCard = true;
                    break;
                }
            }
        }

        // If discard pile has any card I have, take it.
        if (discardHasMatchingCard) {
            hand.add(discardPile.peek());
            System.out.println("Opponent took " + discardPile.peek() + " from discard.");
            discardPile.pop();
        }
        // If not, we draw from the pile.
        else {
            System.out.println("Opponent drew from draw pile.");
            hand.add(drawPile.pop());
        }

        discard(discardPile);

        checkWinStatus();

    }

    public void draw() {

    }

    public void discard(Stack<Card> discardPile) {
        // With our new hand, we reasses and see if we have more than one of a kind of
        // card.
        indexQuantityAndValue2 = count();
        idealCardValue = indexQuantityAndValue2[2];
        idealCardQuantity = indexQuantityAndValue2[1];
        if (idealCardQuantity <= 1) {
            hasIdealCard = false;
        } else {
            hasIdealCard = true;
            System.out.println(hand);
            System.out.println(idealCardValue + " " + idealCardQuantity);
        }

        // Doesn't matter what we discard if we don't have more than 1 card of a kind.
        if (!hasIdealCard) {
            discardPile.add(hand.get(2));
            hand.remove(2);
        }

        // Discard first card that isn't important.
        if (hasIdealCard) {
            for (Card N : hand) {
                if (N.getValue() != idealCardValue) {
                    discardPile.add(N);
                    hand.remove(N);
                    break;
                }
            }
        }
    }

    public void checkWinStatus() {
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

    public int[] count() { // took int[].
        int[] indexQuantityAndValue = new int[3];
        int[] cardValues = new int[5];
        for (int i = 0; i < hand.size(); i++) {
            cardValues[i] = hand.get(i).getValue();
        }

        int[] numTotal = { 0, 0, 0, 0, 0 };
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).getValue() == cardValues[0]) {
                numTotal[0]++;
            }
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
        System.out.println(Arrays.toString(numTotal));
        System.out.println(Arrays.toString(cardValues));
        int biggestIndex = 0;
        for (int i = 0; i < numTotal.length; i++) {
            if (numTotal[i] > numTotal[biggestIndex]) {
                biggestIndex = i;
            }
        }
        indexQuantityAndValue[0] = biggestIndex;
        indexQuantityAndValue[1] = numTotal[biggestIndex];
        indexQuantityAndValue[2] = cardValues[biggestIndex];
        return indexQuantityAndValue;
    }

}
