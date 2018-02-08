package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @Author SKYLIFE on 03/05/2017.
 */

public class Crime {

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("difficulty")
    @Expose
    private int difficultyLevel;
    @SerializedName("_id")
    @Expose
    private String crimeID;
    @SerializedName("PropertyCrime")
    @Expose
    private String isProperty;

    /**
     * No args constructor for use in serialization
     */

    public Crime() {

    }

    public Crime(String description, String crimeID, int difficultyLevel, String isProperty) {

        super();
        this.description = description;
        this.difficultyLevel = difficultyLevel;
        this.crimeID = crimeID;
        this.isProperty = isProperty;
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    public String getCrimeID() {
        return crimeID;
    }

    public String getDescription() {
        return description;
    }

    public String getisProperty() {
        return isProperty;
    }

    public void setCrimeID(String crimeID) {
        this.crimeID = crimeID;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDifficultyLevel(int difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public void setProperty(String isProperty) {
        this.isProperty = isProperty;
    }


}
