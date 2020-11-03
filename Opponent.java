
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

    public Opponent(ArrayList<Card> startingHand, Stack<Card> discardPile, int playerNumber) {
        this.hand = startingHand;
        this.discardPile = discardPile;
        this.playerNumber = playerNumber;
    }

    public void makeMove(Deck drawPile, Stack<Card> discardPile) {
        // See if we have more than one of a kind of card.
        topCard = count(hand);
        idealCardValue = topCard[2];
        idealCardQuantity = topCard[1];

        // If we do, we have an ideal card to look for in discard pile.
        if (idealCardQuantity <= 1) {
            hasIdealCard = false;
            hasNextIdeal = false;
        } else {
            hasIdealCard = true;
            System.out.println("Ideal card pre draw: " + idealCardValue);
        }

        handWithoutBestCard.clear();
        //If we have an idea, check for second ideal. Mark true if we have one, false if we don't.
        if(hasIdealCard){
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
            System.out.println("No second ideal.");
        } else {
            hasNextIdeal = true;
            System.out.println("Next ideal: " + secondChoiceValue + " " + secondChoiceQuantity);
        }
    }


        // Check if the discard pile has a matching card, to any card in our hand.
        System.out.println("Discard pile: " + discardPile.peek().getValue());
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
            System.out.println("Opponent drew " + drawPile.peek() + " from draw pile.");
            hand.add(drawPile.pop());
        }

        checkWinStatus();

        // With our new hand, we reasses and see if we have more than one of a kind of
        // card.
        topCard = count(hand);
        idealCardValue = topCard[2];
        idealCardQuantity = topCard[1];

        if (idealCardQuantity <= 1) {
            hasIdealCard = false;
        } else {
            hasIdealCard = true;
        }
        handWithoutBestCard.clear();
        if(hasIdealCard){
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
        Discard();
        checkWinStatus();
    }

        // At this point, in discard phase, this is to make sure opponent doesn't
        // discard card he has more of 1 of.
        // In draw phase, it will be compared to discard pile so he knows if he should
        // draw from it.

        /*
         * Also check if has next ideal card, indexed in 4,5 and 6. If neither in deck,
         * draw pile. If discard has either type of ideal card, draw it and discard card
         * i have least of. Add user input safety.
         */

        // Doesn't matter what we discard if we don't have more than 1 card of a kind.
        // If he doesn't have an ideal card, he won't have a second ideal card.

        /*
            *Discard Phase*
        */
        public void Discard() {          
        if (!hasIdealCard) {
            discardPile.add(hand.get(2));
            hand.remove(2);
            return;
        }

        if (hasIdealCard && !hasNextIdeal){
            for (Card N : hand) {
                if (N.getValue() != idealCardValue) {
                    discardPile.add(N);
                    hand.remove(N);
                    return;
                }
            }
        }
        //If we have 3 idealcards, and 2 second ideals, discard a second ideal, or anything non ideal.
        //If we have 2 ideal, 2 second ideal, discard the one non ideal.
        if(hasIdealCard && hasNextIdeal && idealCardQuantity == 2){
            for(Card N: hand){
                if(N.getValue() != idealCardValue && N.getValue() != secondChoiceValue){
                    discardPile.add(N);
                    hand.remove(N);
                    return;
                }
            }
        }

        if(hasIdealCard && hasNextIdeal && idealCardQuantity == 3){
            for(Card N : hand){
                if(N.getValue() != idealCardValue){
                    discardPile.add(N);
                    hand.remove(N);
                    return;
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
            System.out.println("Player " + playerNumber + " wins!");
            System.exit(0);
        }
    }

    public int[] count(ArrayList<Card> X) {
        int[] indexQuantityAndValue = new int[6];
        int[] cardValues = new int[5];
        for (int i = 0; i < X.size(); i++) {
            cardValues[i] = X.get(i).getValue();
        }

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
        System.out.println("Card quantities: " + Arrays.toString(numTotal));
        System.out.println("Card values: " + Arrays.toString(cardValues));
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
