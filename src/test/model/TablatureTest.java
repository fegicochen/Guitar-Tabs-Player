package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class TablatureTest {
    private Tablature testTabs;
    private String e0;
    private String e1;
    private String e2;


    @BeforeEach
    void runBefore() {
        testTabs = new Tablature();
        e0 = "E0";
        e1 = "E1";
        e2 = "E2";

    }

    @Test
    void testConstructor() {
        assertEquals(0, testTabs.getTabs().size());
    }

    @Test
    void testAddNote() {
        testTabs.addNote(e1);
        assertEquals(e1,testTabs.getTabs().get(0));
        assertEquals(1, testTabs.getTabs().size());
    }

    @Test
    void testAddNoteMultiple() {
        testTabs.addNote(e1);
        testTabs.addNote(e2);
        assertEquals(e1,testTabs.getTabs().get(0));
        assertEquals(e2,testTabs.getTabs().get(1));
        assertEquals(2, testTabs.getTabs().size());
    }

    @Test
    void testRemoveNote() {
        testTabs.addNote(e1);
        testTabs.addNote(e2);
        testTabs.removeNote();
        assertEquals(e1,testTabs.getTabs().get(0));
        assertEquals(1, testTabs.getTabs().size());

    }

    @Test
    void testRemoveNoteMultiple() {
        testTabs.addNote(e0);
        testTabs.addNote(e1);
        testTabs.addNote(e2);
        testTabs.removeNote();
        testTabs.removeNote();
        assertEquals(e0,testTabs.getTabs().get(0));
        assertEquals(1, testTabs.getTabs().size());
        testTabs.removeNote();
        assertEquals(0, testTabs.getTabs().size());
    }

}