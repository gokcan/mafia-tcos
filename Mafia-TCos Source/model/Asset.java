package model;

/**
 * Created by Basak Melis OCAL on 5/6/2017.
 */
public abstract class Asset{

    private double price;
    private double FBIRaidRisk;
    private double productionRate;
    private int rankThreshold;

    public Asset() {}

    public Asset(double price, double FBIRaidRisk, double productionRate, int rankThreshold)
    {
        this.price = price;
        this.FBIRaidRisk = FBIRaidRisk;
        this.productionRate = productionRate;
        this.rankThreshold = rankThreshold;
    }

    public double getPrice()
    {
        return price;
    }

    public double getFBIRaidRisk()
    {
        return FBIRaidRisk;
    }

    public double getProductionRate()
    {
        return productionRate;
    }

    public int getRankThreshold()
    {
        return rankThreshold;
    }

    public void setPrice(double price)
    {
        this.price = price;
    }

    public void setFBIRaidRisk(double FBIRaidRisk)
    {
        this.price = price;
    }

    public void setProductionRate()
    {
        this.productionRate = productionRate;
    }

    public void setRankThreshold(int threshold)
    {
        this.rankThreshold = rankThreshold;
    }
}
