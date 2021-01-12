package com.example.game.Presenter.WhackAnAlien;

import android.graphics.Canvas;
import com.example.game.Model.WhackAnAlien.AlienList;
import com.example.game.View.Renderer;

public interface IAlienView {
  void drawView(Canvas canvas, Renderer renderer, AlienList aliens);

  boolean isGameWon();

  boolean isGameLost();
}
