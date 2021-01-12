package com.example.game.Model.BallBumpingGame;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;

import com.example.game.View.BallBumpingGame.PlayFieldView;

abstract class Visible {

  Paint paintText;

  // row coordinate of visible objects
  int row;
  // column coordinate of visible objects
  int col;

  // appearance
  String appearance;

  private float characterWidth, characterHeight;

  Visible() {
    paintText = new Paint();
    paintText.setTextSize(36);
    paintText.setTypeface(Typeface.DEFAULT_BOLD);

    characterWidth = paintText.measureText("W");
    characterHeight = -paintText.ascent() + paintText.descent();
  }

  Visible(int row, int col) {
    this.row = row;
    this.col = col;

    paintText = new Paint();
    paintText.setTextSize(36);
    paintText.setTypeface(Typeface.DEFAULT_BOLD);

    characterWidth = paintText.measureText("W");
    characterHeight = -paintText.ascent() + paintText.descent();
  }

  /**
   * Draws the given string in the given graphics context at the given cursor location.
   *
   * @param canvas the graphics context in which to draw the string.
   * @param s the string to draw.
   * @param x the horizontal coordinate of the string's cursor location.
   * @param y the vertical coordinate of the string's cursor location.
   */
  void drawString(Canvas canvas, String s, int x, int y) {
    canvas.drawText(s, x * PlayFieldView.charWidth, y * PlayFieldView.charHeight, paintText);
  }

  /**
   * Draws this item.
   *
   * @param canvas the graphics context in which to draw this item.
   */
  public void draw(Canvas canvas) {
    int y_offset = 0;
    for (String string : appearance.split("\n")) {
      drawString(canvas, string, row, col + y_offset);
      y_offset += 1;
    }
  }

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }
}
