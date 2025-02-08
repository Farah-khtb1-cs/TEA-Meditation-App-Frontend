package lb.edu.ul.tea;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.textfield.TextInputEditText;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    private TextInputEditText inputStressLevel, inputSleepHours;
    private TextView resultText;
    private BarChartView barChartView;
    private HorizontalScrollView horizontalScrollView; // Added reference to the ScrollView
    private ArrayList<Float> moodData = new ArrayList<>();

    private static final String PREFS_NAME = "MoodDataPrefs";
    private static final String KEY_MOOD_DATA = "MoodData";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Initialize Views
        inputStressLevel = findViewById(R.id.inputStressLevel);
        inputSleepHours = findViewById(R.id.inputSleepHours);
        Button btnCalculate = findViewById(R.id.btnCalculate);
        resultText = findViewById(R.id.resultText);
        barChartView = findViewById(R.id.barChart);
        horizontalScrollView = findViewById(R.id.horizontalScrollView); // Initialize ScrollView

        // Load previous data from SharedPreferences
        loadMoodData();

        // Handle Enter key to dismiss keyboard
        setEnterKeyListener(inputStressLevel);
        setEnterKeyListener(inputSleepHours);

        // Set OnClickListener to calculate mood
        btnCalculate.setOnClickListener(v -> {
            hideKeyboard();
            calculateMood();
        });
    }

    // Dismiss keyboard when pressing Enter
    private void setEnterKeyListener(TextInputEditText inputField) {
        inputField.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE ||
                    (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN)) {
                hideKeyboard();
                calculateMood(); // Optional: Trigger calculation when Enter is pressed
                return true;
            }
            return false;
        });
    }

    // Hide the soft keyboard
    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    // Calculate "Mo3adal Nafseyte"
    private void calculateMood() {
        String stressStr = inputStressLevel.getText().toString().trim();
        String sleepStr = inputSleepHours.getText().toString().trim();

        if (TextUtils.isEmpty(stressStr) || TextUtils.isEmpty(sleepStr)) {
            resultText.setText("Please fill in all fields.");
            return;
        }

        try {
            int stressLevel = Integer.parseInt(stressStr);
            double sleepHours = Double.parseDouble(sleepStr);

            if (stressLevel < 0 || stressLevel > 10 || sleepHours <= 0) {
                resultText.setText("Enter valid values (Stress: 0-10, Sleep: positive number).");
                return;
            }

            double mo3adalNafseyte = (10 - stressLevel) * sleepHours / 10;
            resultText.setText("Mo3adal Nafseyte: " + String.format("%.2f", mo3adalNafseyte));

            moodData.add((float) mo3adalNafseyte);
            updateChart();
            saveMoodData();
            scrollToEnd(); // Ensure scrolling happens

        } catch (NumberFormatException e) {
            resultText.setText("Invalid input. Enter numbers only.");
        }
    }

    private void updateChart() {
        float[] moodArray = new float[moodData.size()];
        for (int i = 0; i < moodData.size(); i++) {
            moodArray[i] = moodData.get(i);
        }
        barChartView.setDataPoints(moodArray);
    }

    private void saveMoodData() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        JSONArray jsonArray = new JSONArray();
        for (Float dataPoint : moodData) {
            jsonArray.put(dataPoint);
        }

        editor.putString(KEY_MOOD_DATA, jsonArray.toString());
        editor.apply();
    }

    private void loadMoodData() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String savedData = sharedPreferences.getString(KEY_MOOD_DATA, null);

        if (savedData != null) {
            try {
                JSONArray jsonArray = new JSONArray(savedData);
                for (int i = 0; i < jsonArray.length(); i++) {
                    moodData.add((float) jsonArray.getDouble(i));
                }
                updateChart();
                scrollToEnd(); // Scroll after loading existing data
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    // Scroll the chart to the rightmost end
    private void scrollToEnd() {
        horizontalScrollView.post(() -> horizontalScrollView.fullScroll(HorizontalScrollView.FOCUS_RIGHT));
    }
}






