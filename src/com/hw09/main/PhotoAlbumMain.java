package com.hw09.main;

import com.hw09.controller.IController;
import com.hw09.controller.PhotoAlbumController;
import com.hw09.model.IModel;
import com.hw09.model.PhotoAlbumModel;
import com.hw09.view.IView;
import com.hw09.view.ViewFactory;


/**
 * Main class for the PhotoAlbum application.
 * Parses command line arguments, initializes the model, view, and controller,
 * and then runs the application.
 */
public class PhotoAlbumMain {
  /**
   * Main method that starts the PhotoAlbum application.
   * Parses the command line arguments, initializes the model, view, and controller,
   * and then runs the application.
   *
   * @param args The command line arguments.
   */
  public static void main(String[] args) {
    // Firstly, parse the command line arguments
    // all the command file with txt extension resides in the resources root
    String inputFile = null;
    String viewType = null;
    String outputFile = null;
    // Default width and height for the Swing view
    int xmax = 1000;
    int ymax = 1000;
    boolean isXSet = false;
    boolean isYSet = false;
    // -in buildings.txt -out myWeb.html  -v web
    // -in buildings.txt -v graphical 800 800
    try {
      for (int i = 0; i < args.length; i++) {
        // find if there is data specify the size of the graphical view
        if (!isXSet && isInteger(args[i])) {
          xmax = Integer.parseInt(args[i]);
          isXSet = true; // now the width has been set by the command line
        } else if (!isYSet && isInteger(args[i])) {
          ymax = Integer.parseInt(args[i]);
          isYSet = true; // now the height has been set by the command line
        }

        switch (args[i]) {
          case "-in":
            i++;
            inputFile = args[i]; // "buildings.txt"
            break;
          case "-view":
          case "-v":
            i++;
            viewType = args[i];
            break;
          case "-out":
            i++;
            outputFile = args[i];
            break;
        }
      }
    } catch (Exception e) {
      System.out.println(
              "Error can't read command line arguments: "
                      + e.getMessage());
      System.exit(1);
    }

    if (inputFile == null || viewType == null) {
      System.out.println("Error: input file or view type is missing.");
      System.exit(1);
    }

    // output file path is needed for the web view
    // exit the program if no output file path provided
    if (outputFile == null && viewType.equalsIgnoreCase("web")) {
      System.out.println(
              "Error: output path not provided for web view in command line.");
      System.exit(1);
    }


    // Secondly, give all the information from the command line to controller
    // Initialize the model, view and controller
    IModel model = new PhotoAlbumModel();
    IView view = ViewFactory.createView(viewType, xmax, ymax, outputFile);
    IController controller = new PhotoAlbumController();
    // couple the view and model with the controller
    controller.setModel(model);
    controller.setView(view);
    // controller parse the command from input file
    controller.takeSnapshotsFromFile(inputFile);
    // controller tells the view to show snapshots
    controller.produceViewFromSnapshots();
  }

  /**
   * Checks if the given string is an integer.
   *
   * @param str The string to be checked.
   * @return True if the string is an integer, false otherwise.
   */
  private static boolean isInteger(String str) {
    try {
      Integer.parseInt(str);
      return true;
    } catch (NumberFormatException e) {
      return false;
    }
  }
}
