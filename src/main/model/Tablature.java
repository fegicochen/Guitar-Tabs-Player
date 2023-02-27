package model;

import java.util.ArrayList;
import java.util.List;

// Represents a guitar tablature having tabs and speed
public class Tablature {
    private static final int speed = 1; // speed to play the notes
    private final List<String> tabs; // guitar tablature with notes to be play



    /*
     * EFFECTS: creates a guitar tablature
     */
    public Tablature() {
        this.tabs = new ArrayList<>();
    }

    public List<String> getTabs() {
        return tabs;
    }

    public int getSpeed() {
        return speed;
    }

    public String getNote(Integer integer) {
        return this.tabs.get(integer);
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds a note to the end of the guitar tablature
     */
    public void addNote(String note) {
        tabs.add(note);
    }


    /*
     * REQUIRES: tabs has at least one element
     * MODIFIES: this
     * EFFECTS: removes a note at the end of the guitar tablature
     */
    public void removeNote() {
        tabs.remove((tabs.size() - 1));
    }


    /*
     * EFFECTS: plays the notes in the tablature
     */
    public void playNotes() {
        for (String tab : tabs) {
            System.out.println(tab);
        }
    }


}
