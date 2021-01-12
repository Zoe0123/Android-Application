package com.example.game.Model.BallBumpingGame;

import android.util.Pair;

import com.example.game.Model.PlayerManager.PlayerFacade;

import java.util.ArrayList;

class VisibleItemFactory {
  private int rangeW;
  private int rangeH;
  private int diff;
  private String theme;
  private int wallSeg;
  private MagicalObjectsFactory moFactory;
  private Pair<Integer, Integer> ballPos;

  VisibleItemFactory(int rangeW, int rangeH) {
    PlayerFacade playerFacade = PlayerFacade.getPlayer();
    this.rangeW = rangeW;
    this.rangeH = rangeH;
    this.theme = playerFacade.getColor();
    this.diff = playerFacade.getDifficultyAsInteger();
    this.moFactory = new MagicalObjectsFactory(rangeW, rangeH, theme);

    if (diff == 2) {
      wallSeg = rangeH / 2;
    } else if (diff == 1) {
      wallSeg = rangeH * 2 / 5;
    } else {
      wallSeg = 0;
    }
  }

  Wall getWall() {
    int row = rangeW / 2;
    int col = 0;

    return new Wall(row, col, wallSeg, theme);
  }

  Goal getGoal() {
    int col = rangeH / 3;
    int row = 0;

    int c;
    if (diff == 2) {
      c = 3;
    } else if (diff == 1) {
      c = 6;
    } else {
      c = 9;
    }

    return new Goal(row, col, col - c, col + c, theme);
  }

  Banner getBanner() {
    int row = 9;
    int col = rangeH - 5;
    int time;

    if (diff == 2) {
      time = 20;
    } else if (diff == 1) {
      time = 30;
    } else {
      time = 40;
    }

    PlayerFacade playerFacade = PlayerFacade.getPlayer();
    if (playerFacade.getMode()) {
      return new Banner(
          row, col, playerFacade.getLives(), playerFacade.getScore(), playerFacade.getTime(), time);
    } else {
      return new Banner(row, col, playerFacade.getLives(), playerFacade.getScore(), 0, time);
    }
  }

  Ball getBall() {
    int row = rangeW;
    int col = rangeH / 3;
    ballPos = new Pair<>(row, col);

    return new Ball(row, col, rangeH, rangeW, theme);
  }

  ArrayList<MagicalObject> getMagicals() {
    int num;

    if (diff == 2) {
      num = 4;
    } else if (diff == 1) {
      num = 5;
    } else {
      num = 6;
    }

    ArrayList<MagicalObject> result = new ArrayList<>();
    int i = 0;
    while (i < num) {
      result.add(moFactory.getMO(wallSeg));
      i++;
    }
    return result;
  }

  ArrayList<Arrow> getArrows() {
    ArrayList<Arrow> arrows = new ArrayList<>();
    int row = ballPos.first;
    int col = ballPos.second;

    arrows.add(new Arrow(row, col, 0));
    arrows.add(new Arrow(row, col, 30));
    arrows.add(new Arrow(row, col, 45));
    arrows.add(new Arrow(row, col, 60));
    arrows.add(new Arrow(row, col, 90));
    arrows.add(new Arrow(row, col, 120));
    arrows.add(new Arrow(row, col, 135));
    arrows.add(new Arrow(row, col, 150));
    arrows.add(new Arrow(row, col, 180));
    arrows.add(new Arrow(row, col, 300));

    return arrows;
  }
}
