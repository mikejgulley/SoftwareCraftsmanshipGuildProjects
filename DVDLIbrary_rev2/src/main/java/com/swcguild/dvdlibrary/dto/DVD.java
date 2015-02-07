package com.swcguild.dvdlibrary.dto;

import java.util.Objects;

/**
 *
 * @author Michael J. Gulley
 */
// This is where we will create the DVDs
// This is our DTO - Data Transfer Object
public class DVD {

    private int id;
    private String title;
    private String releaseDate;
    private String mpaaRating;
    private String format;
    private String directorName;
    private String studio;
    private String userRating; // 1 to 4 stars --- 0 can mean that they haven't yet watched it, or don't want to rate it yet
    // private ArrayList<String> userNotes;
    private String userNote;

    public DVD() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getMpaaRating() {
        return mpaaRating;
    }

    public void setMpaaRating(String mpaaRating) {
        this.mpaaRating = mpaaRating;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }

    public String getUserRating() {
        return userRating;
    }

    public void setUserRating(String userRating) {
        this.userRating = userRating;
    }

//    public ArrayList<String> getUserNotes() {
//        return userNotes;
//    }
//
//    public void setUserNotes(ArrayList<String> userNotes) {
//        this.userNotes = userNotes;
//    }
    public String getUserNote() {
        return userNote;
    }

    public void setUserNote(String userNote) {
        this.userNote = userNote;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.title);
        hash = 29 * hash + Objects.hashCode(this.releaseDate);
        hash = 29 * hash + Objects.hashCode(this.mpaaRating);
        hash = 29 * hash + Objects.hashCode(this.format);
        hash = 29 * hash + Objects.hashCode(this.directorName);
        hash = 29 * hash + Objects.hashCode(this.studio);
        hash = 29 * hash + Objects.hashCode(this.userRating);
        hash = 29 * hash + Objects.hashCode(this.userNote);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DVD other = (DVD) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.releaseDate, other.releaseDate)) {
            return false;
        }
        if (!Objects.equals(this.mpaaRating, other.mpaaRating)) {
            return false;
        }
        if (!Objects.equals(this.format, other.format)) {
            return false;
        }
        if (!Objects.equals(this.directorName, other.directorName)) {
            return false;
        }
        if (!Objects.equals(this.studio, other.studio)) {
            return false;
        }
        if (!Objects.equals(this.userRating, other.userRating)) {
            return false;
        }
        if (!Objects.equals(this.userNote, other.userNote)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "\nID: " + id + "\nTitle: " + title + "\nRelease Date: "
                + releaseDate + "\nMPAA Rating: " + mpaaRating + "\nFormat: "
                + format + "\nDirector Name: " + directorName + "\nStudio: "
                + studio + "\nUser Rating: " + userRating + "\nUser Note: "
                + userNote;
    }

    public String toStringNoId() {
        return "\nTitle: " + title + "\nRelease Date: "
                + releaseDate + "\nMPAA Rating: " + mpaaRating + "\nFormat: "
                + format + "\nDirector Name: " + directorName + "\nStudio: "
                + studio + "\nUser Rating: " + userRating + "\nUser Note: "
                + userNote;
    }

}
