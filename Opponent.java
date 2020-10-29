import java.util.ArrayList;
import java.util.Stack;


public class Opponent extends Player {
    int ones;
int twos;
int threes;
int fours;
int fives;
int sixes;
int sevens;
int eights;
int nines;
int tens;
int jacks;
int queens;
int kings;
    private ArrayList<Card> hand;
    private boolean isPlayer = false;
    public Opponent(){
    }
    public Opponent(ArrayList<Card> startingHand){
        hand = startingHand;
    }
    public boolean isPlayer(){
        return isPlayer();
    }


    //Hand will have cards the opponent won't want to play. MakeMove should figure this out.
    public void makeMove(Stack<Card> drawPile, Stack<Card> discardPile){
        Card idealCard;
 


        ArrayList<Card> handWithDiscard = hand;
        handWithDiscard.add(discardPile.peek());
        
        //Given card stack options, make move. 
        //perhaps boolean doNotPlay in card class to mark desired type of suit.
        //If iterators to determine hand and best combo to go for.
        //Make choice based on if cards missing from desired combo matches cards on discard. 
        //Make choice to discard cards that don't match it.
        //If card on discard doesn't match, then draw from deck.
    }

    public Card countCard(){
        for(Card K : hand){

        }
        return null;
    }
    
}
