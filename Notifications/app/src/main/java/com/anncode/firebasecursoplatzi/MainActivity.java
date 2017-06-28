package com.anncode.firebasecursoplatzi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String KEY_DESCOUNT = "descount_key";

    private TextView tvDescountMessage;
    private Button btnSuscribeAndroid;
    private Button btnSuscribeFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDescountMessage = (TextView) findViewById(R.id.tvDescountMessage);
        btnSuscribeAndroid = (Button) findViewById(R.id.btnSuscribeAndroid);
        btnSuscribeFirebase = (Button) findViewById(R.id.btnSuscribeFirebase);
        tvDescountMessage.setVisibility(View.GONE);



        String token = FirebaseInstanceId.getInstance().getToken();
        Log.w(TAG, "token: " + token);

        if (getIntent().getExtras() != null){
            tvDescountMessage.setVisibility(View.VISIBLE);
            String descount = getIntent().getExtras().getString(KEY_DESCOUNT);
            tvDescountMessage.append(descount);
        }

    }

    public void suscribeAndroid(View view){
        FirebaseMessaging.getInstance().subscribeToTopic("Android");
        Toast.makeText(this, "Felicidades te suscribiste a Android", Toast.LENGTH_SHORT).show();
    }

    public void suscribeFirebase(View view){
        FirebaseMessaging.getInstance().subscribeToTopic("Firebase");
        Toast.makeText(this, "Felicidades te suscribiste a Firebase", Toast.LENGTH_SHORT).show();
    }
}
