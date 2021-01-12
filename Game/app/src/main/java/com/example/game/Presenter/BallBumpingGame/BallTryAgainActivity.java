package com.example.game.Presenter.BallBumpingGame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.game.Model.PlayerManager.PlayerFacade;
import com.example.game.R;

public class BallTryAgainActivity extends AppCompatActivity {
  //    public static final String EXTRA_MESSAGE2 = "com.example.Game.MESSAGE7";
  //    private String p_info;
  //    private final String databaseFile = "player_database";
  //    private ArrayList<String> userContents;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_ball_try_again);

    PlayerFacade.updateUserData(this);

    //        Intent intent = getIntent();
    //        p_info = intent.getStringExtra(BallBumpingGameActivity.EXTRA_MESSAGE);

    //        userContents = loadUserContents();
  }

  public void tryAgain(View view) {
    Intent intent = new Intent(this, BallBumpingGameActivity.class);
    //        if (p_info != null){
    //            updateUser(p_info);
    //        }
    //        intent.putExtra(EXTRA_MESSAGE2, p_info);
    startActivity(intent);
  }

  //    public void updateUser(String updated_info) {
  //        String[] userl = updated_info.split(",");
  //        String name = userl[0];
  //
  //        StringBuilder newFile = new StringBuilder();
  //
  //        for (int i = 0; i < userContents.size(); i++) {
  //            String info = userContents.get(i);
  //            String[] infol = info.split(",");
  //            if (infol[0].equals(name)) {
  //                StringBuilder sb = new StringBuilder();
  //                sb.append(updated_info);
  //                sb.append(",l2");
  //                sb.append(",");
  //                sb.append(infol[6]);
  //
  //                info = sb.toString();
  //            }
  //            newFile.append(info);
  //            newFile.append("\n");
  //        }
  //        String result = newFile.toString();
  //
  //        try {
  //            FileOutputStream database = openFileOutput("player_database", Context.MODE_PRIVATE);
  //            OutputStreamWriter osw = new OutputStreamWriter(database);
  //            osw.write(result);
  //            osw.close();
  //        } catch (IOException e) {
  //            e.printStackTrace();
  //        }
  //
  //        // Todo: update the info in the storage file!
  //    }
  //
  //    private ArrayList<String> loadUserContents() {
  //
  //        ArrayList<String> res = new ArrayList<>();
  //
  //        try {
  //
  //            InputStream input = openFileInput(databaseFile);
  //
  //            if (input != null) {
  //                InputStreamReader inputStreamReader = new InputStreamReader(input);
  //                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
  //                String receiveString;
  //
  //                while ((receiveString = bufferedReader.readLine()) != null) {
  //                    res.add(receiveString);
  //                    System.out.println(res.toString());
  //                }
  //                input.close();
  //            }
  //
  //        } catch (Throwable e) {
  //            e.printStackTrace();
  //        }
  //        return res;
  //    }
}
