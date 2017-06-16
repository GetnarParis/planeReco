package airblink.planerecognizer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Alexandre on 12/06/2017.
 */
public class dbAircraft extends SQLiteOpenHelper {
    protected final static int VERSION = 2;
    // Le nom du fichier qui représente ma base
    protected final static String NOM = "databaseAC.db";
    public static final String AC_KEY = "id";
    public static final String AC_MANUF = "manuf";
    public static final String AC_MODEL = "model";
    public static final String AC_VERSION = "version";
    public static final String WEIGHT = "weight";

    public static final String ASSOCIATED_DRAWABLE = "drawable";

    public static final String DIFF_TABLE_NAME = "databaseAC_db";
    public static final String DIFF_TABLE_CREATE =
            "CREATE TABLE " + DIFF_TABLE_NAME + " (" +
                    AC_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    AC_MANUF + " TEXT, " +
                    AC_MODEL + " TEXT, " +
                    AC_VERSION + " TEXT, " +
                    WEIGHT + " REAL DEFAUlT 1," +
                    ASSOCIATED_DRAWABLE + " INT);"
            ;

    public dbAircraft(Context context) {
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
