package network;
/**
 * @Author Skylifee7 on 01/05/2017.
 */

import java.util.prefs.Preferences;

public class SharedDataHelper {

    private final String AUTH_TOKEN = "auth_token";
    private final String USER_ID = "user_id";

    private Preferences preferences = Preferences.userNodeForPackage(SharedDataHelper.class);


    public void putUserToken(String token) {

        preferences.put(AUTH_TOKEN, token);
    }

    public String getUserToken() {

        return preferences.get(AUTH_TOKEN, "");
    }


    public void putUserID(String userID) {

        preferences.put(USER_ID, userID);
    }

    public String getUserID() {

        return preferences.get(USER_ID, "");
    }
}
