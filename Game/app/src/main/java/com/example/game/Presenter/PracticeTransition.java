package com.example.game.Presenter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;

public class PracticeTransition {
  public static void transition(int old, Context context, Intent intent) {
    AlertDialog alertDialog = new AlertDialog.Builder(context).create();
    alertDialog.setTitle("Congratulations");
    String message;
    switch (old) {
      case 0:
        message = "Switching from easy difficulty to medium in 4 seconds.";
        break;
      case 1:
        message = "Switching from medium difficulty to hard in 4 seconds.";
        break;
      default:
        message = "Illegal level. This should never happen.";
        break;
    }
    alertDialog.setMessage(message);
    alertDialog.setButton(
        AlertDialog.BUTTON_NEUTRAL,
        "OK",
        new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
          }
        });
    alertDialog.show();
    Handler handler = new Handler();
    handler.postDelayed(
        new Runnable() {
          @Override
          public void run() {
            context.startActivity(intent);
          }
        },
        4000);
  }
}
