package ui;

import java.io.FileNotFoundException;

// Runs TabsApp
public class Main {
    // EFFECTS: runs TabsApp(), return string if file not found
    public static void main(String[] args) {
        try {
            new TabsApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
