package com.example.game.Model.BallBumpingGame;

import android.graphics.Color;
import static java.lang.Math.abs;

public class MagicalObject extends Visible {
  private int rangeW;

  // the speed of the MagicalObject
  private int speed;

  // Used to keep track of ball position for collisions
  private static int b_x;
  private static int b_y;

  /** Create a new MagicalObject. */
  MagicalObject(int row, int col, int rangeW, int speed, String theme) {
    super(row, col);
    this.rangeW = rangeW;
    // change the appearance later!!

    this.speed = speed;

    if (theme.equals("0")) {
      paintText.setColor(Color.rgb(153, 255, 255));
    } else if (theme.equals("1")) {
      paintText.setColor(Color.rgb(255, 102, 0));
    }
  }

  public int getX() {
    return row;
  }

  public int getY() {
    return col;
  }

  /** Move the MagicalObject. */
  void move() {
    this.row += this.speed;

    if (row <= 0) {
      this.speed = abs(this.speed);
    } else if (row >= rangeW) {
      this.speed = (-1) * abs(this.speed);
    }
  }

  boolean toRemove(){
    return (getX() + 4 >= b_x
            && getX() - 1 <= b_x
            && getY() + 4 >= b_y
            && getY() - 1 <= b_y);
  }

  static void updateB(int bx, int by){
    b_x = bx;
    b_y = by;
  }

  public void magicalEffect(PlayFieldManager pfm) {}
}
