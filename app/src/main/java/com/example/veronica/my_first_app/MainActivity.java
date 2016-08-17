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

    private TrueFalse mQuestionBank;

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

    /*
    @date: 14th Aug 2016
    @function: Function called to destroy the activity and free the resources
    allotted to current activity
     */
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "Inside onDestroy() method");
    }

    /*
    @date: 13th Aug 2016
    @function: This is an overloaded function of Activity class.
    It controls the link between the View layer and Model layer.
    This method is used to create an activity on startup. It initializes
    the widgets on of android application and captures
    the data from model layer to be viewed on View layer.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //call the super class constructor
        super.onCreate(savedInstanceState);
        //logging the information -- debug log
        Log.d(TAG, "Inside onCreate Method");
        setContentView(R.layout.activity_main);

        //capture the view id used to display the question
        mQuestionTextView = (TextView)findViewById(R.id.question_textViewID);
        //update the random generated value question
        updateQuestion();

        //set a listener on 'True' button to describe the activity for further
        //proceedings
        mTrueButton = (Button)findViewById(R.id.trueButtonID);
        mTrueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //check if the input for question given b user is same as actual answer.
                checkAnswer(true);
            }
        });

        //set a listener on 'False' button to describe the activity for further
        //proceedings
        mFalseButton = (Button)findViewById(R.id.falseButtonID);
        mFalseButton.setOnClickListener(new View.OnClickListener(){
           @Override
            public void onClick(View view){
               //check if the input for question given b user is same as actual answer.
               checkAnswer(false);
           }
        });

        //set a listener on 'Next' button to describe the activity for further
        //proceedings -- to display next random generated question
        mNextButton = (Button)findViewById(R.id.nextButtonID);
        mNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //update the random generated value question
                updateQuestion();
            }
        });
    }

    /*
    @date: 14th Aug 2016
    @function: Function to set the Generated question in TrueFalse Class
     */
    private void updateQuestion(){
        //Set the value of generated random number in 'mRandomNumber' global variables
        getRandomNumber();

        //Form the Question to be displayed on screen
        String question = "Q" + ++mNumbersCount + " : Is " + mRandomNumber + " a prime number ? ";
        //set the value of question in TrueFalse Class
        mQuestionTextView.setText(question);
    }

    /*
    @date: 14th Aug 2016
    @function: Function to test if answer entered by user and Actual answer matches or not
    @param: Boolean (value entered by user)
     */
    private void checkAnswer(boolean userPressedTrue){

        //program to calculate if generated number is prime or not
        findPrimeNumber();

        boolean answerIsTrue = mQuestionBank.isTrueQuestion();

        int toastMessageId = 0;

        //check if userPressed input is same as Actual answer
        if(userPressedTrue == answerIsTrue){
            // if same -- put value of 'toastMessageId' as 'Correct'
            toastMessageId = R.string.correct_toast;
        }else{
            //otherwise 'Incorrect'
            toastMessageId = R.string.incorrect_toast;
        }

        Toast.makeText(this, toastMessageId, Toast.LENGTH_SHORT).show();
    }

    /*
    @date: 16th Aug 2016
    @function: Function to Generate a single random number between 1 - NUM_COUNT
     */
    private void getRandomNumber(){
        Random randomNumber = new Random();
        mRandomNumber = randomNumber.nextInt(NUM_COUNT);
    }

    /*
    @date: 17th Aug 2016
    @function: Function to calculate whether the randomly generated number is prime or not
     */
    private void findPrimeNumber() {

    //flag to determine whether number is indivisible or not
    boolean isDivisible = false;

    // '0', '1' and '2' are prime number
    for (int i = 2; i <= mRandomNumber; i++) {
        if (mRandomNumber % i == 0) {
            isDivisible = true;
            break;
        }
    }

    if (isDivisible == false) {
        //number is a prime number
        mQuestionBank.setTrueQuestion(true);
    } else
        //number is not prime
        mQuestionBank.setTrueQuestion(false);
    }
}
