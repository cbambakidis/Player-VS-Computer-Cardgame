import java.util.Scanner;
class Main{
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        System.out.println("Please enter the number of other player: (1-3):");
        int numPlayers = in.nextInt();
        new Game(numPlayers);
    }
}