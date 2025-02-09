package lb.edu.ul.tea;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

public class stress2 extends AppCompatActivity {
    private ImageView playPauseButton;
    private SeekBar seekBar;
    private MediaPlayer mediaPlayer;
    private boolean isPlaying = false;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stress2);

        // Initialize views
        playPauseButton = findViewById(R.id.playPauseButton);
        seekBar = findViewById(R.id.seekBar);

        // Retrieve the song resource ID from the Intent
        Intent intent = getIntent();
        int songResId = intent.getIntExtra("songResId", -1);

        if (songResId == -1) {
            // Exit if no valid song resource is passed
            finish();
            return;
        }

        // Initialize MediaPlayer with the received audio file
        mediaPlayer = MediaPlayer.create(this, songResId);
        seekBar.setMax(mediaPlayer.getDuration());

        // Update SeekBar progress every second
        handler.postDelayed(updateSeekBarRunnable, 1000);

        // Play/Pause Button Listener
        playPauseButton.setOnClickListener(v -> {
            if (isPlaying) {
                mediaPlayer.pause();
                playPauseButton.setImageResource(R.drawable.playy); // Replace with your play icon
            } else {
                mediaPlayer.start();
                playPauseButton.setImageResource(R.drawable.pausee); // Replace with your pause icon
            }
            isPlaying = !isPlaying;
        });

        // SeekBar Listener
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {}
        });

        // Reset button state when audio finishes
        mediaPlayer.setOnCompletionListener(mp -> {
            playPauseButton.setImageResource(R.drawable.playy); // Reset to play icon
            isPlaying = false;
            seekBar.setProgress(0); // Reset SeekBar
        });
    }

    // Runnable to update SeekBar progress in the main thread
    private Runnable updateSeekBarRunnable = new Runnable() {
        @Override
        public void run() {
            if (mediaPlayer != null && mediaPlayer.isPlaying()) {
                seekBar.setProgress(mediaPlayer.getCurrentPosition());
            }
            handler.postDelayed(this, 1000); // Update every second
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        handler.removeCallbacks(updateSeekBarRunnable); // Stop updating the SeekBar
    }
}
