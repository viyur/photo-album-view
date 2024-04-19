package com.hw09.view;

/**
 * Utility class to create view according to the command line arguments.
 */
public class ViewFactory {
  public static IView createView(String viewType,
                                 int width,
                                 int height,
                                 String outputFile) {
    return switch (viewType.toLowerCase()) {
      // Ignore the output file information if it is for swing view
      case "graphical" -> new SwingView(width, height);
      case "web" -> new HTMLView(outputFile);
      default ->
              throw new IllegalArgumentException(
                      "Illegal view type: " + viewType);
    };
  }
}
