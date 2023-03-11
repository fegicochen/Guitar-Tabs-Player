package model;

import jm.music.data.Note;
import org.json.JSONObject;
import persistence.Writable;

public class GuitarNote extends Note implements Writable  {
    private String name;

    public GuitarNote(String name) {
        this.name = name;
    }

    public String getGuitarNote() {
        return name;
    }

    public int getNotePitch() {
        return this.getPitch();
    }

    @Override
    public void setPitch(int i) {
        super.setPitch(i);
        this.name = getName();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("pitch", this.getPitch());
        return json;
    }
}
