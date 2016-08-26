package com.example.veronica.my_first_app;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String KEY_INDEX = "index";
    private static final int NUM_COUNT = 1000;
    private static final int QUESTION_COUNT = 5;

    private Button mTrueButton;
    private Button mFalseButton;
    private Button mNextButton;
    private Button mCheatButton;
    private Button mHintButton;
    private TextView mQuestionTextView;

    private boolean mTrueCheck = false;
    private boolean mFalseCheck = false;

    private int mCurrentIndex = 0;
    private boolean mIsCheater;

    private TrueFalse[] mQuestionBank =
            new TrueFalse[QUESTION_COUNT];


    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "Inside onActivityResult method");
        if(data == null){
            return;
        }

        mIsCheater = data.getBooleanExtra(CheatActivity.EXTRA_ANSWER_SHOWN, false);
    }

    /*
    @date: 17th Aug 2016
    @function: Function to save the state of all activities during
    change in device configuration
     */
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        Log.i(TAG, "onSavedInstanceState");
        savedInstanceState.putInt(KEY_INDEX, mCurrentIndex);
    }

    /*
    @date: 16th Aug 2016
    @function: Function called when device configuration changed
     */
    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);

        //Logging when orientation changes to 'landscape'
        if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            Log.d(TAG, "ORIENTATION_LANDSCAPE");
            //Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
        } else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            //Logging when orientation changes to ''
            Log.d(TAG, "ORIENTATION_PORTRAIT");
            //Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
        //setContentView(R.layout.activity_main);
    }

    /*
    @date: 13th Aug 2016
    @function: Function called when activity is started (if
    activity is already created but not started)
     */
    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "Inside onStart() method");
    }

    /*
    @date: 13th Aug 2016
    @function: Function called when acivity is paused (if
    activity is not in foreground but is partially visible)
     */
    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG, "Inside onPause() method");
    }

    /*
    @date: 13th Aug 2016
    @function: Function called when activity is resumed (if
    activity is in foreground and is visible)
     */
    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG, "Inside onResume() method");
    }

    /*
    @date: 13th Aug 2016
    @function: Function called when activity is stopped (if
    activity is not visible)
     */
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

        //Autopopulate the mQuestionBank Array with random generated values
        //Fill array only is array is not empty

        if(savedInstanceState == null && (mQuestionBank!=null)){
            for(int i=0; i < mQuestionBank.length; i++){
                //Set the value of generated random number in 'mRandomNumber' global variables
                int randomNumber = getRandomNumber();
                //Form the Question to be displayed on screen
                String question = "Q" + ++mCurrentIndex + " : Is " + randomNumber + " a prime number ? ";
                //program to calculate if generated number is prime or not
                //boolean isNumberPrime = findPrimeNumber(randomNumber, i);
                TrueFalse questionBankObj = findPrimeNumber(randomNumber, i);
                questionBankObj.setQuestion(questionBankObj.getQuestion()+question);

                mQuestionBank[i] = questionBankObj;
            }
            //set mCurrentIndex to 0 -- to iterate array from start
            mCurrentIndex = 0;
        }else{
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

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
                Log.d(TAG, "Inside onClick method of TrueButton");
                mTrueCheck = true;
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
               Log.d(TAG, "Inside onClick method of FalseButton");
               mFalseCheck = true;
               //check if the input for question given b user is same as actual answer.
               checkAnswer(false);
           }
        });


        //set a listener on 'Hint' button to describe the activity for further
        //proceedings
        mHintButton = (Button)findViewById(R.id.hint_ButtonID);
        mHintButton.setOnClickListener(new View.OnClickListener(){
           @Override
            public void onClick(View view){
               Log.d(TAG, "Inside onClick method of HintButton");
               String hintString="";
               //create an intent to pass to startActivity method (to pass to HintActivity)
               Intent i = new Intent(MainActivity.this, HintActivity.class);
               int divisibleBy = mQuestionBank[mCurrentIndex].getFirstDivisibleNumber();
               //check if the number greater than 1 (since default value
               // is 1 as every number is divisible by 1)
               if(divisibleBy != 1){
                   //means number is not prime
                    hintString = hintString + "Check divisibility by" + divisibleBy;
               }else{
                   //number is prime
                    hintString = hintString + "Number is divisible by 1 and Itself";
               }

               i.putExtra(HintActivity.EXTRA_HINT_TEXT, hintString);
               startActivity(i);
           }
        });

        //set a listener on 'Cheat' button to describe the activity for further
        //proceedings
        mCheatButton = (Button)findViewById(R.id.cheatButtonID);
        mCheatButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Log.d(TAG, "Inside onClick method of CheatButton");
                //create an intent to pass into startActivity method (to pass to CheatActivity)
                Intent i = new Intent(MainActivity.this, CheatActivity.class);
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();
                i.putExtra(CheatActivity.EXTRA_ANSWER_IS_TRUE,answerIsTrue);
                startActivityForResult(i,0);
            }
        });

        //set a listener on 'Next' button to describe the activity for further
        //proceedings -- to display next random generated question
        mNextButton = (Button)findViewById(R.id.nextButtonID);
        mNextButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                Log.d(TAG, "Inside onClick method of NextButton");
                if((mTrueCheck == true) || (mFalseCheck == true)){
                    resetChecks();
                    mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                    //set the mIsCheater boolean variable to false
                    mIsCheater = false;
                    //update the random generated value question
                    updateQuestion();
                }else{
                    int toastPressOptionID = R.string.pressOption;
                    Toast.makeText(MainActivity.this, toastPressOptionID, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /*
    @date:
    @function:
     */
    private void resetChecks(){
        mTrueCheck = false;
        mFalseCheck = false;
    }

    /*
    @date: 14th Aug 2016
    @function: Function to set the Generated question in TrueFalse Class
     */
    private void updateQuestion(){

        String question = mQuestionBank[mCurrentIndex].getQuestion();
        //set the value of question in TrueFalse Class
        mQuestionTextView.setText(question);

    }

    /*
    @date: 14th Aug 2016
    @function: Function to test if answer entered by user and Actual answer matches or not
    @param: Boolean (value entered by user)
     */
    private void checkAnswer(boolean userPressedTrue){

        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isTrueQuestion();

        int toastMessageId = 0;

        if(mIsCheater){
            //if user has cheated the answer, display the appropriate reply
            toastMessageId = R.string.judgment_toast;
        }else{
            //check if userPressed input is same as Actual answer
            if(userPressedTrue == answerIsTrue){
                // if same -- put value of 'toastMessageId' as 'Correct'
                toastMessageId = R.string.correct_toast;
            }else{
                //otherwise 'Incorrect'
                toastMessageId = R.string.incorrect_toast;
            }
        }

        Toast.makeText(this, toastMessageId, Toast.LENGTH_SHORT).show();
    }

    /*
    @date: 16th Aug 2016
    @function: Function to Generate a single random number between 1 - NUM_COUNT
     */
    private int getRandomNumber(){
        //Log.d(TAG, "Inside getRandomNumber() method");
        Random randomNumber = new Random();
        return (randomNumber.nextInt(NUM_COUNT));

    }

    /*
    @date: 17th Aug 2016
    @function: Function to calculate whether the randomly generated number is prime or not
    @return: Boolean value indicating whether number is prime or not
     */
    private TrueFalse findPrimeNumber(int randomNumber, int index) {

        //Log.d(TAG, "Inside findPrimeNumber() method");
        //flag to determine whether number is indivisible or not
        boolean isDivisible = false;
        int divisibleNumber = 1;

        // '0', '1' and '2' are prime number
        for (int i = 2; i < randomNumber; i++) {
            if (randomNumber % i == 0) {
                isDivisible = true;
                divisibleNumber = i;
                break;
            }
        }

        if (isDivisible == false) {
            //number is a prime number
            return (new TrueFalse("",true,divisibleNumber));
            //return true;
        } else
            //number is not prime
            return (new TrueFalse("",false,divisibleNumber));
            //return false;
        }
}
