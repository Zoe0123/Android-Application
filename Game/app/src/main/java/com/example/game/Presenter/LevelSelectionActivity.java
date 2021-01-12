package com.example.game.Presenter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.game.Model.PlayerManager.PlayerFacade;
import com.example.game.Presenter.BallBumpingGame.BallIntroActivity;
import com.example.game.Presenter.Trivia.TriviaActivity;
import com.example.game.Presenter.WhackAnAlien.AlienActivity;
import com.example.game.R;

public class LevelSelectionActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_level_selection);

    PlayerFacade.initPlayer();
  }

  /** Sends the player to the alien game. */
  public void goToAlien(View view) {
    Intent intent = new Intent(this, AlienActivity.class);
    startActivity(intent);
  }

  /** Sends the player to the trivia game. */
  public void goToTrivia(View view) {
    Intent intent = new Intent(this, TriviaActivity.class);
    startActivity(intent);
  }

  /** Sends the player to the ball game. */
  public void goToBall(View view) {
    Intent intent = new Intent(this, BallIntroActivity.class);
    startActivity(intent);
  }

  /** Sends the player to the main screen. */
  public void goToMain(View view) {
    Intent intent = new Intent(this, ModeSelectionActivity.class);
    startActivity(intent);
  }
}
