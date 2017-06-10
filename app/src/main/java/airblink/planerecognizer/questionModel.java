package airblink.planerecognizer;

import java.util.ArrayList;

/**
 * Created by Alexandre on 10/06/2017.
 */

public class questionModel {

    ArrayList<questionObj> myModel = new ArrayList<>();
    questionObj myObj =  new questionObj ("airbus", "tata",1,true);

    public questionModel () {

       // AIRBUS
        myModel.add(new questionObj ("airbus", "nose350",1,true));
        myModel.add(new questionObj ("airbus", "nose320",1,true));
        myModel.add(new questionObj ("airbus", "tail",1,true));
        myModel.add(new questionObj ("airbus", "engine",1,true));
        myModel.add(new questionObj ("airbus", "ldgGear",1,true));
        myModel.add(new questionObj ("airbus", "tail",1,true));
        myModel.add(new questionObj ("airbus", "tail",1,true));






    }

}
