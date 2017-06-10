package airblink.planerecognizer;

import android.graphics.drawable.Drawable;

/**
 * Created by Alexandre on 10/06/2017.
 */

public class questionObj  {
    public questionObj (String acManuf, String physicalDiff, int weight, boolean isOpen, int associatedDrawable) {
        this.acManuf = acManuf;
        this.physicalDiff = physicalDiff;
        this.weight= weight;
        this.isOpen= isOpen;
        this.associatedDrawable = associatedDrawable;
    }
    private String acManuf;
    private String physicalDiff;
    private String questionString;
    public int weight;
    public boolean isOpen;
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

    public String getPhysicalDiff() {
        return physicalDiff;
    }

    public String getQuestionString() {
        return questionString;
    }
}
