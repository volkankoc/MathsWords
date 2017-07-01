package com.homestudio.mathswords;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.homestudio.mathswords.games.Game;
import com.homestudio.mathswords.games.GameFactory;
import com.homestudio.mathswords.games.ResultFastMathGame;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout mLayout;
    private TextView mTextCounter;
    private TextView mTextMark;
    private GameLayout mGameLayout;
    private Button mCheckButton;
    private ProgressBar mProgressBar;
    private TextView mTextEnd;

    private Game game;
    private int mark=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mTextCounter = (TextView) findViewById(R.id.text_counter);
        mTextMark = (TextView) findViewById(R.id.text_mark);
        mTextEnd = (TextView) findViewById(R.id.text_end);
        mLayout = (LinearLayout) findViewById(R.id.layout);
        mGameLayout = (GameLayout) findViewById(R.id.game_layout);
        mCheckButton = (Button) findViewById(R.id.button);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        mTextCounter.setText("3");

        new CountDownTimer(3500, 1000) {
            public void onTick(long millisUntilFinished) {
                mTextCounter.setText(""+millisUntilFinished / 1000);
            }
            public void onFinish() {
                mTextCounter.setVisibility(View.INVISIBLE);
                mLayout.setVisibility(View.VISIBLE);
                mTextMark.setText("0");
                startGame(null);

                new CountDownTimer(getResources().getInteger(R.integer.fastGameTime), 500) {
                    public void onTick(long millisUntilFinished) {
                        mProgressBar.setProgress((int) (getResources().getInteger(R.integer.fastGameTime) - millisUntilFinished));
                    }
                    public void onFinish() {
                        mProgressBar.setProgress(getResources().getInteger(R.integer.fastGameTime));
                        mLayout.setVisibility(View.INVISIBLE);
                        mTextMark.setVisibility(View.INVISIBLE);
                        mTextEnd.setVisibility(View.VISIBLE);
                        mTextEnd.setText(String.format(getResources().getString(R.string.mark),mark));
                    }
                }.start();
            }
        }.start();

        mCheckButton.setOnClickListener(this);
    }

    private void startGame(String strGame){
        if (strGame != null){
            game = GameFactory.parseGame(strGame);
        }else{
            game = GameFactory.createMathGame();
        }
        mGameLayout.setGame(game.toString());
    }

    @Override
    public void onClick(View view) {
        String answer = mGameLayout.getAnswer();
        if (game.evaluate(answer)){
            mark++;
            mTextMark.setText("" + mark);
        }
        startGame(null);
    }
}
