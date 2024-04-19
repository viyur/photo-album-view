package com.hw09.model;
/**
 * This class represents an oval shape.
 */
public class Oval extends AbstractShape {


  /**
   * Constructs an oval with the specified properties.
   *
   * @param name        the name of the oval.
   * @param centerPoint the center point of the oval.
   * @param color       the color of the oval.
   * @param xRadius     the x-axis radius of the oval.
   * @param yRadius     the y-axis radius of the oval.
   * @throws IllegalArgumentException if the x radius is <= y radius.
   */
  public Oval(String name,
              Point centerPoint,
              Colour color,
              double xRadius,
              double yRadius) {
    super(name, centerPoint, color, xRadius, yRadius);


  }

  /**
   * Resizes the oval by the specified amount.
   *
   * @param newWidth  the new width of the oval.
   * @param newHeight the new height of the oval.
   */
  @Override
  public void resize(double newWidth, double newHeight) {
    this.setXWidth(newWidth);
    this.setYHeight(newHeight);
  }

  /**
   * Returns a string representation of the oval.
   *
   * @return a string representation of the oval.
   */
  @Override
  public String toString() {
    return String.format(
            "Name: %s\n"
                    + "Type: oval\nCenter: (%.1f,%.1f),"
                    + " X radius: %.1f, Y radius: %.1f,\n"
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
    return new Oval(this.getName(),
            new Point(this.getCenterPoint()),
            new Colour(this.getColor()),
            this.getXWidth(),
            this.getYHeight());
  }




}
