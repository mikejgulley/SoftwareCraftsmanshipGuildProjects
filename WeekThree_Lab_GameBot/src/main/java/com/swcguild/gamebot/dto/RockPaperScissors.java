package com.swcguild.gamebot.dto;

import java.util.Random;

/**
 *
 * @author apprentice
 */
public class RockPaperScissors implements Game {

    @Override
    public String getGameName() {
        return "Rock, Paper, Scissors";
    }

    @Override
    public void run() {
        Random rand = new Random();

        final int MIN_ROUNDS = 1;
        final int MAX_ROUNDS = 10;

        int numRounds = 0;
        int userChoice, compChoice;
        int tieCount;
        int userWins;
        int compWins;
        String resultTxt = "";
        String playAgain = "";

        do {
            tieCount = 0;
            userWins = 0;
            compWins = 0;

            io.writeString("Let's play \"Rock, Paper, Scissors\"!!!");

            numRounds = setNumRounds(numRounds, MIN_ROUNDS, MAX_ROUNDS);

            for (int i = 0; i < numRounds; i++) {

                io.writeString("\nThe choices are: ");
                io.writeString("1 = Rock");
                io.writeString("2 = Paper");
                io.writeString("3 = Scissors");

                // userChoice = 1 + rand.nextInt(3); // Random user choice generator
                userChoice = io.readInt("\nMake your choice: "); // User choice input
                compChoice = 1 + rand.nextInt(3);

                // result() - but don't yet know how to return multiple values from a method (resultTxt, tieCount, userWins, compWins) -- Need to return a class
                if (userChoice == compChoice) {
                    resultTxt = "Tie";
                    tieCount++;
                } else if (userChoice - compChoice == 1 || userChoice - compChoice == -2) {
                    resultTxt = "You win!";
                    userWins++;
                } else {
                    resultTxt = "Bummer. Computer wins.";
                    compWins++;
                }

                io.writeString("Result: " + resultTxt);
                // System.out.println();
            }

            endCurrentGame(tieCount, userWins, compWins);

            playAgain = io.readString("\nWould you like to play again? (y or n) ");
            io.writeString("");

        } while (playAgain.equalsIgnoreCase("y"));
    }

    public class Results {

    }

    public static int setNumRounds(int numRounds, int minRounds, int maxRounds) {
        // Technically the spec says the program should quit and give an error message if the user
        // inputs an invalid number of rounds. However, I chose to reprompt the user.
        do {
            numRounds = io.readInt("How many rounds (from 1 to 10) would you like to play? ");;

            if (numRounds < minRounds || numRounds > maxRounds) {
                io.writeString("That is an invalid number of rounds.");
            }
        } while (numRounds < minRounds || numRounds > maxRounds);

        return numRounds;
    }

    public static void endCurrentGame(int tieCount, int userWins, int compWins) {
        // Print number of ties, user wins, comp wins, and overall winner
        io.writeString("\nSummary: ");
        io.writeString("Ties: " + tieCount);
        io.writeString("User Wins: " + userWins);
        io.writeString("Computer Wins: " + compWins);

        if (userWins > compWins) {
            io.writeString("Congratualtions! You are the overall winner!");
        } else {
            io.writeString("Drat. The computer is the overall winner.");
        }
    }
}
