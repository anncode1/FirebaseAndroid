package com.anncode.firebasecursoplatzi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.crash.FirebaseCrash;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivty";
    private EditText edtNumber1;
    private EditText edtNumber2;
    private Button btnDivide;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNumber1 = (EditText) findViewById(R.id.edtNumber1);
        edtNumber2 = (EditText) findViewById(R.id.edtNumber2);
        btnDivide = (Button) findViewById(R.id.btnDivide);
        tvResult = (TextView) findViewById(R.id.tvResult);

    }

    public void divide(View view){
        int n1 = Integer.parseInt(edtNumber1.getText().toString());
        int n2 = Integer.parseInt(edtNumber2.getText().toString());

        try {
            tvResult.append(String.valueOf(n1/n2));
        }catch (NumberFormatException nfe){
//            Log.e(TAG, "NumberFormatException");
            FirebaseCrash.report(nfe);
            FirebaseCrash.log("Ocurrió un NumberFormatException");
            FirebaseCrash.logcat(Log.ERROR, TAG, "NumberFormatException Logcat");
            nfe.printStackTrace();

        }catch (NullPointerException npe){
//            Log.e(TAG, "NullPointerException");
            FirebaseCrash.report(npe);
            FirebaseCrash.log("Ocurrió un NullPointerException");
            FirebaseCrash.logcat(Log.ERROR, TAG, "NullPointerException Logcat");
            npe.printStackTrace();
        }catch (Exception e){
//            Log.e(TAG, "Exception");
            FirebaseCrash.report(e);
            FirebaseCrash.log("Ocurrió un Exception");
            FirebaseCrash.logcat(Log.ERROR, TAG, "Exception Logcat");
            e.printStackTrace();
        }
    }
}
