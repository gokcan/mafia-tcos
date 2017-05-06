package model;

/**
 * Created by Basak Melis OCAL on 5/6/2017.
 */
public class DrugBusiness extends Crime{

    private Asset asset;

    public DrugBusiness() {}

    public DrugBusiness(String description, String crimeID, int difficultyLevel, Asset asset)
    {
        super(description, crimeID, difficultyLevel);
        this.asset = asset;
    }

    public Asset getAsset()
    {
        return asset;
    }

    public void setAsset(Asset asset)
    {
        this.asset = asset;
    }

}
