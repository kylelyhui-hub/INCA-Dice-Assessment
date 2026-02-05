import com.sun.source.tree.ReturnTree;
import com.sun.source.util.SourcePositions;

import java.util.*;
enum playerType {
    HUMAN,
    COMPUTER
}

public class Player {
    public static final int MAX_LIVES = 5;
    private String name;
    private int lives;
    private int turnScore;
    private int turnRolls;
    private playerType playerID;

    /**
     * Q1. Complete the constructor for the Player class so that it initialises all of the fields
     * Set lives to MAX_LIVES
     * Set turnScore to 0
     * Set the name
     * playerType is done for you
     */
    public Player(String name, playerType playerID) {
        setName(name);
        setPlayerID(playerID);
        lives = MAX_LIVES;
        this.turnScore = 0;

    }


    /**
     * Q2. Complete the setter to set the player name. NB: The computer players name is always Computer
     */
    //sets the name of the computer name to "Computer" or the player's inputted name
    public void setName(String name) {
        if (getPlayerID()==playerType.COMPUTER ) {
            this.name= "Computer";
        }else{
            this.name=name;
        }
    }

    /**
     * Q3. Complete the getter to return the player name.
     */
    //this method is used to return the player's name which allows it to be called
    public String getName() {
        return name;
    }


    /**
     * Q4. Complete the getter to return the player turn score.
     */
    //this method is used to return the player's turn score which allow it to be called
    public int getTurnScore(){
        return turnScore;
    }

    /**
     * Q5. Complete the setter to set the player score
     */
    //this method is used to set the player's score and being called by the getTurnScore() method
    public void setTurnScore(int turnScore){
        this.turnScore=turnScore;
    }

    /**
     * Q6. Complete the getter to return the player lives
     */
    //this method is used to return the player's remaining life which allows it to be called
    public int getLives(){
        return lives;
    }

    /**
     * Q7.  Complete the getter for turnRolls
     */
    //this method is used to return the number of turn rolls of the player and allows it to be called
    public int getTurnRolls(){
        return turnRolls;
    }

    /**
     * Q8. Complete the method to reset the turnRolls to 1
     */
    //reset the value of the turn roll to 1
    public void resetTurnRolls(){
        turnRolls=1;
    }
    /**
     * Q9. Complete the method to increase the turnRolls by 1
     */
    //increase the values of the turn rolls by 1 after each rolls performed by the player
    public void increaseTurnRolls() {
        turnRolls++;
    }

    /**
     *  Get the player ID
     */
    //this method returns the player ID and allows it to be called
    public playerType getPlayerID() {
        return playerID;
    }

    /**
     *  Set the player ID
     */
    //this method sets the playerID and allows it to be called by the getter
    public void setPlayerID(playerType playerID) {
        this.playerID = playerID;

    }


    /**
     * Q10. Complete the method to reduce lives by 1 as long as lives is greater than 0
     */
    //this method reduces the life of the player by one if the player loses the round
    public void reduceLife(){
        if(lives>0){
            lives --;
        }
    }
}
