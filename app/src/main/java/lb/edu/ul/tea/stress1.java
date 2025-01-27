package lb.edu.ul.tea;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class stress1 extends AppCompatActivity {

    private ArrayList<ImageView> favoriteIcons;
    private boolean[] isFavoriteArray;
    MediaPlayer mediaPlayer; // MediaPlayer object to handle audio
    Button playAudioButton1, playAudioButton2, playAudioButton3, playAudioButton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_stress1);

        // Initialize the favoriteIcons ArrayList and isFavoriteArray
        favoriteIcons = new ArrayList<>();
        isFavoriteArray = new boolean[4]; // Assuming there are 4 favorite buttons

        // Find buttons by ID
        playAudioButton1 = findViewById(R.id.playAudioButton1);
        playAudioButton2 = findViewById(R.id.playAudioButton2);
        playAudioButton3 = findViewById(R.id.playAudioButton3);
        playAudioButton4 = findViewById(R.id.playAudioButton4);

        // Set button click listeners to play audio and navigate
        playAudioButton1.setOnClickListener(view -> playAndNavigate(R.raw.st1));
        playAudioButton2.setOnClickListener(view -> playAndNavigate(R.raw.st2));
        playAudioButton3.setOnClickListener(view -> playAndNavigate(R.raw.st3));
        playAudioButton4.setOnClickListener(view -> playAndNavigate(R.raw.st4));

        // Add favorite ImageViews to the list
        favoriteIcons.add(findViewById(R.id.heart1));
        favoriteIcons.add(findViewById(R.id.heart2));
        favoriteIcons.add(findViewById(R.id.heart3));
        favoriteIcons.add(findViewById(R.id.heart4));

        // Set click listeners for all favorite buttons
        for (int i = 0; i < favoriteIcons.size(); i++) {
            final int index = i;  // Capture the index for the click listener
            favoriteIcons.get(i).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    toggleFavorite(favoriteIcons.get(index), index);  // Pass the corresponding button and index
                }
            });
        }
    }

    // Toggle favorite state for each button
    private void toggleFavorite(ImageView favoriteIcon, int index) {
        if (isFavoriteArray[index]) {
            favoriteIcon.setImageResource(R.drawable.ic_favorite_border);  // Set empty icon
            isFavoriteArray[index] = false;  // Update the state
        } else {
            favoriteIcon.setImageResource(R.drawable.ic_favorite);  // Set filled icon
            isFavoriteArray[index] = true;  // Update the state
        }
    }

    private void playAndNavigate(int songResId) {
        // Release any previously playing MediaPlayer
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }

        // Initialize MediaPlayer with the selected song
        mediaPlayer = MediaPlayer.create(this, songResId);
        mediaPlayer.start();

        // Pass the selected song to Stress2
        Intent intent = new Intent(stress1.this, stress2.class);
        intent.putExtra("songResId", songResId); // Pass the song resource ID
        startActivity(intent);
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
