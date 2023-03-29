package ui;

import java.io.FileNotFoundException;

// Runs TabsApp
public class Main {
    public static void main(String[] args) {
        try {
            new TabsApp();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
    }
}
