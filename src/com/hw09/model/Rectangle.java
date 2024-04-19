package com.hw09.model;
/**
 * This class represents a rectangle shape.
 *
 * @author Tabnine
 */
public class Rectangle extends AbstractShape {


  /**
   * Constructs a new Rectangle object with the specified properties.
   *
   * @param name        the name of the rectangle.
   * @param centerPoint the center point of the rectangle.
   * @param color       the color of the rectangle.
   * @param width       the width of the rectangle.
   * @param height      the height of the rectangle.
   */
  public Rectangle(
          String name,
          Point centerPoint,
          Colour color,
          double width,
          double height) {
    super(name, centerPoint, color, width, height);

  }

  /**
   * Resizes the rectangle by the specified amount.
   *
   * @param newWidth  the new width of the rectangle.
   * @param newHeight the new height of the rectangle.
   */
  @Override
  public void resize(double newWidth, double newHeight) {
    this.setXWidth(newWidth);
    this.setYHeight(newHeight);
  }

  /**
   * Returns a string representation of the rectangle.
   *
   * @return a string representation of the rectangle.
   */
  @Override
  public String toString() {
    return String.format(
            "Name: %s\n"
                    + "Type: rectangle\n"
                    + "Min corner: (%.1f,%.1f), Width: %.1f, Height: %.1f,\n"
                    + "Color: (%.1f,%.1f,%.1f)\n",
            this.getName(),
            this.getCenterPoint().getX(),
            this.getCenterPoint().getY(),
            this.getXWidth(),
            this.getYHeight(),
            this.getColor().getRed(),
            this.getColor().getGreen(),
            this.getColor().getBlue());
  }

  @Override
  public IShape copy() {
    return new Rectangle(this.getName(),
            new Point(this.getCenterPoint()),
            new Colour(this.getColor()),
            this.getXWidth(),
            this.getYHeight());
  }



}
