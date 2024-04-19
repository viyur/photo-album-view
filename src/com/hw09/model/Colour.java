package com.hw09.model;
import java.util.Objects;

/**
 * A color in the RGB color space.
 *
 * @author Tabnine
 */
public class Colour {
  private double red;
  private double green;
  private double blue;


  /**
   * Creates a new color with the specified RGB values.
   *
   * @param red   the amount of red.
   * @param green the amount of green.
   * @param blue  the amount of blue.
   */
  public Colour(double red, double green, double blue) {

    this.red = red;
    this.green = green;
    this.blue = blue;
  }




  /**
   * Copy constructor.
   *
   * @param other other color.
   */
  public Colour(Colour other) {
    this.red = other.red;
    this.green = other.green;
    this.blue = other.blue;
  }

  /**
   * Default Color.
   */
  public Colour() {
    this(255.0, 0.0, 0.0);
  }





  /**
   * Returns the red value of this color.
   *
   * @return the red value, between 0 and 1.
   */
  public double getRed() {
    return red;
  }

  /**
   * Returns the green value of this color.
   *
   * @return the green value.
   */
  public double getGreen() {
    return green;
  }

  /**
   * Returns the blue value of this color.
   *
   * @return the blue value.
   */
  public double getBlue() {
    return blue;
  }

  /**
   * Sets the red value of this color.
   *
   * @param red the red value.
   */
  public void setRed(double red) {


    this.red = red;
  }

  /**
   * Sets the green value of this color.
   *
   * @param green the green value.
   */
  public void setGreen(double green) {


    this.green = green;
  }

  /**
   * Sets the blue value of this color.
   *
   * @param blue the blue value.
   */
  public void setBlue(double blue) {

    this.blue = blue;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Colour color = (Colour) o;
    return Double.compare(red, color.red) == 0
            && Double.compare(green, color.green) == 0
            && Double.compare(blue, color.blue) == 0;
  }

  @Override
  public int hashCode() {
    return Objects.hash(red, green, blue);
  }
}
