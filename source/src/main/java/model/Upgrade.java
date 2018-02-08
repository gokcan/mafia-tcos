package model;
/**
 * @Author Skylifee7 on 30/04/2017.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Upgrade {

    @SerializedName("launderingAsset")
    @Expose
    private Integer launderingAsset;
    @SerializedName("manufacturingAsset")
    @Expose
    private Integer manufacturingAsset;

    /**
     * No args constructor for use in serialization
     *
     */
    public Upgrade() {
    }

    /**
     * @param launderingAsset
     * @param manufacturingAsset
     */
    public Upgrade(Integer launderingAsset, Integer manufacturingAsset) {
        super();
        this.launderingAsset = launderingAsset;
        this.manufacturingAsset = manufacturingAsset;
    }

    public Integer getLaunderingAsset() {
        return launderingAsset;
    }

    public void setLaunderingAsset(Integer launderingAsset) {
        this.launderingAsset = launderingAsset;
    }

    public Integer getManufacturingAsset() {
        return manufacturingAsset;
    }

    public void setManufacturingAsset(Integer manufacturingAsset) {
        this.manufacturingAsset = manufacturingAsset;
    }

    @Override
    public String toString() {

        return "Upgrades{" +
                "name='" + launderingAsset + '\'' +
                ",money=" + manufacturingAsset +
                '}';
    }

}