package com.anncode.firebasecursoplatzi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PostMovieActivity extends AppCompatActivity {
    private static final String KEY_EXTRA_POST = "key_post";
    private TextView tvTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_movie);
        tvTitle = (TextView) findViewById(R.id.tvTitlePost);

        if (getIntent().getExtras() != null){
            tvTitle.setText(getIntent().getExtras().getString(KEY_EXTRA_POST));
        }

    }
}
