package com.example.veronica.my_first_app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by veronica on 22/08/16.
 */
public class CheatActivity extends Activity{

    private static final String TAG = "CheatActivity";
    private static boolean mAnswerIsTrue;
    public static final String EXTRA_ANSWER_IS_TRUE = "in.ac.iiitd.veronica1428.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN = "in.ac.iiitd.veronica1428.answer_shown";

    private TextView mCheatHintTextView;
    private Button mShowAnswerButton;

    protected void onStart(){
        super.onStart();
        Log.d(TAG, "Inside onStart method");
    }

    protected void onResume(){
        super.onResume();
        Log.d(TAG, "Inside onResume method");
    }

    protected void onPause(){
        super.onPause();
        Log.d(TAG, "Inside onPause method");
    }

    protected void onStop(){
        super.onStop();
        Log.d(TAG, "Inside onStop method");
    }

    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "Inside onDestroy method");
    }

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        Log.d(TAG, "Inside onCreate method");

        // "second argument" is default argument
        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mCheatHintTextView = (TextView) findViewById(R.id.cheatHint_textViewID);

        //Answer will not shown until the user presses the button
        setAnswerShownResult(false);

        mShowAnswerButton = (Button) findViewById(R.id.showAnswerButtonID);
        mShowAnswerButton.setOnClickListener(new View.OnClickListener(){
           @Override
            public void onClick(View view){

               if(mAnswerIsTrue){
                   mCheatHintTextView.setText(R.string.true_text);
               }else{
                   mCheatHintTextView.setText(R.string.false_text);
               }
               setAnswerShownResult(true);
           }
        });
    }

    private void setAnswerShownResult(boolean isAnswerShown){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN, isAnswerShown);

        setResult(RESULT_OK, data);
    }
}
