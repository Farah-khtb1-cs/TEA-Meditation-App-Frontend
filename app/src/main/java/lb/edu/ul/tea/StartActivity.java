package lb.edu.ul.tea;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;

public class StartActivity extends AppCompatActivity {

    private Spinner spinnerCountries;
    private EditText editTextUsername, editTextEmail, editTextPassword;
    private Button signupButton;
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        // Adjust padding for system bars (e.g., status bar, navigation bar)
        adjustSystemBarsPadding();

        // Show a welcome toast message
        showWelcomeMessage();

        // Initialize UI components
        initializeUIComponents();

        // Populate the country spinner with a sorted list of countries
        setupCountrySpinner();

        // Check if the user is already logged in
        checkIfUserLoggedIn();

        // Set up the signup button click listener
        setupSignupButton();
    }

    // Adjust padding for system bars to ensure the content is not hidden
    private void adjustSystemBarsPadding() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    // Show a simple welcome message in a toast
    private void showWelcomeMessage() {
        Toast.makeText(this, "WELCOME WELCOME", Toast.LENGTH_SHORT).show();
    }

    // Initialize UI components for user input
    private void initializeUIComponents() {
        spinnerCountries = findViewById(R.id.myspi);
        editTextUsername = findViewById(R.id.editTextText2);
        editTextEmail = findViewById(R.id.editText3);
        editTextPassword = findViewById(R.id.editText4);
        signupButton = findViewById(R.id.signup);
    }

    // Populate the country spinner with sorted country names
    private void setupCountrySpinner() {
        ArrayList<String> countryList = getAllCountries();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, countryList);
        spinnerCountries.setAdapter(adapter);
    }

    // Check if the user is already logged in, and if so, navigate to MainActivity
    private void checkIfUserLoggedIn() {
        sharedPref = getSharedPreferences("my file", Context.MODE_PRIVATE);
        String savedUsername = sharedPref.getString("username", null);
        if (savedUsername != null) {
            navigateToMainActivity();
        }
    }

    // Set up the signup button click listener to handle user registration
    private void setupSignupButton() {
        signupButton.setOnClickListener(v -> {
            // Get input values from the UI components
            String username = editTextUsername.getText().toString().trim();
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            int countryPosition = spinnerCountries.getSelectedItemPosition();

            // Validate inputs
            if (isInputValid(username, email, password, countryPosition)) {
                // Save user data and navigate to MainActivity
                saveUserData(username, email, password, countryPosition);
                navigateToMainActivity();
            }
        });
    }

    // Check if the user inputs are valid
    private boolean isInputValid(String username, String email, String password, int countryPosition) {
        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (countryPosition <= 0) {
            Toast.makeText(this, "Please select a valid country.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    // Save user data to SharedPreferences
    private void saveUserData(String username, String email, String password, int countryPosition) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("username", username);
        editor.putString("email", email);
        editor.putString("password", password);
        editor.putString("country", spinnerCountries.getSelectedItem().toString());
        editor.apply();
    }

    // Navigate to MainActivity
    private void navigateToMainActivity() {
        Intent intent = new Intent(StartActivity.this, MainActivity.class);
        startActivity(intent);
        finish(); // Close the current activity
    }

    // Helper method to get a sorted list of all country names
    public static ArrayList<String> getAllCountries() {
        String[] isoCountryCodes = Locale.getISOCountries();
        ArrayList<String> countries = new ArrayList<>();
        for (String countryCode : isoCountryCodes) {
            Locale locale = new Locale("", countryCode);
            countries.add(locale.getDisplayCountry());
        }
        Collections.sort(countries);
        countries.add(0, "Select a Country"); // Add a placeholder for the spinner
        return countries;
    }
}
