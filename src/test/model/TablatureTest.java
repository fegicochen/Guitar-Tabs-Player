package model;

import jm.JMC;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Tests for Tablature Class
class TablatureTest {
    private Tablature testTabs;
    private GuitarNote e0;
    private GuitarNote e1;
    private GuitarNote e2;


    @BeforeEach
    void runBefore() {
        testTabs = new Tablature("Song");
        e0 = new GuitarNote("e0");
        e0.setPitch(JMC.e0);
        e1 = new GuitarNote("e1");
        e1.setPitch(JMC.e1);
        e2 = new GuitarNote("e2");
        e2.setPitch(JMC.e2);
    }

    @Test
    void testConstructor() {
        assertEquals(0, testTabs.getNotes().size());
    }

    @Test
    void testGetSpeed() {
        assertEquals(500, testTabs.getSpeed());
    }

    @Test
    void testSetSpeed() {
        testTabs.setSpeed(10);
        assertEquals(10, testTabs.getSpeed());
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
        assertEquals(e1, testTabs.getNotes().get(0));
        assertEquals(1, testTabs.getNotes().size());
    }

    @Test
    void testAddNoteMultiple() {
        testTabs.addNote(e1);
        testTabs.addNote(e2);
        assertEquals(e1, testTabs.getNotes().get(0));
        assertEquals(e2, testTabs.getNotes().get(1));
        assertEquals(2, testTabs.getNotes().size());
    }

    @Test
    void testRemoveNote() {
        testTabs.addNote(e1);
        testTabs.addNote(e2);
        testTabs.removeNote();
        assertEquals(e1, testTabs.getNotes().get(0));
        assertEquals(1, testTabs.getNotes().size());

    }

    @Test
    void testRemoveNoteMultiple() {
        testTabs.addNote(e0);
        testTabs.addNote(e1);
        testTabs.addNote(e2);
        testTabs.removeNote();
        testTabs.removeNote();
        assertEquals(e0, testTabs.getNotes().get(0));
        assertEquals(1, testTabs.getNotes().size());
        testTabs.removeNote();
        assertEquals(0, testTabs.getNotes().size());
    }

    @Test
    void testPlayNotes() {
        // no exceptions thrown
        testTabs.addNote(e0);
        testTabs.addNote(e1);
        testTabs.setSpeed(100);
        try {
            testTabs.playNotes();
        } catch (RuntimeException e) {
            fail("RuntimeException should not have been thrown");
        }
        assertEquals(2, testTabs.getNotes().size());
    }

    @Test
    void testPlayNotesException() {
        testTabs.addNote(e0);
        testTabs.addNote(e1);
        testTabs.setSpeed(-100);
        try {
            Thread.currentThread().interrupt();
            testTabs.playNotes();
            Assertions.assertTrue(Thread.interrupted());
        } catch (RuntimeException e) {
            // good
        }
    }

}