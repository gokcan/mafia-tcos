package model;
//import com.google.gson.annotations.Expose;
//import com.google.gson.annotations.SerializedName;
/**
 * Created by Basak Melis OCAL on 5/6/2017.
 */

public abstract class Crime {
 /*
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("difficulty")
    @Expose
    private int difficultyLevel;
    @SerializedName("_id")
    @Expose
*/
    private String crimeId;
    private int difficultyLevel;
    private String description;

    public Crime() {}

    public Crime(String description, String crimeID, int difficultyLevel) {
        this.description = description;
        this.difficultyLevel = difficultyLevel;
        this.crimeId = crimeID;
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    public String getCrimeID() {
        return crimeId;
    }

    public String getDescription() {
        return description;
    }

    public void setCrimeID(String crimeID) {
        this.crimeId = crimeID;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDifficultyLevel(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }
}