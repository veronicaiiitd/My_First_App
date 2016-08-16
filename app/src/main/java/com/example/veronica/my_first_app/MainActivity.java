package com.example.veronica.my_first_app;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final int NUM_COUNT = 1000;

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private TextView mQuestionTextView;

    private int mCurrentIndex = 0;
    private int mNumbersCount = 0;
    private int mRandomNumber = 0;

    private TrueFalse[] mQuestionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_pluto, false),
            new TrueFalse(R.string.question_prime, false),
            new TrueFalse(R.string.question_vipul, true),
            new TrueFalse(R.string.question_veronica, true)
    };

    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);

        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            Log.d(TAG, "ORIENTATION_LANDSCAPE");
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Log.d(TAG, "ORIENTATION_PORTRAIT");
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }

        //setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "Inside onStart() method");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG, "Inside onPause() method");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG, "Inside onResume() method");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d(TAG, "Inside onStop() method");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "Inside onDestroy() method");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Inside onCreate Method");
        setContentView(R.layout.activity_main);

        //System.out.print("mCurrentIndex: " + mCurrentIndex);

        mQuestionTextView = (TextView)findViewById(R.id.question_textViewID);
        updateQuestion();

        mTrueButton = (Button)findViewById(R.id.trueButtonID);
        mTrueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                checkAnswer(true);
            }
        });

        mFalseButton = (Button)findViewById(R.id.falseButtonID);
        mFalseButton.setOnClickListener(new View.OnClickListener(){
           @Override
            public void onClick(View view){
               checkAnswer(false);
           }
        });

        mNextButton = (Button)findViewById(R.id.nextButtonID);
        mNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

    }

    private void updateQuestion(){
        int randomNumber = getRandomNumber();
        randomNumber = mRandomNumber;

        String question = "Q" + ++mNumbersCount + " : Is " + randomNumber + " a prime number ? ";
        mQuestionTextView.setText(question);

    }

    private void checkAnswer(boolean userPressedTrue){

        //Boolean randomNumber = findPrimeNumber();
        
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();

        int toastMessageId = 0;

        if(userPressedTrue == answerIsTrue){
            toastMessageId = R.string.correct_toast;
        }else{
            toastMessageId = R.string.incorrect_toast;
        }

        Toast.makeText(this, toastMessageId, Toast.LENGTH_SHORT).show();
    }

    private int getRandomNumber(){
        Random randomNumber = new Random();
        return randomNumber.nextInt(NUM_COUNT);
    }

    private void findPrimeNumber(){

    }
}