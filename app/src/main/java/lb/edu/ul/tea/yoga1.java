package lb.edu.ul.tea;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class yoga1 extends AppCompatActivity {
    private List<TextView> textViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoga1);

        // Handle window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

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

    }

    private void setSelection(TextView selectedTextView) {
        for (TextView tv : textViews) {
            tv.setSelected(false);
        }
        selectedTextView.setSelected(true);
    }
}
