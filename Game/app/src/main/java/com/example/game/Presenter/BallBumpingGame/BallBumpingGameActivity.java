package com.example.game.Presenter.BallBumpingGame;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import androidx.appcompat.app.AppCompatActivity;
import com.example.game.Model.PlayerManager.PlayerFacade;
import com.example.game.Presenter.LoseActivity;
import com.example.game.Presenter.PracticeWinActivity;
import com.example.game.View.BallBumpingGame.PlayFieldView;

public class BallBumpingGameActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    requestWindowFeature(Window.FEATURE_NO_TITLE);
    getWindow()
        .setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

    if (PlayerFacade.getPlayer().getMode()) {
      PlayerFacade.getPlayer().startCounting();
    }

    setContentView(new PlayFieldView(this));
  }

  public void toNextLevel() {
    Intent nextLevel = new Intent(this, BallWinActivity.class);
    startActivity(nextLevel);
    finish();
  }

  public void toNextDiff() {
    PlayerFacade playerFacade = PlayerFacade.getPlayer();
    if (playerFacade.getDifficultyAsInteger() == 2) {
      Intent nextDiff = new Intent(this, PracticeWinActivity.class);
      startActivity(nextDiff);
      finish();
    } else {
      playerFacade.shiftDiff();
      Intent nextDiff = new Intent(this, BallBumpingGameActivity.class);
      startActivity(nextDiff);
      finish();
    }
  }

  public void startOver() {
    Intent over = new Intent(this, LoseActivity.class);
    startActivity(over);
    finish();
  }

  public void tryAgain() {

    Intent again = new Intent(this, BallTryAgainActivity.class);
    startActivity(again);
    finish();
  }
}
