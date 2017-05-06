package network;
/**
 * @Author: Skylifee7 on 27/04/2017.
 */
import java.util.ArrayList;
import java.util.List;

import Model.Crime;
import Model.Player;
import retrofit2.Call;
import retrofit2.http.*;
import retrofit2.http.PUT;


public interface APIClient {

    @GET("/api/players/")
    Call <Player> getPlayerData();

    @POST("/api/authenticate")
    Call<Authentication> doLoginOperation(@Body LoginInformation loginInformation);

    @POST("/api/signup")
    Call<Authentication> doSignupOperation(@Body LoginInformation loginInformation);

    @GET("/api/players/{player_uid}")
    Call<Player> getPlayerData(@Header("x-access-token") String token,
                               @Path("player_uid") String userID);

    @PUT("/api/players/{player_uid}/")
    Call<Player> updatePlayerData (@Header("x-access-token") String token,
                                   @Path("player_uid") String userID,
                                   @Body Player player);
    @GET("/api/crimes/")
    Call<ArrayList<Crime>> getCrimes();
}
