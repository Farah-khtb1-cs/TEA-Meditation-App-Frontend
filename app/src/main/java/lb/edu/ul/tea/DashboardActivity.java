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
    private HorizontalScrollView horizontalScrollView;
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
        horizontalScrollView = findViewById(R.id.horizontalScrollView);

        // Load previous mood data from SharedPreferences
        loadMoodData();

        // Set Enter key listener for input fields
        setEnterKeyListener(inputStressLevel);
        setEnterKeyListener(inputSleepHours);

        // Set OnClickListener for Calculate button
        btnCalculate.setOnClickListener(v -> {
            hideKeyboard();
            calculateMood();
        });
    }

    // Helper method to handle Enter key press and trigger mood calculation
    private void setEnterKeyListener(TextInputEditText inputField) {
        inputField.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE ||
                    (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN)) {
                hideKeyboard();
                calculateMood();
                return true;
            }
            return false;
        });
    }

    // Helper method to hide soft keyboard
    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    // Calculate the "Mo3adal Nafseyte" value and update the result
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
            scrollToEnd(); // Ensure the chart scrolls to the end

        } catch (NumberFormatException e) {
            resultText.setText("Invalid input. Enter numbers only.");
        }
    }

    // Update the bar chart with the latest mood data
    private void updateChart() {
        float[] moodArray = new float[moodData.size()];
        for (int i = 0; i < moodData.size(); i++) {
            moodArray[i] = moodData.get(i);
        }
        barChartView.setDataPoints(moodArray);
    }

    // Save the current mood data to SharedPreferences
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

    // Load saved mood data from SharedPreferences
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

    // Scroll the HorizontalScrollView to the rightmost position
    private void scrollToEnd() {
        horizontalScrollView.post(() -> horizontalScrollView.fullScroll(HorizontalScrollView.FOCUS_RIGHT));
    }
}





