# LaTeX Editor

A simple LaTeX editor built in Java with a Swing-based graphical user interface. This editor allows you to create, edit, and save LaTeX documents, with additional features for version control and HTML export.

## Features

*   **Create, Load, and Save Documents:**
    *   Create new LaTeX documents from templates (e.g., article, book, letter).
    *   Load existing `.tex` or `.html` files.
    *   Save your work as a `.tex` or `.html` file.
*   **LaTeX Commands:**
    *   Easily insert common LaTeX elements like chapters, sections, subsections, and lists (itemize, enumerate).
    *   Add tables and figures.
*   **Version Control:**
    *   Enable or disable version tracking for your documents.
    *   Choose between two strategies:
        *   **Volatile:** Keeps only the previous version in memory for quick rollbacks.
        *   **Stable:** Saves each version to a file for persistent version history.
    *   Roll back to a previous version of your document.
*   **HTML Export:**
    *   Save your LaTeX document as an HTML file.

## Getting Started

### Prerequisites

*   Java Development Kit (JDK) 8 or higher.

### Compiling and Running the Project

To compile and run the LaTeX editor, follow these steps:

1.  **Open a terminal or command prompt.**
2.  **Navigate to the `src` directory of the project:**
    ```bash
    cd src
    ```
3.  **Compile the Java source files:**
    ```bash
    javac -d ../bin ./**/*.java
    ```
    *This command compiles all `.java` files and places the resulting `.class` files in a `bin` directory at the root of the project.*
4.  **Run the application:**
    ```bash
    java -cp ../bin view.OpeningWindow
    ```
    *This command runs the main class `OpeningWindow` from the `bin` directory.*

## How to Use

1.  **Launch the application** using the instructions in the "Getting Started" section.
2.  **Choose an option from the opening window:**
    *   **Create New Document:** Select a template to start a new document.
    *   **Open Existing Document:** Load a `.tex` file to continue editing.
    *   **Open HTML Document:** Load an `.html` file.
3.  **Use the menu bar to access various features:**
    *   **File:** Create, open, save, or export your document.
    *   **Commands:** Insert LaTeX elements like chapters, sections, and lists.
    *   **Strategy:** Manage version control for your document.
        *   Enable "Volatile" or "Stable" versioning.
        *   Disable versioning.
        *   Roll back to a previous version.
4.  **Edit your document** in the main text area.
5.  **Save your changes** using the "Save" or "Save file" options in the "File" menu.