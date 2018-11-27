package com.example.jam.quizandanswer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPreviousButton;
    private TextView mquestionText;

    //初始化问题
    private Question[] mQuestions = new Question[]{
            new Question(R.string.question1, true),
            new Question(R.string.question2, false),
            new Question(R.string.question3,false),
            new Question(R.string.question4,true)
    };

    private int mCurrentIndex = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //绑定UI
        mTrueButton = (Button) findViewById(R.id.chooseTrue);
        mFalseButton = (Button) findViewById(R.id.chooseFalse);
        mNextButton = (Button) findViewById(R.id.chooseNext);
        mPreviousButton = (Button) findViewById(R.id.choosePrevious);

        mquestionText = (TextView) findViewById(R.id.questionTextView);

        //为“确认”键设定监听器
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });
        //为“否认”键设定监听器
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });
        //为“下一个”键设定监听器
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex+1)%(mQuestions.length);
                updataQuestion();
            }
        });
        //为“上一个”键设定监听器
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((mCurrentIndex-1)<0) {
                    mCurrentIndex = mQuestions.length - 1;
                }else{
                    mCurrentIndex = (mCurrentIndex-1)%(mQuestions.length);
                }
                updataQuestion();
            }
        });

        updataQuestion();
    }

    private void updataQuestion(){
        //显示问题
        int question = mQuestions[mCurrentIndex].getQuestionId();
        mquestionText.setText(question);
    }

    private void checkAnswer(boolean judgement){
        boolean settingAnswer = mQuestions[mCurrentIndex].isAnswer();
        int judgementPrint = 0;
        if(judgement == settingAnswer)
            judgementPrint = R.string.true_Toast;
        else
            judgementPrint = R.string.false_Toast;
        Toast.makeText(getApplicationContext(),judgementPrint,Toast.LENGTH_SHORT).show();
    }


}
