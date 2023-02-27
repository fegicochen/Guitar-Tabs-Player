package model;

import jm.JMC;
import jm.music.data.Note;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

// Tests for Tablature Class
class TablatureTest {
    private Tablature testTabs;
    private Note e0;
    private Note e1;
    private Note e2;


    @BeforeEach
    void runBefore() {
        testTabs = new Tablature();
        e0 = new Note();
        e0.setPitch(JMC.e0);
        e1 = new Note();
        e1.setPitch(JMC.e1);
        e2 = new Note();
        e2.setPitch(JMC.e2);
    }

    @Test
    void testConstructor() {
        assertEquals(0, testTabs.getTabs().size());
    }

    @Test
    void testGetSpeed() {
        assertEquals(500, testTabs.getSpeed());
    }


    @Test
    void testGetNote() {
        testTabs.addNote(e0);
        testTabs.addNote(e1);
        assertEquals(e1, testTabs.getNote(1));
        assertEquals(e0, testTabs.getNote(0));
    }


    @Test
    void testAddNote() {
        testTabs.addNote(e1);
        assertEquals(e1, testTabs.getTabs().get(0));
        assertEquals(1, testTabs.getTabs().size());
    }

    @Test
    void testAddNoteMultiple() {
        testTabs.addNote(e1);
        testTabs.addNote(e2);
        assertEquals(e1, testTabs.getTabs().get(0));
        assertEquals(e2, testTabs.getTabs().get(1));
        assertEquals(2, testTabs.getTabs().size());
    }

    @Test
    void testRemoveNote() {
        testTabs.addNote(e1);
        testTabs.addNote(e2);
        testTabs.removeNote();
        assertEquals(e1, testTabs.getTabs().get(0));
        assertEquals(1, testTabs.getTabs().size());

    }

    @Test
    void testRemoveNoteMultiple() {
        testTabs.addNote(e0);
        testTabs.addNote(e1);
        testTabs.addNote(e2);
        testTabs.removeNote();
        testTabs.removeNote();
        assertEquals(e0, testTabs.getTabs().get(0));
        assertEquals(1, testTabs.getTabs().size());
        testTabs.removeNote();
        assertEquals(0, testTabs.getTabs().size());
    }

@Test
    void testPlayNotes() throws RuntimeException {
    testTabs.addNote(e0);
    testTabs.addNote(e1);
    testTabs.playNotes();
    try {
        Thread.sleep(500);
    } catch (InterruptedException e) {
        throw new RuntimeException(e);
    }
    assertEquals(2, testTabs.getTabs().size());
}

}