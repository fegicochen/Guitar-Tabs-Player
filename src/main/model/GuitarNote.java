package model;

import jm.music.data.Note;
import org.json.JSONObject;
import persistence.Writable;

// Represents guitar notes with a name
public class GuitarNote extends Note implements Writable  {
    private String name;

    // EFFECTS: creates a guitar note
    public GuitarNote(String name) {
        this.name = name;
    }

    public String getGuitarNote() {
        return name;
    }

    public int getNotePitch() {
        return this.getPitch();
    }


    //EFFECTS: sets the pitch of a guitar note and sets the name to the actual note
    @Override
    public void setPitch(int i) {
        super.setPitch(i);
        this.name = getName();
    }

    //EFFECTS: puts the name and pitch to the JSON file and returns the JSON object
    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("pitch", this.getPitch());
        return json;
    }
}
