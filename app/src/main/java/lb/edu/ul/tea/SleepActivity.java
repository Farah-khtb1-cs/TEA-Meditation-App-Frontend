package lb.edu.ul.tea;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
public class SleepActivity extends AppCompatActivity {
    private TextView timerText;
    private ProgressBar circularProgressBar;
    private MediaPlayer mediaPlayer;
    private CountDownTimer countDownTimer;
    private long timeLeftMillis;
    private Button startButton;
    private boolean isTimerRunning = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);

        timerText = findViewById(R.id.timer_text);
        circularProgressBar = findViewById(R.id.circularProgressBar);
        startButton = findViewById(R.id.start_button);

        int duration = getIntent().getIntExtra("DURATION", 1800000); // Default: 30 mins
        timeLeftMillis = duration;

        mediaPlayer = MediaPlayer.create(this, R.raw.st1);
        mediaPlayer.setLooping(true);

        startButton.setOnClickListener(v -> {
            if (isTimerRunning) {
                pauseTimer(); // Pause functionality
                startButton.setText("Start");
            } else {
                startTimer(timeLeftMillis); // Resume or start timer
                mediaPlayer.start();
                startButton.setText("Stop");
                startFadeAnimation();
            }
            isTimerRunning = !isTimerRunning; // Toggle the timer state
        });
    }

    private void startTimer(long duration) {
        countDownTimer = new CountDownTimer(duration, 1000) {
            public void onTick(long millisUntilFinished) {
                timeLeftMillis = millisUntilFinished;
                int minutes = (int) (timeLeftMillis / 60000);
                int seconds = (int) (timeLeftMillis % 60000 / 1000);
                timerText.setText(String.format("%02d:%02d", minutes, seconds));

                int progress = (int) ((millisUntilFinished * 100) / duration);
                circularProgressBar.setProgress(progress);
            }

            public void onFinish() {
                stopSleepSession();
                startButton.setText("Start"); // Reset button text
            }
        }.start();
    }

    private void pauseTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    private void startFadeAnimation() {
        Animation fade = new AlphaAnimation(0.3f, 1.0f);
        fade.setDuration(1000);
        fade.setRepeatMode(Animation.REVERSE);
        fade.setRepeatCount(Animation.INFINITE);
        timerText.startAnimation(fade);
    }

    private void stopSleepSession() {
        if (countDownTimer != null) countDownTimer.cancel();
        if (mediaPlayer != null) {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
            }
            mediaPlayer.release();
            mediaPlayer = null;
        }
        timeLeftMillis = 0;
        isTimerRunning = false;
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopSleepSession();
    }
}

