package ui;

import model.GuitarNote;
import model.Tablature;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

public class GraphicalUI extends JFrame {
    private static final String JSON_STORE = "./data/tablature.json";
    private static final int WIDTH = 1000;
    public static final int HEIGHT = 800;
    private JDesktopPane desktop;
    private JInternalFrame noteFrame;
    private JInternalFrame tabFrame;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private Tablature tabs;
    static JList<String> tabList;
    private DefaultListModel<String> dlm;


    public GraphicalUI() {
        setup();

        noteFrame.setLayout(new BorderLayout());
        tabFrame.setLayout(new BorderLayout());

        tabFrame.setSize(500, 500);
        tabFrame.setLocation(300, 200);


        setContentPane(desktop);
        setTitle("Guitar Tabs Player");
        setSize(WIDTH, HEIGHT);

        addButtonPanel();
        addListPanel();


        noteFrame.pack();
        noteFrame.setVisible(true);
        desktop.add(noteFrame);
        tabFrame.pack();
        tabFrame.setVisible(true);
        desktop.add(tabFrame);

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        centreOnScreen();
        setVisible(true);
    }

    private void setup() {
        tabs = new Tablature("MY TAB");
        noteFrame = new JInternalFrame("Actions");
        tabFrame = new JInternalFrame("Tab");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        desktop = new JDesktopPane();
        dlm = new DefaultListModel<String>();
    }

    public static void main(String[] args) {
        new GraphicalUI();
    }


    /**
     * Helper to centre main application window on desktop
     */
    private void centreOnScreen() {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setLocation((width - getWidth()) / 2, (height - getHeight()) / 2);
    }


    private void addListPanel() {
        JPanel p = new JPanel();
        tabList = new JList<>(dlm);
        tabList.setSelectedIndex(2);
        p.add(tabList);
        tabFrame.add(p);
        tabFrame.setSize(400, 400);
        tabFrame.show();
    }


    /**
     * Helper to add control buttons.
     */
    private void addButtonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 2));
        buttonPanel.add(new JButton(new PlayNotesAction()));
        buttonPanel.add(new JButton(new AddNoteAction()));
        buttonPanel.add(new JButton(new RemoveNoteAction()));
        buttonPanel.add(new JButton(new SaveNotesAction()));
        buttonPanel.add(new JButton(new LoadNotesAction()));
        noteFrame.add(buttonPanel);
    }

    private class PlayNotesAction extends AbstractAction {

        PlayNotesAction() {
            super("Play Notes");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            tabs.playNotes();
        }
    }

    private class AddNoteAction extends AbstractAction {

        AddNoteAction() {
            super("Add Note");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String note = JOptionPane.showInputDialog(null, "Enter Note",
                    "Enter an Integer from 1 to 140", JOptionPane.QUESTION_MESSAGE);
            int intNote = Integer.parseInt(note);
            GuitarNote newNote = new GuitarNote("note");
            newNote.setPitch(intNote);
            tabs.addNote(newNote);
            dlm.addElement(newNote.getGuitarNote());
        }
    }

    private class RemoveNoteAction extends AbstractAction {

        RemoveNoteAction() {
            super("Remove Note");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            tabs.removeNote();
            dlm.removeElementAt(dlm.size() - 1);
        }
    }

    private class SaveNotesAction extends AbstractAction {
        SaveNotesAction() {
            super("Save Notes");
        }

        @Override
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

    private class LoadNotesAction extends AbstractAction {
        LoadNotesAction() {
            super("Load Notes");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
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
