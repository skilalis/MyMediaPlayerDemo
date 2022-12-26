package com.example.mymediaplayerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton playButton,pauseButton;
    private MediaPlayer mediaPlayer;
    private TextView titleTextView , duraitonTextView;
    int[] songName ={R.raw.ae_allah_tu_hi_atta,R.raw.main_to_ummati_hoon};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playButton = findViewById(R.id.playButtonId);
        pauseButton = findViewById(R.id.pauseButtonId);
        titleTextView =findViewById(R.id.titleTextViewId);
        duraitonTextView = findViewById(R.id.durationTextViewId);


        mediaPlayer = MediaPlayer.create(this,R.raw.ae_allah_tu_hi_atta);

        playButton.setOnClickListener(this);
        pauseButton.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.playButtonId){

            if (mediaPlayer != null) {
                mediaPlayer.start();
                int duration = mediaPlayer.getDuration()/1000;
                Toast.makeText(this, "song played" + duration, Toast.LENGTH_SHORT).show();

            }

        }
        if (v.getId() == R.id.pauseButtonId) {
            if (mediaPlayer != null) {
                mediaPlayer.pause();
                Toast.makeText(this, "song paused", Toast.LENGTH_SHORT).show();

            }
        }


    }
    public void showDetails() {
        int count = songName.length;
        titleTextView.setText(songName[count]);
        long finalTime = mediaPlayer.getDuration();
        duraitonTextView.setText(String.format("%d:%d",
                TimeUnit.MILLISECONDS.toMinutes((long) finalTime),
                TimeUnit.MILLISECONDS.toSeconds((long) finalTime)- TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes((long)finalTime)) ));
        
    }

    @Override
    protected void onDestroy() {
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer=null;
        }
        super.onDestroy();
    }

}