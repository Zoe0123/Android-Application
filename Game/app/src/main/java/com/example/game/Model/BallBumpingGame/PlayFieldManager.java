package com.example.game.Model.BallBumpingGame;

import android.graphics.Canvas;
import android.view.MotionEvent;

import com.example.game.Model.Grid;
import com.example.game.Model.PlayerManager.PlayerFacade;
import com.example.game.Model.UserInput;

import java.util.ArrayList;

public class PlayFieldManager {
  // the difficulty level that player choose to play for the games
  // a list of MagicalObjects shown on the game field
  private ArrayList<MagicalObject> magicalObjects;
  // the ball throw by the player in the game
  private Ball ball;
  // the wall (obstacle) in the middle of the game field
  private Wall wall;
  // the column range of the hole which the ball is expected to be throw into finally
  private Goal goal;
  private Banner banner;
  private ArrayList<Arrow> arrows;

  private UserInput userInput;
  private int[] inputTileCoordinates;
  private final Grid grid;

  private int scoreScale;
  private boolean started = false;

  private boolean win = false;
  private boolean lose = false;

  private int time;

  // width of grid is 30, height is 50.
  /*
  Create a new PlayFieldManager
   */
  public PlayFieldManager(int hei, int wid) {
    PlayerFacade playerFacade = PlayerFacade.getPlayer();
    int diff = playerFacade.getDifficultyAsInteger();

    if (diff == 2) {
      time = 20;
      scoreScale = 250;
    } else if (diff == 1) {
      time = 30;
      scoreScale = 200;
    } else {
      time = 40;
      scoreScale = 150;
    }

    userInput = new UserInput();
    grid = new Grid(wid, hei);

    VisibleItemFactory factory = new VisibleItemFactory(wid, hei);
    this.ball = factory.getBall();
    this.banner = factory.getBanner();
    this.magicalObjects = factory.getMagicals();
    this.wall = factory.getWall();
    this.goal = factory.getGoal();
    this.arrows = factory.getArrows();
  }

  private void start(int direction) {
    ball.start(direction);
    started = true;
  }

  public void draw(Canvas canvas) {
    for (MagicalObject mo : magicalObjects) {
      mo.draw(canvas);
    }
    ball.draw(canvas);
    wall.draw(canvas);
    banner.draw(canvas);
    goal.draw(canvas);
    if (!started) {
      for (Arrow arrow : arrows) {
        arrow.draw(canvas);
      }
    }
  }

  public void update() {
    banner.update(time);

    for (MagicalObject mo : magicalObjects) {
      mo.move();
    }

    if (time <= 0) {
      gameOver(false);
    }
    if (started) {
      time--;
      ball.move();
    }

    int b_x = ball.getX();
    int b_y = ball.getY();

    ifHitWall(b_x, b_y);
    ifHitMagical(b_x, b_y);
    ifHitGoal(b_x, b_y);

    if (inputTileCoordinates != null) {
      for (Arrow arrow : arrows) {
        if (arrowIsHit(arrow)) {
          int direction = arrow.getOrientation();
          System.out.println("direction: " + direction);
          System.out.println("arrow pos: " + arrow.getRow() + "," + arrow.getCol());
          if (direction <= 180) {
            start(direction);
          }
        }
      }
    }
  }

  private void ifHitWall(int b_x, int b_y) {

    if (b_y < wall.getSegment() && b_y > 0) {
      if (b_x >= wall.getX() - 1 && b_x <= wall.getX() + 2) {
        gameOver(false);
      }
    }
  }

  private void ifHitMagical(int b_x, int b_y) {
    if (b_y >= wall.getSegment()) {
      for (int i = 0; i < magicalObjects.size(); i++) {
        MagicalObject mo = magicalObjects.get(i);
        if (mo.getX() + 4 >= b_x
            && mo.getX() - 1 <= b_x
            && mo.getY() + 4 >= b_y
            && mo.getY() - 1 <= b_y) {
          mo.magicalEffect(this);
          MagicalObject.updateB(b_x, b_y);
        }
      }
      magicalObjects.removeIf(MagicalObject::toRemove);
    }
  }

  private void ifHitGoal(int b_x, int b_y) {
    if (b_x <= 0) {
      if (goal.getRange().first <= b_y && b_y <= goal.getRange().second) {
        gameOver(true);
      }
    }
  }

  public boolean isWin() {
    return win;
  }

  public boolean isLose() {
    return lose;
  }

  private void gameOver(boolean won) {
    PlayerFacade playerFacade = PlayerFacade.getPlayer();
    if (won) {
      playerFacade.addScore(scoreScale);
      this.win = true;
    } else {
      playerFacade.loseLife();
      this.lose = true;
    }
  }

  /**
   * Checks if an arrow is tapped on by the user.
   *
   * @param arrow to check for hit
   * @return if the arrow was hit or not
   */
  private boolean arrowIsHit(Arrow arrow) {
    int a_x = arrow.getRow();
    int a_y = arrow.getCol();

    return a_y - 1 <= inputTileCoordinates[1]
        && a_y >= inputTileCoordinates[1]
        && a_x <= inputTileCoordinates[0]
        && a_x + 2 >= inputTileCoordinates[0];
  }

  public void processEvent(MotionEvent event) {
    userInput.processEvent(event, grid);
    inputTileCoordinates = userInput.getGridPosition();
    System.out.println("touched: " + inputTileCoordinates[0] + "," + inputTileCoordinates[1]);
  }

  void addTime(int t) {
    time += t;
  }
}
