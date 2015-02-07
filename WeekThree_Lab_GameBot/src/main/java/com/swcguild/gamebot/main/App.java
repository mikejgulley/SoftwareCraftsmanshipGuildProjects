package com.swcguild.gamebot.main;

import com.swcguild.gamebot.controller.GamebotController;

/**
 *
 * @author Michael J. Gulley
 */
public class App {

    public static void main(String[] args) {
        GamebotController gbc = new GamebotController();
        gbc.run();
    }
}
