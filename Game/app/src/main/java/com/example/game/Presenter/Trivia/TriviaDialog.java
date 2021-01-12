package com.example.game.Presenter.Trivia;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import com.example.game.R;

public class TriviaDialog extends DialogFragment {

  /**
   * Construct a dialog to display whether the user answers the last question correctly.
   *
   * @param savedInstanceState The bundle
   * @return the dialog that displays whether the user answers correctly.
   */
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    // Use the Builder design pattern for convenient dialog construction
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    if (getActivity() != null) {
      String message = ((TriviaActivity) getActivity()).getSetter().getQuestionResult();
      builder
          .setMessage(message)
          .setPositiveButton(
              R.string.trivia_next_question,
              new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                  ((TriviaActivity) getActivity()).updateQuestion();
                  // Reset color of three buttons to grey.
                  ((TriviaActivity) getActivity()).getSetter().resetColor();
                  ((TriviaActivity) getActivity()).displayStatistics();
                  dismiss();
                }
              });
    }
    return builder.create();
  }
}
