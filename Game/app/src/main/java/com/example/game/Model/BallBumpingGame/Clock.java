package com.example.game.Model.BallBumpingGame;

public class Clock extends MagicalObject {

  Clock(int row, int col, int rangeW, int speed, String theme) {
    super(row, col, rangeW, speed, theme);
    this.appearance = "(¬ )";
  }

  public void magicalEffect(PlayFieldManager pfm) {
    pfm.addTime(5);
  }
}
