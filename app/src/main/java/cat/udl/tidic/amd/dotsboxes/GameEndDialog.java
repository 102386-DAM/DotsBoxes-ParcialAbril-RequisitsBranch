package cat.udl.tidic.amd.dotsboxes;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.GameManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

import cat.udl.tidic.amd.dotsboxes.models.Game;

public class GameEndDialog extends DialogFragment {

    private View rootView;
    private TextInputLayout playerLayout;
    private TextInputEditText playerEditText;
    private Game game;
    private GameActivity gameActivity;

    public GameEndDialog() {
        // Required empty public constructor
    }

    public static GameEndDialog newInstance(Game game, GameActivity gameActivity) {
        GameEndDialog dialog = new GameEndDialog();
        //dialog.activity = activity;
        dialog.game = game;
        dialog.gameActivity = gameActivity;
        return dialog;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        initViews();
        AlertDialog alertDialog = new AlertDialog.Builder(getContext())
                .setView(rootView)
                .setTitle("End game")
                .setCancelable(false)
                .setPositiveButton("End game", null)
                .setNegativeButton("Play Again!", null)
                .create();
        alertDialog.setCanceledOnTouchOutside(false);
        alertDialog.setCancelable(false);
        alertDialog.setOnShowListener(dialog -> {
            onDialogShow(alertDialog);
        });
        return alertDialog;
    }

    private void initViews() {
        rootView = LayoutInflater.from(getContext())
                .inflate(R.layout.game_end_dialog, null, false);

        playerLayout = rootView.findViewById(R.id.layout_player);
        MaterialTextView textViewWinner = rootView.findViewById(R.id.winner);

        if (game.playerBlue.getSquares() > game.playerRed.getSquares()) {
            textViewWinner.setText("Blue win!");
        } else {
            textViewWinner.setText("Red win!");
        }

    }

    private void onDialogShow(AlertDialog dialog) {
        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(v -> {
            onDoneClickedPositiveButton();
        });

        Button negativeButton = dialog.getButton(AlertDialog.BUTTON_NEGATIVE);
        negativeButton.setOnClickListener(v -> {
            onDoneClickedNegativeButton();
        });
    }

    private void onDoneClickedPositiveButton() {
        gameActivity.returnToInitView();
        dismiss();
    }

    private void onDoneClickedNegativeButton() {
        gameActivity.restartActivity();
        dismiss();
    }
}