package com.hw09.controller;

import com.hw09.model.Colour;
import com.hw09.model.IModel;
import com.hw09.model.ISnapshot;
import com.hw09.model.Oval;
import com.hw09.model.Point;
import com.hw09.model.Rectangle;
import com.hw09.view.IView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/**
 * PhotoAlbumController class.
 */
public class PhotoAlbumController implements IController {
  private IModel model;
  private IView view;

  /**
   * Sets the view component of the controller.
   *
   * @param view the view component to be set
   */
  @Override
  public void setView(IView view) {
    this.view = view;
    view.setController(this);
  }

  /**
   * Sets the model component of the controller.
   *
   * @param model the model component to be set
   */
  @Override
  public void setModel(IModel model) {
    this.model = model;
  }


  /**
   * Takes all the snapshots from the specified file and stores them in the model.
   * When reading from file, it knows whether the file could be found in the resources root.
   *
   * @param fileName the name of the file containing the commands to take snapshots.
   */
  @Override
  public void takeSnapshotsFromFile(String fileName) {
    // Firstly find whether the file exists in the resources root directory
    // if the txt file exists, get the raw data as the InputStream
    InputStream inputFilePath = getClass()
            .getClassLoader()
            .getResourceAsStream(fileName);
    if (inputFilePath == null) {
      System.err.println(
              "Error loading snapshot to controller: "
                      + "File not found: " + fileName);
      return;
    }
    try (
            // convert InputStream to character data
            InputStreamReader fileReader = new InputStreamReader(inputFilePath);
            // read the whole file
            BufferedReader reader = new BufferedReader(fileReader)) {
      String line;
      while ((line = reader.readLine()) != null) {
        if (line.trim().isEmpty() || line.startsWith("#")) {
          // ignore empty lines and comments
          continue;
        }
        commandModelFromLine(line.trim());
      }
    } catch (IOException e) {
      System.err.println(
              "Error loading snapshot to controller: "
                      + e.getMessage());
    }
  }


  /**
   * Tell the model to do exactly what the command asks.
   * Command received from a single line in the file.
   *
   * @param line the line of text containing the command
   */
  private void commandModelFromLine(String line) {
    // split line into list consisting of words separated by spaces
    String[] commandList = line.split("\\s+");

    // possible data might be received from the command
    String commandType = commandList[0].toLowerCase();
    String id; // the unique id of the shape
    String shapeType; // the type of shape
    String description; // snapshot description
    // starting point(x, y) of the shape
    int x;
    int y;
    // width and height of the shape
    int width;
    int height;
    // red, green and blue values of the shape's color
    int r;
    int g;
    int b;

    // after identified the command type, ask the model to execute the command
    switch (commandType) {
      case "shape":
        // shape window002 rectangle 280 500 20 20 255 255 255
        id = commandList[1];
        shapeType = commandList[2];
        x = Integer.parseInt(commandList[3]);
        y = Integer.parseInt(commandList[4]);
        width = Integer.parseInt(commandList[5]);
        height = Integer.parseInt(commandList[6]);
        r = Integer.parseInt(commandList[7]);
        g = Integer.parseInt(commandList[8]);
        b = Integer.parseInt(commandList[9]);
        Colour color = new Colour(r, g, b);
        Point point = new Point(x, y);
        if ("rectangle".equalsIgnoreCase(shapeType)) {
          model.addShape(new Rectangle(id, point, color, width, height));
        } else if ("oval".equalsIgnoreCase(shapeType)) {
          model.addShape(new Oval(id, point, color, width, height));
        } else {
          System.err.println("Unknown shape type: " + shapeType);
        }
        break;
      case "move":
        // move myrect 300 200
        id = commandList[1];
        x = Integer.parseInt(commandList[2]);
        y = Integer.parseInt(commandList[3]);
        model.moveShape(id, x, y);
        break;
      case "resize":
        // resize myrect 25 100
        id = commandList[1];
        width = Integer.parseInt(commandList[2]);
        height = Integer.parseInt(commandList[3]);
        model.resizeShape(id, width, height);
        break;
      case "color":
        // color myrect 0 0 255
        id = commandList[1];
        r = Integer.parseInt(commandList[2]);
        g = Integer.parseInt(commandList[3]);
        b = Integer.parseInt(commandList[4]);
        model.changeShapeColor(id, new Colour(r, g, b));
        break;
      case "remove":
        // remove myrect
        if (commandList.length < 2) return;
        id = commandList[1];
        model.removeShape(id);
        break;
      case "snapshot":
        // snapshot Turn on the Lights!
        // snapshot
        if (commandList.length < 2) {
          description = "";
        } else {
          // slice the commandList from index 1
          // combine all the description together
          description = String.join(" ",
                  Arrays.copyOfRange(commandList, 1, commandList.length));
        }
        model.takeSnapshot(description);
        break;
    }
  }


  /**
   * After fetch all snapshots from the model. Show graphics.
   * If there are no snapshots, a message is displayed to the view.
   */
  @Override
  public void produceViewFromSnapshots() {
    List<ISnapshot> snapshots = model.getAllSnapshots();
    if (!snapshots.isEmpty()) {
      // Pass all the snapshots data to the view
      view.setSnapshots(snapshots);
      // Tell the view to show the graphs according to the shapes described in snapshots
      // HTMLView will create the HTML file under this command
      // while SwingView will show interactive view to the client
      view.showView();
    } else {
      view.showMessage("Controller did not receive any snapshots.");
    }
  }
}
