package msharma.net.mhere.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;

import msharma.net.mhere.R;
import msharma.net.mhere.models.AddUserEvent;
import msharma.net.mhere.models.Event;
import msharma.net.mhere.models.User;
import msharma.net.mhere.utils.JsonUtils;
import msharma.net.mhere.utils.ServicesUtil;

public class AddUserAdapter extends RecyclerView.Adapter<AddUserAdapter.AddUserViewHolder> {

    private static final String TAG = "AddUserAdapter";

    Context mContext;
    public ArrayList<User> mUsers;
    private Integer eventId;

    public AddUserAdapter(Context context, ArrayList<User> mUsers, Integer eventId){
        this.mContext = context;
        this.mUsers = mUsers;
        this.eventId = eventId;
    }

    @Override
    public AddUserAdapter.AddUserViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(R.layout.add_user_rv, parent, shouldAttachToParentImmediately);
        AddUserAdapter.AddUserViewHolder viewHolder = new AddUserAdapter.AddUserViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(AddUserAdapter.AddUserViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mUsers.size();
    }

    public class AddUserViewHolder  extends RecyclerView.ViewHolder {

        TextView username;
        Button addBtn;

        public AddUserViewHolder(View itemView) {
            super(itemView);
            username = (TextView) itemView.findViewById(R.id.userName);
            addBtn = (Button) itemView.findViewById(R.id.addBtn);
        }

        void bind(final int listIndex) {
            username.setText(mUsers.get(listIndex).getFullName());

            // navigate to add user activity on click.
            addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    new AddUserToEventAsyncTask().execute(mUsers.get(listIndex).getId());
                    Log.d(TAG, " Event id clicked - " + mUsers.get(getAdapterPosition()).getId() );
                }
            });

        }

        public class AddUserToEventAsyncTask extends AsyncTask<Integer, Void, String> {

            @Override
            protected String doInBackground(Integer... params) {
                AddUserEvent addUserEvent = new AddUserEvent();
                addUserEvent.setEventId(eventId);
                addUserEvent.setUserId(params[0]);

                Log.d("AAAAA", addUserEvent.toString() );
                String responseBody = "";
                try {
                    responseBody = new ServicesUtil(mContext).postResponseWithToken( "user-events", new ObjectMapper().writeValueAsString(addUserEvent));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return responseBody;
            }

            @Override
            protected void onPostExecute(String responseBody) {
                AddUserEvent _addedUserEvent = JsonUtils.parseUserEvent(responseBody);
                if ( _addedUserEvent.getId() != null ) {
                    Toast toast = Toast.makeText(mContext,"User added to event", Toast.LENGTH_SHORT);
//                    toast.setMargin(50,50);
                    toast.show();
                } else {
                    Toast toast = Toast.makeText(mContext,"Failed to add user to event", Toast.LENGTH_SHORT);
//                    toast.setMargin(50,50);
                    toast.show();
                }

            }
        }

    }

}
