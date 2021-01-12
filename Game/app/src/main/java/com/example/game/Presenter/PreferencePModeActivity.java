package com.example.game.Presenter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.game.Model.PlayerManager.PlayerFacade;
import com.example.game.R;

public class PreferencePModeActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_preference_pmode);
  }

  public void setCool(View view) {
    PlayerFacade.getPlayer().setColor("0");
  }

  public void setWarm(View view) {
    PlayerFacade.getPlayer().setColor("1");
  }

  public void confirm(View view) {
    if (PlayerFacade.getPlayer().getColor().equals("na")) {
      TextView errorM = findViewById(R.id.notSelected);
      String wrong = "Please make a selection from the category.";
      errorM.setText(wrong);
    } else {
      Intent intent1 = new Intent(this, LevelSelectionActivity.class);
      startActivity(intent1);
    }
  }
}
