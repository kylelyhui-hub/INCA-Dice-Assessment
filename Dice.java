import java.util.*;
public class Dice {
    public static final int INCA = 21;

    /**
     * Q11. Complete the method that takes two integers and returns a new single integer
     * with the largest single digit as the first digit of the number
     */
    private static int putGreatestDigitFirst(int num1, int num2) {
        if(num1 > num2){
            return num1*10 + num2;
        }else {
            return num2*10 + num1;
        }

    }

    /**
     * Q12. Complete the method so that the method will return a random number between 1 and 6
     */

    private static int getDiceRoll(){ //this method is for the generating the numbers on the incan dice
        int range = 5;
        return (int)(Math.random() * range) +1;
    }

    /**
     * Q13. Complete the method putGreatestDigitFirst if you pass in two random integers 1 to 6
     */

    public static int getDiceScore() {
        int diceScore1 = getDiceRoll();
        int diceScore2 = getDiceRoll();
        int diceScore = putGreatestDigitFirst(diceScore1, diceScore2);
        return diceScore;
    }
    /**
     * Q14. Complete a method to works out if the two digits of a number are the same (it's a double)
     */
    private static boolean isDouble(int score){
        int divisor = 11;
        int remainder = score % divisor;
        if(remainder == 0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Q15. Complete the method to compare two dice scores to see if one is higher than another following the
     * rules of the game
     *
     * Don't forget in the game:
     * 21 beats everything (INCA)
     * 11 still beats 65 as a double will beat any two different numbers that (apart from 21)
     * So the order is
     * 21, 66, 55, 44, 33, 22, 11, 65 and then any other number in descending order
     * return true if score 1 beats score 2. If the dice are equal this is handled in the
     * game code (so you don't need to check if they are equal)
     */
    //score1 = player score
    //score2 = computer score
    public static boolean compareDice(int score1, int score2) {
        if (score1 == INCA) {
            return true;
        }else if(score2==INCA){
                return false;
        }else if (isDouble(score1)&&isDouble(score2)) {
            if(score1>score2){
                return true;
            }else{
                return false;
            }
        }else if(isDouble(score1) && !isDouble(score2)){
            return true;
        }else if (!isDouble(score1) && isDouble(score2)){
            return false;
        }else if(!isDouble(score1) && !isDouble(score2)) {
            if (score1 > score2) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

}
