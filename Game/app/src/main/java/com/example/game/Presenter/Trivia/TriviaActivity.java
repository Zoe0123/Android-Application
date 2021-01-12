package com.example.game.Presenter.Trivia;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.game.Model.PlayerManager.PlayerFacade;
import com.example.game.Presenter.LoseActivity;
import com.example.game.Presenter.PracticeWinActivity;
import com.example.game.R;

import com.example.game.Model.Trivia.Question;

public class TriviaActivity extends AppCompatActivity {
  // static int threshold;

  Button buttonA, buttonB, buttonC;
  TextView questionHeading, questionBody;
  QuestionManager questionManager;
  TriviaSetter setter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_trivia_question);

    // Starts keeping track of time
    if (PlayerFacade.getPlayer().getMode()) {
      PlayerFacade.getPlayer().startCounting();
    }

    // Customization of color
    setBackgroundColor();

    // Display questions and buttons
    setter = new TriviaSetter();
    questionManager = setter.getManager();
    initViews();

    // Customization of difficulty
    setter.setWinningThreshold();
  }

  /** Set up the buttons and question. */
  private void initViews() {
    // initialize views here
    buttonA = findViewById(R.id.buttonOptionA);
    buttonB = findViewById(R.id.buttonOptionB);
    buttonC = findViewById(R.id.buttonOptionC);
    questionHeading = findViewById(R.id.questionHeading);
    questionBody = findViewById(R.id.questionBody);

    // get a question randomly from the question bank, set up buttons
    setter.setButtons(buttonA, buttonB, buttonC);
    updateQuestion();
    displayStatistics();
  }

  /** Update the question. */
  public void updateQuestion() {
    questionManager.setCurrentQuestion();
    Question currentQuestion = questionManager.getCurrentQuestion();

    // Display the current question.
    questionBody.setText(currentQuestion.getQuestion());
    buttonA.setText(currentQuestion.getOptionA());
    buttonB.setText(currentQuestion.getOptionB());
    buttonC.setText(currentQuestion.getOptionC());
  }

  /** Customize background color based on the player's choice of cool or warm. */
  public void setBackgroundColor() {
    View layout = findViewById(R.id.linearLayout);
    if (PlayerFacade.getPlayer().getColor().equals("0")) {
      layout.setBackgroundColor(Color.rgb(204, 255, 153));
    } else {
      layout.setBackgroundColor(Color.rgb(255, 153, 51));
    }
  }

  /**
   * Onclick listener of the three buttons.
   *
   * @param view the button pressed
   */
  public void button(View view) {
    Button currentButton = (Button) view;

    // Grade the question.
    setter.setQuestionResult(currentButton);

    PlayerFacade playerFacade = PlayerFacade.getPlayer();
    transition(playerFacade);
  }

  /**
   * Determine whether the player answers another question, wins, or loses.
   *
   * @param playerFacade the player.
   */
  public void transition(PlayerFacade playerFacade) {
    if (setter.getCorrectAnswers() == setter.getThreshold()) {
      triviaGameWon();
    } else if (playerFacade.getLives() == 0) {
      triviaGameLose();
    } else {
      showResultDialog();
    }
  }

  /** Show result dialog that displays whether the player answers correctly. */
  public void showResultDialog() {
    DialogFragment correctFragment = new TriviaDialog();
    correctFragment.show(getSupportFragmentManager(), "resultDialog");
  }

  /** Show transition dialog that notifies the player to go to next practice level. */
  public void showTransitionDialog() {
    DialogFragment transitionFragment = new LevelTransitionDialog();
    transitionFragment.show(getSupportFragmentManager(), "transitionDialog");
  }

  /** Transition method when the player wins the game. */
  public void triviaGameWon() {
    PlayerFacade playerFacade = PlayerFacade.getPlayer();
    if (playerFacade.getMode()) { // Adventurous mode, go to Ball Bumping Game
      Intent intent = new Intent(this, TriviaWinActivity.class);
      startActivity(intent);
      finish();
    } else {
      if (playerFacade.getDifficultyAsInteger() == 2) { // Practice mode, highest level
        Intent intent = new Intent(this, PracticeWinActivity.class);
        startActivity(intent);
        finish();
      } else { // Practice mode and there's higher levels available, go to higher level
        playerFacade.shiftDiff();
        showTransitionDialog();
      }
    }
  }

  /** Start lose activity to go back to the main page. */
  public void triviaGameLose() {
    Intent intent = new Intent(this, LoseActivity.class);
    startActivity(intent);
    finish();
  }

  /** @return setter of Trivia game. */
  public TriviaSetter getSetter() {
    return this.setter;
  }

  /** Display the user's score, lives, and time used. */
  public void displayStatistics() {
    // Display player score and lives
    TextView textPoints = findViewById(R.id.result_textPoints);
    TextView textLives = findViewById(R.id.result_textLives);

    PlayerFacade playerFacade = PlayerFacade.getPlayer();
    String userScore = "Score: " + playerFacade.getScore();
    String userLives = "Lives: " + playerFacade.getLives();

    textPoints.setText(userScore);
    textLives.setText(userLives);

    // Display time in Adventurous mode
    if (playerFacade.getMode()) {
      TextView textTime = findViewById(R.id.result_textTime);
      String userTime = "Time in seconds: " + playerFacade.getTime();
      textTime.setText(userTime);
    }
  }

  @Override
  protected void onDestroy() {
    // When the player leaves the game they need to start all over again.
    // correctAnswers = 0;

    super.onDestroy();
    // Reload the question bank.
    questionManager.resetUnanswered();
  }
}
