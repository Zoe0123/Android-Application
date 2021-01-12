package com.example.game.Presenter.Trivia;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import com.example.game.R;

public class LevelTransitionDialog extends DialogFragment {

  /**
   * @param savedInstanceState a bundle.
   * @return a dialog that notifies the player to go to next level in Trivia game.
   */
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    builder
        .setMessage(R.string.practice_win)
        .setPositiveButton(
            R.string.practice_next_level,
            new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {
                Context context = getContext();

                Intent intent = new Intent(context, TriviaActivity.class);
                if ((context) != null) {
                  ((TriviaActivity) context).finish();
                  context.startActivity(intent);
                }

                dismiss();
              }
            });
    return builder.create();
  }
}
