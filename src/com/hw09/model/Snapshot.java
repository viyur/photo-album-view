package com.hw09.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Record all the information about the state and information of shapes
 * the moment when user use takeSnapshot functionality in the model.
 * Each snapshot knows its timestamp and description, has a list of strings describing information
 * of each shape before the snapshot taken, and also has a list of shapes object
 * copy.
 */
public class Snapshot implements ISnapshot {
  private LocalDateTime timestamp;
  private List<String> shapeInfo;
  private String description;

  private List<IShape> currentShapes;

  /**
   * Constructor.
   *
   * @param timestamp   the timestamp when the snapshot take place.
   * @param shapeInfo   the list of shape information.
   * @param description the context of the snapshot.
   */
  public Snapshot(LocalDateTime timestamp,
                  List<String> shapeInfo,
                  String description,
                  List<IShape> currentShapes) {
    this.timestamp = timestamp;
    this.shapeInfo = new ArrayList<>(shapeInfo);
    this.description = description;
    this.currentShapes = currentShapes;
  }

  @Override
  public LocalDateTime getTimestamp() {
    return timestamp;
  }


  /**
   * Return the list of information each snapshot stores.
   *
   * @return list of information.
   */
  @Override
  public List<String> getshapeInfo() {
    return List.copyOf(shapeInfo);
  }

  @Override
  public String getDescription() {
    return this.description;
  }

  @Override
  public List<IShape> getCurrentShapes() {
    return this.currentShapes;
  }

  /**
   * Return the String representation of the snapshot.
   *
   * @return String representation of the snapshot.
   */
  @Override
  public String toString() {
    StringBuilder info = new StringBuilder();
    info.append("Snapshot ID: ")
            .append(timestamp.toString())
            .append("\n")
            .append("Timestamp: ")
            .append(timestamp
                    .format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")))
            .append("\n")
            .append("Description: ")
            .append(this.description)
            .append("\n")
            .append("Shape Information:\n");


    for (String each : shapeInfo) {
      info.append(each).append("\n");
    }
    info.append("\n");
    return info.toString();
  }
}


