package msharma.net.mhere.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import msharma.net.mhere.R;
import msharma.net.mhere.models.User;
import msharma.net.mhere.sharedpreferences.UserProfileManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get user profile from shared preferences.
        User _savedUser = UserProfileManager.getUserInfo(MainActivity.this);

        // if user is already logged in, navigate to home screen...
        if ( _savedUser.getToken() == null ) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
        } else { // else navigate to home screen...
            startActivity(new Intent(MainActivity.this, HomeActivity.class));
        }
    }

}