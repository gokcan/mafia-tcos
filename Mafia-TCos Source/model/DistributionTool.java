package model;

/**
 * Created by Basak Melis OCAL on 5/6/2017.
 */
public class DistributionTool extends Asset{

    public DistributionTool(){};

    public DistributionTool(double price, double FBIRaidRisk, double productionRate, int rankThreshold)
    {
        super(price, FBIRaidRisk, productionRate, rankThreshold);
    }
}
