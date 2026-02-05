import java.util.Scanner;

public class Game {
    final static int MAX_ROLL = 3;

    /*
     * Complete the method that asks the player if they want to roll again
     * Output the String "Stop (S) or roll again (R)?"
     * return true only if the type "R" or "r"
     */
    private static boolean askPlayerRollAgain() {
        System.out.println("Stop (S) or roll again (R)?");
        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine().toUpperCase();
        if (!choice.equals("R"))
            return false;
        else {
            return true;
        }
    }
    
    private static boolean getHumanDecision(Player player) {
        boolean rolling;
        if (player.getTurnRolls() < MAX_ROLL) {
            rolling = askPlayerRollAgain();
            if (rolling) {
                player.increaseTurnRolls();
            }
        } else {
            rolling = false;
        }
        return rolling;
    }
    //compare the two turn scores for computer
    private static boolean isDouble(int score){
        int divisor = 11;
        int remainder = score % divisor;
        if(remainder == 0){
            return true;
        }else{
            return false;
        }
    }

    private static boolean getComputerDecision(Player computerPlayer, Player humanPlayer) {
        int score2 = computerPlayer.getTurnScore(); //computer's turn score
        int score1 = humanPlayer.getTurnScore(); //human's turn scpre
        boolean rolling = Dice.compareDice(score2, score1);
        /**
         * this loop is used to make the computer more intelligent which allows it to make smarter decisions under
         * a few conditions
         */
        if (computerPlayer.getTurnRolls() < MAX_ROLL) {
            if (rolling) {
                computerPlayer.increaseTurnRolls();
                if (score2 == Dice.INCA) {
                    return rolling = false;
                } else if (isDouble(score2) == isDouble(score1)) {
                    if (score2 > score1) {
                        return rolling = false;
                    } else {
                        return rolling = true;
                    }
                } else if (isDouble(score2) && !isDouble(score1)) {
                    return rolling = false;
                } else if (!isDouble(score2) && isDouble(score1)) {
                    return rolling = true;
                } else if (!isDouble(score1) && !isDouble(score2)) {
                    if (score2 > score1) {
                        return rolling = false;
                    } else {
                        return rolling = true;
                    }
                }
            }
        }else {
            rolling = false;
        }
        return rolling;
    }

    /**
     * Handles the human player turn
     * @param //player1 current player
     * @param //numRolls - set by the opposite player
     * @return how many rolls taken
     */
    private static int playPlayerTurn(Player player1, Player player2, int numRolls) {
        boolean rolling = true;
        player1.resetTurnRolls();
        while (rolling && player1.getTurnRolls() <= numRolls) {
            System.out.println(player1.getName() + " roll number " + player1.getTurnRolls() + " of " + numRolls);
            int rollScore = Dice.getDiceScore();
            System.out.println("Score is " + rollScore);
            Scanner sc = new Scanner(System.in);
            System.out.println(" Press any key to continue... ");
            sc.nextLine();
            if (player1.getPlayerID() == playerType.HUMAN) {
                rolling = getHumanDecision(player1);
            } else {
                rolling = getComputerDecision(player1, player2);
            }
            player1.setTurnScore(rollScore);
        }
        System.out.println("");
        return player1.getTurnRolls();
    }
    
    /**
     * Formatted lose message
     * @param losePlayer
     * @param winPlayer
     */
    private static void outputLoseMessage(Player losePlayer, Player winPlayer) {
        losePlayer.reduceLife();
        String winnerStr = winPlayer.getName() + " wins round ";
        if (winPlayer.getTurnScore() == Dice.INCA) {
            winnerStr += " with an Inca (21) ";
        } else
            winnerStr += winPlayer.getTurnScore();
        System.out.println(winnerStr
                + " beats "
                + losePlayer.getTurnScore()
                + ". "
                + losePlayer.getName()
                + " has "
                + losePlayer.getLives()
                + " lives left!\n");
    }

    /**
     * Checks the winner according to the rules of the game
     * @param player1
     * @param player2
     * @return number of the player that won (1 = player 1 2 = computer player)
     */
    private static playerType checkWin(Player player1, Player player2) {
        if (player1.getTurnScore() == player2.getTurnScore()) {
            System.out.println("It is a draw. Score is " + player1.getTurnScore());
        } else {
            if (Dice.compareDice(player1.getTurnScore(), player2.getTurnScore())) {
                outputLoseMessage(player2, player1);
                return playerType.HUMAN;
            } else {
                outputLoseMessage(player1, player2);
                return playerType.COMPUTER;
            }
        }
        System.out.println("");
        return playerType.HUMAN;
    }

    /*
     * Complete the method that asks the human player to enter their name
     */
    private static String getPlayerName() {
        System.out.println("Player 1 enter your name.");
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    /**
     * Main game method
     * Sets up the players & loops until one player has no lives left
     */
    public static void playGame() {
        //12 is the lowest score (as 11 is a good score!)
        final int MIN_SCORE = 12;
        //playing controls the main game loop
        boolean playing = true;
        //Initialise the classes for the human and computer player
        Player humanPlayer = new Player(getPlayerName(), playerType.HUMAN);
        Player computerPlayer = new Player("Computer", playerType.COMPUTER);
        //THis sets up the condition for the human player to go first
        playerType winner = playerType.COMPUTER;
        int lastPlayerRolls = MAX_ROLL;
        while (playing) {
            //If computer player won the previous round human player (humanPlayer) goes first
            if (winner == playerType.COMPUTER) {
                lastPlayerRolls = playPlayerTurn(humanPlayer, computerPlayer, lastPlayerRolls);
                lastPlayerRolls = playPlayerTurn(computerPlayer, humanPlayer, lastPlayerRolls);
            } else {
                //If human player won the previous round computer player goes first
                lastPlayerRolls = playPlayerTurn(computerPlayer, humanPlayer, lastPlayerRolls);
                lastPlayerRolls = playPlayerTurn(humanPlayer, computerPlayer, lastPlayerRolls);
            }
            //Check if who was the winner of the last round
            winner = checkWin(humanPlayer, computerPlayer);
            //If one player has no lives the game is over...
            if (humanPlayer.getLives() == 0 || computerPlayer.getLives() == 0)
                //One of the players has won
                playing = false;
            else {
                //Sets up a new round of the game by resetting the turn scores & numRolls
                lastPlayerRolls = MAX_ROLL;
                humanPlayer.setTurnScore(MIN_SCORE);
                computerPlayer.setTurnScore(MIN_SCORE);
            }
        }
    }
}
