package msharma.net.mhere.sharedpreferences;

import android.content.Context;
import android.content.SharedPreferences;
import msharma.net.mhere.models.Token;
import msharma.net.mhere.models.User;

public class UserProfileManager {

    private static final String PREFERENCES_NAME = "mhere_user_profile";
    private static final String ID = "id";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String FULL_NAME = "fullname";
    private static final String LOGIN = "login";
    private static final String EMAIL = "email";
    private static final String JWT_TOKEN = "id_token";

    public static void saveUserToken(Context context, Token token) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);

        sp.edit()
                .putString(JWT_TOKEN, token.getIdToken())
                .apply();
    }

    public static void deleteUserToken(Context context) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);

        sp.edit()
                .putString(JWT_TOKEN, null)
                .apply();
    }

    public static void saveUserInfo(Context context, User user) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);

        sp.edit()
                .putInt(ID, user.getId())
                .putString(FIRST_NAME, user.getFirstName())
                .putString(LAST_NAME, user.getLastName())
                .putString(FULL_NAME, user.getFullName())
                .putString(LOGIN, user.getLogin())
                .putString(EMAIL, user.getEmail())
                .putString(JWT_TOKEN, user.getToken())
                .apply();
    }

    public static User getUserInfo(Context context) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);

        return new User(
                sp.getInt(ID, 0),
                sp.getString(FIRST_NAME, null),
                sp.getString(LAST_NAME, null),
                sp.getString(FULL_NAME, null),
                sp.getString(LOGIN, null),
                sp.getString(EMAIL, null),
                sp.getString(JWT_TOKEN, null)
        );
    }

    public static void deleteUserInfo(Context context) {
        SharedPreferences sp = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);

        sp.edit()
                .putInt(ID, 0)
                .putString(FIRST_NAME, null)
                .putString(LAST_NAME, null)
                .putString(FULL_NAME, null)
                .putString(LOGIN, null)
                .putString(EMAIL, null)
                .putString(JWT_TOKEN, null)
                .apply();
    }

}