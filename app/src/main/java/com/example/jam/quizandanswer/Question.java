package com.example.jam.quizandanswer;

/**
 * @author Jam_pang
 * @package com.example.jam.quizandanswer
 * @fileName Question
 * @date on 2018/11/27 15:53
 * @github https://github.com/sam88819
 **/
public class Question {
    //设定问题编号mQuestionId以及问题的答案mAnswer
    private int mQuestionId;
    private boolean mAnswer;

    public Question(int id,boolean answer)
    {
        mQuestionId = id;
        mAnswer = answer;
    }

    public int getQuestionId() {
        return mQuestionId;
    }

    public void setQuestionId(int questionId) {
        mQuestionId = questionId;
    }

    public boolean isAnswer() {
        return mAnswer;
    }

    public void setAnswer(boolean answer) {
        mAnswer = answer;
    }
}
