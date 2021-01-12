package com.example.game.Model.BallBumpingGame;

import android.graphics.Color;

class Arrow extends Visible {
  private int orientation;

  /**
   * Creates a new arrow
   *
   * @param row the row position of this arrow
   * @param col the column position of this arrow
   * @param orientation the orientation of this arrow
   */
  Arrow(int row, int col, int orientation) {
    this.orientation = orientation;
    paintText.setColor(Color.WHITE);

    switch (orientation) {
      case 0:
        appearance = "0˚";
        this.col = col + 4;
        this.row = row - 1;
        break;
      case 30:
        appearance = "30˚";
        this.col = col + 3;
        this.row = row - 2;
        break;
      case 45:
        appearance = "45˚";
        this.col = col + 2;
        this.row = row - 3;
        break;
      case 60:
        appearance = "60˚";
        this.col = col + 1;
        this.row = row - 4;
        break;
      case 90:
        appearance = "90˚";
        this.col = col;
        this.row = row - 5;
        break;
      case 120:
        appearance = "120˚";
        this.col = col - 1;
        this.row = row - 4;
        break;
      case 135:
        appearance = "135˚";
        this.col = col - 2;
        this.row = row - 3;
        break;
      case 150:
        appearance = "150˚";
        this.col = col - 3;
        this.row = row - 2;
        break;
      case 180:
        appearance = "180˚";
        this.col = col - 4;
        this.row = row - 1;
        break;
      case 300:
        appearance = "Pick a direction to shoot!";
        this.col = col - 6;
        this.row = row - 15;
        break;
    }
    System.out.println(appearance + ": " + this.row + "," + this.col);
  }

  int getOrientation() {
    return orientation;
  }
}
