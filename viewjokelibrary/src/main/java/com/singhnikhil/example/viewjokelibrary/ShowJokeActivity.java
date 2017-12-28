package com.singhnikhil.example.viewjokelibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ShowJokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_joke);
        String joke = getIntent().getStringExtra(getString(R.string.key_joke));
        TextView jokeTV = (TextView) findViewById(R.id.tvjoke);
        jokeTV.setText(joke);
    }
}
