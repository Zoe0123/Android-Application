package com.example.game.Presenter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.game.Model.PlayerManager.PlayerFacade;
import com.example.game.Presenter.Trivia.TriviaActivity;
import com.example.game.R;

public class PreferenceAModeActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_preference_amode);
  }

  public void setCool(View view) {
    PlayerFacade.getPlayer().setColor("0");
  }

  public void setWarm(View view) {
    PlayerFacade.getPlayer().setColor("1");
  }

  public void setEasy(View view) {
    PlayerFacade.getPlayer().setDifficulty("0");
  }

  public void setMedium(View view) {
    PlayerFacade.getPlayer().setDifficulty("1");
  }

  public void setHard(View view) {
    PlayerFacade.getPlayer().setDifficulty("2");
  }

  public void goToNext(View view) {
    if (PlayerFacade.getPlayer().getColor().equals("na")
        || PlayerFacade.getPlayer().getDifficulty().equals("na")) {
      TextView errorM = findViewById(R.id.notSelected);
      String wrong = "Please make a selection from each categories.";
      errorM.setText(wrong);
    } else {
      PlayerFacade.updateUserData(this);
      Intent intent = new Intent(this, TriviaActivity.class);
      startActivity(intent);
    }
  }
}
