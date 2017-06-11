package airblink.planerecognizer;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;



/**
 * Created by Alexandre on 10/06/2017.
 */

public class questionDAO  {
    private SQLiteDatabase mDb;

    public static final String TABLE_NAME = "questions_db";
    public static final String DIFF_KEY = "id";
    public static final String AC_MANUF = "manuf";
    public static final String PHYSICAL_DIFF = "physDiff";
    public static final String WEIGHT = "weight";
    public static final String IS_OPEN = "isOpen";
    public static final String QUESTION_TEXT = "questionText";

    public static final String ASSOCIATED_DRAWABLE = "drawable";

    public static final String DIFF_TABLE_NAME = "questions_db";
    public static final String DIFF_TABLE_CREATE =
            "CREATE TABLE " + DIFF_TABLE_NAME + " (" +
                    DIFF_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    AC_MANUF + " TEXT, " +
                    PHYSICAL_DIFF + " TEXT," +
                    WEIGHT + " REAL DEFAUlT 1," +
                    QUESTION_TEXT + " TEXT,"+
                    IS_OPEN + " INTEGER DEFAULT 1," +

                    ASSOCIATED_DRAWABLE + " INT);"
            ;

    public static final String DIFF_TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public questionDAO(Context pContext) {
        mDb = new dbQuestion(pContext).getWritableDatabase();

    }


    /**
     * @param q le métier à ajouter à la base
     */
    public void add(questionObj q) {
        ContentValues value = new ContentValues();
        value.put(questionDAO.AC_MANUF, q.getAcManuf());
        value.put(questionDAO.PHYSICAL_DIFF, q.getPhysicalDiff());
        value.put(questionDAO.WEIGHT, q.getWeight());
        value.put(questionDAO.QUESTION_TEXT, q.getQuestionString());
        value.put(questionDAO.IS_OPEN, q.getIsOpen());
        value.put(questionDAO.ASSOCIATED_DRAWABLE, q.getAssociatedDrawable());

        mDb.insert(questionDAO.TABLE_NAME, null, value);

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

      /*  while (cursor.moveToNext()) {
            // Faire quelque chose
        }
        cursor.close();*/
        Cursor c = mDb.rawQuery("SELECT *," + ASSOCIATED_DRAWABLE+ ", MAX ("+WEIGHT+") FROM " + TABLE_NAME + " GROUP BY " + ASSOCIATED_DRAWABLE, null );
        c.moveToFirst();

        questionObj qObj = new questionObj(c.getString(1),c.getString(2),c.getInt(3),c.getInt(5),c.getString(4),c.getInt(6));
        return qObj;
    }
}
