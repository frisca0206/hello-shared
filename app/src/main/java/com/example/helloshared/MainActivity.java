package com.example.helloshared;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private int mCount = 0;
    private int mColor;
    private TextView mShowCountTextView;

    private final String COUNT_KEY = "count";
    private final String COLOR_KEY = "color";
    private SharedPreferences mPreferences;

    private static final String mSharedPrefFile = "com.example.helloshared";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mShowCountTextView = (TextView) findViewById(R.id.count_textview);
        mColor = ContextCompat.getColor(this, R.color.default_background);
        mPreferences = getSharedPreferences(mSharedPrefFile, MODE_PRIVATE);

            mCount = mPreferences.getInt(COUNT_KEY, 0);
            mShowCountTextView.setText(String.format("%s", mCount));
            mColor = mPreferences.getInt(COLOR_KEY, mColor);
            mShowCountTextView.setBackgroundColor(mColor);
        }

    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putInt(COUNT_KEY, mCount);
        preferencesEditor.putInt(COUNT_KEY, mCount);
        preferencesEditor.apply();
    }

    public void changebackground(View view) {
        int color = ((ColorDrawable) view.getBackground()).getColor();
        mShowCountTextView.setBackgroundColor(color);
        mColor = color;
    }

    public void countup(View view) {
        mCount++;
        mShowCountTextView.setText(String.format("%s", mCount));
    }
    public void Reset(View view) {
        mCount = 0;
        mShowCountTextView.setText(String.format("%s", mCount));

        mColor = ContextCompat.getColor(this, R.color.default_background);
        mShowCountTextView.setBackgroundColor(mColor);

        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.clear();
        preferencesEditor.apply();
    }
}