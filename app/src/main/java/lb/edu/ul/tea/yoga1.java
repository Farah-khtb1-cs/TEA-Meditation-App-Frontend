package lb.edu.ul.tea;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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


        // Initialize the list of TextViews
        textViews = new ArrayList<>();
        textViews.add(findViewById(R.id.tv_cardio));
        textViews.add(findViewById(R.id.tv_legs));
        textViews.add(findViewById(R.id.tv_back));
        textViews.add(findViewById(R.id.tv_press));
        textViews.add(findViewById(R.id.tv_triceps));

        // Set click listeners for each TextView
        for (TextView textView : textViews) {
            textView.setOnClickListener(v -> setSelection(textView));
        }

        // Initialize the favoriteIcons ArrayList
        favoriteIcons = new ArrayList<>();

        // Initialize the state array with the number of buttons
        isFavoriteArray = new boolean[3];  // Adjust size based on the number of buttons

        // Find the ImageViews and add them to the list
        favoriteIcons.add(findViewById(R.id.heart11));
        favoriteIcons.add(findViewById(R.id.heart22));
        favoriteIcons.add(findViewById(R.id.heart33));

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

    private void toggleFavorite(ImageView favoriteIcon, int index) {
        if (isFavoriteArray[index]) {
            favoriteIcon.setImageResource(R.drawable.ic_favorite_border);  // Set empty icon
            isFavoriteArray[index] = false;  // Update the state
        } else {
            favoriteIcon.setImageResource(R.drawable.ic_favorite);  // Set filled icon
            isFavoriteArray[index] = true;  // Update the state
        }
    }

    private void setSelection(TextView selectedTextView) {
        for (TextView tv : textViews) {
            tv.setSelected(false);
        }
        selectedTextView.setSelected(true);
    }
}
