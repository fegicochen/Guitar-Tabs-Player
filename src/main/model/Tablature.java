package model;

import jm.music.data.Note;
import jm.util.Play;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a guitar tablature having tabs and speed
public class Tablature implements Writable {
    private String name;
    private int speed = 500; // speed to play the notes in milliseconds
    private final List<GuitarNote> notes; // guitar tablature with notes to be play


    /*
     * EFFECTS: creates a guitar tablature
     */
    public Tablature(String name) {
        this.notes = new ArrayList<>();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int numNotes() {
        return notes.size();
    }


    public List<GuitarNote> getNotes() {
        return notes;
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
        return this.notes.get(integer);
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds a note to the end of the guitar tablature
     */
    public void addNote(GuitarNote note) {
        notes.add(note);
    }


    /*
     * REQUIRES: tabs has at least one element
     * MODIFIES: this
     * EFFECTS: removes a note at the end of the guitar tablature
     */
    public void removeNote() {
        notes.remove((notes.size() - 1));
    }

    /*
     * EFFECTS: plays all notes in the guitar tablature
     */
    public void playNotes() {
        for (GuitarNote note : notes) {
            try {
                Thread.sleep(speed);
            } catch (Exception e) {
                throw new RuntimeException();
            }
            Play.midi(note);
        }
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("notes", notesToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray notesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (GuitarNote note : notes) {
            jsonArray.put(note.toJson());
        }

        return jsonArray;
    }
}
