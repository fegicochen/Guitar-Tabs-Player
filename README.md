# Guitar Tabs Player

## Description of Project Structure

This application will take in **guitar notes**
and play them by turning them into guitar sounds of those notes.
People who want to listen to what a guitar song sounds
like without playing it, and I will use it. This project
is of interest to me because I enjoy playing guitar,
and I think creating this application will make it *easier*
to create and listen to songs for guitar.

## User Stories
As a user, I want to be able to add multiple notes to the tabs.

As a user, I want to be able to remove notes from the tabs.

As a user, I want to be able to play the notes in the tabs to create sounds.

As a user, I want to be able to view the notes in the tabs.

As a user, I want to be able to exit the tabs.

As a user, I want to be able to save the tabs to file. (if I so choose)

As a user, I want to be able to load the tabs from file. (if I so choose)

# Instructions for Grader
- You can play the tabs by clicking the Play Notes button

- You can add a GuitarNote to the Tablature by clicking the Add Note button and entering a pitch

- You can remove a GuitarNote from the Tablature by clicking the Remove Note button

- You can locate my visual component on the right-hand side which is a pitch to note reference sheet

- You can save the state of my application by clicking the Save Notes button

- You can reload the state of my application by clicking the Load Notes button

# Phase 4: Task 2

Thu Apr 13 15:32:56 PDT 2023
Added a note to tabs.
Thu Apr 13 15:32:58 PDT 2023
Added a note to tabs.
Thu Apr 13 15:32:59 PDT 2023
Added a note to tabs.
Thu Apr 13 15:33:02 PDT 2023
Added a note to tabs.
Thu Apr 13 15:33:04 PDT 2023
Removed a note from tabs.
Thu Apr 13 15:33:05 PDT 2023
Removed a note from tabs.
Thu Apr 13 15:33:08 PDT 2023
Played notes in tabs.
Thu Apr 13 15:33:11 PDT 2023
Saved notes to data.
Thu Apr 13 15:33:12 PDT 2023
Removed a note from tabs.
Thu Apr 13 15:33:13 PDT 2023
Loaded notes from data.
Thu Apr 13 15:33:13 PDT 2023
Added a note to tabs.
Thu Apr 13 15:33:13 PDT 2023
Added a note to tabs.

# Phase 4: Task 3
If I had more time to work on the project, 
I would try to reduce coupling by abstracting duplicated code into methods.
Specifically, there are some code clones between the GraphicalUI and TabsApp,
so I can pull them into methods. The advantage of this refactoring is that
there is less duplicated code, and it improves readability. One disadvantage of
refactoring is that it takes time.