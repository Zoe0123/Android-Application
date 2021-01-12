package com.example.game.Presenter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.Model.PlayerManager.PlayerFacade;
import com.example.game.R;

public class PlayerSignInActivity extends AppCompatActivity {
  //    private final String databaseFile = "player_database";
  //    ArrayList<String> userContents;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_player_sign_in);

    //        userContents = loadUserContents();
  }

  public void signin(View view) {
    EditText nameE = findViewById(R.id.userID);
    String name = nameE.getText().toString();
    EditText passwordE = findViewById(R.id.password);
    String password = passwordE.getText().toString();

    String message = PlayerFacade.signIn(this, name, password);
    if (message.equals("password")) {
      TextView errorM = findViewById(R.id.wrong);
      String wrong = "Incorrect password.";
      errorM.setText(wrong);
    } else if (message.equals("name")) {
      TextView errorM = findViewById(R.id.wrong);
      String wrong = "Incorrect user ID.";
      errorM.setText(wrong);
    } else {
      TextView errorM = findViewById(R.id.wrong);
      String wrong = "Signed in successfully.";
      errorM.setText(wrong);

      Intent intent = new Intent(this, ModeSelectionActivity.class);
      startActivity(intent);
    }
  }
}
