package com.example.game.Presenter.Trivia;

import android.graphics.Color;
import android.widget.Button;

import com.example.game.Model.PlayerManager.PlayerFacade;
import com.example.game.Model.Trivia.Question;

class TriviaSetter {
  private Button buttonA, buttonB, buttonC;
  private String questionResult;
  private QuestionManager manager;
  private int threshold;
  private int correctAnswers;

  TriviaSetter() {
    this.manager = new QuestionManager();
  }

  QuestionManager getManager() {
    return manager;
  }

  void setButtons(Button buttonA, Button buttonB, Button buttonC) {
    this.buttonA = buttonA;
    this.buttonB = buttonB;
    this.buttonC = buttonC;
  }

  /** Set the number of correct answer needed based on the difficulty level the player chooses. */
  void setWinningThreshold() {
    int difficulty = PlayerFacade.getPlayer().getDifficultyAsInteger();
    // Number of correct answers needed: Easy = 3, Medium = 5, Hard = 7
    threshold = difficulty * 2 + 3;
  }

  int getThreshold() {
    return threshold;
  }

  /** @return the number of questions the player answered correctly. */
  int getCorrectAnswers() {
    return correctAnswers;
  }

  /** Reset the button colors to grey. */
  void resetColor() {
    buttonA.setBackgroundColor(Color.LTGRAY);
    buttonB.setBackgroundColor(Color.LTGRAY);
    buttonC.setBackgroundColor(Color.LTGRAY);
  }

  /**
   * Grade the question and update statistics.
   *
   * @param currentButton the button chosen by player.
   */
  void setQuestionResult(Button currentButton) {
    PlayerFacade playerFacade = PlayerFacade.getPlayer();
    // Get answer chosen by player.
    String answer = currentButton.getText().toString();
    Question currentQuestion = manager.getCurrentQuestion();

    // Compare answer.
    boolean isCorrect = currentQuestion.answerCorrect(answer);
    if (isCorrect) {
      currentButton.setBackgroundColor(Color.GREEN);
      // The user earns 20 points for every question answered correctly.
      playerFacade.addScore(20);
      // Remove the question answered correctly so that the player wouldn't see it again
      // in this level. (But the player may still answers it in the next level.)
      manager.removeAnsweredQuestion(currentQuestion);
      correctAnswers += 1;
      questionResult = "Correct!";
    } else {
      currentButton.setBackgroundColor(Color.RED);
      // The user loses 5 points and 1 life for every question answered wrong.
      playerFacade.addScore(-5);
      playerFacade.loseLife();
      questionResult = "Wrong :(";
    }
  }

  /** @return message to be displayed on the result dialog */
  String getQuestionResult() {
    return questionResult;
  }
}
