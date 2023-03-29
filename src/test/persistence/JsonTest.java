package persistence;

import model.GuitarNote;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Tests for Json
public class JsonTest {
    protected void checkGuitarNote(String name, int pitch, GuitarNote guitarNote) {
        assertEquals(name, guitarNote.getName());
        assertEquals(pitch, guitarNote.getNotePitch());

    }
}
