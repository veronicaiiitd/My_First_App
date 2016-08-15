package com.example.veronica.my_first_app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private Button mTrueButton;
    private Button mFalseButton;
    private ImageButton mNextButton;
    private ImageButton mPrevButton;
    private TextView mQuestionTextView;

    private int mCurrentIndex = 0;

    private TrueFalse[] mQuestionBank = new TrueFalse[]{
            new TrueFalse(R.string.question_pluto, false),
            new TrueFalse(R.string.question_prime, false),
            new TrueFalse(R.string.question_vipul, true),
            new TrueFalse(R.string.question_veronica, true)
    };

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "Inside onStart() method");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG, "Insie onPause() method");
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

        mNextButton = (ImageButton)findViewById(R.id.nextImageButtonID);
        mNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        mPrevButton = (ImageButton)findViewById(R.id.prevImageButtonID);
        mPrevButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                if(mCurrentIndex - 1 < 0){
                    mCurrentIndex = (mQuestionBank.length - (mCurrentIndex + 1)) % mQuestionBank.length;
                } else{
                    mCurrentIndex = (mCurrentIndex - 1)  % mQuestionBank.length;
                }

                updateQuestion();
            }
        });
    }

    private void updateQuestion(){
        int question = mQuestionBank[mCurrentIndex].getQuestion();
        mQuestionTextView.setText(question);
    }

    private void checkAnswer(boolean userPressedTrue){
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();

        int toastMessageId = 0;

        if(userPressedTrue == answerIsTrue){
            toastMessageId = R.string.correct_toast;
        }else{
            toastMessageId = R.string.incorrect_toast;
        }

        Toast.makeText(this, toastMessageId, Toast.LENGTH_SHORT).show();
    }
}