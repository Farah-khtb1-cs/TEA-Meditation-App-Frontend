
package lb.edu.ul.tea;

import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class yoga_ex extends AppCompatActivity {
    private VideoView videoView;
    private TextView timerTextView;
    private TextView counterTextView;
    private Button startPauseButton;
    private CountDownTimer countDownTimer;
    private boolean timerRunning;
    private long timeLeftInMillis = 45000; // 45 seconds
    private int repsCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_yoga_ex);

        // Initialize views
        videoView = findViewById(R.id.videoView);
        timerTextView = findViewById(R.id.timerTextView);
        counterTextView = findViewById(R.id.counterTextView);
        startPauseButton = findViewById(R.id.startPauseButton);

        // Set up the video
        Uri videoUri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.exercice_video); // Replace with your video file
        videoView.setVideoURI(videoUri);
        videoView.start();

        // Set click listener for the Start/Pause button
        startPauseButton.setOnClickListener(v -> {
            if (timerRunning) {
                pauseTimer();
            } else {
                startTimer();
            }
        });

        // Update the timer text initially
        updateTimerText();
    }
    private void startTimer() {
        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateTimerText();
            }

            @Override
            public void onFinish() {
                timerRunning = false;
                startPauseButton.setText("Start");
                repsCount++;
                counterTextView.setText("Reps: " + repsCount);
                timeLeftInMillis = 45000; // Reset timer to 45 seconds
                updateTimerText();
            }
        }.start();

        timerRunning = true;
        startPauseButton.setText("Pause");
    }

    private void pauseTimer() {
        countDownTimer.cancel();
        timerRunning = false;
        startPauseButton.setText("Start");
    }

    private void updateTimerText() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;
        String timeFormatted = String.format("%02d:%02d", minutes, seconds);
        timerTextView.setText(timeFormatted);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}

