package com.example.veronica.my_first_app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.content.Intent;

/**
 * Created by veronica on 24/08/16.
 */
public class HintActivity extends Activity{

    private static final String TAG = "HintActivity";

    private Button mShowHintButton;
    private TextView mShowHintTextView;

    public static final String EXTRA_HINT_SHOWN = "in.ac.iiitd.veronica1428.extra_hint_shown";

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_hint);
        Log.d(TAG, "Inside onCreate method");

        mShowHintTextView = (TextView)findViewById(R.id.showHint_TextViewID);

        setHintShownResult(false);

        //get view id of Hint Button
        mShowHintButton = (Button)findViewById(R.id.showHint_ButtonID);
        mShowHintButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                mShowHintTextView.setText(R.string.hint_text);
                Log.d(TAG, "Inside onClick method of ShowHintButton");

                setHintShownResult(true);
            }
        });
    }

    private void setHintShownResult(boolean isHintShown){
        Intent data = new Intent();

        data.putExtra(EXTRA_HINT_SHOWN, isHintShown);
        setResult(RESULT_OK, data);
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
