package ui;

import jm.music.data.Note;
import model.Tablature;


import java.util.Scanner;

// Guitar Tabs application
// Code used from TellerApp in AccountNotRobust
public class TabsApp {
    private Tablature tabs;
    private Scanner input;

    // EFFECTS: runs the guitar tabs application
    public TabsApp() {
        runTabs();
    }

    private void runTabs() {
        boolean keepGoing = true;
        String command;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nExiting Tabs!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        switch (command) {
            case "a":
                addNotes();
                break;
            case "r":
                removeNotes();
                break;
            case "p":
                playNotes();
                break;
            default:
                System.out.println("Selection not valid...");
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes tabs
    private void init() {
        tabs = new Tablature();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add note");
        System.out.println("\tr -> remove note");
        System.out.println("\tp -> play tabs");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: adds a note
    private void addNotes() {
        System.out.print("Enter note to add: ");
        int note = input.nextInt();
        Note newNote = new Note();
        newNote.setPitch(note);
        tabs.addNote(newNote);
        printNotes();
    }

    // MODIFIES: this
    // EFFECTS: removes last note
    private void removeNotes() {
        tabs.removeNote();
        printNotes();
    }

    // MODIFIES: this
    // EFFECTS: plays the notes in the tabs
    private void playNotes() {
        tabs.playNotes();
    }


    // EFFECTS: prints tabs to the screen
    private void printNotes() {
        System.out.println("Tab:");
        for (Note note : tabs.getTabs()) {
            System.out.println(note);
        }
    }

}

