package ui;

import jm.music.data.Note;
import model.GuitarNote;
import model.Tablature;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// Guitar Tabs application
// Code used from TellerApp in AccountNotRobust
public class TabsApp {
    private static final String JSON_STORE = "./data/tablature.json";
    private Tablature tabs;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: constructs tabs and runs application
    public TabsApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        tabs = new Tablature("Fegico's Tabs");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runTabs();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runTabs() {
        boolean keepGoing = true;
        String command;
        input = new Scanner(System.in);

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
            case "v":
                printNotes();
                break;
            case "a":
                addNotes();
                break;
            case "r":
                removeNotes();
                break;
            case "p":
                playNotes();
                break;
            case "s":
                saveTabs();
                break;
            case "l":
                loadTabs();
                break;
            default:
                System.out.println("Selection not valid...");
                break;
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes tabs
    private void init() {
        tabs = new Tablature("Song");
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tv -> print tabs");
        System.out.println("\ta -> add note");
        System.out.println("\tr -> remove note");
        System.out.println("\tp -> play tabs");
        System.out.println("\ts -> save tabs to file");
        System.out.println("\tl -> load tabs from file");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: adds a note
    private void addNotes() {
        System.out.print("Enter note to add: ");
        int note = input.nextInt();
        GuitarNote newNote = new GuitarNote("note");
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
        for (Note note : tabs.getNotes()) {
            System.out.println(note);
        }
    }

    // EFFECTS: saves the tabs to file
    private void saveTabs() {
        try {
            jsonWriter.open();
            jsonWriter.write(tabs);
            jsonWriter.close();
            System.out.println("Saved " + tabs.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads tabs from file
    private void loadTabs() {
        try {
            tabs = jsonReader.read();
            System.out.println("Loaded " + tabs.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }

}

