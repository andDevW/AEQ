package com.anddevw.aeq;


import android.content.Intent;
import android.media.audiofx.AudioEffect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static android.media.audiofx.AudioEffect.CONTENT_TYPE_MUSIC;
import static android.media.audiofx.AudioEffect.EXTRA_AUDIO_SESSION;
import static android.media.audiofx.AudioEffect.EXTRA_CONTENT_TYPE;


// AEQ - Android AudioEffect EQ
// More info https://developer.android.com/reference/android/media/audiofx/AudioEffect.html
// Created by andDevW(Andrew Wright) ©2017.
// https://andDevW.com



public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_EQ = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openEqualizer();
    }

    public void openEqualizer() {

        Intent eqIntent = new Intent(AudioEffect.ACTION_DISPLAY_AUDIO_EFFECT_CONTROL_PANEL);
        eqIntent.putExtra(EXTRA_CONTENT_TYPE, CONTENT_TYPE_MUSIC);
        eqIntent.putExtra(EXTRA_AUDIO_SESSION, CONTENT_TYPE_MUSIC);

        if ((eqIntent.resolveActivity(getPackageManager()) != null)) {
            startActivityForResult(eqIntent, REQUEST_EQ);
            finish();
        } else {
            quickReturn();
        }
    }

    public void quickReturn() {
        Intent goHome = new Intent(Intent.ACTION_MAIN);
        goHome.addCategory(Intent.CATEGORY_HOME);
        goHome.setPackage("com.android.launcher");
        goHome.addCategory(Intent.CATEGORY_LAUNCHER);
        goHome.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(goHome);
    }

    @Override
    public void onResume() {
        super.onResume();  // Always call the superclass method first
        openEqualizer();
    }
}
