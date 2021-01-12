package com.example.game.Model.WhackAnAlien;

import com.example.game.Model.ScreenObject;

import java.util.Arrays;

public class Alien extends ScreenObject {
  /** Health of this alien */
  private int health;
  /** How much score the player gets for killing this alien */
  private int worth;
  /** Is this alien dead? */
  private boolean isDead;
  /** Is the alien visible (drawn on the screen)? */
  private boolean isVisible;
  /** The position of this alien in the grid */
  private int gridX, gridY;

  /**
   * How many updates away is this alien from the user? This is drawn on the screen beside the alien
   */
  private int distance;

  /**
   * More versatile constructor for alien.
   *
   * @param health how much health does the alien have to start?
   * @param worth how much score the player gets for killing this alien?
   * @param x x coordinate of alien's location
   * @param y y coordinate of alien's location
   */
  Alien(int health, int worth, float x, float y) {
    super(
        Arrays.asList(
            "",
            "",
            "       o        o",
            "          )--(",
            "       (O   O)",
            "         \\ = /",
            "  0",
            "  0"),
        x,
        y);
    this.health = health;
    this.worth = worth;
    this.distance = 100;
    this.isDead = false;
    this.isVisible = false;
    this.gridX = 0;
    this.gridY = 0;
  }

  /**
   * Gets worth of alien.
   *
   * @return worth
   */
  public int getWorth() {
    return worth;
  }

  /**
   * Hits alien with strength equal to power
   *
   * @param power strength of weapon used to hit alien (amount of health to remove from alien)
   */
  public void hit(int power) {
    this.health -= power;
    if (health <= 0) {
      isDead = true;
    }
  }

  /** Updates alien once (moves it closer to player) */
  public void update() {
    distance--;

    this.appearance.set(appearance.size() - 1, Integer.toString(distance));
    this.appearance.set(appearance.size() - 2, Integer.toString(health));
  }

  public int getGridX() {
    return gridX;
  }

  public int getGridY() {
    return gridY;
  }

  public void setGridX(int gridX) {
    this.gridX = gridX;
  }

  public void setGridY(int gridY) {
    this.gridY = gridY;
  }

  /**
   * Determines whether or not alien is at the player's location
   *
   * @return whether or not the alien is at the player's location
   */
  public boolean isHere() {
    return (distance <= 0);
  }

  /**
   * Whether or not the alien is dead
   *
   * @return true is the alien is dead, false otherwise
   */
  public boolean isDead() {
    return isDead;
  }

  /** Makes the alien visible */
  public void makeVisible() {
    this.isVisible = true;
  }

  /**
   * Whether or not the alien is visible.
   *
   * @return true if the alien is visible, false otherwise
   */
  public boolean isVisible() {
    return isVisible;
  }
}
