public class Card {
    private int suit;
    private int value; 
    //This method for organizing card suit and value inspired by Alisa on stackoverflow.
    private String[] values = {"Ace" , "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};
    private String[] cardSuits = {"Clubs", "Hearts", "Diamonds", "Spades"};
    public Card(){

    }
    public Card(int value, int suit){
        this.value = value;
        this.suit = suit;

    }
    
    public String toString(){
    return values[value] + " of " + cardSuits[suit];
    }
    
}
