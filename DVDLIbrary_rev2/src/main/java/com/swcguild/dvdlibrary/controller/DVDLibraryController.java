package com.swcguild.dvdlibrary.controller;

import com.swcguild.dvdlibrary.dao.DVDLibrary;
import com.swcguild.dvdlibrary.dto.DVD;
import com.swcguild.dvdlibrary.ui.View;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Michael J. Gulley
 */
public class DVDLibraryController {

    View io = new View();
    DVDLibrary dvdLib = new DVDLibrary();
    Random rand = new Random();

    public void run() {
        boolean keepGoing = true;
        int userChoice = 0;

        try {
            dvdLib.loadLibrary();
            while (keepGoing) {
                printMenu();
                userChoice = io.readInt("Please enter a choice from above: ");

                switch (userChoice) {
                    case 1:
                        createDVD();
                        break;
                    case 2:
                        deleteDVD();
                        break;
                    case 3:
                        listDVDsByCriteria();
                        break;
                    case 4:
                        listTotalNumberOfDVDs();
                        break;
                    case 5:
                        listAllDVDs();
                        break;
                    case 6:
                        editDVD();
                        break;
                    case 7:
                        keepGoing = false;
                        break;
                    default:
                        io.writeString("Invalid input. Please try again.");
                }
            }
            io.writeString("\nThanks for using the Interactive DVD Library!");
            dvdLib.writeLibrary();
        } catch (FileNotFoundException fnf) {
            io.writeString("Error loading DVD Library.");
        } catch (IOException ioe) {
            io.writeString("Error writing to file.");
        }
    }

    private void printMenu() {
        io.writeString("Welcome to the Interactive DVD Library: ");
        io.writeString("\tMain Menu:");
        io.writeString("\t1. Add DVD to Library");
        io.writeString("\t2. Delete DVD");
        io.writeString("\t3. Find DVDs by Criteria");
        io.writeString("\t4. List total number of DVDs");
        io.writeString("\t5. List all DVDs");
        io.writeString("\t6. Edit DVD Library");
        io.writeString("\t7. Quit");
    }

    private void createDVD() {
        ArrayList<Integer> dvdIds = dvdLib.getAllDvdIDs();

        io.writeString("");
        io.writeString("Please enter to following fields to add a DVD to your library: ");
//        int id = dvdLib.idGen();
//        int id = rand.nextInt(1000);

        String title = io.readString("Title: ");
        String releaseDate = io.readString("Release Date (Year): ");
        String mpaaRating = Integer.toString(io.readInt("MPAA Rating (1 = G, 2 = PG, 3 = PG-13, 4 = R, 5 = other): ", 1, 5));
        switch (mpaaRating) {
            case "1":
                mpaaRating = "G";
                break;
            case "2":
                mpaaRating = "PG";
                break;
            case "3":
                mpaaRating = "PG-13";
                break;
            case "4":
                mpaaRating = "R";
                break;
            case "5":
                mpaaRating = "Other";
                break;
            default:
                io.writeString("Invalid input. Please try again.");
        }
        String format = Integer.toString(io.readInt("Format (1 = DVD, 2 = BluRay, 3 = Digital, 4 = Other): ", 1, 4));
        switch (format) {
            case "1":
                format = "DVD";
                break;
            case "2":
                format = "BluRay";
                break;
            case "3":
                format = "Digital";
                break;
            case "4":
                format = "Other";
                break;
            default:
                io.writeString("Invalid input. Please try again.");
        }
        String directorName = io.readString("Director (first and last name): ");
        String studio = io.readString("Studio: ");
        String userRating = Integer.toString(io.readInt("Rating (1 = One Star, 2 = Two Stars, 3 = Three Stars, 4 = Four Stars; 0 = skip for now): ", 0, 4));
        switch (userRating) {
            case "1":
                userRating = "*";
                break;
            case "2":
                userRating = "**";
                break;
            case "3":
                userRating = "***";
                break;
            case "4":
                userRating = "****";
                break;
            case "5":
                userRating = "";
                break;
            default:
                io.writeString("Invalid input. Please try again.");
        }
        String note = io.readString("Notes: ");
        // userNotes.add(note);

        // Check for duplicate ID's
        int id = rand.nextInt(10000);
        for (Integer currentId : dvdIds) {
            if (id != currentId) {
                continue;
            }
        }

        DVD currentDVD = new DVD();

        currentDVD.setId(id);
        currentDVD.setTitle(title);
        currentDVD.setReleaseDate(releaseDate);
        currentDVD.setMpaaRating(mpaaRating);
        currentDVD.setFormat(format);
        currentDVD.setDirectorName(directorName);
        currentDVD.setStudio(studio);
        currentDVD.setUserRating(userRating);
        currentDVD.setUserNote(note);

        dvdLib.addDVD(id, currentDVD);

        io.readString("Movie successfully added to DVD Library. Please press <enter> to continue.");
        io.writeString("");
    }

    private void deleteDVD() {
        ArrayList<Integer> dvdIds = dvdLib.getAllDvdIDs();
        Integer userChoice = 0;
        try {
            if (dvdIds.isEmpty()) {
                io.writeString("There are currently no DVD's in the database.");
                io.writeString("");
            } else {
                String title = io.readString("Please enter the title of the DVD you would like to remove. Or press <enter> to cancel: ");
                if (!title.equals("")) {
                    for (Integer currentId : dvdLib.getDVDsByTitle(title)) {
                        DVD currentDVD = dvdLib.getDVD(currentId);
                        io.writeString(currentDVD.toString());
                    }
                    if (dvdLib.getDVDsByTitle(title).isEmpty()) {
                        io.writeString("Title does not exist in database. Please try again.");
                        io.writeString("");
                    } else if (dvdLib.getDVDsByTitle(title).size() > 1) {
                        userChoice = io.readInt("\nPlease enter the ID of the DVD you would like to delete. Or press 0 (zero) to cancel: ");
                        if (userChoice != 0) {
                            dvdLib.removeDVD(userChoice);
                            io.readString("Movie successfully deleted from DVD Library. Please press <enter> to continue. ");
                            io.writeString("");
                        }
                    }
                }
            }
        } catch (NullPointerException npe) {
            io.writeString("DVD does not exist in database. Please try again.");
            io.writeString("");
        }
    }

    private void listDVDsByCriteria() {
        ArrayList<Integer> dvdIds = dvdLib.getAllDvdIDs();
        if (dvdIds.isEmpty()) {
            io.writeString("There are currently no DVD's in the database.");
            io.writeString("");
        } else {
            io.writeString("\nList DVDs by Criteria Menu: ");
            io.writeString("\t1. List DVDs by Title");
            io.writeString("\t2. List DVDs by Release Date");
            io.writeString("\t3. List DVDs by Format");
            io.writeString("\t4. List DVDs by MPAA Rating");
            io.writeString("\t5. List DVDs by Director Name");
            io.writeString("\t6. List DVDs by Studio");
            io.writeString("\t7. List DVDs by User Rating");
            io.writeString("\t8. Back to Main Menu");
            int userChoice = io.readInt("\nPlease choose the criteria by which you would like to list DVDs: ");
            switch (userChoice) {
                case 1:
                    listDVDsByTitle();
                    break;
                case 2:
                    listDVDsByReleaseDate();
                    break;
                case 3:
                    listDVDsByFormat();
                    break;
                case 4:
                    listDVDsByMpaaRating();
                    break;
                case 5:
                    listDVDsByDirectorName();
                    break;
                case 6:
                    listDVDsByStudio();
                    break;
                case 7:
                    listDVDsByUserRating();
                    break;
                default:
                    io.writeString("Invalid input. Please try again.");
            }
        }
        io.writeString("");
    }

    private void listTotalNumberOfDVDs() {
        ArrayList<Integer> dvdIds = dvdLib.getAllDvdIDs();

        if (dvdIds.isEmpty()) {
            io.writeString("There are currently no DVD's in the database.");
            io.writeString("");
        } else {
            io.writeString("");
            io.writeString("Total Number of DVDs in database: " + dvdIds.size());
        }
        io.writeString("");
    }

    private void listAllDVDs() {
        ArrayList<Integer> dvdIds = dvdLib.getAllDvdIDs();

        if (dvdIds.isEmpty()) {
            io.writeString("There are currently no DVD's in the database.");
            io.writeString("");
        } else {
            for (Integer currentId : dvdIds) {
                DVD currentDVD = dvdLib.getDVD(currentId);

                io.writeString(currentDVD.toStringNoId());
            }
        }
        io.writeString("");
    }

    private void editDVD() {
        ArrayList<Integer> dvdIds = dvdLib.getAllDvdIDs();
        int userChoice = 0;

        try {
            String title = io.readString("Please enter the title of the DVD you would like to edit. Or press <enter> to cancel: ");
            if (!title.equals("")) {
                for (Integer currentId : dvdLib.getDVDsByTitle(title)) {
                    DVD currentDVD = dvdLib.getDVD(currentId);
                    io.writeString(currentDVD.toString());
                }
                if (dvdLib.getDVDsByTitle(title).isEmpty()) {
                    io.writeString("Title does not exist in database. Please try again.");
                    io.writeString("");
                } else if (dvdLib.getDVDsByTitle(title).size() > 1) {
                    userChoice = io.readInt("\nPlease enter the ID of the DVD you would like to edit. Or press 0 (zero) to cancel: ");
                    if (userChoice != 0) {
                        io.writeString("Please enter new value for the following fields, or press <enter> to skip. ");

                        String newValue = io.readString("Title: ");

                        if (!newValue.equals("")) {
                            dvdLib.getDVD(userChoice).setTitle(newValue);
                        }

                        newValue = io.readString("Release Date (Year): ");

                        if (!newValue.equals("")) {
                            dvdLib.getDVD(userChoice).setReleaseDate(newValue);
                        }

                        newValue = Integer.toString(io.readInt("MPAA Rating (1 = G, 2 = PG, 3 = PG-13, 4 = R, 5 = other, 0 = Keep current MPAA Rating): ", 0, 5));

                        if (!newValue.equals("0")) {
                            switch (newValue) {
                                case "1":
                                    newValue = "G";
                                    break;
                                case "2":
                                    newValue = "PG";
                                    break;
                                case "3":
                                    newValue = "PG-13";
                                    break;
                                case "4":
                                    newValue = "R";
                                    break;
                                case "5":
                                    newValue = "Other";
                                    break;
                                default:
                                    io.writeString("Invalid input. Please try again.");
                            }
                            dvdLib.getDVD(userChoice).setMpaaRating(newValue);
                        }

                        newValue = Integer.toString(io.readInt("Format (1 = DVD, 2 = BluRay, 3 = Digital, 4 = Other, 0 = Keep Current Format): ", 0, 4));

                        if (!newValue.equals("0")) {
                            switch (newValue) {
                                case "1":
                                    newValue = "DVD";
                                    break;
                                case "2":
                                    newValue = "BluRay";
                                    break;
                                case "3":
                                    newValue = "Digital";
                                    break;
                                case "4":
                                    newValue = "Other";
                                    break;
                                default:
                                    io.writeString("Invalid input. Please try again.");
                            }
                            dvdLib.getDVD(userChoice).setDirectorName(newValue);
                        }

                        newValue = io.readString("Studio: ");

                        if (!newValue.equals("")) {
                            dvdLib.getDVD(userChoice).setStudio(newValue);
                        }

                        newValue = Integer.toString(io.readInt("Rating (1 = One Star, 2 = Two Stars, 3 = Three Stars, 4 = Four Stars; 0 = Keep Current User Rating): ", 0, 4));

                        if (!newValue.equals("0")) {
                            switch (newValue) {
                                case "1":
                                    newValue = "*";
                                    break;
                                case "2":
                                    newValue = "**";
                                    break;
                                case "3":
                                    newValue = "***";
                                    break;
                                case "4":
                                    newValue = "****";
                                    break;
                                case "5":
                                    newValue = "";
                                    break;
                                default:
                                    io.writeString("Invalid input. Please try again.");
                            }
                            dvdLib.getDVD(userChoice).setUserRating(newValue);
                        }

                        newValue = io.readString("Notes: ");

                        if (!newValue.equals("")) {
                            dvdLib.getDVD(userChoice).setUserNote(newValue);
                        }

                        io.readString("Field(s) successfully updated. Please press <enter> to continue.");
                        io.writeString("");
                    } else {
                        io.writeString("No entries updated.");
                        io.writeString("");
                    }
                } else {
                    String confirmUpdate = io.readString("Are you sure you want to update this entry? (y/n) ");
                    if (confirmUpdate.equalsIgnoreCase("y")) {
                        io.writeString("Please enter new value for the following fields, or press <enter> to skip. ");

                        String newValue = io.readString("Title: ");

                        if (!newValue.equals("")) {
                            dvdLib.getDVD(userChoice).setTitle(newValue);
                        }

                        newValue = io.readString("Release Date (Year): ");

                        if (!newValue.equals("")) {
                            dvdLib.getDVD(userChoice).setReleaseDate(newValue);
                        }

                        newValue = Integer.toString(io.readInt("MPAA Rating (1 = G, 2 = PG, 3 = PG-13, 4 = R, 5 = other, 0 = Keep current MPAA Rating): ", 0, 5));

                        if (!newValue.equals("0")) {
                            switch (newValue) {
                                case "1":
                                    newValue = "G";
                                    break;
                                case "2":
                                    newValue = "PG";
                                    break;
                                case "3":
                                    newValue = "PG-13";
                                    break;
                                case "4":
                                    newValue = "R";
                                    break;
                                case "5":
                                    newValue = "Other";
                                    break;
                                default:
                                    io.writeString("Invalid input. Please try again.");
                            }
                            dvdLib.getDVD(userChoice).setMpaaRating(newValue);
                        }

                        newValue = Integer.toString(io.readInt("Format (1 = DVD, 2 = BluRay, 3 = Digital, 4 = Other, 0 = Keep Current Format): ", 0, 4));

                        if (!newValue.equals("0")) {
                            switch (newValue) {
                                case "1":
                                    newValue = "DVD";
                                    break;
                                case "2":
                                    newValue = "BluRay";
                                    break;
                                case "3":
                                    newValue = "Digital";
                                    break;
                                case "4":
                                    newValue = "Other";
                                    break;
                                default:
                                    io.writeString("Invalid input. Please try again.");
                            }
                            dvdLib.getDVD(userChoice).setDirectorName(newValue);
                        }

                        newValue = io.readString("Studio: ");

                        if (!newValue.equals("")) {
                            dvdLib.getDVD(userChoice).setStudio(newValue);
                        }

                        newValue = Integer.toString(io.readInt("Rating (1 = One Star, 2 = Two Stars, 3 = Three Stars, 4 = Four Stars; 0 = Keep Current User Rating): ", 0, 4));

                        if (!newValue.equals("0")) {
                            switch (newValue) {
                                case "1":
                                    newValue = "*";
                                    break;
                                case "2":
                                    newValue = "**";
                                    break;
                                case "3":
                                    newValue = "***";
                                    break;
                                case "4":
                                    newValue = "****";
                                    break;
//                                case "5":
//                                    newValue = "";
//                                    break;
                                default:
                                    io.writeString("Invalid input. Please try again.");
                            }
                            dvdLib.getDVD(userChoice).setUserRating(newValue);
                        }

                        newValue = io.readString("Notes: ");

                        if (!newValue.equals("")) {
                            dvdLib.getDVD(userChoice).setUserNote(newValue);
                        }

                        io.readString("Field(s) successfully updated. Please press <enter> to continue.");
                        io.writeString("");
                    } else {
                        io.writeString("No entries updated.");
                        io.writeString("");
                    }
                }
            }
        } catch (NullPointerException npe) {
            io.writeString("No entries updated.");
            io.writeString("");
        }
    }

    private void listDVDsByTitle() {
        ArrayList<Integer> dvdIds = dvdLib.getAllDvdIDs();
        String title = io.readString("Please enter the title of the DVD you would like to find: ");
        dvdIds = dvdLib.getDVDsByTitle(title);
        for (Integer currentId : dvdIds) {
            DVD currentDVD = dvdLib.getDVD(currentId);
            io.writeString(currentDVD.toStringNoId());
        }
    }

    private void listDVDsByReleaseDate() {
        ArrayList<Integer> dvdIds = dvdLib.getAllDvdIDs();
        String releaseDate = io.readString("Please enter the Year of Release of the DVDs you would like to find: ");
        dvdIds = dvdLib.getDVDsByReleaseDate(releaseDate);
        for (Integer currentId : dvdIds) {
            DVD currentDVD = dvdLib.getDVD(currentId);
            io.writeString(currentDVD.toStringNoId());
        }
    }

    private void listDVDsByMpaaRating() {
        ArrayList<Integer> dvdIds = dvdLib.getAllDvdIDs();
        String mpaaRating = io.readString("Please enter the MPAA rating of the DVDs you would like to find: ");
        dvdIds = dvdLib.getDVDsByMpaaRating(mpaaRating);
        for (Integer currentId : dvdIds) {
            DVD currentDVD = dvdLib.getDVD(currentId);
            io.writeString(currentDVD.toStringNoId());
        }
    }

    private void listDVDsByDirectorName() {
        ArrayList<Integer> dvdIds = dvdLib.getAllDvdIDs();
        String directorName = io.readString("Please enter the Director's Name for DVDs you would like to find: ");
        dvdIds = dvdLib.getDVDsByDirectorName(directorName);
        for (Integer currentId : dvdIds) {
            DVD currentDVD = dvdLib.getDVD(currentId);
            io.writeString(currentDVD.toStringNoId());
        }
    }

    private void listDVDsByFormat() {
        ArrayList<Integer> dvdIds = dvdLib.getAllDvdIDs();
        String format = io.readString("Please enter the format of the DVDs you would like to find: ");
        dvdIds = dvdLib.getDVDsByFormat(format);
        for (Integer currentId : dvdIds) {
            DVD currentDVD = dvdLib.getDVD(currentId);
            io.writeString(currentDVD.toStringNoId());
        }
    }

    private void listDVDsByStudio() {
        ArrayList<Integer> dvdIds = dvdLib.getAllDvdIDs();
        String studio = io.readString("Please enter the production studio of the DVDs you would like to find: ");
        dvdIds = dvdLib.getDVDsByStudio(studio);
        for (Integer currentId : dvdIds) {
            DVD currentDVD = dvdLib.getDVD(currentId);
            io.writeString(currentDVD.toStringNoId());
        }
    }

    private void listDVDsByUserRating() {
        ArrayList<Integer> dvdIds = dvdLib.getAllDvdIDs();
        String userRating = io.readString("Please enter the User Rating of the DVDs you would like to find: ");
        dvdIds = dvdLib.getDVDsByUserRating(userRating);
        for (Integer currentId : dvdIds) {
            DVD currentDVD = dvdLib.getDVD(currentId);
            io.writeString(currentDVD.toStringNoId());
        }
    }

}
