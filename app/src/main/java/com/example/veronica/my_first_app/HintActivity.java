package com.example.veronica.my_first_app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

/**
 * Created by veronica on 24/08/16.
 */
public class HintActivity extends Activity{

    private static final String TAG = "HintActivity";

    private Button mShowHintButton;
    private TextView mShowHintTextView;

    private String mShowHintText;

    public static final String EXTRA_HINT_TEXT = "in.ac.iiitd.veronica1428.extra_hint_text";

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_hint);
        Log.d(TAG, "Inside onCreate method");

        //get the intent message value in mHintText string
        mShowHintText = getIntent().getStringExtra(EXTRA_HINT_TEXT);

        mShowHintTextView = (TextView)findViewById(R.id.showHint_TextViewID);

        //get view id of Hint Button
        mShowHintButton = (Button)findViewById(R.id.showHint_ButtonID);
        mShowHintButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mShowHintTextView.setText(mShowHintText);
                Log.d(TAG, "Inside onClick method of ShowHintButton");
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.d(TAG, "Inside onStart method");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.d(TAG, "Inside onResume method");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.d(TAG, "Inside onPause method");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.d(TAG, "Inside onStop method");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "Inside onDestroy method");
    }
}
