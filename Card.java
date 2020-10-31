public class Card {
    private int suit;
    private int value;
    // This method for organizing card suit and value inspired by Alisa on
    // stackoverflow.
    private String[] values = { null, "Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
            "Jack", "Queen", "King" };
    private String[] cardSuits = { null, "Clubs", "Hearts", "Diamonds", "Spades" };

    public Card() {
    }

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
