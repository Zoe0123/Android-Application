package com.example.game.Model;

import java.util.List;

public class ScreenObject {
  protected List<String> appearance;
  protected float x, y;

  /**
   * Creates a new object that can be drawn to the screen
   *
   * @param appearance what the object looks like, each index of the list will be on a separate line
   * @param x the x position of the object
   * @param y the y position of the object
   */
  public ScreenObject(List<String> appearance, float x, float y) {
    this.appearance = appearance;
    this.x = x;
    this.y = y;
  }

  public List<String> getAppearance() {
    return appearance;
  }

  public float getX() {
    return x;
  }

  public float getY() {
    return y;
  }
}
