package airblink.planerecognizer;

import java.util.ArrayList;

/**
 * Created by Alexandre on 10/06/2017.
 */

public class questionModel {

    ArrayList<questionObj> myModelTab = new ArrayList<>();

    public ArrayList<questionObj> getMyModelTab() {
        return myModelTab;
    }

    public questionModel () {

       // AIRBUS
        myModelTab.add(new questionObj ("airbus", "nose350",1,true, R.drawable.nose_a350));
        myModelTab.add(new questionObj ("airbus", "nose320",1,true, R.drawable.nose_a320));
        myModelTab.add(new questionObj ("airbus", "tail",1,true, R.drawable.tail_a320));
        myModelTab.add(new questionObj ("airbus", "engine",1,true, 0));
        myModelTab.add(new questionObj ("airbus", "ldgGear",1,true, 0));
        myModelTab.add(new questionObj ("airbus", "tail",1,true, R.drawable.tail_a330));







    }

}
