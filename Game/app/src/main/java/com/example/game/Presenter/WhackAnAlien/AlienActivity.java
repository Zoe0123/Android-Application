package com.example.game.Presenter.WhackAnAlien;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.Model.PlayerManager.PlayerFacade;
import com.example.game.Presenter.AdventurousWinActivity;
import com.example.game.Presenter.LoseActivity;
import com.example.game.Presenter.PracticeWinActivity;
import com.example.game.Presenter.PracticeTransition;
import com.example.game.View.WhackAnAlien.AlienView;

public class AlienActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow()
        .setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    final AlienView alienView = new AlienView(this, new AlienManager(4, 4, this));
    setContentView(alienView);
    final AlienActivity act = this;

    PlayerFacade playerFacade = PlayerFacade.getPlayer();
    // If continuing progress from before, resume the timer.
    if (PlayerFacade.getPlayer().getMode()) {
      PlayerFacade.getPlayer().startCounting();
    }
    final Handler handler = new Handler();
    Context alienActCon = this;
    handler.postDelayed(
        new Runnable() {
          @Override
          public void run() {
            // Checks gamestate every 1000 ms
            if (alienView.isGameWon()) {
              // Go to correct win activity based on practice/adventurous mode
              if (!playerFacade.getMode()) { // Practice Mode
                Intent intent;
                switch (playerFacade.getDifficultyAsInteger()) {
                  case 0:
                  case 1:
                    intent = new Intent(act, AlienActivity.class);
                    PracticeTransition.transition(
                        playerFacade.getDifficultyAsInteger(), alienActCon, intent);
                    playerFacade.shiftDiff();
                    break;
                  default:
                    intent = new Intent(act, PracticeWinActivity.class);
                    playerFacade.shiftDiff();
                    startActivity(intent);
                    break;
                }
              } else { // Adventurous Mode
                playerFacade.stopCounting();
                PlayerFacade.updateUserData(act);
                Intent intent = new Intent(act, AdventurousWinActivity.class);
                startActivity(intent);
              }
            } else if (alienView.isGameLost()) {
              playerFacade.stopCounting();
              PlayerFacade.initPlayer();
              PlayerFacade.updateUserData(act);
              Intent intent = new Intent(act, LoseActivity.class);
              startActivity(intent);
            } else {
              handler.postDelayed(this, 1000);
            }
          }
        },
        1000);
  }
}
