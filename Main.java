import java.util.Scanner;
/*
- @author cbambakidis
- 1181 Project 4
- Cheatham, Fall 2020 
*/
class Main {
    /*
    - This is a java version of the card game "Spoons".
    */
    public static void main(String[] args) {
        Scanner keyboard = new Scanner(System.in);
        boolean hasInput = false;
        int numPlayers = 0;
        while (!hasInput) {
            System.out.print("Enter the number of players: ");
            String input = keyboard.next();
            try {
                 numPlayers = Integer.parseInt(input);
                if (numPlayers <=3 && numPlayers > 0) {
                    hasInput = true;
                }
                else{
                    System.out.println("Please enter a number between 1 and 3. ");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter an integer.");
            }
        }
        new Game(numPlayers);
        keyboard.close();
    }
}