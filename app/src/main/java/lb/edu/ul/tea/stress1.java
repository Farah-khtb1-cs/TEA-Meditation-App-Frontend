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

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class stress1 extends AppCompatActivity {

    private ArrayList<ImageView> favoriteIcons;
    private boolean[] isFavoriteArray;
    private SharedPreferences sharedPreferences;
    MediaPlayer mediaPlayer;
    Button playAudioButton1, playAudioButton2, playAudioButton3, playAudioButton4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_stress1);

        sharedPreferences = getSharedPreferences("Favorites", MODE_PRIVATE);

        favoriteIcons = new ArrayList<>();
        isFavoriteArray = new boolean[4];

        playAudioButton1 = findViewById(R.id.playAudioButton1);
        playAudioButton2 = findViewById(R.id.playAudioButton2);
        playAudioButton3 = findViewById(R.id.playAudioButton3);
        playAudioButton4 = findViewById(R.id.playAudioButton4);

        playAudioButton1.setOnClickListener(view -> playAndNavigate(R.raw.st1));
        playAudioButton2.setOnClickListener(view -> playAndNavigate(R.raw.st2));
        playAudioButton3.setOnClickListener(view -> playAndNavigate(R.raw.st3));
        playAudioButton4.setOnClickListener(view -> playAndNavigate(R.raw.st4));

        favoriteIcons.add(findViewById(R.id.heart1));
        favoriteIcons.add(findViewById(R.id.heart2));
        favoriteIcons.add(findViewById(R.id.heart3));
        favoriteIcons.add(findViewById(R.id.heart4));

        loadFavorites();

        for (int i = 0; i < favoriteIcons.size(); i++) {
            final int index = i;
            favoriteIcons.get(i).setOnClickListener(view -> toggleFavorite(favoriteIcons.get(index), index));
        }
    }

    private void toggleFavorite(ImageView favoriteIcon, int index) {
        isFavoriteArray[index] = !isFavoriteArray[index];
        int iconResource = isFavoriteArray[index] ? R.drawable.ic_favorite : R.drawable.ic_favorite_border;
        favoriteIcon.setImageResource(iconResource);
        saveFavorites();
    }

    private void saveFavorites() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        for (int i = 0; i < isFavoriteArray.length; i++) {
            editor.putBoolean("favorite_" + i, isFavoriteArray[i]);
        }
        editor.apply();
    }

    private void loadFavorites() {
        for (int i = 0; i < isFavoriteArray.length; i++) {
            isFavoriteArray[i] = sharedPreferences.getBoolean("favorite_" + i, false);
            favoriteIcons.get(i).setImageResource(isFavoriteArray[i] ? R.drawable.ic_favorite : R.drawable.ic_favorite_border);
        }
    }

    private void playAndNavigate(int songResId) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
        }
        mediaPlayer = MediaPlayer.create(this, songResId);
        mediaPlayer.start();

        Intent intent = new Intent(stress1.this, stress2.class);
        intent.putExtra("songResId", songResId);
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
