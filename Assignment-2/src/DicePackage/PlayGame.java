package DicePackage;

class Dice {

    int dice;
    int score = 0;

    public int getDice() {
        return this.dice;
    }

    public void rollDice() {
        this.dice = ((int) (Math.random() * (6) + 1));
    }

    public void startRolling() {
        while (this.dice != 1) {
            rollDice();
            System.out.println("The roll was: " + getDice());
            if (this.dice != 1) {
                score += getDice();
            } else {
                break;
            }
        }
    }

}

public class PlayGame {

    public static void main(String[] args) {
        Dice dice = new Dice();

        dice.startRolling();
        System.out.println("Your score is: " + dice.score);
    }

}
