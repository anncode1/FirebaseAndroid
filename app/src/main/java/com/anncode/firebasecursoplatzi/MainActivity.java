package com.anncode.firebasecursoplatzi;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

public class MainActivity extends AppCompatActivity {

    private FirebaseRemoteConfig remoteConfig;

    private LinearLayout linearLayout;
    private ImageView imvLogo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linearLayout = (LinearLayout) findViewById(R.id.activity_main);
        imvLogo = (ImageView) findViewById(R.id.imvLogo);

        remoteConfig = FirebaseRemoteConfig.getInstance();

        FirebaseRemoteConfigSettings remoteConfigSettings
                = new FirebaseRemoteConfigSettings.Builder()
                .setDeveloperModeEnabled(true)
                .build();

        remoteConfig.setConfigSettings(remoteConfigSettings);
        remoteConfig.setDefaults(R.xml.remote_config_defaults);

        setConfigurationView();

    }

    private void setConfigurationView(){
        linearLayout.setBackgroundColor(Color.parseColor(remoteConfig.getString("color_background")));
        Log.w("NAME", "name: " + remoteConfig.getString("image_background"));
        if (remoteConfig.getString("image_background").equals("happyface")){
            imvLogo.setImageResource(R.drawable.happyface);
        }else if(remoteConfig.getString("image_background").equals("pikachuchistmas")){
            imvLogo.setImageResource(R.drawable.pikachuchistmas);
        }else if(remoteConfig.getString("image_background").equals("valentinesday")){
            imvLogo.setImageResource(R.drawable.valentinesday);
        }
    }

    public void syncroinizeData(View view){
        long cacheExpiration = 3600;

        if (remoteConfig.getInfo().getConfigSettings().isDeveloperModeEnabled()){
            cacheExpiration = 0;
        }

        remoteConfig.fetch(cacheExpiration).addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(MainActivity.this, "Syncronize Done", Toast.LENGTH_SHORT).show();
                    remoteConfig.activateFetched();
                }else{
                    Toast.makeText(MainActivity.this, "Syncronize Fail", Toast.LENGTH_SHORT).show();
                }

                setConfigurationView();
            }
        });

    }
}
