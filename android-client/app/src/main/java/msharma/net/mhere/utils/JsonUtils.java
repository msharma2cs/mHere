package msharma.net.mhere.utils;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

import msharma.net.mhere.models.AddUserEvent;
import msharma.net.mhere.models.Event;
import msharma.net.mhere.models.Token;
import msharma.net.mhere.models.User;

public class JsonUtils {

    private static final String TAG = "JsonUtils";

    public static Token parseLoginResponse(String loginJsonString) {
        Token token = new Token();

        try {
            JSONObject mainJSONObject = new JSONObject(loginJsonString);
            token.setIdToken(mainJSONObject.getString("id_token"));
            token.setRefreshToken(mainJSONObject.getString("refresh_token"));
        } catch (JSONException e) {
            Log.d(TAG, "Login JSON String parse error");
            e.printStackTrace();
        }
        return token;
    }

    public static User parseUserDetails(String userDetailsJsonString) {
        User _userDetails = new User();

        try {
            JSONObject mainJSONObject = new JSONObject(userDetailsJsonString);
            _userDetails.setId( mainJSONObject.getInt("id") );
            _userDetails.setFirstName( mainJSONObject.getString("firstName") );
            _userDetails.setLastName( mainJSONObject.getString("lastName") );
            _userDetails.setEmail( mainJSONObject.getString("email") );
            _userDetails.setLogin( mainJSONObject.getString("login") );
            _userDetails.setFullName();
        } catch (JSONException e) {
            Log.d(TAG, "Login JSON String parse error");
            e.printStackTrace();
        }
        return _userDetails;
    }

    public static ArrayList<Event> parseEvents(String eventsJsonString) {
        ArrayList<Event> events = new ArrayList<>();

        try {
            JSONArray mainJSONObject = new JSONArray(eventsJsonString);

            for ( int i = 0; i < mainJSONObject.length(); i++ ) {
                JSONObject eventJson = mainJSONObject.getJSONObject(i);
                Event _event = new Event();
                _event.setId(eventJson.getInt("id"));
                _event.setTitle(eventJson.getString("title"));
                _event.setDescription(eventJson.getString("description"));
                _event.setLat(eventJson.getDouble("lat"));
                _event.setLon(eventJson.getDouble("lon"));
                _event.setGeoRadius(eventJson.getInt("geoRadius"));
                _event.setEventDate(DateTimeUtils.formatDateFromString(eventJson.getString("eventDate")));
                _event.setStartTime(DateTimeUtils.formatDateFromString(eventJson.getString("startTime")));
                _event.setEndTime(DateTimeUtils.formatDateFromString(eventJson.getString("endTime")));
                _event.setCreatedAt(DateTimeUtils.formatDateFromString(eventJson.getString("createdAt")));
                _event.setCreatedById(eventJson.getLong("createdById"));
                _event.setCreatedByLogin(eventJson.getString("createdByLogin"));
Log.d("JSON", _event.toString());
                events.add(_event);
            }
        } catch (JSONException e) {
            Log.d(TAG, "Login JSON String parse error");
            e.printStackTrace();
        }

        return events;
    }

    public static ArrayList<User> parseUsers(String eventsJsonString) {
        ArrayList<User> users = new ArrayList<>();

        try {
            JSONArray mainJSONObject = new JSONArray(eventsJsonString);

            for ( int i = 0; i < mainJSONObject.length(); i++ ) {
                JSONObject userJson = mainJSONObject.getJSONObject(i);
                User _user = new User();
                _user.setId( userJson.getInt("id") );
                _user.setFirstName( userJson.getString("firstName") );
                _user.setLastName( userJson.getString("lastName") );
                _user.setEmail( userJson.getString("email") );
                _user.setLogin( userJson.getString("login") );
                _user.setFullName();
                users.add(_user);
            }
        } catch (JSONException e) {
            Log.d(TAG, "Login JSON String parse error");
            e.printStackTrace();
        }

        return users;
    }

    public static AddUserEvent parseUserEvent(String eventsJsonString) {
        AddUserEvent addUserEvent = new AddUserEvent();

        try {
            JSONObject mainJSONObject = new JSONObject(eventsJsonString);
            addUserEvent.setId(mainJSONObject.getInt("id"));
            addUserEvent.setUserId(mainJSONObject.getInt("userId"));
            addUserEvent.setEventId(mainJSONObject.getInt("eventId"));
        } catch (JSONException e) {
            Log.d(TAG, "Login JSON String parse error");
            e.printStackTrace();
        }

        return addUserEvent;
    }

}
