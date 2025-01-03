package lb.edu.ul.tea;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ArrayAdapter;

import lb.edu.ul.tea.MainActivity;
import lb.edu.ul.tea.R;
import lb.edu.ul.tea.StartActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Locale;
import java.util.Arrays;
import java.util.Collection;

public class StartActivity<StartActivity> extends AppCompatActivity {

    private Object requestPermissionLauncher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_start);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Toast.makeText(this, "WELCOME WELCOME",
                Toast.LENGTH_SHORT).show();


        Spinner spinnercountries =findViewById(R.id.myspi);
        ArrayList<String> countryList=getAllCountries();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>
                (this,android.R.layout.simple_spinner_dropdown_item,countryList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnercountries.setAdapter(adapter);


        Button signupbutton=findViewById(R.id.signup);
        signupbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = spinnercountries.getSelectedItemPosition(); // Get the selected position
                if (position > 0) { // Check if a valid country is selected
                    Intent intent = new Intent(lb.edu.ul.tea.StartActivity.this, MainActivity.class);
                    intent.putExtra("selectedCountry", countryList.get(position)); // Pass the selected country
                    startActivity(intent);
                } else {
                    Toast.makeText(lb.edu.ul.tea.StartActivity.this, "Please select a country first.", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }



    public static ArrayList<String> getAllCountries() {
        String[] isoCountryCodes = Locale.getISOCountries();
        ArrayList<String> countries = new ArrayList<>();
        for (String countryCode : isoCountryCodes) {
            Locale locale = new Locale("", countryCode);
            countries.add(locale.getDisplayCountry());
        }

        Collections.sort(countries);
        return countries;
    }
}