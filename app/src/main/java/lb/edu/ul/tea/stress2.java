package lb.edu.ul.tea;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class stress2 extends AppCompatActivity {
    private ImageView playPauseButton;
    private SeekBar seekBar;
    private MediaPlayer mediaPlayer;
    private boolean isPlaying = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
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

        // Update SeekBar progress
        new Thread(() -> {
            while (mediaPlayer != null) {
                try {
                    if (mediaPlayer.isPlaying()) {
                        seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    }
                    Thread.sleep(1000); // Update every second
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}