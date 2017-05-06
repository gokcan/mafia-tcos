package model;

/**
 * Created by Basak Melis OCAL on 5/6/2017.
 */
public class LaunderingTool extends Asset {

    public LaunderingTool(){}

    public LaunderingTool(double price, double FBIRaidRisk, double productionRate, int rankThreshold)
    {
        super(price, FBIRaidRisk, productionRate, rankThreshold);
    }
}
