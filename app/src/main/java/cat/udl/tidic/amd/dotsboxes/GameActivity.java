package cat.udl.tidic.amd.dotsboxes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import cat.udl.tidic.amd.dotsboxes.models.Game;
import cat.udl.tidic.amd.dotsboxes.models.Player;
import cat.udl.tidic.amd.dotsboxes.viewmodels.GameViewModel;
import cat.udl.tidic.amd.dotsboxes.views.GameView;

public class GameActivity extends AppCompatActivity {

    protected GameView gameView;
    private  GameViewModel gameViewModel;
    private TextView textView;
    private TextView player_red;
    private TextView player_blue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        initDataBinding();
        gameView = (GameView) findViewById(R.id.gameView);
        gameView.setGameViewModel(gameViewModel, this);
        //

        textView = findViewById(R.id.currentPlayerTV);
        textView.setText("blue");

        player_blue = findViewById(R.id.pointsP1TV);
        player_red = findViewById(R.id.pointsP2TV);


    }

    //TODO
    private void initDataBinding() {
    }
    //

    public void updateCurrentPlayer(String current_player) {
        textView = findViewById(R.id.currentPlayerTV);
        textView.setText(current_player);
    }

    public void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void updateScore(String player, int score) {
        if(player.equals("red")) {
            player_red.setText(String.valueOf(score));
        }
        else {
            player_blue.setText(String.valueOf(score));
        }
    }

    public void initDialogFragment(Game game) {
        GameEndDialog dialog = GameEndDialog.newInstance(game, this);
        dialog.setCancelable(false);
        dialog.show(getSupportFragmentManager(), "GAME_END_DIALOG");
    }

    public void restartActivity() {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }

    public void returnToInitView() {
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
        finish();
    }
}