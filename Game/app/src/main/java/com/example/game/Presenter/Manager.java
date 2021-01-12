package com.example.game.Presenter;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;

import com.example.game.View.Renderer;

public abstract class Manager {
  /**
   * Creates a new manager
   *
   * @param height height of screen
   * @param width width of screen
   * @param context context for this activity
   */
  protected Manager(int height, int width, Context context) {}

  /** Update this manager and everything managed by it. */
  public abstract void update();

  /** Draw the objects managed by this manager. */
  public abstract void draw(Canvas canvas, Renderer renderer);

  /**
   * Process user events
   *
   * @param event the touch event being processed
   */
  public abstract void processEvent(MotionEvent event);
}
