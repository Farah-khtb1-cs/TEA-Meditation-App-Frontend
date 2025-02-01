
package lb.edu.ul.tea;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.time.Year;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private boolean isFragmentDisplayed = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Apply system bar insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });




        ImageView imageAnexiety = findViewById(R.id.anxiety);
        imageAnexiety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(MainActivity.this, Anexiety.class);
                startActivity(I);
            }
        });
        ImageView psych = findViewById(R.id.mentalhealth);
        psych.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(MainActivity.this, psychologists.class);
                startActivity(I);
            }
        });

        ImageView profile = findViewById(R.id.profilebutton);
        profile.setOnClickListener(v -> {
            Intent I = new Intent(MainActivity.this, profile1.class);
            startActivityForResult(I, 1000);
        });

        ImageView stress=findViewById(R.id.soundhilling);
        stress.setOnClickListener(v -> {
            Intent I = new Intent(MainActivity.this, stress1.class);
            startActivity(I);
        });


        Button allButton = findViewById(R.id.button1);
        Button guidelinesButton = findViewById(R.id.button2);
        TableLayout tableLayout = findViewById(R.id.table);
        TableRow row2 = findViewById(R.id.row2);
        TableRow row3 = findViewById(R.id.row3);
        TableRow row4 = findViewById(R.id.row4);
        TableRow row1 = findViewById(R.id.row1);

        TableRow imagesrow = findViewById(R.id.imagesrow);


        View anxiety = findViewById(R.id.anxiety);
        View depression = findViewById(R.id.depression);
        View superSensors = findViewById(R.id.sensers);
        View reframeStress = findViewById(R.id.stress);
        View parentRole = findViewById(R.id.parent);
        View psychologist = findViewById(R.id.mentalhealth);
        View goToSleep = findViewById(R.id.sleep);
        View morningCalm = findViewById(R.id.sunrise);
        View waterMeditation = findViewById(R.id.watermeditation);
        View soundHealing = findViewById(R.id.soundhilling);
        View yoga = findViewById(R.id.yoga);
        View pregnant = findViewById(R.id.pregnant);


        allButton.setOnClickListener(v -> {
            // Make all buttons visible
            anxiety.setVisibility(View.VISIBLE);
            depression.setVisibility(View.VISIBLE);
            superSensors.setVisibility(View.VISIBLE);
            reframeStress.setVisibility(View.VISIBLE);
            parentRole.setVisibility(View.VISIBLE);
            psychologist.setVisibility(View.VISIBLE);
            goToSleep.setVisibility(View.VISIBLE);
            morningCalm.setVisibility(View.VISIBLE);
            waterMeditation.setVisibility(View.VISIBLE);
            soundHealing.setVisibility(View.VISIBLE);
            yoga.setVisibility(View.VISIBLE);
            pregnant.setVisibility(View.VISIBLE);
            row1.setVisibility(View.VISIBLE);
            row2.setVisibility(View.VISIBLE);
            row3.setVisibility(View.VISIBLE);
            row4.setVisibility(View.VISIBLE);


        });

        guidelinesButton.setOnClickListener(v -> {
            // Make only specific buttons visible
            imagesrow.setVisibility(View.VISIBLE);
            anxiety.setVisibility(View.VISIBLE);
            depression.setVisibility(View.VISIBLE);
            superSensors.setVisibility(View.VISIBLE);
            reframeStress.setVisibility(View.VISIBLE);
            parentRole.setVisibility(View.VISIBLE);
            psychologist.setVisibility(View.VISIBLE);
            row2.setVisibility(View.VISIBLE);

            // Hide the rest
            goToSleep.setVisibility(View.GONE);
            morningCalm.setVisibility(View.GONE);
            waterMeditation.setVisibility(View.GONE);
            soundHealing.setVisibility(View.GONE);
            yoga.setVisibility(View.GONE);
            pregnant.setVisibility(View.GONE);
            row3.setVisibility(View.GONE);
            row4.setVisibility(View.GONE);


        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            // Handle profile fragment
            if (item.getItemId() == R.id.nav_profile) {
                // Check if the profile fragment is already in the fragment manager
                Fragment currentFragment = getSupportFragmentManager().findFragmentByTag(profile1.class.getSimpleName());

                if (currentFragment != null && currentFragment.isVisible()) {
                    // If the fragment is already visible, pop it from the back stack to close it
                    getSupportFragmentManager().popBackStack();
                    return true;  // Do nothing more if we just close the fragment
                }

                // Otherwise, replace with the new profile1 fragment
                selectedFragment = new profile1();
            }

            // Handle home fragment
            if (item.getItemId() == R.id.nav_home) {
                Fragment currentFragment = getSupportFragmentManager().findFragmentByTag(profile1.class.getSimpleName());
                if (currentFragment != null && currentFragment.isVisible()) {
                    // If the fragment is already visible, pop it from the back stack to close it
                    getSupportFragmentManager().popBackStack();
                    return true;  // Do nothing more if we just close the fragment
                }
            }

            // If a fragment is selected, perform the fragment transaction
            if (selectedFragment != null) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, selectedFragment, profile1.class.getSimpleName()); // Tag the fragment
                transaction.addToBackStack(null);  // Optional, if you want to keep fragment history
                transaction.commit();
            }

            return true;  // Return true to indicate item selection was handled
        });

        yoga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I=new Intent(MainActivity.this, yoga1.class);
                startActivity(I);
            }
        });


        Button favoriteButton = findViewById(R.id.button3);
        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });


    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000) {
            if (resultCode == RESULT_OK) {
                String name = data.getStringExtra("name");
                // Use the retrieved name here
            }
        }

    }


}

