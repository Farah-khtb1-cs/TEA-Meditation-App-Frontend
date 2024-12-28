package lb.edu.ul.tea;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

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





        ImageView imageAnexiety =findViewById(R.id.anxiety);
        imageAnexiety.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I=new Intent(MainActivity.this, Anexiety.class);
                startActivity(I);
            }
        });
        ImageView imageDr =findViewById(R.id.mentalhealth);
        imageDr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I=new Intent(MainActivity.this, psychologists.class);
                startActivity(I);
            }
        });

        ImageView profile = findViewById(R.id.profilebutton);
        profile.setOnClickListener(v -> {
            Intent I = new Intent(MainActivity.this, Profile.class);
            startActivityForResult(I, 1000);
        });

        /*Button guidelinebutton = findViewById(R.id.button2);
        profile.setOnClickListener(v -> {
            ArrayList Imagesbutt=new ArrayList<>();
            Imagesbutt.add(R.id.anxiety);
            Imagesbutt.add(R.id.depression);
            Imagesbutt.add(R.id.meditation);
            Imagesbutt.add(R.id.stress);
            Imagesbutt.add(R.id.parent);
            Imagesbutt.add(R.id.mentalhealth);

            for(int i=0;i<Imagesbutt.size();i++)
            { //Imagesbutt.get(i).setVisibility(View.VISIBLE);

            }





        });*/




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