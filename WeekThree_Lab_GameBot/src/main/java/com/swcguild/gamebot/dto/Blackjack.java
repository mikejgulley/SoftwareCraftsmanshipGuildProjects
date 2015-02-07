package com.swcguild.gamebot.dto;

import java.util.Random;

/**
 *
 * @author Michael Gulley
 */
public class Blackjack implements Game {

    @Override
    public String getGameName() {
        return "Blackjack";
    }

    @Override
    public void run() {
        Random rand = new Random();

        int playerDraw1 = 2 + rand.nextInt(10);
        int playerDraw2 = 2 + rand.nextInt(10);
        int playerTotal = playerDraw1 + playerDraw2;
        int dealerDraw1 = 2 + rand.nextInt(10);
        int dealerDraw2 = 2 + rand.nextInt(10);
        int dealerTotal = dealerDraw1 + dealerDraw2;
        String hitStay;
        int playerHit;
        int dealerHit;

        io.writeString("Let's play \"Blackjack\"!!!\n");
        io.writeString("You drew " + playerDraw1 + " and " + playerDraw2 + ".");
        io.writeString("Your total is " + playerTotal + ".");
        io.writeString("");

        io.writeString("The dealer has a " + dealerDraw1 + " showing, and a hidden card.");
        io.writeString("His total is hidden, too.");
        io.writeString("");

        do {
            hitStay = io.readString("Would you like to \"hit\" or \"stay\"? ");

            if (hitStay.equalsIgnoreCase("hit")) {
                playerHit = 2 + rand.nextInt(10);
                playerTotal += playerHit;

                io.writeString("\nYou drew a " + playerHit + ".");
                io.writeString("Your total is " + playerTotal + ".");
            }

            if (playerTotal > 21) {
                io.writeString("\nBUST! You lose.");
                io.writeString("");
                return;
                // System.exit(0);
            }
        } while (hitStay.equalsIgnoreCase("hit"));

        io.writeString("\nOkay, dealer's turn.");
        io.writeString("His hidden card was " + dealerDraw2 + ".");
        io.writeString("His total was " + dealerTotal + ".");

        if (dealerTotal <= 16) {
            io.writeString("\nDealer chooses to hit.");
            dealerHit = 2 + rand.nextInt(10);
            dealerTotal += dealerHit;
            io.writeString("Dealer draws a " + dealerHit + ".");
            io.writeString("His total is " + dealerTotal + ".");

            if (dealerTotal > 21) {
                io.writeString("\nDealer busts! You win!");
                System.exit(0);
            }
        } else {
            io.writeString("\nDealer stays.");
        }

        io.writeString("\nDealer total is " + dealerTotal + ".");
        io.writeString("Your total is " + playerTotal + ".");
        io.writeString("");

        if (playerTotal > dealerTotal) {
            io.writeString("YOU WIN!");
            io.writeString("");
        } else {
            io.writeString("Bummer. Dealer wins.");
            io.writeString("");
        }
    }

}
