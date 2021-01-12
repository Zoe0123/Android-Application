package com.example.game.Presenter.Trivia;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.Model.PlayerManager.PlayerFacade;
import com.example.game.Presenter.BallBumpingGame.BallIntroActivity;
import com.example.game.R;

public class TriviaWinActivity extends AppCompatActivity {

  /**
   * So this activity is only reached in A mode.
   *
   * @param savedInstanceState a bundle
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_trivia_win);

    PlayerFacade.getPlayer().shiftLevel();
    PlayerFacade.updateUserData(this);
  }

  /**
   * Go to the Ball Bumping Game.
   *
   * @param view the view
   */
  public void goToNext(View view) {
    Intent intent = new Intent(this, BallIntroActivity.class);
    startActivity(intent);
    finish();
  }
}
