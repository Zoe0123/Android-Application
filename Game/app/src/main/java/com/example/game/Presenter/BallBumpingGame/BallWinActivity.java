package com.example.game.Presenter.BallBumpingGame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.Model.PlayerManager.PlayerFacade;
import com.example.game.Presenter.WhackAnAlien.AlienActivity;
import com.example.game.R;

public class BallWinActivity extends AppCompatActivity {

  /**
   * This activity is only reached in A mode.
   *
   * @param savedInstanceState a bundle.
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_ball_win);

    PlayerFacade playerFacade = PlayerFacade.getPlayer();
    playerFacade.shiftLevel();

    PlayerFacade.updateUserData(this);
  }

  public void goToAlien(View view) {
    Intent intent = new Intent(this, AlienActivity.class);
    startActivity(intent);
  }
}
