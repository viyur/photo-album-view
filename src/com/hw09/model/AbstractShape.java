package com.hw09.model;
import java.util.Objects;

/**
 * AbstractShape that represents all possible shapes.
 */
public abstract class AbstractShape implements IShape {
  private final String name;
  private final Point centerPoint;
  private final Colour color;

  private double xWidth;
  private double yHeight;

  /**
   * Constructor for abstract shape.
   *
   * @param name        cannot be null or empty.
   * @param centerPoint the center point of the shape.
   * @param color       rgb color of the shape.
   * @throws IllegalArgumentException if name is null or empty,
   *                                  or if centerPoint is null,
   *                                  or if color is null,
   *                                  or the width and height of the shape < 0.
   */
  public AbstractShape(String name,
                       Point centerPoint,
                       Colour color,
                       double xWidth,
                       double yHeight) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("The name of the shape "
              + "cannot be null or empty!");
    }
    if (centerPoint == null || color == null) {
      throw new IllegalArgumentException("The center point or color of "
              + "the shape cannot be null!");
    }
    if (xWidth <= 0 || yHeight <= 0) {
      throw new IllegalArgumentException("The width and height of "
              + "the shape cannot be non positive!");
    }
    this.name = name;
    this.centerPoint = centerPoint;
    this.color = color;
    this.xWidth = xWidth;
    this.yHeight = yHeight;
  }

  @Override
  public double getXWidth() {
    return this.xWidth;
  }

  @Override
  public double getYHeight() {
    return this.yHeight;
  }

  @Override
  public void setXWidth(double width) {
    this.xWidth = width;
  }

  @Override
  public void setYHeight(double height) {
    this.yHeight = height;
  }


  /**
   * Get the name of the shape.
   *
   * @return the unique name of the shape.
   */
  @Override
  public String getName() {
    return name;
  }

  /**
   * Get the starting point of the shape.
   *
   * @return the starting point of the shape.
   */
  @Override
  public Point getCenterPoint() {
    return this.centerPoint;
  }

  /**
   * Get the rgb color of the shape.
   *
   * @return the rgb color of the shape.
   */
  @Override
  public Colour getColor() {
    return this.color;
  }


  /**
   * Change the center point of the shape. Different shapes might have different
   * center points.
   *
   * @param x the new x coordinate of the center point of the shape.
   * @param y the new y coordinate of the center point of the shape.
   */
  @Override
  public void move(double x, double y) {
    this.centerPoint.setX(x);
    this.centerPoint.setY(y);
  }

  @Override
  public void changeColor(double red, double green, double blue) {
    this.color.setRed(red);
    this.color.setGreen(green);
    this.color.setBlue(blue);
  }

  /**
   * Two shape is equal by the name.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AbstractShape that = (AbstractShape) o;
    return Objects.equals(name, that.name);
  }

  /**
   * Two shape is equal by the name.
   */
  @Override
  public int hashCode() {
    return Objects.hash(name);
  }



}
