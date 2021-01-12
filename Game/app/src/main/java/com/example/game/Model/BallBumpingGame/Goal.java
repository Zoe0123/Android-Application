package com.example.game.Model.BallBumpingGame;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Pair;

public class Goal extends Visible {

  private Pair<Integer, Integer> range;

  Goal(int row, int col, int c1, int c2, String theme) {
    super(row, col);

    range = new Pair<>(c1, c2);

    if (theme.equals("0")) {
      paintText.setColor(Color.GREEN);
    } else if (theme.equals("1")) {
      paintText.setColor(Color.rgb(255, 255, 0));
    }
  }

  Pair<Integer, Integer> getRange() {
    return range;
  }

  public void draw(Canvas canvas) {
    for (int i = range.first; i <= col - 2; i++) {
      drawString(canvas, ":", row, i);
    }

    drawString(canvas, "G", row, col - 1);
    drawString(canvas, "O", row, col);
    drawString(canvas, "A", row, col + 1);
    drawString(canvas, "L", row, col + 2);

    for (int i = col + 3; i <= range.second; i++) {
      drawString(canvas, ":", row, i);
    }
  }
}
