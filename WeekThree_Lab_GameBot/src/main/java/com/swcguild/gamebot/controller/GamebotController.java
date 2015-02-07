package com.swcguild.gamebot.controller;

import com.swcguild.gamebot.dao.GameLibrary;
import com.swcguild.gamebot.dto.Blackjack;
import com.swcguild.gamebot.dto.LuckySevens;
import com.swcguild.gamebot.dto.RockPaperScissors;
import com.swcguild.gamebot.ui.View;
import java.util.ArrayList;

/**
 *
 * @author Michael J. Gulley
 */

// FIXME -- Menu does not print in order of games added -- so entering "2"
// plays Rock, Paper, Scissors instead of Blackjack

public class GamebotController {

    View io = new View();
    GameLibrary gbl = new GameLibrary();
    LuckySevens l7s = new LuckySevens();
    RockPaperScissors rps = new RockPaperScissors();
    Blackjack bj = new Blackjack();

    public void run() {
        boolean keepGoing = true;
        int menuChoice = 0;

        while (keepGoing) {
            printmenu();
            menuChoice = io.readInt("Please choose a game to play: ");

            switch (menuChoice) {
                case 1:
                    lucky7s();
                    break;
                case 2:
                    rockPaperScissors();
                    break;
                case 3:
                    blackjack();
                    break;
//                case 4:
//                    ticTacToe();
//                    break;
//                case 5:
//                    hangman();
//                    break;
                case 4:
                    keepGoing = false;
                    break;
                default:
                    io.writeString("Invalid choice. Please try again.");
            }
        }
        io.writeString("");
        io.writeString("Thanks for using GameBot!");
    }

    private void printmenu() {
        io.writeString("Welcome to GameBot: ");
        io.writeString("-------------------");
        loadInventory();
        listGameInventory();
    }

    private void lucky7s() {
        io.writeString("");
        io.writeString(l7s.getGameName());
        io.writeString("-------------------");
        l7s.run();
    }

    private void rockPaperScissors() {
        io.writeString("");
        io.writeString(rps.getGameName());
        io.writeString("----------------------");
        rps.run();
    }

    private void blackjack() {
        io.writeString("");
        io.writeString(bj.getGameName());
        io.writeString("----------------------");
        bj.run();
    }

//    private void ticTacToe() {
//        io.writeString("");
//        io.writeString("Playing Tic-Tac-Toe...");
//    }
//
//    private void hangman() {
//        io.writeString("");
//        io.writeString("Playing Hangman...");
//    }
    private void loadInventory() {
        gbl.addGame(l7s.getGameName(), l7s);
        gbl.addGame(rps.getGameName(), rps);
        gbl.addGame(bj.getGameName(), bj);
    }

    private void listGameInventory() {
        ArrayList<String> gameInventory = new ArrayList<String>(gbl.getGameInventory());
        int counter = 1;

        for (String currentGame : gameInventory) {
            io.writeString(counter + ". " + currentGame);
            counter++;
        }
        
        io.writeString(counter + ". Quit");
        
//        int counter = 1;
//        for (Map.Entry<String, Game> currentGameEntry : gbl.getGameInventory()) {
//            System.out.println(counter + ". " + currentGameEntry.getKey() + ":");
//            Game currentGame = currentGameEntry.getValue();
//            counter++;
//        }
//        io.writeString(counter + ". Quit");

    }

}
