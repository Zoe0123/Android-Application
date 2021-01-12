package com.example.game.Model.BallBumpingGame;

import android.graphics.Canvas;
import android.graphics.Color;

import com.example.game.Model.PlayerManager.PlayerFacade;

public class Banner extends Visible {
  private String countDown;
  private boolean mode;

  /**
   * Creates a new banner.
   *
   * @param row the row this banner is at
   * @param col the column this banner is at
   * @param lives lives of the player
   * @param scores score cumulated
   * @param times this elapsed
   * @param countDown countdown of the game
   */
  Banner(int row, int col, int lives, int scores, int times, int countDown) {
    super(row, col);
    if (times == 0) {
      mode = false;
      appearance = "Lives: " + lives + ";  Scores: " + scores;
    } else {
      mode = true;
      appearance = "Lives: " + lives + ";  Scores: " + scores + ";  Time in total: " + times;
    }
    this.countDown = "Countdown:" + countDown;
    paintText.setColor(Color.LTGRAY);
  }

  public void draw(Canvas canvas) {
    super.draw(canvas);
    drawString(canvas, countDown, row, col - 1);
  }

  /**
   * Updates this banner with player information.
   *
   * @param countDown the time remaining in this game
   */
  public void update(int countDown) {
    PlayerFacade player = PlayerFacade.getPlayer();
    if (mode) {
      appearance =
          "Lives: "
              + player.getLives()
              + ";  Scores: "
              + player.getScore()
              + ";  Time: "
              + player.getTime();
    } else {
      appearance = "Lives: " + player.getLives() + ";  Scores: " + player.getScore();
    }
    this.countDown = "Countdown:" + countDown;
  }
}
