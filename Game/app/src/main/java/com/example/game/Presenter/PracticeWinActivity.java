package com.example.game.Presenter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.game.R;

public class PracticeWinActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_practice_win);
  }

  public void goToLevelSelection(View view) {
    Intent intent = new Intent(this, LevelSelectionActivity.class);
    startActivity(intent);
  }
}
