package persistence;

import model.GuitarNote;
import model.Tablature;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class JsonWriterTest extends JsonTest {
    //NOTE TO CPSC 210 STUDENTS: the strategy in designing tests for the JsonWriter is to
    //write data to a file and then use the reader to read it back in and check that we
    //read in a copy of what was written out.

    @Test
    void testWriterInvalidFile() {
        try {
            Tablature tab = new Tablature("Song");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyTablature() {
        try {
            Tablature tab = new Tablature("Song");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyTab.json");
            writer.open();
            writer.write(tab);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyTab.json");
            tab = reader.read();
            assertEquals("Song", tab.getName());
            assertEquals(0, tab.numNotes());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralTab() {
        try {
            Tablature tab = new Tablature("Song");
            tab.addNote(new GuitarNote("C"));
            tab.addNote(new GuitarNote("C"));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralTab.json");
            writer.open();
            writer.write(tab);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralTab.json");
            tab = reader.read();
            assertEquals("Song", tab.getName());
            List<GuitarNote> notes = tab.getNotes();
            assertEquals(2, notes.size());
            checkGuitarNote("C", 60, notes.get(0));
            checkGuitarNote("C", 60, notes.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}