import java.util.Comparator;

public class CardCounter implements Comparator<Card> {

    @Override
    public int compare(Card o1, Card o2) {
        if(o1.getValue() < o2.getValue()){
            return -1;
        }
        if(o1.getValue() > o2.getValue()){
            return 1;
        }
        else return 0;
    }
    
}
