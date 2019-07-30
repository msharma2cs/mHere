package msharma.net.mhere.utils;

import android.content.Context;
import java.io.IOException;
import msharma.net.mhere.sharedpreferences.UserProfileManager;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ServicesUtil {

    private final String BASE_URL = "http://gateway.msharma.net:8080/api/";
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    OkHttpClient client = new OkHttpClient();
    Context context;

    public ServicesUtil(Context context) {
        this.context = context;
    }

    private Request getRequestWithoutToken(String url) {
        return new Request.Builder()
                .url(url)
                .build();
    }

    private Request getRequestWithToken(String url) {
        String userToken = UserProfileManager.getUserInfo(context).getToken();
        return new Request.Builder()
                .url(url)
                .header("Authorization", "Bearer " + userToken)
                .build();
    }

    private Request postRequestWithoutToken(String url, RequestBody requestBody) {
        return new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
    }

    private Request postRequestWithToken(String url, RequestBody requestBody) {
        String userToken = UserProfileManager.getUserInfo(context).getToken();
        return new Request.Builder()
                .url(url)
                .header("Authorization", "Bearer " + userToken)
                .post(requestBody)
                .build();
    }

    public String getResponseWithoutToken(String path) throws IOException  {
        Response response = client.newCall(getRequestWithoutToken(this.BASE_URL + path)).execute();
        return response.body().string();
    }

    public String getResponseWithToken(String path) throws IOException  {
        Response response = client.newCall(getRequestWithToken(this.BASE_URL + path)).execute();
        return response.body().string();
    }

    public String postResponseWithoutToken(String path, String jsonBody) throws IOException  {
        RequestBody body = RequestBody.create(JSON, jsonBody);
        Response response = client.newCall(postRequestWithoutToken(this.BASE_URL + path, body)).execute();
        return response.body().string();
    }

    public String postResponseWithToken(String path, String jsonBody) throws IOException  {
        RequestBody body = RequestBody.create(JSON, jsonBody);
        Response response = client.newCall(postRequestWithToken(this.BASE_URL + path, body)).execute();
        return response.body().string();
    }

    public String authenticate(String jsonBody) throws IOException {
        RequestBody body = RequestBody.create(JSON, jsonBody);
        Response response = client.newCall(postRequestWithoutToken(this.BASE_URL + "authenticate", body)).execute();
        return response.body().string();
    }

    public String getUserDetails() throws IOException {
        Response response = client.newCall(getRequestWithToken(this.BASE_URL + "users/user")).execute();
        return response.body().string();
    }

}