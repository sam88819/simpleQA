package com.example.jam.quizandanswer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * @author Jam_pang
 * @package com.example.jam.quizandanswer
 * @fileName cheatActivity
 * @date on 2018/12/11 17:06
 * @github https://github.com/sam88819
 **/
public class cheatActivity extends AppCompatActivity {
    private static final String EXTRA_ANSWER_IS_TRUE = "com.bignerdranch.android.geoquiz.answer_is_true";
    private boolean mAnswerIsTrue;
    private TextView mTextView;
    private Button mButton;


    /**
     * @name newIntent
     * @function 封装Intent方法，传达对应答案到cheatActivity
     * @param packageContext
     * @param answerIsTrue
     * @return
     */
    public static Intent newIntent(Context packageContext,boolean answerIsTrue)
    {
        Intent i = new Intent(packageContext,cheatActivity.class);
        i.putExtra(EXTRA_ANSWER_IS_TRUE,answerIsTrue);
        return i;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.answer);
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,false);
        mTextView = (TextView)findViewById(R.id.answer);
        mButton = (Button)findViewById(R.id.getAnswerButton);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAnswerIsTrue){
                    mTextView.setText(R.string.choice_true);
                }else{
                    mTextView.setText(R.string.choice_false);
                }
            }
        });
    }
}
