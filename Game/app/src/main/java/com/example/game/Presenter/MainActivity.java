package com.example.game.Presenter;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.example.game.R;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    loadLocale();
    setContentView(R.layout.activity_main);

    // If timer is accidentally running (i.e. game is quit in the middle, stop counting)

    // The Button to change language.
    Button changeLang = findViewById(R.id.changeLang);
    changeLang.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            showChangeLanguageDialog();
          }
        });
  }

  private void showChangeLanguageDialog() {
    final String[] listItems = {"Chinese", "English"};
    AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
    mBuilder.setTitle("Choose Language:");
    mBuilder.setSingleChoiceItems(
        listItems,
        -1,
        new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
            if (i == 0) {
              setLocale("zh");
              recreate();
            } else if (i == 1) {
              setLocale("en");
              recreate();
            }

            dialogInterface.dismiss();
          }
        });

    AlertDialog mDialog = mBuilder.create();
    mDialog.show();
  }

  private void setLocale(String lang) {
    Locale locale = new Locale(lang);
    Locale.setDefault(locale);
    Configuration config = new Configuration();
    config.setLocale(locale);
    getBaseContext()
        .getResources()
        .updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
    editor.putString("My_Lang", lang);
    editor.apply();
  }

  public void loadLocale() {
    SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
    String language = prefs.getString("My_Lang", "");
    setLocale(language);
  }

  public void signUp(View view) {
    Intent intent = new Intent(this, PlayerRegistrationActivity.class);
    startActivity(intent);
  }

  public void signIn(View view) {
    Intent intent = new Intent(this, PlayerSignInActivity.class);
    startActivity(intent);
  }
}
