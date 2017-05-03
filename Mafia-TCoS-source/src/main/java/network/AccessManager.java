package network;
/**
 * @Author: Skylifee7 on 01/05/2017.
 */

import Model.Player;
import retrofit2.*;
import retrofit2.Call;

import java.io.IOException;
import java.util.prefs.Preferences;

public class AccessManager {

    private Preferences prefs;
    private String userName = "Test";
    private String userPassword = "123456";
    private String auth_token = "Auth-Token";
    private String user_id = "User-ID";
    private SharedDataHelper sharedDataHelper;
    private AccessClientListener listener;

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

                    sharedDataHelper.putUserID(user_id);
                    sharedDataHelper.putUserToken(auth_token);

                    if(listener!=null)
                    listener.onLoginSuccess("Success");

                    System.out.println("Success.");

                } else if (response.code() == 401 || response.code() == 403)
                    System.out.println("Unauthorized access.");
                else
                    System.out.println("Unsuccessful.");


            }

            @Override
            public void onFailure(Call<Authentication> call, Throwable t) {

                if (t instanceof IOException)
                    System.out.println("Check your network connection.");
                else
                    System.out.println("Service is unavailable.");
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
                    System.out.println("Success.");


                } else if (response.code() == 401 || response.code() == 403)
                    System.out.println("Unauthorized access.");
                else
                    System.out.println("Unsuccessful.");

            }

            @Override
            public void onFailure(Call<Authentication> call, Throwable t) {

                if (t instanceof IOException)
                    System.out.println("Check your network connection.");
                else
                    System.out.println("Service is unavailable.");
            }
        });


    }

    protected void callGetPlayerData() {

        sharedDataHelper = new SharedDataHelper();

        APIClient apiClient = APICallHelper.getInstance().create(APIClient.class);
        Call<Player> getPlayerData = apiClient.getPlayerData(sharedDataHelper.getUserToken(), sharedDataHelper.getUserID());
        getPlayerData.enqueue(new Callback<Player>() {

            @Override
            public void onResponse(Call<Player> call, Response<Player> response) {

                if (response != null && response.body() != null) {
                    final Player player1;
                    player1 = response.body();

                    System.out.println(player1.toString());
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

    private LoginInformation getBodyParameter() {

        LoginInformation loginInformation = new LoginInformation();
        loginInformation.setUsername(userName);
        loginInformation.setPassword(userPassword);
        return loginInformation;
    }

    public interface AccessClientListener {
        void onLoginFailure(ErrorType type);
        void onSignupFailure(ErrorType type);
        void onLoginSuccess(String response);
        void onSignupSuccess(Player player);
    }


}
