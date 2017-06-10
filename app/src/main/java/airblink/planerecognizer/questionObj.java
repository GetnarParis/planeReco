package airblink.planerecognizer;

/**
 * Created by Alexandre on 10/06/2017.
 */

public class questionObj  {
    public questionObj (String acManuf, String physicalDiff, int weight, boolean isOpen) {
        this.acManuf = acManuf;
        this.physicalDiff = physicalDiff;
        this.weight= weight;
        this.isOpen= isOpen;
    }
    private String acManuf;
    private String physicalDiff;
    public int weight;
    public boolean isOpen;

}
