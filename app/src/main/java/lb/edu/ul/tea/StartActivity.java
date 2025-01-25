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

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        // Adjust padding for system bars
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toast.makeText(this, "WELCOME WELCOME", Toast.LENGTH_SHORT).show();

        // Initialize UI components
        Spinner spinnerCountries = findViewById(R.id.myspi);
        EditText editTextUsername = findViewById(R.id.editTextText2);
        EditText editTextEmail = findViewById(R.id.editText4);
        EditText editTextPassword = findViewById(R.id.editText3);
        Button signupButton = findViewById(R.id.signup);

        // Populate the spinner with country names
        ArrayList<String> countryList = getAllCountries();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_dropdown_item, countryList);
        spinnerCountries.setAdapter(adapter);

        // Check if the user is already logged in
        SharedPreferences sharedPref = getSharedPreferences("my file", Context.MODE_PRIVATE);
        String savedUsername = sharedPref.getString("username", null);
        if (savedUsername != null) {
            // Navigate to MainActivity if already logged in
            Intent intent = new Intent(StartActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            return; // Exit the current activity
        }

        // Set up the signup button click listener
        signupButton.setOnClickListener(v -> {
            // Get input values
            String username = editTextUsername.getText().toString().trim();
            String email = editTextEmail.getText().toString().trim();
            String password = editTextPassword.getText().toString().trim();
            int countryPosition = spinnerCountries.getSelectedItemPosition();

            // Validate inputs
            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
                return;
            }

            if (countryPosition <= 0) {
                Toast.makeText(this, "Please select a valid country.", Toast.LENGTH_SHORT).show();
                return;
            }

            // Save user data to SharedPreferences
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putString("username", username);
            editor.putString("email", email);
            editor.putString("password", password);
            editor.putString("country", spinnerCountries.getSelectedItem().toString());
            editor.apply();

            // Navigate to MainActivity
            Intent intent = new Intent(StartActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
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
