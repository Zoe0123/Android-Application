package com.example.game.Presenter.BallBumpingGame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.Model.PlayerManager.PlayerFacade;
import com.example.game.R;

public class BallIntroActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_ball_intro);

    PlayerFacade.getPlayer().startCounting();
  }

  public void start(View view) {
    Intent intent = new Intent(this, BallBumpingGameActivity.class);
    startActivity(intent);
  }
}
