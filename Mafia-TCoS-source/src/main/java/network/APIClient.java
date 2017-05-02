package network;

/**
 * @Author: Skylifee7 on 27/04/2017.
 */

import java.util.List;

import Model.Player;
import retrofit2.Call;
import retrofit2.http.*;


public interface APIClient {

    @GET("/api/players/")
    Call<Player> getPlayerData();

    @POST("/api/authenticate")
    Call<Authentication> doLoginOperation(@Body LoginInformation loginInformation);

    @POST("/api/signup")
    Call<Authentication> doSignupOperation(@Body LoginInformation loginInformation);

    @GET("/api/players/{user_id}")
    Call<Player> getPlayerData(@Header("x-access-token") String token, @Path("user_id") String userID);


}

