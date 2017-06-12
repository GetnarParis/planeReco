package airblink.planerecognizer;

/**
 * Created by Alexandre on 12/06/2017.
 */

public class aircraftObj {
    public aircraftObj (String acManuf, String acModel, String acVersion, int weight, int associatedDrawable) {
        this.acManuf = acManuf;
        this.acModel = acModel;
        this.acVersion =acVersion;
        this.weight= weight;
        this.associatedDrawable = associatedDrawable;
    }

    private String acManuf;
    private String acModel;
    private String acVersion;
    public int weight;
    private int associatedDrawable;

    public int getAssociatedDrawable() {
        return associatedDrawable;
    }
    public int getWeight() {
        return weight;
    }
    public String getAcManuf() {
        return acManuf;
    }
    public String getAcModel() {
        return acModel;
    }
    public String getAcVersion() {
        return acVersion;
    }

}
