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
    public static final String DIFF_KEY = "id";
    public static final String AC_MANUF = "manuf";
    public static final String AC_MODEL = "ac_model";
    public static final String AC_VERSION = "ac_version";
    public static final String PHYSICAL_DIFF = "physDiff";
    public static final String WEIGHT = "weight";
    public static final String IS_OPEN = "isOpen";
    public static final String QUESTION_TEXT = "questionText";

    public static final String ASSOCIATED_DRAWABLE = "drawable";




    public questionDAO(Context pContext) {
        mDbQuestion = new dbQuestion(pContext).getWritableDatabase();
      mDbAC = new dbAircraft (pContext).getWritableDatabase();

    }


    /**
     * @param q le métier à ajouter à la base
     */
    public void addQuestion(questionObj q) {
        ContentValues value = new ContentValues();
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

    /**
     * @param id l'identifiant du métier à récupérer
     */
    public questionObj select() {
        int nbrOfTableLine= 0;
        Cursor c = mDbQuestion.rawQuery("SELECT *," + ASSOCIATED_DRAWABLE+ ", MAX ("+WEIGHT+") FROM " + TABLE_NAME_QUESTION + " GROUP BY " + ASSOCIATED_DRAWABLE, null );
       // c.moveToFirst();
        nbrOfTableLine=  c.getCount();
        Log.d ("nbrof line",String.valueOf(nbrOfTableLine));
        Random r = new Random();
        int rdmNbr = r.nextInt(nbrOfTableLine);
        c.moveToPosition(rdmNbr);


        questionObj qObj = new questionObj(c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getInt(5),c.getString(2),c.getInt(7),c.getInt(8));
        c.close();
        return qObj;
    }

    public void updateTablesWeight () {
        Cursor c = mDbAC.rawQuery("SELECT " + WEIGHT+ " FROM " + TABLE_NAME_AIRCRAFT + " where ac_Model = ? " , new String[]{"350"});
        c.moveToFirst();
        int weightToChange = c.getInt(0);
        String s = String.valueOf(weightToChange);
        Log.d ("test",s);
        weightToChange= weightToChange+1;
        ContentValues value = new ContentValues();
        value.put(WEIGHT,weightToChange);
        mDbAC.update(TABLE_NAME_AIRCRAFT, value, AC_MODEL + " = ?", new String[] {"350"});
        mDbQuestion.update(TABLE_NAME_QUESTION, value, AC_MODEL  + " = ?", new String[] {"350"});

    }
}
