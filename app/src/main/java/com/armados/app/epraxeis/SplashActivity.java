package com.armados.app.epraxeis;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends BaseActivity {
    private Handler mHandler;
    private Runnable longRunningTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        mHandler = new Handler();

        longRunningTask = new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        };

        mHandler.postDelayed(longRunningTask, Configuration.SPLASH_DISPLAY_LENGTH);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        // Terminate application when user click back button
        exitApplication();
    }

    private void exitApplication() {
        // Remove active runnable task from handler
        mHandler.removeCallbacks(longRunningTask);

        // Finish Splash Activity
        finish();
    }

}