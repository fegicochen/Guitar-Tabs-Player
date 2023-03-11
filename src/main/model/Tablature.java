package model;

import jm.music.data.Note;
import jm.util.Play;

import java.util.ArrayList;
import java.util.List;

// Represents a guitar tablature having tabs and speed
public class Tablature {
    private String name;
    private String artist;
    private int speed = 500; // speed to play the notes in milliseconds
    private final List<Note> tabs; // guitar tablature with notes to be play


    /*
     * EFFECTS: creates a guitar tablature
     */
    public Tablature(String name, String artist) {
        this.tabs = new ArrayList<>();
        this.name = name;
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }

    public List<Note> getTabs() {
        return tabs;
    }

    public int getSpeed() {
        return speed;
    }

    /*
     * EFFECTS: sets the speed of played notes to num
     */
    public void setSpeed(int num) {
        this.speed = num;
    }

    /*
     * REQUIRES: the number of element has a note
     * EFFECTS: gets the note of the number element in tabs
     */
    public Note getNote(Integer integer) {
        return this.tabs.get(integer);
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds a note to the end of the guitar tablature
     */
    public void addNote(Note note) {
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


        public void playNotes() {
        for (Note note : tabs) {
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Play.midi(note);
        }
    }


}
