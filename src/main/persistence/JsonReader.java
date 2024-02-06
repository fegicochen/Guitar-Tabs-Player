package persistence;

import model.Event;
import model.EventLog;
import model.GuitarNote;
import model.Tablature;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads tablature from JSON data stored in file
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads tablature from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Tablature read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseTablature(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        EventLog.getInstance().logEvent(new Event("Loaded notes from data."));
        return contentBuilder.toString();

    }

    // EFFECTS: parses tablature from JSON object and returns it
    private Tablature parseTablature(JSONObject jsonObject) {
        String song = jsonObject.getString("name");
        Tablature tab = new Tablature(song);
        addGuitarNotes(tab, jsonObject);
        return tab;
    }

    // MODIFIES: tablature
    // EFFECTS: parses notes from JSON object and adds them to tablature
    private void addGuitarNotes(Tablature tab, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("notes");
        for (Object json : jsonArray) {
            JSONObject nextGuitarNote = (JSONObject) json;
            addGuitarNote(tab, nextGuitarNote);
        }
    }

    // MODIFIES: tablature
    // EFFECTS: parses GuitarNote from JSON object and adds it to tablature
    private void addGuitarNote(Tablature tab, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int note = jsonObject.getInt("pitch");
        GuitarNote guitarNote = new GuitarNote(name);
        guitarNote.setPitch(note);
        tab.addNote(guitarNote);
    }
}
