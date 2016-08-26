package com.example.veronica.my_first_app;

/**
 * Created by veronica on 12/08/16
 */
public class TrueFalse {
    private String mQuestion;

    private boolean mTrueQuestion;
    //default divisible value (every number is divisible by 1)
    private int firstDivisibleNumber ;

    public TrueFalse(String question, boolean trueQuestion, int divisibleBy){
        mQuestion = question;
        mTrueQuestion = trueQuestion;
        firstDivisibleNumber = divisibleBy;
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

    public int getFirstDivisibleNumber(){
        return firstDivisibleNumber;
    }

    public void setFirstDivisibleNumber(int number){
        firstDivisibleNumber = number;
    }
}
