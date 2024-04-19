package com.hw09.model;
/**
 * Interface of shapes.
 */
public interface IShape {
  /**
   * move shape to new position.
   * @param x new x position.
   * @param y new y position.
   */
  void move(double x, double y);
  /**
   * Resize the shape
   */
  void resize(double newWidth, double newHeight);

  /**
   * change the color of the shape
   */
  void changeColor(double red, double green, double blue);

  /**
   * get unique name of the shape
   */
  String getName();

  /**
   * get the center point of the shape.
   */
  Point getCenterPoint();

  /**
   * get the rgb color of the shape.
   */
  Colour getColor();

  /**
   * get the clone of the shape.
   */


  /**
   * get the width of the shape.
   */
  double getXWidth();

  /**
   * get the height of the shape.
   */
  double getYHeight();

  /**
   * set the width of the shape.
   */
  void setXWidth(double width);

  /**
   * set the height of the shape.
   */
  void setYHeight(double height);


  IShape copy();

}
