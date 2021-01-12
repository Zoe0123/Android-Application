package com.example.game.Model.BallBumpingGame;

import com.example.game.Model.PlayerManager.PlayerFacade;

public class Heart extends MagicalObject {

  Heart(int row, int col, int rangeW, int speed, String theme) {
    super(row, col, rangeW, speed, theme);
    this.appearance = " _ _ \n" + "  -- \n" + "   ˙ ";
  }

  public void magicalEffect(PlayFieldManager pfm) {
    PlayerFacade.getPlayer().addLife(1);
  }
}
