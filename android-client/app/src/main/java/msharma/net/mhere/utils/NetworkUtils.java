package msharma.net.mhere.utils;

import android.net.Uri;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Network utils class to get data from api in background using AsyncTask.
 */
public class NetworkUtils {

    private static final String TAG = "NetworkUtils";

    final static String BASE_URL = "http://gateway.msharma.net:8080/api/";

    public static URL buildUrl(String path) {
        Uri builtUri = Uri.parse(BASE_URL + path).buildUpon()
                .build();

        Log.d(TAG, "URL - " + builtUri.toString());

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            Log.d(TAG, "Malformed URL built exception.");
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();

            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } catch ( Exception e ) {
            Log.d(TAG, "Network connection HTTP exception.");
            e.printStackTrace();
            return null;
        } finally {
            urlConnection.disconnect();
        }
    }

}