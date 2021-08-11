# CPSC 210 Personal Project

## Flashcard app

This app can be used by students to generate flashcards that test one's knowledge of course material. As a student
myself, I find flashcards to be an effective tool to have when reviewing course material, especially when preparing
for exams. With this app, I hope to provide other students with a handy tool that saves them time and effort from 
hand-writing their own flashcards.

### User Stories

- As a user, I want to be able to create a new flashcard and add it to a collection of cards
- As a user, I want to be able to select a flashcard in the collection and view its description or keyword
- As a user, I want to be able to edit a flashcard's description or keyword
- As a user, I want to be able to delete a flashcard from the collection
- As a user, I want to be able to save my collection of flashcards to file
- As a user, I want to be able to load my existing collection of flashcards from file

### Phase 4: Task 2

I chose to implement a type hierarchy in the dialogs package (which is inside the ui package). Four subclasses 
(AddDialog, DeleteDialog, EditTitleDialog, and LoadDialog) extend the Dialog abstract class and override its 
hideDialog abstract method.

### Phase 4: Task 3

If I had more time to work on this project, I would refactor the FlashcardGUI and FlashcardApp classes
by making separate classes for certain functionalities contained within these UI classes, specifically:
  - A class to represent the menu bar contained within FlashcardGUI
  - A class to represent a manager that processes user actions (e.g. add/delete a flashcard) in FlashcardGUI
    and FlashcardApp
  - A class to represent a loader for dialog windows that process user actions in FlashcardGUI

