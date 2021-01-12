package com.example.game.Presenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.Model.PlayerManager.PlayerFacade;
import com.example.game.R;

public class LoseActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_lose);

    PlayerFacade.getPlayer().stopCounting();
    PlayerFacade.initPlayer();
    if (PlayerFacade.getPlayer().getMode()) {
      PlayerFacade.updateUserData(this);
    }
  }

  public void goToMain(View view) {
    Intent intent = new Intent(this, ModeSelectionActivity.class);
    startActivity(intent);
  }
}
