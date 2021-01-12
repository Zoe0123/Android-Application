package com.example.game.Presenter.ScoreBoard;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.Model.PlayerManager.PlayerFacade;
import com.example.game.Presenter.ModeSelectionActivity;
import com.example.game.R;

import java.util.ArrayList;


public class ScoreBoardActivity extends AppCompatActivity {
    private ScoreBoardView boardView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);
        setBackgroundColor();
        this.boardView = new ScoreBoardView(this, "score", "first");
    }

    /**
     * Update the rankType of the boardView object.
     */
    public void setTimeType(View view) { boardView.setRankType("time"); }

    public void setScoreType(View view) { boardView.setRankType("score");}

    /**
     * Update the range of the boardView object.
     */
    public void setFirstRange(View view) { boardView.setRange("first"); }

    public void setSelfRange(View view) { boardView.setRange("self"); }


    /**
     * Set text at the TextView "rank'number'". Find the text content (stats and name) at list[index].
     * If index of out bound, set the text as "not enough users".
     * @param list
     * @param number
     * @param index
     */
    void displayContents(ArrayList<Pair> list, int number, int index) {
        TextView text = findViewById(R.id.rank0);
        switch(number){
            case 0: text = findViewById(R.id.rank0);
            break;
            case 1: text = findViewById(R.id.rank1);
            break;
            case 2: text = findViewById(R.id.rank2);
            break;
            case 3: text = findViewById(R.id.rank3);
            break;
            case 4: text = findViewById(R.id.rank4);
        }
        String message;
        try{ Pair content = list.get(index);
            message = "rank: " + (index+1) + "     name: " + content.getValue() + "        stats: " + content.getKey();
            text.setText(message);}
        catch (IndexOutOfBoundsException e){
            message = "not enough users";
            text.setText(message);}
    }

    /**
     * Customize background color based on the player's choice of cool or warm.
     */
    public void setBackgroundColor() {
        View layout = findViewById(R.id.scoreboardLayout);
        if (PlayerFacade.getPlayer().getColor().equals("0")) {
            layout.setBackgroundColor(Color.rgb(204, 255, 153));
        } else {
            layout.setBackgroundColor(Color.rgb(255, 153, 51));
        }
    }

    /**
     * Go to ModeSelection page.
     */
    public void goToModeSelection(View view){
        Intent intent = new Intent(this, ModeSelectionActivity.class);
        startActivity(intent);
        finish();
    }

}

