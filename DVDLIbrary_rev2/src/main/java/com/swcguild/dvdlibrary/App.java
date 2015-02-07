package com.swcguild.dvdlibrary;

import com.swcguild.dvdlibrary.controller.DVDLibraryController;

/**
 *
 * @author Michael J. Gulley
 */
public class App {

    public static void main(String[] args) {
        DVDLibraryController dlc = new DVDLibraryController();
        dlc.run();
    }
}
