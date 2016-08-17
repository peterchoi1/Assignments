package crapsPackage;

import java.util.Scanner;

public class SimpleCraps {

    public static void main(String[] args) {
        // create dice object
        Dice dice = new Dice();
        // start the game
        dice.startGame();
    }

}

class Dice {
    private int die1;
    private int die2;
    private int point; // save what the point is
    private Scanner in = new Scanner(System.in);
    boolean phase1 = true; // boolean for turn 1 or not
    boolean playing = true; // boolean for ending the game or not

    // get the total amount
    public int getRoll() {
        return this.die1 + this.die2;
    }

    // roll the dice
    public void rollDice() {
        this.die1 = ((int) (Math.random() * 6 + 1));
        this.die2 = ((int) (Math.random() * 6 + 1));
    }

    // check if the user wants to play again
    public void playAgain() {
        System.out.println("Would you like to play again? Type any key to continue or Type 'N' to quit.");
        in = new Scanner(System.in);
        String input = in.nextLine();
        if (input.toLowerCase().equals("n")) {
            System.out.println("The game has ended.");
            playing = false;
        } else {
            phase1 = true;
            playing = true;
            startGame();
        }
    }

    // starts the game
    public void startGame() {

        do {
            System.out.println("Please hit any key to roll.");
            String input = in.nextLine();

            rollDice();

            System.out.println("Roll: " + getRoll());

            //*********** PHASE 2 *************
            // win on phase 2 if i roll my point
            if (getRoll() == point && phase1 == false) {
                System.out.println("You rolled your point: " + getRoll() + ". You won!");
                playing = false;
                playAgain();
                // lose on phase 2 if i roll a 7
            } else if (getRoll() == 7 && phase1 == false) {
                System.out.println("You rolled a " + getRoll() + ". You Lose.");
                playing = false;
                playAgain();
            }

            
            //************ TURN 1 *****************
            // lose on turn 1 if i roll these numbers
            if ((getRoll() == 2 || getRoll() == 3 || getRoll() == 12) && phase1 == true) {
                System.out.println("You rolled a " + getRoll() + ", you lose.");
                playAgain();
                // win on turn 1 if i roll a 7
            } else if (getRoll() == 7 && phase1 == true) {
                playing = false;
                phase1 = false;
                System.out.println("You rolled a 7 on the first roll. You won!");
                playAgain();
                // record point and continue to phase 2
            } else if ((getRoll() != 2 || getRoll() != 3 || getRoll() != 12 || getRoll() != 7) && phase1 == true) {
                point = getRoll();
                phase1 = false;
                System.out.println("***Your point is: " + point + "***");
            }
            // check if we are still playing
        } while (playing == true);

    }
}
