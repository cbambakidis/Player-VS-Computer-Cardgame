import java.util.Stack;

public class Deck extends Stack<Card> {
    private static final long serialVersionUID = 1L;

    public Deck() {
        //Make new stack of 52 cards. Shuffle either here or main method.
        for (int i=0; i<13; i++){
            Card A = new Card(i, 1);
            Card B = new Card(i, 2);
            Card C = new Card(i, 3);
            Card D = new Card(i, 4);
            this.add(A);
            this.add(B);
            this.add(C);
            this.add(D);
        }
    }

    public void shuffle(){
        this.shuffle();
    }
}
