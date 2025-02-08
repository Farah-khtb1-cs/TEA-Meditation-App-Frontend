package lb.edu.ul.tea;


import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SleepActivity extends AppCompatActivity {
    private TextView timerText;
    private ProgressBar progressBar;
    private MediaPlayer mediaPlayer;
    private CountDownTimer countDownTimer;
    private long timeLeftMillis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sleep);

        timerText = findViewById(R.id.timer_text);
        progressBar = findViewById(R.id.progressBar);

        int duration = getIntent().getIntExtra("DURATION", 1800000); // Default: 30 mins
        timeLeftMillis = duration;

        progressBar.setMax((int) timeLeftMillis / 1000);

        mediaPlayer = MediaPlayer.create(this, R.raw.st1);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        startTimer(timeLeftMillis);
        startFadeAnimation();
    }

    private void startTimer(long duration) {
        countDownTimer = new CountDownTimer(duration, 1000) {
            public void onTick(long millisUntilFinished) {
                timeLeftMillis = millisUntilFinished;
                int minutes = (int) (timeLeftMillis / 60000);
                int seconds = (int) (timeLeftMillis % 60000 / 1000);
                timerText.setText(String.format("%02d:%02d", minutes, seconds));
                progressBar.setProgress((int) millisUntilFinished / 1000);
            }

            public void onFinish() {
                stopSleepSession();
            }
        }.start();
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
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopSleepSession();
    }
}
