package com.hw09.model;
import java.util.Objects;

/**
 * Point is the class to represent the x and y coordinates of a point.
 */
public class Point {
  private double x;
  private double y;

  /**
   * Point constructor
   *
   * @param x the x coordinate.
   * @param y the y coordinate.
   */
  public Point(double x, double y) {
    if (x < 0 || y < 0 ) {
      throw new IllegalArgumentException("Point coordinates must be non-negative");
    }
    this.x = x;
    this.y = y;
  }

  public Point(Point other) {
    this.x = other.x;
    this.y = other.y;
  }

  public Point() {
    this.x = 0;
    this.y = 0;
  }

  /**
   * Get the y coordinate.
   *
   * @return get the y coordinate.
   */
  public double getY() {
    return this.y;
  }

  /**
   * Get the x coordinate.
   *
   * @return get the x coordinate.
   */
  public double getX() {
    return this.x;
  }

  /**
   * Set the x coordinate.
   *
   * @param x new x coordinate.
   */
  public void setX(double x) {
    this.x = x;
  }

  /**
   * Set the y coordinate.
   *
   * @param y new y coordinate.
   */
  public void setY(double y) {
    this.y = y;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Point point = (Point) o;
    return Double.compare(x, point.x) == 0 && Double.compare(y, point.y) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }
}
