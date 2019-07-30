package msharma.net.mhere.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import msharma.net.mhere.R;
import msharma.net.mhere.activities.AddUserActivity;
import msharma.net.mhere.models.Event;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.EventItemViewHolder> {

    private static final String TAG = "EventAdapter";

    Context mContext;
    public ArrayList<Event> mEvents;
    private int currentTab;

    public EventAdapter(Context context, ArrayList<Event> mEvents){
        this.mContext = context;
        this.mEvents = mEvents;
    }

    @Override
    public EventAdapter.EventItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;

        View view = inflater.inflate(R.layout.event_item_rv, parent, shouldAttachToParentImmediately);
        EventAdapter.EventItemViewHolder viewHolder = new EventAdapter.EventItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EventAdapter.EventItemViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mEvents.size();
    }

    public int getCurrentTab() {
        return currentTab;
    }

    public void setCurrentTab(int currentTab) {
        this.currentTab = currentTab;
    }

    public class EventItemViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView description;
        TextView eventDate;
        TextView startTime;
        TextView endTime;
        TextView addedOn;
        TextView location;
        Button addUser;
        Button startGeoFencing;

        public EventItemViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            description = (TextView) itemView.findViewById(R.id.description);
            eventDate = (TextView) itemView.findViewById(R.id.eventDate);
            startTime = (TextView) itemView.findViewById(R.id.startTime);
            endTime = (TextView) itemView.findViewById(R.id.endTime);
            addedOn = (TextView) itemView.findViewById(R.id.addedOn);
            location = (TextView) itemView.findViewById(R.id.location);
            addUser = (Button) itemView.findViewById(R.id.addUser);
            startGeoFencing = (Button) itemView.findViewById(R.id.startFencing);
        }

        void bind(final int listIndex) {
            title.setText("Title : ".concat(mEvents.get(listIndex).getTitle()));
            description.setText("Description : ".concat(mEvents.get(listIndex).getDescription()));
            eventDate.setText("Event date : ".concat(mEvents.get(listIndex).getEventDate()));
            startTime.setText("Starts at : ".concat(mEvents.get(listIndex).getStartTime()));
            endTime.setText("Ends at : ".concat(mEvents.get(listIndex).getEndTime()));
            addedOn.setText("Added on : ".concat(mEvents.get(listIndex).getCreatedAt()));
            location.setText("Location : ".concat( String.valueOf(mEvents.get(listIndex).getLat()) ).concat("/").concat( String.valueOf(mEvents.get(listIndex).getLat()) ) );

            if ( getCurrentTab() == 1 ) {
                addUser.setVisibility(View.VISIBLE);
                startGeoFencing.setVisibility(View.VISIBLE);
            }

            if ( getCurrentTab() == 2 ) {
                addUser.setVisibility(View.GONE);
                startGeoFencing.setVisibility(View.GONE);
            }

            // navigate to add user activity on click.
            addUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Integer id = mEvents.get(getAdapterPosition()).getId();
                    Intent intent = new Intent(mContext, AddUserActivity.class);
                    intent.putExtra("eventId", id);
                    mContext.startActivity( intent);
                    Log.d(TAG, " Event id clicked - " + id);
                }
            });

            // start geo fence on click.
            startGeoFencing.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast toast = Toast.makeText(mContext,"Creating GeoFence at location " + mEvents.get(getAdapterPosition()).getLat() + "/" + mEvents.get(getAdapterPosition()).getLon(), Toast.LENGTH_SHORT);
//                    toast.setMargin(50,50);
                    toast.show();
                }
            });
        }

    }

}
