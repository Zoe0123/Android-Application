package com.example.game.Presenter;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.Model.PlayerManager.PlayerFacade;
import com.example.game.R;

public class PlayerRegistrationActivity extends AppCompatActivity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_player_registration);
  }

  /**
   * Attempts to login a player. If the player doesn't exist, they will be automatically registered.
   */
  public void registerUser(View view) {
    EditText nameField = findViewById(R.id.userID);
    String name = nameField.getText().toString();
    EditText passwordField = findViewById(R.id.password);
    String password = passwordField.getText().toString();

    if (name.equals("")) {
      TextView textView = findViewById(R.id.errorMessage);
      String wrong = "User name cannot be empty";
      textView.setText(wrong);
    } else if (password.equals("")) {
      TextView textView = findViewById(R.id.errorMessage);
      String wrong = "Password cannot be empty";
      textView.setText(wrong);
    } else {
      boolean succeed = PlayerFacade.registerUser(this, name, password);
      if (succeed) {
        TextView textView = findViewById(R.id.success);
        String message = "Successfully Registered! Now you can sign in.";
        textView.setText(message);
      } else {
        TextView textView = findViewById(R.id.errorMessage);
        String wrong = "User name already exists.";
        textView.setText(wrong);
      }
    }
  }
}
