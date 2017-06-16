package airblink.planerecognizer;

import android.graphics.drawable.Drawable;

/**
 * Created by Alexandre on 10/06/2017.
 */

public class questionObj  {
    public questionObj (int id, String acManuf,String acModel, String acVersion, String physicalDiff,  int weight,String questionString, int isOpen, int associatedDrawable) {
        this.id = id;
        this.acManuf = acManuf;
        this.acModel = acModel;
        this.acVersion =acVersion;
        this.physicalDiff = physicalDiff;
        this.weight= weight;
        this.isOpen= isOpen;
        this.questionString = questionString;
        this.associatedDrawable = associatedDrawable;
    }
    private int id;
    private String acManuf;
    private String acModel;
    private String acVersion;
    private String physicalDiff;
    private String questionString;
    public int weight;
    public int isOpen;
    private int associatedDrawable;

    public int getAssociatedDrawable() {
        return associatedDrawable;
    }

    public int getWeight() {
        return weight;
    }
    public int getId() {
        return id;
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
    public String getPhysicalDiff() {
        return physicalDiff;
    }
    public String getQuestionString() {
        return questionString;
    }
    public int getIsOpen() {
        return isOpen;
    }
}
