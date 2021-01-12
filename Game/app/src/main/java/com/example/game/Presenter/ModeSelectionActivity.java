package com.example.game.Presenter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.game.Model.PlayerManager.PlayerFacade;
import com.example.game.Presenter.BallBumpingGame.BallIntroActivity;
import com.example.game.Presenter.ScoreBoard.ScoreBoardActivity;
import com.example.game.Presenter.WhackAnAlien.AlienActivity;
import com.example.game.R;

public class ModeSelectionActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_mode_selection);

    // In case player got here by pressing back arrow, pause timer.
    PlayerFacade.getPlayer().stopCounting();
  }

  public void continueAdventure(View view) {
    PlayerFacade.setAMode();
    PlayerFacade player = PlayerFacade.getPlayer();

    player.loadPlayerContents(this);
    String level = player.getLevel();
    switch (level) {
      case "1":
        Intent intent1 = new Intent(this, PreferenceAModeActivity.class);
        startActivity(intent1);
        break;
      case "2":
        Intent intent2 = new Intent(this, BallIntroActivity.class);
        startActivity(intent2);
      case "3":
        Intent intent3 = new Intent(this, AlienActivity.class);
        startActivity(intent3);
    }
  }

  public void goToPractice(View view) {
    PlayerFacade.setPMode();

    Intent intent = new Intent(this, PreferencePModeActivity.class);
    startActivity(intent);
  }

  public void newAdventure(View view) {
    PlayerFacade.setAMode();
    PlayerFacade.initPlayer();

    Intent intent = new Intent(this, PreferenceAModeActivity.class);
    startActivity(intent);
  }

  public void goToBoard(View view) {
    Intent intent = new Intent(this, ScoreBoardActivity.class);
    startActivity(intent);
    finish();
  }
}
