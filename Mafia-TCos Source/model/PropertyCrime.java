package model;
//import com.google.gson.annotations.Expose;
//import com.google.gson.annotations.SerializedName;
/**
 * Created by Basak Melis OCAL on 5/6/2017.
 */
public class PropertyCrime extends Crime{

    public PropertyCrime() {}

    public PropertyCrime(String description, String crimeID, int difficultyLevel)
    {
        super(description, crimeID, difficultyLevel);
    }
}


