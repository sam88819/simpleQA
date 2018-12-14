package com.example.jam.quizandanswer;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mPreviousButton;
    private Button mCheatButton;
    private TextView mquestionText;
    private static final String EXTRA_ANSWER_IS_TRUE =
            "com.bignerdranch.android.geoquiz.answer_is_true";
    private static final int REQUEST_CODE_ISCHEAT=0;

    //初始化问题
    private Question[] mQuestions = new Question[]{
            new Question(R.string.question1, true),
            new Question(R.string.question2, false),
            new Question(R.string.question3,false),
            new Question(R.string.question4,true)
    };

    private int mCurrentIndex = 0;
    private boolean mIsCheater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //绑定UI
        mTrueButton = (Button) findViewById(R.id.chooseTrue);
        mFalseButton = (Button) findViewById(R.id.chooseFalse);
        mNextButton = (Button) findViewById(R.id.chooseNext);
        mPreviousButton = (Button) findViewById(R.id.choosePrevious);
        mCheatButton = (Button) findViewById(R.id.getAnswer);

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
        //为“作弊”键设定监听器
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean answerIsTrue = mQuestions[mCurrentIndex].isAnswer();
                Intent i = cheatActivity.newIntent(MainActivity.this,answerIsTrue);
                startActivityForResult(i,REQUEST_CODE_ISCHEAT);
            }
        });

        updataQuestion();
    }

    /**
     * 函数：updataQuestion()
     * 通过mCurrentIndex指定问题的下标，然后将对应的问题显示出来
     *
     */
    private void updataQuestion(){
        //显示问题
        int question = mQuestions[mCurrentIndex].getQuestionId();
        mquestionText.setText(question);
    }

    private void checkAnswer(boolean judgement){
        boolean settingAnswer = mQuestions[mCurrentIndex].isAnswer();
        int judgementPrint = 0;
        if(mIsCheater){
            judgementPrint = R.string.judgement_Toast;
        }else{
            if(judgement == settingAnswer)
                judgementPrint = R.string.true_Toast;
            else
                judgementPrint = R.string.false_Toast;
        }
        Toast.makeText(getApplicationContext(),judgementPrint,Toast.LENGTH_SHORT).show();
    }

    /**
     * 通过getMenuInflater方法获得对象，
     * 第一个参数是指定我们通哪个资源文件创建菜单，第二个参数是指定我们的菜单添加到哪个Menu对象中
     * @param menu
     * @return
     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(resultCode != Activity.RESULT_OK)
        {
            return;
        }
        if(requestCode == REQUEST_CODE_ISCHEAT)
        {
            if(data == null)
            {
                return;
            }
            mIsCheater = cheatActivity.wasAnswerShown(data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
