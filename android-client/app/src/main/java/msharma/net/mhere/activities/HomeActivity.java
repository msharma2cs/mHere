package msharma.net.mhere.activities;

import android.content.ClipData;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.util.ArrayList;
import msharma.net.mhere.R;
import msharma.net.mhere.adapters.EventAdapter;
import msharma.net.mhere.models.Event;
import msharma.net.mhere.sharedpreferences.UserProfileManager;
import msharma.net.mhere.utils.JsonUtils;
import msharma.net.mhere.utils.ServicesUtil;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = "HomeActivity";

    private RecyclerView mRecyclerView;
    private EventAdapter eventAdapter;
    private ArrayList<Event> events = new ArrayList<>();
    private int currentTab;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_events_created:
                    currentTab = 1;
                    new EventsCreatedAsyncTask().execute();
                    return true;

                case R.id.navigation_events_attend:
                    currentTab = 2;
                    new EventsAttendAsyncTask().execute();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mRecyclerView = (RecyclerView) findViewById(R.id.events_recyclerview);
        eventAdapter = new EventAdapter(this, events);
        mRecyclerView.setAdapter(eventAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        currentTab = 1;
        new EventsCreatedAsyncTask().execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout) {
            UserProfileManager.deleteUserInfo(HomeActivity.this);
            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.logout, menu);
        return true;
    }

    public class EventsCreatedAsyncTask extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            eventAdapter.mEvents.clear();
        }

        @Override
        protected String doInBackground(Void... voids) {
            String responseBody = "";
            try {
                responseBody = new ServicesUtil(HomeActivity.this).getResponseWithToken( "events/created" );
            } catch (IOException e) {
                e.printStackTrace();
            }
            return responseBody;
        }

        @Override
        protected void onPostExecute(String responseBody) {
            ArrayList<Event> _events = JsonUtils.parseEvents(responseBody);
            eventAdapter.setCurrentTab(currentTab);
            eventAdapter.mEvents.addAll(_events);
            eventAdapter.notifyDataSetChanged();
        }
    }

    public class EventsAttendAsyncTask extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            eventAdapter.mEvents.clear();
        }

        @Override
        protected String doInBackground(Void... voids) {
            String responseBody = "";
            try {
                responseBody = new ServicesUtil(HomeActivity.this).getResponseWithToken( "events/attending" );
            } catch (IOException e) {
                e.printStackTrace();
            }
            return responseBody;
        }

        @Override
        protected void onPostExecute(String responseBody) {
            ArrayList<Event> _events = JsonUtils.parseEvents(responseBody);
            eventAdapter.setCurrentTab(currentTab);
            eventAdapter.mEvents.addAll(_events);
            eventAdapter.notifyDataSetChanged();
        }
    }

}