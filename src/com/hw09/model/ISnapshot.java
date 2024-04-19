package com.hw09.model;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Interface representing a snapshot of a shape.
 * A snapshot contains information about the current state of the shapes,
 * including their descriptions, timestamps, shape information in string,
 * and also a copy of all the shape objects
 */
public interface ISnapshot {
  /**
   * Get the timestamp of the snapshot.
   *
   * @return the timestamp of the snapshot
   */
  public LocalDateTime getTimestamp();

  /**
   * Get the shape information of the current shapes.
   *
   * @return a list of strings representing the shape information
   */
  public List<String> getshapeInfo();

  /**
   * Get the description of the snapshot.
   *
   * @return the description of the snapshot
   */
  String getDescription();

  /**
   * Get the current shapes in the snapshot. The List<IShape> is a deep copy
   * of the shapes processed by the model.
   *
   * @return a list of shapes in the current state
   */
  List<IShape> getCurrentShapes();
}
