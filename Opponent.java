
import java.util.*;

public class Opponent extends Player {
    private ArrayList<Card> hand;
    private boolean hasIdealCard;
    private boolean hasNextIdeal;
    private int idealCardValue;
    private int idealCardQuantity;
    private int[] topCard;
    private int[] nextTopCard;
    private int secondChoiceValue;
    private Stack<Card> discardPile;
    private int secondChoiceQuantity;
    private ArrayList<Card> handWithoutBestCard = new ArrayList<Card>();
    private int playerNumber;
    private Deck drawPile;

    /*
     * Constructs new opponent with hand, discard pile access and a player number
     * for when they win.
     */
    public Opponent(ArrayList<Card> startingHand, Stack<Card> discardPile, Deck drawPile, int playerNumber) {
        this.hand = startingHand;
        this.discardPile = discardPile;
        this.playerNumber = playerNumber;
        this.drawPile = drawPile;
    }

    public void makeMove() {
        // See if we have more than one of a kind of card, using Count() method.
        topCard = count(hand);
        idealCardValue = topCard[2];
        idealCardQuantity = topCard[1];

        // If we do, we have an ideal card to look for in discard pile.
        if (idealCardQuantity <= 1) {
            hasIdealCard = false;
            hasNextIdeal = false;
        } else {
            hasIdealCard = true;
        }

        handWithoutBestCard.clear();

        // If we have more than one card of a kind, check for second ideal. Mark true if
        // we have one, false if we don't.
        if (hasIdealCard) {
            handWithoutBestCard.addAll(hand);
            for (Card D : hand) {
                if (D.getValue() == idealCardValue) {
                    handWithoutBestCard.remove(D);
                }
            }

            nextTopCard = count(handWithoutBestCard);
            secondChoiceValue = nextTopCard[2];
            secondChoiceQuantity = nextTopCard[1];

            if (secondChoiceQuantity <= 1) {
                hasNextIdeal = false;
            } else {
                hasNextIdeal = true;
            }
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
            System.out.println("Player " + playerNumber + " draws the " + discardPile.peek().toString()
                    + " from the discard pile.");
            hand.add(discardPile.peek());
            discardPile.pop();
        }
        // If not, we draw from the pile.
        else {
            System.out.println("Player " + playerNumber + " draws from the draw pile.");
            hand.add(drawPile.pop());
        }

        checkWinStatus();
        // With our new hand, we re assess and see if we have more than one of a kind of
        // card.
        topCard = count(hand);
        idealCardValue = topCard[2];
        idealCardQuantity = topCard[1];
        //If we have more than one of a card, we have an ideal card, i.e a card we should get more of to win.
        if (idealCardQuantity <= 1) {
            hasIdealCard = false;
        } else {
            hasIdealCard = true;
        }

        /* 
         *Create a hypothetical second hand, without the ideal card, to determine if we
         * have any other cards of a kind.
         * I just remove the ideal cards and run it through my count method again.

        */ 
        handWithoutBestCard.clear();
        if (hasIdealCard) {
            handWithoutBestCard.addAll(hand);
            for (Card D : hand) {
                if (D.getValue() == idealCardValue) {
                    handWithoutBestCard.remove(D);
                }
            }
            nextTopCard = count(handWithoutBestCard);
            secondChoiceValue = nextTopCard[2];
            secondChoiceQuantity = nextTopCard[1];
            if (secondChoiceQuantity <= 1) {
                hasNextIdeal = false;
            } else {
                hasNextIdeal = true;
            }
        }
        // We know should know if we have any cards worth keeping, so discard.
        discard();
        // And check if I win.
        checkWinStatus();
    }

    // This method Discards a card from the opponents hand, after calculating
    // whether or not
    // He has any cards worth keeping.
    private void discard() {
        if (!hasIdealCard) {
            System.out.println("I will discard the " + hand.get(2).toString() + ".");
            discardPile.push(hand.get(2));
            hand.remove(2);
            return;
        }

        if (hasIdealCard && !hasNextIdeal) {
            for (Card N : hand) {
                if (N.getValue() != idealCardValue) {
                    System.out.println("Player " + playerNumber + " discards the " + N.toString() + " from his hand.");
                    discardPile.push(N);
                    hand.remove(N);
                    return;
                }
            }
        }
        // If we have 3 ideal cards, and 2 second ideals, discard a second ideal, or
        // anything non ideal.
        // If we have 2 ideal, 2 second ideal, discard the remaining non ideal.
        if (hasIdealCard && hasNextIdeal && idealCardQuantity == 2) {
            for (Card N : hand) {
                if (N.getValue() != idealCardValue && N.getValue() != secondChoiceValue) {
                    System.out.println("Player " + playerNumber + " discards the " + N.toString() + " from his hand.");
                    discardPile.add(N);
                    hand.remove(N);
                    return;
                }
            }
        }

        if (hasIdealCard && hasNextIdeal && idealCardQuantity == 3) {
            for (Card N : hand) {
                if (N.getValue() != idealCardValue) {
                    System.out.println("Player " + playerNumber + " discards the " + N.toString() + " from his hand.");
                    discardPile.add(N);
                    hand.remove(N);
                    return;
                }
            }
        }
    }

    // Same as player class, but with different message.
    private void checkWinStatus() {
        int numberOfLikeCard = 1;
        for (int i = 0; i < hand.size() - 1; i++) {
            if (hand.get(i).getValue() == hand.get(i + 1).getValue()) {
                numberOfLikeCard++;
            }
        }
        if (numberOfLikeCard == 4) {
            System.out.println("Player " + playerNumber + " wins!");
            System.exit(0);
        }
    }

    // Learned about hashmaps after had already finished making this method :(
    // I know it's clunky but it works.
    private int[] count(ArrayList<Card> X) {
        // Convert given hand into an array of card values.
        int[] indexQuantityAndValue = new int[6];
        int[] cardValues = new int[5];
        for (int i = 0; i < X.size(); i++) {
            cardValues[i] = X.get(i).getValue();
        }
        // Make an array to represent total quantities of each corresponding card.
        // Iterate and add 1 to corresponding positon in numTotal array if we have an
        // instance of that card.
        int[] numTotal = { 0, 0, 0, 0, 0 };
        for (int i = 0; i < X.size(); i++) {
            if (X.get(i).getValue() == cardValues[0]) {
                numTotal[0]++;
            }
            if (X.get(i).getValue() == cardValues[1]) {
                numTotal[1]++;
            }
            if (X.get(i).getValue() == cardValues[2]) {
                numTotal[2]++;
            }
            if (X.get(i).getValue() == cardValues[3]) {
                numTotal[3]++;
            }
            if (X.get(i).getValue() == cardValues[4]) {
                numTotal[4]++;
            }
        }
        // Now, find which position in numTotals has the biggest number i.e quantity of
        // cards.
        int biggestIndex = 0;
        for (int i = 0; i < numTotal.length; i++) {
            if (numTotal[i] > numTotal[biggestIndex]) {
                biggestIndex = i;
            }
        }
        // Then we match that index with the corresponding index in the original values
        // array.
        // And return an array containing the index of the largest card, number of that
        // card, and the card value.
        indexQuantityAndValue[0] = biggestIndex;
        indexQuantityAndValue[1] = numTotal[biggestIndex];
        indexQuantityAndValue[2] = cardValues[biggestIndex];

        return indexQuantityAndValue;
    }

}
