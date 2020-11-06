import java.util.Stack;
//This class is basically a stack of cards with a shuffle method.
public class Deck extends Stack<Card> {
    private static final long serialVersionUID = 1L;

    public Deck() {
        //Make new stack of 52 cards. Shuffle either here or main method.
        for (int i=1; i<13; i++){
            Card A = new Card(i, 1);
            Card B = new Card(i, 2);
            Card C = new Card(i, 3);
            Card D = new Card(i, 4);
            this.add(A);
            this.add(B);
            this.add(C);
            this.add(D);
        }
        shuffle();
    }
    
    //Idea for shuffle method adapted from Hobart and William Smith Colleges.
    public void shuffle(){
            for ( int i = this.size()-1; i > 0; i--) {
                int rand = (int)(Math.random()*(i+1));
                Card temp = this.get(i);
                this.set(i, this.get(rand));
                this.set(rand, temp);
            }        
    }
}
