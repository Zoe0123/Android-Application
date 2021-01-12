package com.example.game.Model.BallBumpingGame;

import android.graphics.Canvas;
import android.graphics.Color;

public class Ball extends Visible {
  private int rangeH;
  private int rangeW;

  // speed of the ball in the horizontal direction
  private int v_x = 0;
  // speed of the ball in the vertical direction
  private int v_y = 0;

  private boolean movingRight = false;
  private boolean movingDown = false;

  /**
   * Create a new ball
   *
   * @param row the row the ball will appear on
   * @param col the column the ball will appear on
   * @param rangeH the height of the 'travelable' screen (where the ball can travel vertically)
   * @param rangeW the width of the 'travelable' screen (where the ball can travel horizontally)
   * @param theme
   */
  Ball(int row, int col, int rangeH, int rangeW, String theme) {
    super(row, col);
    this.rangeH = rangeH;
    this.rangeW = rangeW;

    this.appearance = "O";

    if (theme.equals("0")) {
      paintText.setColor(Color.rgb(51, 153, 255));
    } else if (theme.equals("1")) {
      paintText.setColor(Color.rgb(255, 255, 0));
    }
  }

  /**
   * Launches the ball at the specified angle
   *
   * @param angle the angle to launch the ball
   */
  public void start(int angle) {
    switch (angle) {
      case 0:
        v_x = 0;
        v_y = 3;
        movingDown = true;
        break;
      case 30:
        v_x = 1;
        v_y = 2;
        movingDown = true;
        break;
      case 45:
        v_x = 2;
        v_y = 2;
        movingDown = true;
        break;
      case 60:
        v_x = 2;
        v_y = 1;
        movingDown = true;
        break;
      case 90:
        v_x = 3;
        v_y = 0;
        break;
      case 120:
        v_x = 2;
        v_y = 1;
        break;
      case 135:
        v_x = 2;
        v_y = 2;
        break;
      case 150:
        v_x = 1;
        v_y = 2;
        break;
      case 180:
        v_x = 0;
        v_y = 3;
        break;
    }
  }

  public int getX() {
    return row;
  }

  public int getY() {
    return col;
  }

  /** Move the ball. */
  void move() {
    if (movingRight) {
      this.row += this.v_x;
    } else {
      this.row -= this.v_x;
    }

    if (movingDown) {
      this.col += this.v_y;
    } else {
      this.col -= this.v_y;
    }

    checkReverse();
  }

  private void checkReverse() {
    if (col >= rangeH) {
      movingDown = false;
    } else if (col <= 0) {
      movingDown = true;
    }
    if (row <= 0) {
      movingRight = true;
    } else if (row >= rangeW) {
      movingRight = false;
    }
  }
}
