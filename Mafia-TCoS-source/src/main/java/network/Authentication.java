package network; /**
 * @Author: Skylifee7 on 01/05/2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Authentication {

    @SerializedName("authToken")
    @Expose
    public String authenticationToken;

    @SerializedName("player_uid")
    @Expose
    public String playerUID;

}