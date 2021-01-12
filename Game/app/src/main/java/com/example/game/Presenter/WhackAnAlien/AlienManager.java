package com.example.game.Presenter.WhackAnAlien;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.view.MotionEvent;

import com.example.game.Model.Grid;
import com.example.game.Model.PlayerManager.PlayerFacade;
import com.example.game.Model.UserInput;
import com.example.game.Presenter.Manager;
import java.util.Random;
import com.example.game.Model.WhackAnAlien.*;
import com.example.game.View.Renderer;
import com.example.game.View.WhackAnAlien.AlienView;

public class AlienManager extends Manager {
  private AlienList alienList;
  private Grid grid;
  private IAlienView view;
  private UserInput userInput;

  /** Determines when to end the game, and which activity to start once the game has ended */
  private boolean gameWon = false;

  private boolean gameLost = false;

  /**
   * Constructs a new alien manager.
   *
   * @param height height of screen
   * @param width width of screen
   * @param context context for this activity
   */
  AlienManager(int height, int width, Context context) {
    super(height, width, context);

    this.alienList = new AlienList();
    this.grid = new Grid(width, height);
    this.view = new AlienView(context, this);
    this.userInput = new UserInput();

    generateAliens(height, width);

    showRandomAlien();
    // Show a random alien every 4/3.5/3 seconds based on difficulty (easy/med/hard)
    int delay = 500 * (8 - PlayerFacade.getPlayer().getDifficultyAsInteger());
    final Handler handler = new Handler();
    handler.postDelayed(
        new Runnable() {
          @Override
          public void run() {
            if (alienList.size() > 1 && !gameLost) {
              showRandomAlien();
              // Repeat every 4/3.5/3 seconds while there are aliens
              handler.postDelayed(this, delay);
            } else {
              gameWon = true;
            }
          }
        },
        delay);
  }

  /**
   * Generates the aliens that will be shown throughout playing. Alien difficulty is determined by
   * the player.
   *
   * @param height the number of columns in the display
   * @param width the number of rows in the display
   */
  private void generateAliens(int height, int width) {
    int difficulty = PlayerFacade.getPlayer().getDifficultyAsInteger();
    Random random = new Random();
    int numAliens = 10;

    // Set the number of aliens to randomly generate based on difficulty (5,7,9 for e,m,h resp.)
    if (difficulty <= 2) {
      numAliens = 6 + difficulty * 2;
    }

    for (int i = 0; i < numAliens; i++) {
      int x = random.nextInt(height);
      int y = random.nextInt(width);
      int[] screenCoords = grid.convertToScreenCoordinates(x, y);

      // Adds alien based on difficulty (defaults to easy alien)
      switch (difficulty) {
        case 1:
          {
            MedAlien newAlien = new MedAlien(screenCoords[0], screenCoords[1]);
            newAlien.setGridX(x);
            newAlien.setGridY(y);
            addAlien(newAlien);
            break;
          }
        case 2:
          {
            HardAlien newAlien = new HardAlien(screenCoords[0], screenCoords[1]);
            newAlien.setGridX(x);
            newAlien.setGridY(y);
            addAlien(newAlien);
            break;
          }
        default:
          {
            EasyAlien newAlien = new EasyAlien(screenCoords[0], screenCoords[1]);
            newAlien.setGridX(x);
            newAlien.setGridY(y);
            addAlien(newAlien);
            break;
          }
      }
    }
  }

  /**
   * Adds an alien to the list of aliens if it has not yet been added.
   *
   * @param alien the alien to be added
   */
  private void addAlien(Alien alien) {
    if (!alienList.contains(alien)) {
      alienList.add(alien);
    }
  }

  /**
   * Is the game won?
   *
   * @return true if all aliens are dead, false otherwise
   */
  public boolean isGameWon() {
    return gameWon;
  }

  /**
   * Is the game lost?
   *
   * @return returns true if player is out of lives, false otherwise
   */
  public boolean isGameLost() {
    return gameLost;
  }

  /**
   * Displays the visible aliens that are currently alive.
   *
   * @param canvas the canvas to display the aliens
   * @param renderer the renderer to display the aliens
   */
  public void draw(Canvas canvas, Renderer renderer) {
    view.drawView(canvas, renderer, alienList);
  }

  /**
   * Checks if any aliens have reached the player, have been touched by the player, or have died and
   * updates accordingly.
   */
  public void update() {
    PlayerFacade playerFacade = PlayerFacade.getPlayer();
    for (Alien alien : alienList) {
      if (alien.isVisible()) {
        if (alienIsHit(alien)) {
          // Hit with base power 20. If we had more time for this project, we would expand this game
          // and allow the player to have customizable weapons with varying powers. More powerful
          // weapons would be unlocked later.
          alien.hit(20);
          if (alien.isDead()) {
            playerFacade.addScore(alien.getWorth());
            // Dead aliens all removed at once after this for loop
            continue;
          }

          // Immediately reset state of user input.
          // Otherwise, a user can just hold down on an alien and quickly kill them.
          userInput.touchUp();
        }

        alien.update();
        if (alien.isHere()) {
          playerFacade.loseLife();
          if (playerFacade.getLives() <= 0) {
            gameLost = true;
          }
        }
      }
    }
    // Removes all dead aliens and aliens which have hit the player
    alienList.removeTrash();
  }

  /**
   * Checks if an alien is tapped on by the user.
   *
   * @param alien alien to check for hit
   * @return if the alien was hit or not
   */
  private boolean alienIsHit(Alien alien) {
    return alien.getGridX() == userInput.getGridX() && alien.getGridY() == userInput.getGridY();
  }

  /** Makes a random existing alien visible. */
  private void showRandomAlien() {
    Random rand = new Random();
    if (alienList.size() <= 1) return;
    int index = rand.nextInt(alienList.size());
    System.out.println(
        "Showing alien at: " + alienList.get(index).getX() + " " + alienList.get(index).getY());
    alienList.get(index).makeVisible();
  }

  /**
   * Send user events to UserInput to be processed
   *
   * @param event the touch event being processed
   */
  public void processEvent(MotionEvent event) {
    userInput.processEvent(event, grid);
  }
}
