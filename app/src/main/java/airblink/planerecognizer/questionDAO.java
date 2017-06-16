package airblink.planerecognizer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.Random;


/**
 * Created by Alexandre on 10/06/2017.
 */

public class questionDAO  {
    private SQLiteDatabase mDbQuestion;
    private SQLiteDatabase mDbAC;

    public static final String TABLE_NAME_QUESTION = "questions_db";
    public static final String TABLE_NAME_AIRCRAFT = "databaseAC_db";
    public static final String DIFF_ID = "key";
    public static final String DIFF_KEY = "id";
    public static final String AC_MANUF = "manuf";
    public static final String AC_MODEL = "model";
    public static final String AC_VERSION = "version";
    public static final String PHYSICAL_DIFF = "physDiff";
    public static final String WEIGHT = "weight";
    public static final String IS_OPEN = "isOpen";
    public static final String QUESTION_TEXT = "questionText";
    public static final String ASSOCIATED_DRAWABLE = "drawable";

    private String selected_AC_Manuf;
    private String selected_AC_Model;
    private String selected_AC_Version;
    private String selected_Question_Id;


    public questionDAO(Context pContext) {
        mDbQuestion = new dbQuestion(pContext).getWritableDatabase();
      mDbAC = new dbAircraft (pContext).getWritableDatabase();

    }


    /**
     * @param q le métier à ajouter à la base
     */
    public void addQuestion(questionObj q) {
        ContentValues value = new ContentValues();
        value.put(questionDAO.DIFF_KEY, q.getId());
        value.put(questionDAO.AC_MANUF, q.getAcManuf());
        value.put(questionDAO.AC_MODEL, q.getAcModel());
        value.put(questionDAO.AC_VERSION, q.getAcVersion());

        value.put(questionDAO.PHYSICAL_DIFF, q.getPhysicalDiff());

        value.put(questionDAO.WEIGHT, q.getWeight());
        value.put(questionDAO.QUESTION_TEXT, q.getQuestionString());
        value.put(questionDAO.IS_OPEN, q.getIsOpen());
        value.put(questionDAO.ASSOCIATED_DRAWABLE, q.getAssociatedDrawable());

        mDbQuestion.insert(questionDAO.TABLE_NAME_QUESTION, null, value);

    }

    public void addAircraft(aircraftObj ac) {
        ContentValues value = new ContentValues();
        value.put(questionDAO.AC_MANUF, ac.getAcManuf());
        value.put(questionDAO.AC_MODEL, ac.getAcModel());
        value.put(questionDAO.AC_VERSION, ac.getAcVersion());
        value.put(questionDAO.WEIGHT, ac.getWeight());
        value.put(questionDAO.ASSOCIATED_DRAWABLE,ac.getAssociatedDrawable());
        mDbAC.insert(questionDAO.TABLE_NAME_AIRCRAFT, null, value);

    }

    /**
     * @param id l'identifiant du métier à supprimer
     */
    public void delete(long id) {
        // CODE
    }

    /**
     * @param m le métier modifié
     */
    public void updateQuestion(questionObj m) {
        // CODE
    }


    public questionObj select() {
        int nbrOfTableLine= 0;
       // Cursor c = mDbQuestion.rawQuery("SELECT * " +" FROM " + TABLE_NAME_QUESTION +  " WHERE " + WEIGHT + "= (SELECT MAX(" + WEIGHT +") FROM "+ TABLE_NAME_QUESTION +")", null );
        Cursor c = mDbQuestion.rawQuery("SELECT * FROM " + TABLE_NAME_QUESTION +  " WHERE " + WEIGHT + " >= 1", null );

        nbrOfTableLine=  c.getCount();
        Log.d ("nbr of line",String.valueOf(nbrOfTableLine));
        Random r = new Random();
        int rdmNbr = r.nextInt(nbrOfTableLine);
        c.moveToPosition(rdmNbr);


        questionObj qObj = new questionObj(c.getInt(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5),c.getInt(6),c.getString(7),c.getInt(8),c.getInt(9));
        selected_AC_Manuf = qObj.getAcManuf();
        selected_AC_Model = qObj.getAcModel();
        selected_AC_Version = qObj.getAcVersion();
        selected_Question_Id = String.valueOf(qObj.getId());
        Log.d ("model de la selection",String.valueOf(qObj.getAcManuf()));
        c.close();
        return qObj;
    }

    public void updateTablesWeight () {


      //  Cursor c = mDbAC.rawQuery("SELECT *  FROM " + TABLE_NAME_QUESTION + " where manuf = ? and " + AC_MODEL + "= ? and " + DIFF_KEY + " != ?" , new String[]{selected_AC_Manuf,selected_AC_Model,selected_Question_Id});
        Cursor c = mDbQuestion.rawQuery("SELECT *  FROM " + TABLE_NAME_QUESTION,null);
        c.moveToFirst();
        int nbrOfTableLineModel= 0;
        nbrOfTableLineModel=  c.getCount();
        double weight = 0.5/nbrOfTableLineModel;
        ContentValues value = new ContentValues();
        value.put(WEIGHT,weight);
        // mDbAC.update(TABLE_NAME_AIRCRAFT, value, AC_MANUF + " = ?" , new String[] {selected_AC_Manuf});
        mDbQuestion.update(TABLE_NAME_QUESTION, value, AC_MANUF  + " = ? and " + AC_MODEL + "= ?" , new String[] {selected_AC_Manuf,selected_AC_Model});

        Cursor cSelected = mDbQuestion.rawQuery("SELECT *  FROM " + TABLE_NAME_QUESTION + " where id = ?" , new String[]{selected_Question_Id});
        cSelected.moveToFirst();
        double weightSelected = 0;
        ContentValues valueSelected = new ContentValues();
        valueSelected.put(WEIGHT,weightSelected);
        // mDbAC.update(TABLE_NAME_AIRCRAFT, value, AC_MANUF + " = ?" , new String[] {selected_AC_Manuf});
        mDbQuestion.update(TABLE_NAME_QUESTION, valueSelected, DIFF_KEY  + " = ?" , new String[] {selected_Question_Id});

        Cursor cToUp = mDbQuestion.rawQuery("SELECT *  FROM " + TABLE_NAME_QUESTION + " where manuf = ?" , new String[]{selected_AC_Manuf});
        cToUp.moveToFirst();
        int nbrOfTableLineToUp= 0;
        double weightToAdd;
        nbrOfTableLineToUp=  cToUp.getCount();
        if (nbrOfTableLineModel == 0) {
             weightToAdd = 2 / nbrOfTableLineToUp;
        }
        else  weightToAdd = 1.5/ nbrOfTableLineToUp;
        ContentValues valueUp = new ContentValues();
        valueUp.put(WEIGHT,weightToAdd);
       // mDbAC.update(TABLE_NAME_AIRCRAFT, value, AC_MANUF + " = ?" , new String[] {selected_AC_Manuf});
        mDbQuestion.update(TABLE_NAME_QUESTION, valueUp, AC_MANUF  + " != ? " , new String[] {selected_AC_Manuf});

        Cursor cToDown = mDbQuestion.rawQuery("SELECT *  FROM " + TABLE_NAME_QUESTION + " where manuf = ?" , new String[]{selected_AC_Manuf});
        cToUp.moveToFirst();
        int nbrOfTableLineToDown= 0;
        nbrOfTableLineToDown=  cToDown.getCount();
        double weightToWithdraw = 1/nbrOfTableLineToDown;
        ContentValues valueDown = new ContentValues();
        valueDown.put(WEIGHT,weightToWithdraw);
        // mDbAC.update(TABLE_NAME_AIRCRAFT, value, AC_MANUF + " = ?" , new String[] {selected_AC_Manuf});
        mDbQuestion.update(TABLE_NAME_QUESTION, valueDown, AC_MANUF  + " != ? " , new String[] {selected_AC_Manuf});

        cSelected.close();
        cToUp.close();
        cToDown.close();
        c.close();
    }

    public void resetWeightOfTables () {
        Cursor c = mDbAC.rawQuery("SELECT * FROM " + TABLE_NAME_AIRCRAFT  , null);
        while (c.moveToNext()) {
            ContentValues value = new ContentValues();
            value.put(WEIGHT,1);
            mDbAC.update(TABLE_NAME_AIRCRAFT, value,WEIGHT  +" !=? ",  new String[] {"1"});
        }
        c.close();
         c = mDbQuestion.rawQuery("SELECT * FROM " + TABLE_NAME_QUESTION  , null);
        while (c.moveToNext()) {
            ContentValues value = new ContentValues();
            value.put(WEIGHT,1);
            mDbQuestion.update(TABLE_NAME_QUESTION, value,WEIGHT  +" !=? ",  new String[] {"1"});
        }
        c.close();


    }
    public void resetDb () {

        mDbQuestion.execSQL("delete from "+ TABLE_NAME_QUESTION);
        mDbAC.execSQL("delete from "+ TABLE_NAME_AIRCRAFT);

    }
}
