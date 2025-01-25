package lb.edu.ul.tea;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link profile1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class profile1 extends Fragment {

    // Keys for SharedPreferences
    private static final String SHARED_PREFS_NAME = "userPrefs";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_GMAIL = "email";
    private static final String ARG_PARAM1 ="" ;
    private static final String ARG_PARAM2="";

    private TextView usernameTextView;
    private TextView gmailTextView;

    public profile1() {
        // Required empty public constructor
    }

    public static profile1 newInstance(String param1, String param2) {
        profile1 fragment = new profile1();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // Retrieve fragment arguments if necessary
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile1, container, false);

        // Initialize TextView elements
        usernameTextView = view.findViewById(R.id.username_entred);
        gmailTextView = view.findViewById(R.id.gmail_entred);

        // Retrieve data from SharedPreferences
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("my file", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString(KEY_USERNAME, "Default Username");
        String gmail = sharedPreferences.getString(KEY_GMAIL, "Default gmail");

        // Set the retrieved data in TextView elements
        usernameTextView.setText(username);
        gmailTextView.setText(gmail);


        Button logoutButton= view.findViewById(R.id.logout);
        logoutButton.setOnClickListener(v -> {
            // Optionally, clear SharedPreferences if you want to "log out" entirely
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove(KEY_USERNAME);
            editor.remove(KEY_GMAIL);
            editor.apply();

            // Pass the username back to the Login activity
            Intent intent = new Intent(getActivity(), StartActivity.class);
            intent.putExtra(KEY_USERNAME, username); // Pass the username
            startActivity(intent);

            // Optional: Close the current fragment or activity
            getActivity().finish();  // This closes the current activity (if needed)
        });

        return view;
    }
}
