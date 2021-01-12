package com.example.game.Presenter;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.example.game.Model.PlayerManager.PlayerFacade;
import com.example.game.Presenter.ScoreBoard.ScoreBoardActivity;
import com.example.game.R;

public class AdventurousWinActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_adventurous_win);
    PlayerFacade.getPlayer().stopCounting();

    TextView name = findViewById(R.id.name);
    TextView time = findViewById(R.id.time);
    TextView score = findViewById(R.id.score);
    TextView record = findViewById(R.id.record);

    PlayerFacade playerFacade = PlayerFacade.getPlayer();
    String nameS = "User ID: " + playerFacade.getName();
    String timeS = "Time used: " + playerFacade.getTime();
    String scoreS = "Score: " + playerFacade.getScore();
    String recordR =
        "Score: " + playerFacade.getBestScore() + "; Time: " + playerFacade.getBestTime();

    name.setText(nameS);
    time.setText(timeS);
    score.setText(scoreS);
    record.setText(recordR);

    PlayerFacade.updateUserData(this);
  }

  public void goToBoard(View view) {
    PlayerFacade.getPlayer().updateRankingInfo();
    PlayerFacade.updateUserData(this);

    Intent intent = new Intent(this, ScoreBoardActivity.class);
    startActivity(intent);
    finish();
  }

  public void goToModeSelection(View view) {
    PlayerFacade.initPlayer();
    Intent intent = new Intent(this, ModeSelectionActivity.class);
    startActivity(intent);
    finish();
  }
}
