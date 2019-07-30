package msharma.net.mhere.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.io.IOException;
import java.util.ArrayList;

import msharma.net.mhere.R;
import msharma.net.mhere.adapters.AddUserAdapter;
import msharma.net.mhere.models.User;
import msharma.net.mhere.utils.JsonUtils;
import msharma.net.mhere.utils.ServicesUtil;

public class AddUserActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private AddUserAdapter addUserAdapter;
    private ArrayList<User> users = new ArrayList<>();
    private Integer eventId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle != null){
            eventId = bundle.getInt("eventId");
        }

        mRecyclerView = (RecyclerView) findViewById(R.id.addUsers_recyclerview);
        addUserAdapter = new AddUserAdapter(this, users, eventId);
        mRecyclerView.setAdapter(addUserAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        new UsersAsyncTask().execute();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.home) {
            startActivity(new Intent(AddUserActivity.this, HomeActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    public class UsersAsyncTask extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            addUserAdapter.mUsers.clear();
        }

        @Override
        protected String doInBackground(Void... voids) {
            String responseBody = "";
            try {
                responseBody = new ServicesUtil(AddUserActivity.this).getResponseWithToken( "users" );
            } catch (IOException e) {
                e.printStackTrace();
            }
            return responseBody;
        }

        @Override
        protected void onPostExecute(String responseBody) {
            ArrayList<User> _users = JsonUtils.parseUsers(responseBody);
            addUserAdapter.mUsers.addAll(_users);
            addUserAdapter.notifyDataSetChanged();
        }
    }

}
