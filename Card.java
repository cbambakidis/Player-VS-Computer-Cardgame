public class Card {
    private int suit;
    private int value;
    // This method for organizing card suit and value was adapted from user Alisa on
    // stackoverflow.
    private String[] values = { null, "Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Jack", "Queen", "King" };
    private String[] cardSuits = { null, "Clubs", "Hearts", "Diamonds", "Spades" };

    public Card() {
    }

    /*
     * Constructs new card with a value and suit. Whenever we use toString, it
     * compares the number - with a word from the arrays above. - This way makes
     * keeping track of face cards easier - jacks, queens and kings are just 12, 13
     * and 14.
     */
    public Card(int value, int suit) {
        this.value = value;
        this.suit = suit;

    }

    public int getValue() {
        return value;
    }

    public String toString() {
        return values[value] + " of " + cardSuits[suit];
    }

}
