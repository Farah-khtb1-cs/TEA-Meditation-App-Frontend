package lb.edu.ul.tea;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class yoga1 extends AppCompatActivity {
    private ArrayList<ImageView> favoriteIcons;
    private boolean[] isFavoriteArray;
    private List<TextView> textViews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga1);

        // Initialize the TextView list and set click listeners
        textViews = new ArrayList<>();
        textViews.add(findViewById(R.id.tv_cardio));
        textViews.add(findViewById(R.id.tv_legs));
        textViews.add(findViewById(R.id.tv_back));
        textViews.add(findViewById(R.id.tv_press));
        textViews.add(findViewById(R.id.tv_triceps));

        for (TextView textView : textViews) {
            textView.setOnClickListener(v -> setSelection((TextView) v));
        }

        // Initialize the favoriteIcons list and state array
        favoriteIcons = new ArrayList<>();
        favoriteIcons.add(findViewById(R.id.heart11));
        favoriteIcons.add(findViewById(R.id.heart22));
        favoriteIcons.add(findViewById(R.id.heart33));

        isFavoriteArray = new boolean[favoriteIcons.size()];

        // Set click listeners for favorite icons
        for (int i = 0; i < favoriteIcons.size(); i++) {
            final int index = i;
            favoriteIcons.get(i).setOnClickListener(view -> toggleFavorite(favoriteIcons.get(index), index));
        }

        // Navigate to YogaEx activity when the first image button (row1) is clicked
         TableRow row1 = findViewById(R.id.row1);
        row1.setOnClickListener(v -> {
            Intent intent = new Intent(yoga1.this,yoga_ex.class );
            startActivity(intent);
        });
    }

    private void toggleFavorite(ImageView favoriteIcon, int index) {
        isFavoriteArray[index] = !isFavoriteArray[index];
        int iconResource = isFavoriteArray[index] ? R.drawable.ic_favorite : R.drawable.ic_favorite_border;
        favoriteIcon.setImageResource(iconResource);
    }

    private void setSelection(TextView selectedTextView) {
        for (TextView textView : textViews) {
            textView.setSelected(false);
        }
        selectedTextView.setSelected(true);
    }
}
