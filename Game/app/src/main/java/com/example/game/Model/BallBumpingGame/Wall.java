package com.example.game.Model.BallBumpingGame;

import android.graphics.Color;

public class Wall extends Visible {
  private int segment;

  Wall(int row, int col, int seg, String theme) {
    super(row, col);
    segment = seg;

    if (theme.equals("0")) {
      paintText.setColor(Color.YELLOW);
    } else if (theme.equals("1")) {
      paintText.setColor(Color.RED);
    }

    this.appearance = "";
    for (int i = 0; i < segment - 1; i++) {
      this.appearance += "| |\n";
    }
    this.appearance += "--";

    System.out.println("Wall appearance " + appearance);
  }

  public int getX() {
    return row;
  }

  int getSegment() {
    return segment;
  }
}
