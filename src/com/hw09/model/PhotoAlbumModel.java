package com.hw09.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

/**
 * PhotoAlbumModel class can add, resize, move different shapes.
 */
public class PhotoAlbumModel implements IModel {
  private final Map<String, IShape> shapesMap;
  private final List<ISnapshot> snapshots;

  /**
   * Construct a PhotoAlbumModel.
   *
   */
  public PhotoAlbumModel() {
    // linked hash map will show the order of items as when they are added
    this.shapesMap = new LinkedHashMap<>();
    this.snapshots = new ArrayList<>();
  }


  /**
   * Add new valid shape to the album.
   * A valid shape should not be null and should not be the shape
   * that already exists on the album.
   * Internally PhotoAlbum use Map to manage all the shapes.
   * Each shape has a unique name as the key.
   *
   * @param shape all kinds of shapes.
   * @throws IllegalArgumentException if shape is null,
   *                                  if shape is duplicate,
   */
  @Override
  public void addShape(IShape shape) {
    if (shape == null) {
      throw new IllegalArgumentException("Shape cannot be null");
    }
    String newShapeName = shape.getName();
    // check whether the shape identified by the name already exists
    if (haveShape(newShapeName)) {
      throw new IllegalArgumentException("Cannot add Shape with same name: "
              + newShapeName);
    }
    shapesMap.put(newShapeName, shape);
  }

  /**
   * Move shape to new center point,
   * which will change the center point of that shape.
   *
   * @param name unique name of the shape.
   * @param x    new x position of the shape.Must >= 0.
   * @param y    new y position of the shape.Must >= 0.
   * @throws IllegalArgumentException if the shape is not contained,
   *                                  if the new coordinates is negative,
   *                                  if the movement to the new coordinates
   */
  @Override
  public void moveShape(String name, double x, double y) {

    // can only move shape already added to the shapesMap
    if (!haveShape(name)) {
      throw new IllegalArgumentException("Cannot find Shape with name: " + name);
    }

    IShape shape = shapesMap.get(name);
    shape.move(x, y);
  }

  /**
   * Resize the shape in the album.
   *
   * @param name      unique name of the shape.
   * @param newWidth  new width of the shape. Must >= 0.
   * @param newHeight new height of the shape. Must >= 0.
   * @throws IllegalArgumentException if new dimensions of the shape < 0,
   *                                  if shape is not current inside the album,
   */
  @Override
  public void resizeShape(String name, double newWidth, double newHeight) {
    if (newWidth < 0 || newHeight < 0) {
      throw new IllegalArgumentException(
              "Width and height cannot be negative");
    }
    if (!haveShape(name)) {
      throw new IllegalArgumentException("Cannot find Shape with name: " + name);
    }
    IShape shape = shapesMap.get(name);
    shape.resize(newWidth, newHeight);
  }

  private boolean haveShape(String name) {
    return shapesMap.containsKey(name);
  }

  /**
   * Change the color of the shape on the album.
   *
   * @param name  unique name of the shape
   * @param color new color.
   * @throws IllegalArgumentException if the shape is not on the album,
   *                                  if the Color is null.
   */
  @Override
  public void changeShapeColor(String name, Colour color) {
    // can only change the color of the existing shape
    if (!haveShape(name)) {
      throw new IllegalArgumentException("Cannot find Shape with name: " + name);
    }
    // check if color is null
    if (color == null) {
      throw new IllegalArgumentException("Cannot change color to null");
    }
    IShape shape = shapesMap.get(name);
    shape.changeColor(color.getRed(), color.getGreen(), color.getBlue());
  }

  /**
   * Remove the shape from the album.
   *
   * @param name unique name of the shape.
   * @throws IllegalArgumentException if shape is not found.
   */
  @Override
  public void removeShape(String name) {
    if (!haveShape(name)) {
      throw new IllegalArgumentException("Cannot find Shape with name: " + name);
    }
    shapesMap.remove(name);
  }

  /**
   * Get shape by looking up the unique name.
   *
   * @param name unique name of the shape.
   * @return the shape identified by the name.
   * @throws IllegalArgumentException if shape is not found.
   */
  @Override
  public IShape getShape(String name) {
    if (!haveShape(name)) {
      throw new NoSuchElementException("Cannot find Shape with name: " + name);
    }
    return shapesMap.get(name);
  }

  /**
   * takeSnapshot will record the states and information about
   * all the shapes currently on the album.
   * Internally using List<Snapshot> to record all the snapshots taken
   *
   * @param description the context when taking snapshot.Default is empty string.
   */
  @Override
  public void takeSnapshot(String description) {
    String d = "";
    if (description != null && !description.isEmpty()) {
      d = description;
    }
    // generate the information about each shape in one snapshot in string
    List<String> shapesInfoLog = shapesMap.values().stream()
            .map(shape -> shape.toString())
            .collect(Collectors.toList());
    // generate a copy of all the shapes object represented by single snapshot
    List<IShape> currentShapes = new ArrayList<>();
    for (IShape shape : shapesMap.values()) {
      currentShapes.add(shape.copy());
    }
    snapshots.add(
            new Snapshot(LocalDateTime.now(), shapesInfoLog, d, currentShapes));
  }


  /**
   * Get all the snapshots.
   *
   * @return all the snapshots.
   */
  @Override
  public List<ISnapshot> getAllSnapshots() {
    return Collections.unmodifiableList(snapshots);
  }

  /**
   * Get all the timestamps of the snapshots in the form of string.
   *
   * @return all the timestamps of the snapshots in string.
   */
  @Override
  public String getAllTimeStampsString() {
    return snapshots.stream()
            .map(snapshot -> snapshot.getTimestamp().toString())
            .collect(Collectors.joining(",", "[", "]"));
  }

  /**
   * Get all the shapes.
   *
   * @return a list of all the shapes.
   */
  @Override
  public List<IShape> getAllShapes() {
    if (shapesMap.isEmpty()) {
      return Collections.emptyList();
    }
    return List.copyOf(shapesMap.values());
  }
}
