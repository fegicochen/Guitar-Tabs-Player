package ui;

import jm.JMC;
import jm.music.data.Note;
import jm.util.Play;

public class Music {
    public static void main(String[] args) {
        Note note = new Note();
        note.setPitch(JMC.e4);
        Play.midi(note);
    }
}
