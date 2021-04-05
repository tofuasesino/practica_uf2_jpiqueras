package com.example.joelPiquerasGarcia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
private static final String VIDEO_SAMPLE = "nevergiveup";
private VideoView videoView;
private Button button1;
private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.activity2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity2();
            }
        });

        button2 = findViewById(R.id.activity3);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity3();
            }
        });



        videoView = findViewById(R.id.taContento);


        MediaController mediaController = new MediaController(this);
        mediaController.setMediaPlayer(videoView);
        videoView.setMediaController(mediaController);


    }

    public void openActivity2(){
        Intent intent = new Intent(this, Activity2.class);
        startActivity(intent);
    }

    public void openActivity3(){
        Intent intent = new Intent(this, Activity3.class);
        startActivity(intent);
    }

    private Uri getMedia(String mediaName) {
        return Uri.parse("android.resource://" + getPackageName() + "/raw/" + mediaName);
    }

    private void initializePlayer() {
        Uri videoUri = getMedia(VIDEO_SAMPLE);
        videoView.setVideoURI(videoUri);
        videoView.start();
    }

    private void releasePlayer() {
        videoView.stopPlayback();
    }

    @Override
    protected void onStart(){
        super.onStart();

        initializePlayer();
    }

    @Override
    protected void onStop(){
        super.onStop();

        releasePlayer();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            videoView.pause();
        }
    }
}