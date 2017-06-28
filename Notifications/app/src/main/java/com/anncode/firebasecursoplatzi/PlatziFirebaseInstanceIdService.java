package com.anncode.firebasecursoplatzi;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by anahisalgado on 22/04/17.
 */

public class PlatziFirebaseInstanceIdService extends FirebaseInstanceIdService {

    private static final String TAG = "PlatziInstanceIdService";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.w(TAG, "TokenRefresh: " + token);
    }
}
