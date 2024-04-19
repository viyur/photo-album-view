package com.hw09.view;

import com.hw09.controller.IController;
import com.hw09.model.IShape;
import com.hw09.model.ISnapshot;
import com.hw09.model.Oval;
import com.hw09.model.Rectangle;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HTMLView implements IView {
  private List<ISnapshot> snapshots;
  private String outputPath;
  private IController controller;


  public HTMLView(String outputPath) {
    this.outputPath = outputPath;
  }

  @Override
  public void setSnapshots(List<ISnapshot> snapshots) {
    this.snapshots = snapshots;
  }

  @Override
  public void setController(IController controller) {
    this.controller = controller;
  }

  @Override
  public void showMessage(String message) {
    System.err.println(message);
  }

  @Override
  public void displaySingleSnapshot(ISnapshot snapshot) {
    // write html according to the information provided by single snapshot
    // using append mode
    try (BufferedWriter writer = new BufferedWriter(
            new FileWriter(
                    outputPath,
                    true))) {
      writer.write("<div>\n<h2>Snapshot Time Stamp: "
              + snapshot.getTimestamp()
              + "</h2>\n");
      writer.write("<div>\n<h2>Snapshot Description: "
              + snapshot.getDescription()
              + "</h2>\n");
      // default width and height will be 1000
      writer.write("<svg width=\"1000\" height=\"1000\""
              + " xmlns=\"http://www.w3.org/2000/svg\">\n");
      // write all the shapes from single snapshot to the html
      for (IShape shape : snapshot.getCurrentShapes()) {
        writer.write(convertShapeToSvg(shape));
      }
      // division between different snapshot
      writer.write("</svg>\n</div>\n");
    } catch (IOException e) {
      showMessage("Error writing HTML for a single snapshot: " + e.getMessage());
    }
  }

  @Override
  public void showView() {
    // write and output the html with specified file name
    generateHtml();
  }

  private void generateHtml() {
    if (snapshots == null) {
      showMessage("No snapshots");
      return;
    }

    try (BufferedWriter writer = new BufferedWriter(
            new FileWriter(outputPath))) {
      writer.write("""
              <!DOCTYPE html>
              <html>
              <head>
              <title>CS5004 Photo Album</title>
              </head>
              <body>
              """);
    } catch (IOException e) {
      showMessage("Error writing the starter of the HTML" + e.getMessage());
    }

    // Use displaySnapshot to append each snapshot to the HTML file.
    snapshots.forEach(this::displaySingleSnapshot);

    // Append the closing tags of HTML after all snapshots are processed.
    try (BufferedWriter writer = new BufferedWriter(
            new FileWriter(
                    outputPath,
                    true))) { // Append mode
      writer.write(
              "</body>\n"
                      + "</html>");
    } catch (IOException e) {
      showMessage(
              "Error writing closing tag of HTML: "
                      + e.getMessage());
    }
  }


  private String convertShapeToSvg(IShape shape) {
    // convert shape to SVG string according to the shape type
    StringBuilder svg = new StringBuilder();
    int x = (int) shape.getCenterPoint().getX();
    int y = (int) shape.getCenterPoint().getY();
    int width = (int) shape.getXWidth();
    int height = (int) shape.getYHeight();
    int r = (int) shape.getColor().getRed();
    int g = (int) shape.getColor().getGreen();
    int b = (int) shape.getColor().getBlue();

    if (shape instanceof Rectangle) {
      svg.append(String.format(
              "<rect x='%d' y='%d' "
                      + "width='%d' height='%d' "
                      + "fill='rgb(%d,%d,%d)' />\n",
              x, y, width, height, r, g, b));
    } else if (shape instanceof Oval) {
      svg.append(String.format(
              "<ellipse cx='%d' cy='%d' "
                      + "rx='%d' ry='%d' "
                      + "fill='rgb(%d,%d,%d)' />\n",
              x, y, width / 2, height / 2, r, g, b));
    }
    return svg.toString();
  }
}
