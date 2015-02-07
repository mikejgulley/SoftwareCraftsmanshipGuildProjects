package com.swcguild.dvdlibrary.dao;

import com.swcguild.dvdlibrary.dto.DVD;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 *
 * @author Michael J. Gulley
 */
// This is where we will store the DVDs
// This is our DAO - Data Access Object
public class DVDLibrary {

    private static final String DVD_LIBRARY_FILE = "dvdlibrary.txt";
    private static final String DELIMITER = "::";
    private static final String ID_COUNTER = "counter.txt";
    // TODO -- is this how we want to handle notes ???
    // public static final String USER_NOTES = "notes.txt";
    private int counter;

    private HashMap<Integer, DVD> dvdMap = new HashMap<>();
    Random rand = new Random();

//    public int idGen() {
////        int id = 0;
//        int id = rand.nextInt(1000);
//        return id;
//    }
    public DVD addDVD(int id, DVD dvd) {
        // id = rand.nextInt(1000);

        return dvdMap.put(id, dvd);
    }

    public DVD getDVD(int id) {
        return dvdMap.get(id);
    }

    public DVD removeDVD(int id) {
        return dvdMap.remove(id);
    }

    public ArrayList<Integer> getAllDvdIDs() {
        Set<Integer> dvdKeySet = dvdMap.keySet();
        ArrayList<Integer> dvdKeyList = new ArrayList<Integer>(dvdKeySet);

        return dvdKeyList;
    }

    public ArrayList<Integer> getDVDsByTitle(String title) {
        Set<Integer> dvdKeySet = dvdMap.keySet();
        ArrayList<Integer> dvdList = new ArrayList<Integer>();
        Collection<DVD> dvdValues = dvdMap.values();

        for (DVD currentMovie : dvdValues) {
            if (currentMovie.getTitle().contains(title)) {
                dvdList.add(currentMovie.getId());
            }
        }
        return dvdList;
    }

    public ArrayList<Integer> getDVDsByReleaseDate(String releaseDate) {
        Set<Integer> dvdKeySet = dvdMap.keySet();
        ArrayList<Integer> dvdList = new ArrayList<Integer>();
        Collection<DVD> dvdValues = dvdMap.values();

        for (DVD currentMovie : dvdValues) {
            if (currentMovie.getReleaseDate().equalsIgnoreCase(releaseDate)) {
                dvdList.add(currentMovie.getId());
            }
        }
        return dvdList;
    }

    public ArrayList<Integer> getDVDsByFormat(String format) {
        Set<Integer> dvdKeySet = dvdMap.keySet();
        ArrayList<Integer> dvdList = new ArrayList<Integer>();
        Collection<DVD> dvdValues = dvdMap.values();

        for (DVD currentMovie : dvdValues) {
            if (currentMovie.getFormat().equalsIgnoreCase(format)) {
                dvdList.add(currentMovie.getId());
            }
        }
        return dvdList;
    }

    public ArrayList<Integer> getDVDsByMpaaRating(String mpaaRating) {
        Set<Integer> dvdKeySet = dvdMap.keySet();
        ArrayList<Integer> dvdList = new ArrayList<Integer>();
        Collection<DVD> dvdValues = dvdMap.values();

        for (DVD currentMovie : dvdValues) {
            if (currentMovie.getMpaaRating().equalsIgnoreCase(mpaaRating)) {
                dvdList.add(currentMovie.getId());
            }
        }
        return dvdList;
    }

    public ArrayList<Integer> getDVDsByDirectorName(String directorName) {
        Set<Integer> dvdKeySet = dvdMap.keySet();
        ArrayList<Integer> dvdList = new ArrayList<Integer>();
        Collection<DVD> dvdValues = dvdMap.values();

        for (DVD currentMovie : dvdValues) {
            if (currentMovie.getDirectorName().contains(directorName)) {
                dvdList.add(currentMovie.getId());
            }
        }
        return dvdList;
    }

    public ArrayList<Integer> getDVDsByStudio(String studio) {
        Set<Integer> dvdKeySet = dvdMap.keySet();
        ArrayList<Integer> dvdList = new ArrayList<Integer>();
        Collection<DVD> dvdValues = dvdMap.values();

        for (DVD currentMovie : dvdValues) {
            if (currentMovie.getStudio().contains(studio)) {
                dvdList.add(currentMovie.getId());
            }
        }
        return dvdList;
    }

    public ArrayList<Integer> getDVDsByUserRating(String userRating) {
        ArrayList<Integer> dvdList = new ArrayList<Integer>();
        Collection<DVD> dvdValues = dvdMap.values();

        for (DVD currentMovie : dvdValues) {
            if (currentMovie.getUserRating().equalsIgnoreCase(userRating)) {
                dvdList.add(currentMovie.getId());
            }
        }
        return dvdList;
    }

    public void loadLibrary() throws FileNotFoundException {
        Scanner sc = new Scanner(new BufferedReader(new FileReader(DVD_LIBRARY_FILE)));
        String currentLine;
        String[] currentTokens;

        while (sc.hasNextLine()) {
            currentLine = sc.nextLine();
            currentTokens = currentLine.split(DELIMITER);
            DVD currentDVD = new DVD();
            currentDVD.setId(Integer.parseInt(currentTokens[0]));
            currentDVD.setTitle(currentTokens[1]);
            currentDVD.setReleaseDate(currentTokens[2]);
            currentDVD.setMpaaRating(currentTokens[3]);
            currentDVD.setFormat(currentTokens[4]);
            currentDVD.setDirectorName(currentTokens[5]);
            currentDVD.setStudio(currentTokens[6]);
            currentDVD.setUserRating(currentTokens[7]);
            currentDVD.setUserNote(currentTokens[8]);
            // TODO no idea how to set notes in the dvdlibrary.txt file ... should there be a separate file that contains only notes ???
            // currentDVD.setUserNotes(null);

            dvdMap.put(currentDVD.getId(), currentDVD);
        }
    }

    public void writeLibrary() throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(DVD_LIBRARY_FILE)));
        ArrayList<Integer> dvdIds = this.getAllDvdIDs();

        for (Integer currentMovieId : dvdIds) {
            DVD currentDVD = this.getDVD(currentMovieId);

            out.println(currentDVD.getId() + DELIMITER
                    + currentDVD.getTitle() + DELIMITER
                    + currentDVD.getReleaseDate() + DELIMITER
                    + currentDVD.getMpaaRating() + DELIMITER
                    + currentDVD.getFormat() + DELIMITER
                    + currentDVD.getDirectorName() + DELIMITER
                    + currentDVD.getStudio() + DELIMITER
                    + currentDVD.getUserRating() + DELIMITER
                    + currentDVD.getUserNote());

            out.flush();
        }
        out.close();
    }
}
