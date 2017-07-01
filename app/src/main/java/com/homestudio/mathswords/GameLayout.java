package com.homestudio.mathswords;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.homestudio.mathswords.games.ResultFastMathGame;

/**
 * Created by jbleo on 29/06/2017.
 */

public class GameLayout extends RelativeLayout implements View.OnClickListener {
    private TextView mPreAnswer;
    private TextView mPostAnswer;
    private EditText mAnswer;

    private ResultFastMathGame game;

    public GameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public GameLayout(Context context){
        this (context, null);    }

    private void init(Context context){
        inflate(context, R.layout.view_result_game, this);
        mPreAnswer = (TextView)findViewById(R.id.text_pre_answer);
        mPostAnswer = (TextView)findViewById(R.id.text_post_answer);
        mAnswer = (EditText) findViewById(R.id.answer);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
        findViewById(R.id.button7).setOnClickListener(this);
        findViewById(R.id.button8).setOnClickListener(this);
        findViewById(R.id.button9).setOnClickListener(this);
        findViewById(R.id.button10).setOnClickListener(this);

    }

    public String getAnswer(){
        return mAnswer.getText().toString();
    }

    public void setGame(String game) {
        mPreAnswer.setText(game.substring(0, game.indexOf('?')));
        mPostAnswer.setText(game.substring(game.indexOf('?')+1));
        mAnswer.setText("");
        invalidate();
        requestLayout();
    }


    @Override
    public void onClick(View view) {
        if (view instanceof Button) {
            mAnswer.setText(mAnswer.getText().toString() + ((Button)view).getText());
        }
    }
}
