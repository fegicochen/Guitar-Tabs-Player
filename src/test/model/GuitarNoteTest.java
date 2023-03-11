package model;
import jm.JMC;
import jm.music.data.Note;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GuitarNoteTest {
    private GuitarNote gn;

    @BeforeEach
    void runBefore() {
        gn = new GuitarNote("note");
    }

    @Test
    void testGetGuitarNote() {
        assertEquals("note", gn.getGuitarNote());
    }
}