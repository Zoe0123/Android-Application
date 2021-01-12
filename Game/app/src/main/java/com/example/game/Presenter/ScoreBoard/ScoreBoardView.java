package com.example.game.Presenter.ScoreBoard;

import com.example.game.Model.PlayerManager.PlayerFacade;

import java.util.ArrayList;
import java.util.Collections;

class ScoreBoardView {
    // The type rankings based on, either on score or time.
    private String rankType;
    // The range of rankings, either users ranked first five, or users with rankings closest to the
    //player (the rankings will be like the player in middle, with other four users ranked before and after him/her.
    private String range;
    // The list of user information in pairs (score, name) sorted by score.
    private ArrayList<Pair> sortByScore;
    // The list of user information in pairs (time, name) sorted by score.
    private ArrayList<Pair> sortByTime;
    private ScoreBoardActivity scoreBoard;

    /**
     * Construct a new ScoreBoardView, load the display on default settings (rankType = "score", range = "first").
     * @param scoreBoard
     * @param rankType
     * @param range
     */

    ScoreBoardView(ScoreBoardActivity scoreBoard, String rankType, String range) {
        this.rankType = rankType;
        this.range = range;
        this.scoreBoard = scoreBoard;
        ArrayList<String> userInfo = PlayerFacade.getRankingList(scoreBoard);
        this.sortByTime = sortUserInfo(userInfo, 1);
        this.sortByScore = sortUserInfo(userInfo, 2);
        loadDisplay();
    }

    /**
     * Update the rankType, and after update, call loadDisplay again.
     * @param rankType
     */
    void setRankType(String rankType) {
        this.rankType = rankType;
        loadDisplay();

    }

    void setRange(String range) {
        this.range = range;
        loadDisplay();
    }

    /**
     * Sort the user information list (originally has list of strings like "name, time, score") according
     * to score or time, and return a sorted list of pairs like (score, name) or (time, name).
     * @param info
     * @param index
     * @return
     */
    private ArrayList<Pair> sortUserInfo(ArrayList<String> info, int index) {
        ArrayList<Pair> list = new ArrayList<>();
        for (String item : info) {
            String[] s = item.split(",");
            int stats = Integer.parseInt(s[index]);
            Pair need = new Pair(stats, s[0]);
            list.add(need);
        }
        Collections.sort(list);
        if (index == 1) {
            // The time should be sorted in ascending order, different from sorted by score.
            Collections.reverse(list);}

            return list;
    }

    /**
     * Find the five rankings should display and call scoreBoard.displayContents method.
     */
    private void loadDisplay () {
        ArrayList<Pair> sortedList;
        String player_name = PlayerFacade.getPlayer().getName();
        if (rankType.equals("time")) {
            sortedList = sortByTime;
        } else {
            sortedList = sortByScore;
        }
        int index;
        // Find the index of the pair whose information is going to show on the "rank1" TextView.
        if (range.equals("first")) {
            index = 0;
        } else {
            int i = 0;
            while (i < sortedList.size()) {
                if (sortedList.get(i).getValue().equals(player_name)) {
                    break;
                }
                i ++;
            }
            index = Math.max(i - 2, 0);
        }
        for (int x = 0; x < 5; x++) {
                scoreBoard.displayContents(sortedList, x, index);
                index++;
        }
    }

}

    class Pair implements Comparable<com.example.game.Presenter.ScoreBoard.Pair> {
        private int x;
        private String y;

        Pair(int x, String y) {
            this.x = x;
            this.y = y;
        }

        public int compareTo(com.example.game.Presenter.ScoreBoard.Pair p) {
            return p.x - this.x;
        }

        int getKey() {
            return x;
        }

        String getValue() {
            return y;
        }
    }

