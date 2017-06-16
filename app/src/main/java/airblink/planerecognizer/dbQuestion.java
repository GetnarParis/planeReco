package airblink.planerecognizer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Alexandre on 10/06/2017.
 */

public class dbQuestion extends SQLiteOpenHelper {
    protected final static int VERSION = 5;
    // Le nom du fichier qui représente ma base
    protected final static String NOM = "database.db";
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

    public static final String DIFF_TABLE_NAME = "questions_db";
    public static final String DIFF_TABLE_CREATE =
            "CREATE TABLE " + DIFF_TABLE_NAME + " (" +
                    DIFF_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    DIFF_KEY + " INTEGER, " +
                    AC_MANUF + " TEXT, " +
                    AC_MODEL + " TEXT, " +
                    AC_VERSION + " TEXT, " +
                    PHYSICAL_DIFF + " TEXT," +
                    WEIGHT + " REAL DEFAUlT 1," +
                    QUESTION_TEXT + " TEXT,"+
                    IS_OPEN + " TEXT," +

                    ASSOCIATED_DRAWABLE + " INT);"
            ;

    public dbQuestion(Context context) {
        super(context, NOM, null, VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
 db.execSQL(DIFF_TABLE_CREATE);
    }

    public static final String DIFF_TABLE_DROP = "DROP TABLE IF EXISTS " + DIFF_TABLE_NAME + ";";

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DIFF_TABLE_DROP);
        onCreate(db);
    }

}
