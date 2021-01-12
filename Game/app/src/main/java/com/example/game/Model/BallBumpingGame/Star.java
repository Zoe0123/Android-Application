package com.example.game.Model.BallBumpingGame;

import com.example.game.Model.PlayerManager.PlayerFacade;

public class Star extends MagicalObject {

  Star(int row, int col, int rangeW, int speed, String theme) {
    super(row, col, rangeW, speed, theme);
    this.appearance = "  ∆ \n" + "<   >\n" + "  V ";
  }

  public void magicalEffect(PlayFieldManager pfm) {
    PlayerFacade player = PlayerFacade.getPlayer();

    if (player.getDifficultyAsInteger() == 2) {
      player.addScore(20);
    } else if (player.getDifficultyAsInteger() == 1) {
      player.addScore(15);
    } else {
      player.addScore(10);
    }
  }
}
