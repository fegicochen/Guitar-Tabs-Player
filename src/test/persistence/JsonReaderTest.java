package persistence;

import model.GuitarNote;
import model.Tablature;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Tablature tab = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyTablature() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyTab.json");
        try {
            Tablature tab = reader.read();
            assertEquals("Song", tab.getName());
            assertEquals(0, tab.numNotes());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralTablature() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralTab.json");
        try {
            Tablature notes = reader.read();
            assertEquals("Song", notes.getName());
            List<GuitarNote> tab = notes.getNotes();
            assertEquals(3, tab.size());
            checkGuitarNote("D", 50, tab.get(0));
            checkGuitarNote("Eb", 51, tab.get(1));
            checkGuitarNote("E", 52, tab.get(2));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}