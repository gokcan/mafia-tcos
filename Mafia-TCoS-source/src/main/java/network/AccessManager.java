package network;
/**
 * Author: Skylifee7 on 01/05/2017.
 * ----- ----- ----- ----- ----- ----- -----  * ----- ----- ----- ----- ----- ----- -----
 * Access Manager class handles almost all the communications between Client and Server(REST)
 * via usual HTTP(S) Requests (GET/POST/PUT/DELETE). OnSuccess and OnFailure listeners are really helpful
 * to achieve low coupling. AccessManager class never talks with the UI components. Yeah, she is a little shy..
 */

import Model.Crime;
import Model.Player;
import retrofit2.Call;
import retrofit2.*;

import java.util.ArrayList;
import java.util.prefs.Preferences;
import java.io.IOException;

public class AccessManager {

    private Preferences prefs;
    private String userName = "Test";
    private String userPassword = "123456";
    private String auth_token = "Auth-Token";
    private String user_id = "User-ID";
    private SharedDataHelper sharedDataHelper;
    private AccessClientListener listener;

    public interface AccessClientListener {
        void onLoginFailure(String response);

        void onSignupFailure(String response);

        void onFetchPlayerSuccess(Player player);

        void onFetchPlayerFailure(String response);

        void onLoginSuccess(String response);

        void onSignupSuccess(String response);

        void onFetchCrimesSuccess(ArrayList<Crime> crimes);
    }

    public AccessManager() {
        // set null or default listener or accept as argument to constructor
        this.listener = null;
    }

    public void setCustomListener(AccessClientListener listener) {
        this.listener = listener;
    }


    public void callLoginService(String user, String pass) {
        APIClient apiClient = APICallHelper.getInstance().create(APIClient.class);
        userName = user;
        userPassword = pass;

        Call<Authentication> loginService = apiClient.doLoginOperation(getBodyParameter());
        loginService.enqueue(new Callback<Authentication>() {

            @Override
            public void onResponse(Call<Authentication> call, Response<Authentication> response) {

                if (response != null && response.body() != null && response.body().authenticationToken != null) {

                    sharedDataHelper = new SharedDataHelper();
                    auth_token = response.body().authenticationToken;
                    user_id = response.body().playerUID;

                    //String emoji = new String(Character.toChars(0xF160E));
                    sharedDataHelper.putUserID(user_id);
                    sharedDataHelper.putUserToken(auth_token);

                    if (listener != null)
                        listener.onLoginSuccess("Welcome back " + userName + " :)");

                } else if (response.code() == 401 || response.code() == 403)
                    listener.onLoginFailure("Unauthorized access.");
                else
                    listener.onLoginFailure("Server is unavailable.");
            }

            @Override
            public void onFailure(Call<Authentication> call, Throwable t) {

                if (t instanceof IOException)
                    listener.onLoginFailure("Check your internet connection.");
                else
                    listener.onLoginFailure("Service is unavailable.");
            }
        });
    }

    public void callSignupService(String user, String pass) {

        APIClient apiClient = APICallHelper.getInstance().create(APIClient.class);

        userName = user;
        userPassword = pass;

        Call<Authentication> signupService = apiClient.doSignupOperation(getBodyParameter());
        signupService.enqueue(new Callback<Authentication>() {

            @Override
            public void onResponse(Call<Authentication> call, Response<Authentication> response) {

                if (response != null && response.body() != null && response.body().playerUID != null
                        && !response.body().playerUID.isEmpty()) {

                    sharedDataHelper = new SharedDataHelper();
                    user_id = response.body().playerUID;

                    sharedDataHelper.putUserID(user_id);

                    if (listener != null)
                        listener.onSignupSuccess("Please login now " + userName + " :)");


                } else if (response.code() == 401 || response.code() == 403)
                    listener.onSignupFailure("Unauthorized access.. Check your credentials.");
                else
                    listener.onSignupFailure("Server is unavailable.");
            }

            @Override
            public void onFailure(Call<Authentication> call, Throwable t) {

                if (t instanceof IOException)
                    listener.onSignupFailure("Check your internet connection.");
                else
                    listener.onSignupFailure("Service is unavailable.");
            }
        });

    }

    public void callGetPlayerData() {

        sharedDataHelper = new SharedDataHelper();

        APIClient apiClient = APICallHelper.getInstance().create(APIClient.class);
        Call<Player> getPlayerData = apiClient.getPlayerData(sharedDataHelper.getUserToken(), sharedDataHelper.getUserID());
        getPlayerData.enqueue(new Callback<Player>() {

            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {

                if (response != null && response.body() != null) {

                    final Player player1;
                    player1 = response.body();

                    if (listener != null)
                        listener.onFetchPlayerSuccess(player1);

                    System.out.println(player1.toString());
                }
            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {

                if (t instanceof IOException)
                    listener.onFetchPlayerFailure("Check your internet connection.");
                else
                    listener.onFetchPlayerFailure("Service is unavailable.");
            }
        });
    }

    public void callUpdateService(Player player) {

        APIClient apiClient = APICallHelper.getInstance().create(APIClient.class);

        Call<Player> update = apiClient.updatePlayerData(sharedDataHelper.getUserToken(), sharedDataHelper.getUserID(), player);
        update.enqueue(new Callback<Player>() {

            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {

                if (response != null && response.body() != null && response.isSuccessful()) {

                    System.out.println("Success. Player has been updated.");

                } else if (response.code() == 401 || response.code() == 403) {

                    System.out.println("Unauthorized access.");
                }
            }

            @Override
            public void onFailure(Call<Player> call, Throwable t) {

                if (t instanceof IOException)
                    System.out.println("Check your network connection.");
                else
                    System.out.println("Service is unavailable.");
            }
        });

    }

    public void callGetCrimes() {

        APIClient apiClient = APICallHelper.getInstance().create(APIClient.class);
        Call <ArrayList<Crime>> getCrimes = apiClient.getCrimes();
        getCrimes.enqueue(new Callback<ArrayList<Crime>>() {

            @Override
            public void onResponse(Call<ArrayList<Crime>> call, Response<ArrayList<Crime>> response) {

                if (response != null && response.body() != null) {

                    ArrayList<Crime> crimeArrayList = response.body();

                    if (listener != null)
                        listener.onFetchCrimesSuccess(crimeArrayList);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Crime>> call, Throwable t) {

                if (t instanceof IOException)
                    listener.onFetchPlayerFailure("Check your internet connection.");
                else
                    listener.onFetchPlayerFailure("Service is unavailable.");
            }
        });
    }

    private LoginInformation getBodyParameter() {

        LoginInformation loginInformation = new LoginInformation();
        loginInformation.setUsername(userName);
        loginInformation.setPassword(userPassword);
        return loginInformation;
    }


}
