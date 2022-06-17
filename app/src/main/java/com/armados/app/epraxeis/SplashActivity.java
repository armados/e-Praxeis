package com.armados.app.epraxeis;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.TextView;

public class SplashActivity extends BaseActivity {
    private Handler mHandler;
    private Runnable longRunningTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final TextView txtVersionName = findViewById(R.id.txtVersionName);

        PackageManager manager = this.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(this.getPackageName(), PackageManager.GET_ACTIVITIES);

            txtVersionName.setText(String.format("Έκδοση %s", info.versionName));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }


        mHandler = new Handler();

        longRunningTask = new Runnable() {
            @Override
            public void run() {
                Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(mainIntent);
                finish();
            }
        };

        mHandler.postDelayed(longRunningTask, Config.SPLASH_DISPLAY_LENGTH);
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