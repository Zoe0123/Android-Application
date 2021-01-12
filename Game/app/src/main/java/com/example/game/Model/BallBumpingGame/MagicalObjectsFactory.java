package com.example.game.Model.BallBumpingGame;

import java.util.Random;

class MagicalObjectsFactory {
  private int rangeW;
  private int maxH;
  private String theme;

  MagicalObjectsFactory(int rangeW, int maxH, String theme) {
    this.rangeW = rangeW;
    this.maxH = maxH;
    this.theme = theme;
  }

  MagicalObject getMO(int minH) {

    double rand = Math.random();

    int r = new Random().nextInt(rangeW);
    int c = new Random().nextInt(maxH - minH) + minH;
    int s = new Random().nextInt(2) + 1;

    if (rand <= 0.33) {
      return new Clock(r, c, rangeW, s, theme);
    } else if (rand <= 0.66) {
      return new Star(r, c, rangeW, s, theme);
    } else {
      return new Heart(r, c, rangeW, s, theme);
    }
  }
}
