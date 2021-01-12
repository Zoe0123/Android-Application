package com.example.game.Presenter.Trivia;

import java.util.ArrayList;
import java.util.Random;

import com.example.game.Model.Trivia.Question;

class QuestionManager {

  /**
   * An array list that stores all of the questions that haven't been answered correctly in the
   * Trivia game.
   */
  private ArrayList<Question> unansweredQuestions;

  /** The current question being answered. */
  private Question currentQuestion;

  /** Construct a question manager and load questions. */
  QuestionManager() {
    unansweredQuestions = new ArrayList<>();
    // Add questions to the unanswered question list.
    createQuestions(unansweredQuestions);
  }

  /** Add questions to the question bank. All the questions are added when calling this method. */
  private void createQuestions(ArrayList<Question> questionBank) {
    questionBank.add(
        new Question(
            "What is the approximate seating capacity of Con Hall?",
            "1500",
            "1700",
            "1300",
            "1700"));
    questionBank.add(
        new Question(
            "In what year was the University of Toronto founded?", "1827", "1857", "1835", "1827"));
    questionBank.add(
        new Question(
            "Who invented the computer?",
            "Alan Turing",
            "Konrad Zuse",
            "Charles Babbage",
            "Charles Babbage"));
    questionBank.add(
        new Question(
            "Who developed Java language?",
            "Bill Joy",
            "James Gosling",
            "Dennis Ritchie",
            "James Gosling"));
    questionBank.add(new Question("The answer of 52 + 20 * 8", "212", "175", "130", "212"));
    questionBank.add(new Question("The answer of 10 * 8 - 1", "120", "80", "79", "79"));
    questionBank.add(new Question("The answer of 3 + 78", "72", "81", "90", "81"));
    questionBank.add(new Question("The answer of 52/4 + 5", "18", "20", "17", "18"));
    questionBank.add(new Question("The answer of 97 + 12", "110", "109", "99", "109"));
    questionBank.add(new Question("The answer of 90 + 20", "110", "105", "120", "110"));
    questionBank.add(new Question("The answer of 52 + 37", "85", "89", "91", "89"));
    questionBank.add(new Question("How many campuses does the U of T have?", "2", "3", "4", "3"));
    questionBank.add(
        new Question(
            "Who is the president of the U of T?",
            "Meric Gertler",
            "Kawhi Leonard",
            "Doug Ford",
            "Meric Gertler"));
    questionBank.add(
        new Question("How many libraries does the U of T have?", "22", "33", "44", "44"));
    questionBank.add(
        new Question(
            "Who is not a Turing Award laureate affliated with the U of T?",
            "Geoffrey Hinton",
            "Andrew Chi-Chih Yao",
            "Stephen A. Cook",
            "Andrew Chi-Chih Yao"));
    questionBank.add(
        new Question("When was the Vector Institute launched?", "1997", "2007", "2017", "2017"));
    questionBank.add(
        new Question(
            "How long does a PEY usually last?",
            "5-8 months",
            "6-12 months",
            "12-16 months",
            "12-16 months"));
    questionBank.add(
        new Question(
            "What is the mascot of the U of T?",
            "True Blue",
            "True Green",
            "True Yellow",
            "True Blue"));
    questionBank.add(
        new Question(
            "Which of the following is the campus newspaper of the U of T?",
            "The Varsity",
            "The Ubyssey",
            "UWO Gazette",
            "The Varsity"));
    questionBank.add(
        new Question("When was the Hart House established?", "1909", "1919", "1929", "1919"));
  }

  /** Set the current question. */
  void setCurrentQuestion() {
    currentQuestion = getARandomQuestion();
  }

  /** Reset the unanswered question bank when the current game ends. */
  void resetUnanswered() {
    createQuestions(unansweredQuestions);
  }

  /**
   * If the question is answered correctly by the user, remove it from the unanswered questions.
   *
   * @param question the question that is answered correctly.
   */
  void removeAnsweredQuestion(Question question) {
    unansweredQuestions.remove(question);
  }

  /** @return the current question being answered. */
  Question getCurrentQuestion() {
    return currentQuestion;
  }

  /**
   * Get a random question from the question bank.
   *
   * @return a random question from question bank.
   */
  private Question getARandomQuestion() {
    return unansweredQuestions.get(new Random().nextInt(unansweredQuestions.size()));
  }
}
