package ui;

import model.EventLog;
import model.GuitarNote;
import model.Tablature;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

// Represents a graphical ui for TabsApp
public class GraphicalUI extends JFrame {
    public static final int HEIGHT = 720;
    private static final String JSON_STORE = "./data/tablature.json";
    private static final int WIDTH = 1280;
    static JList<String> tabList;
    private JDesktopPane desktop;
    private JInternalFrame noteFrame;
    private JInternalFrame tabFrame;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Tablature tabs;
    private DefaultListModel<String> dlm;
    private JLabel image;

    // MODIFIES: this
    //EFFECTS: creates a graphical UI (constructor)
    public GraphicalUI() {
        setup();


        noteFrame.setLayout(new BorderLayout());
        tabFrame.setLayout(new BorderLayout());

        tabFrame.setLocation(300, 100);


        setContentPane(desktop);
        setTitle("Guitar Tabs Player");
        setSize(WIDTH, HEIGHT);

        addImage();
        addButtonPanel();
        addListPanel();

        tabFrame.show();

        noteFrame.pack();
        noteFrame.setVisible(true);
        desktop.add(noteFrame);
        tabFrame.pack();
        tabFrame.setVisible(true);
        desktop.add(tabFrame);
        desktop.add(image);


        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        centreOnScreen();
        setVisible(true);

        tabFrame.setSize(200, 520);


    }


    public static void main(String[] args) {
        new GraphicalUI();
    }

    //EFFECTS: helper to set up the fields
    private void setup() {
        tabs = new Tablature("MY TAB");
        noteFrame = new JInternalFrame("Actions", false, false, false, false);
        tabFrame = new JInternalFrame("Tab", true, false, false, false);
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        desktop = new JDesktopPane();
        dlm = new DefaultListModel<String>();
    }

    // MODIFIES: this
    //EFFECTS: centre main application window on desktop
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }

    // MODIFIES: this
    //EFFECTS: adds the pitch to note reference sheet image to the desktop window
    private void addImage() {
        image = new JLabel();
        ImageIcon imageIcon = new ImageIcon("data/midi.png");
        image.setIcon(imageIcon);
        image.setBounds(500, -110, 750, 500);
    }

    // MODIFIES: this
    //EFFECTS: adds the tablature list to the desktop window
    private void addListPanel() {
        JPanel p = new JPanel();
        tabList = new JList<>(dlm);
        p.add(tabList);
        tabFrame.add(p);
    }

    // MODIFIES: this
    //EFFECTS: adds the button panel to the desktop window
    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 2));
        buttonPanel.add(new JButton(new PlayNotesAction()));
        buttonPanel.add(new JButton(new AddNoteAction()));
        buttonPanel.add(new JButton(new RemoveNoteAction()));
        buttonPanel.add(new JButton(new SaveNotesAction()));
        buttonPanel.add(new JButton(new LoadNotesAction()));
        buttonPanel.add(new JButton(new QuitAction()));
        noteFrame.add(buttonPanel);
    }

    // Represents the Play Notes button
    private class PlayNotesAction extends AbstractAction {

        // MODIFIES: this
        //EFFECTS: sets the button name
        PlayNotesAction() {
            super("Play Notes");
        }

        @Override
        // MODIFIES: this
        //EFFECTS: plays the notes when you press the button
        public void actionPerformed(ActionEvent e) {
            tabs.playNotes();
        }
    }


    // Represents the Add Notes button
    private class AddNoteAction extends AbstractAction {
        // MODIFIES: this
        //EFFECTS: sets the button name
        AddNoteAction() {
            super("Add Note");
        }

        @Override
        // MODIFIES: this
        //EFFECTS: prompts an input dialog for you to insert a note to add to the tabs and then adds it
        public void actionPerformed(ActionEvent e) {
            String note = JOptionPane.showInputDialog(null, "Enter an Integer from 0 to 127",
                    "Enter Note", JOptionPane.QUESTION_MESSAGE);
            int intNote = Integer.parseInt(note);
            GuitarNote newNote = new GuitarNote("note");
            newNote.setPitch(intNote);
            tabs.addNote(newNote);
            dlm.addElement(newNote.getGuitarNote());
        }
    }

    // Represents the Remove Notes button
    private class RemoveNoteAction extends AbstractAction {
        // MODIFIES: this
        //EFFECTS: sets the button name
        RemoveNoteAction() {
            super("Remove Note");
        }

        @Override
        // MODIFIES: this
        //EFFECTS: removes the last note in the tabs
        public void actionPerformed(ActionEvent e) {
            tabs.removeNote();
            dlm.removeElementAt(dlm.size() - 1);
        }
    }

    // Represents the Save Notes button
    private class SaveNotesAction extends AbstractAction {
        // MODIFIES: this
        //EFFECTS: sets the button name
        SaveNotesAction() {
            super("Save Notes");
        }

        @Override
        // MODIFIES: this
        //EFFECTS: saves the current tabs for you to load next time
        public void actionPerformed(ActionEvent e) {
            try {
                jsonWriter.open();
                jsonWriter.write(tabs);
                jsonWriter.close();
                System.out.println("Saved " + tabs.getName() + " to " + JSON_STORE);
            } catch (FileNotFoundException exception) {
                System.out.println("Unable to write to file: " + JSON_STORE);
            }
        }
    }


    // Represents the Quit button
    private class QuitAction extends AbstractAction {
        // MODIFIES: this
        //EFFECTS: sets the button name
        QuitAction() {
            super("Quit");
        }

        @Override
        // MODIFIES: this
        //EFFECTS: quits the tabs
        public void actionPerformed(ActionEvent e) {
            for (model.Event event : EventLog.getInstance()) {
                System.out.println(event);
            }
            System.exit(69);
        }
    }


    // Represents the Load Notes button
    private class LoadNotesAction extends AbstractAction {
        // MODIFIES: this
        //EFFECTS: sets the button name
        LoadNotesAction() {
            super("Load Notes");
        }

        @Override
        // MODIFIES: this
        //EFFECTS: loads the previously saved tabs
        public void actionPerformed(ActionEvent e) {
            dlm.removeAllElements();
            try {
                tabs = jsonReader.read();
                System.out.println("Loaded " + tabs.getName() + " from " + JSON_STORE);
            } catch (IOException exception) {
                System.out.println("Unable to read from file: " + JSON_STORE);
            }
            for (GuitarNote g : tabs.getNotes()) {
                dlm.addElement(g.getGuitarNote());
            }
        }
    }


}
