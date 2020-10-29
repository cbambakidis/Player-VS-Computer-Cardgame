import java.util.ArrayList;

public class Player {
    private ArrayList<Card> hand;
    private boolean isPlayer = true;
    public Player(){

    }
    public boolean isPlayer(){
        return isPlayer;
    }
    public Player(ArrayList<Card> startingHand){
        hand = startingHand;
    }

    public void makeMove(){
        System.out.println("Your cards:");
        for(Card N : hand){
            System.out.println();
        }
        //prompt user for choice. Make move based on input.
    }
}
