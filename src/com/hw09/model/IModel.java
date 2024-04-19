package com.hw09.model;
import java.util.List;

/**
 * Model interface.
 */
public interface IModel {

  /**
   * Adds a shape to the model.
   *
   * @param shape the shape to be added.
   */
  void addShape(IShape shape);

  /**
   * move a shape to new position inside the model
   *
   * @param name unique name of the shape.
   * @param x    new x position.
   * @param y    new y position.
   */
  void moveShape(String name, double x, double y);

  /**
   * Resize the shape inside the model.
   *
   * @param name      unique name of the shape.
   * @param newWidth  new width of the shape.
   * @param newHeight new height of the shape.
   */
  void resizeShape(String name, double newWidth, double newHeight);

  /**
   * Change the color of the shape.
   *
   * @param name  unique name of the shape.
   * @param color new Color.
   */
  void changeShapeColor(String name, Colour color);

  /**
   * Remove a shape from the model.
   *
   * @param name unique name of the shape.
   */
  void removeShape(String name);

  /**
   * Get a shape from the model.
   *
   * @param name unique name of the shape.
   * @return the shape identified by the name.
   */
  IShape getShape(String name);

  /**
   * Record all the states and information of all the shapes inside model.
   * Give description of the context of the snapshot.
   */
  void takeSnapshot(String description);

  /**
   * Get all the snapshots taken.
   *
   * @return list of the snapshots.
   */
  List<ISnapshot> getAllSnapshots();

  /**
   * Store all the timestamps of all the snapshots in string.
   *
   * @return string of timestamps of snapshots.
   */
  String getAllTimeStampsString();

  /**
   * Get all the shapes.
   *
   * @return a list of all the shapes.
   */
  List<IShape> getAllShapes();


}
