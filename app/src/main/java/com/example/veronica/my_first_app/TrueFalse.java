package com.example.veronica.my_first_app;

/**
 * Created by veronica on 12/08/16
 */
public class TrueFalse {
    private String mQuestion;

    private boolean mTrueQuestion;

    public TrueFalse(String question, boolean trueQuestion){
        mQuestion = question;
        mTrueQuestion = trueQuestion;
    }

    public String getQuestion(){
        return mQuestion;
    }

    public void setQuestion(String question){
        mQuestion = question;
    }

    public boolean isTrueQuestion(){
        return mTrueQuestion;
    }

    public void setTrueQuestion(boolean trueQuestion){
        mTrueQuestion = trueQuestion;
    }
}
