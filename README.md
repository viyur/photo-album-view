# Photo Album Application

## A. Project Structure

### `src` (Sources Root)
- **Controller package**: Contains `IController` and `PhotoAlbumController`.
- **Main package**: Home of `PhotoAlbumMain`, the entry point of the application.
- **Model package**: Includes classes like `IModel`, `ISnapshot`, and `IShape`.
- **View package**: Holds the `IView` interface along with `HTMLView` and `SwingView` implementations.

### `resources` (Resources Root)
- Stores static resources like command text files, jar files, and output HTML files.

### `test` (Test Root)
- Contains all unit tests for the application.

## B. Workflow Description

### Initialization
- `PhotoAlbumMain` parses command line arguments such as "-in buildings.txt -v graphical 800 800" or "-in buildings.txt -out myWeb.html -v web" to determine the input file name, view type, and other parameters. It then initializes the corresponding view, controller, and model. Only one `IView`, `IController`, and `IModel` are initialized regardless of the view type.

### Command Parsing and Execution
- An instance of `PhotoAlbumController` reads the designated text file line by line from the resources root. It handles commands in a case-insensitive manner, recognizing and processing directives such as "Shape" or "shape" to instruct the model to create and store shapes.

### Snapshot Management
- Once all commands are processed, the model completes all shape manipulations, including creation, movement, color changes, etc. Meanwhile, all snapshots capturing the states of shapes at various moments are compiled into a list.

### View Rendering
- Model will generate a list of snapshots after the controller finishes parsing command line. The controller then passes the list of snapshots (`List<ISnapshot>`) to the view. Depending on the type of view, the rendering outcome differs when the controller calls the `produceViewFromSnapshots()` method:
  - **HTMLView**: Generates an HTML document depicting each snapshot.
  - **SwingView**: Displays an interactive Java Swing interface allowing users to navigate through snapshots visually.

## C. Using the JAR

To use the JAR file for generating visual representations of different shapes based on the provided command files, you will need to run specific commands in the terminal. The JAR file is located in the `resources` directory, along with several text files containing commands that direct the model on how to draw pictures using basic shapes like rectangles and ovals. These commands can render the drawings into either a static HTML view or an interactive Java Swing view.

### Prerequisites

- Ensure you have Java installed on your machine. You can check this by running `java -version` in your terminal.
- Navigate to the `resources` directory where the `album.jar` and input files are located.

### Commands to Execute JAR

1. **For HTML View:**
   To generate a static HTML view, use the following command:
   ```bash
   java -jar album.jar -in buildings.txt -view web -out buildings.html
   ```
   After running the command, open the `buildings.html` file to view the pictures as rendered in the HTML 
   document.
2. **For Java Swing View:**
   To generate an interactive Java Swing view, use this command:
   ```bash
   java -jar album.jar -in buildings.txt -view graphical
   ```
   This will open an interactive view where you can navigate through the pictures using `Prev` and `Next` 
   buttons. You can also jump to a specific picture by selecting its unique timestamp.
   
## Input Files Description

The `resources` directory contains the following text files, which include the commands for the model:
- `demo_input.txt`
- `hoops.txt`
- `buildings.txt`
- `teris_wallpaper.txt`

Each file corresponds to a different set of drawings and can be used as input to the JAR file to render different scenes using the specified commands.

## Note

- The views (either HTML or Java Swing) are determined by the `-view` parameter you specify in the command line.
- The output file (for HTML view) must be specified with the `-out` parameter followed by the desired file name.

By following these steps, you can easily generate visual representations of various designs as specified in the input command files using the `album.jar`.

   
