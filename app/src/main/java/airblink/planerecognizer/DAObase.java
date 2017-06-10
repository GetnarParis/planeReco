package airblink.planerecognizer;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Alexandre on 10/06/2017.
 */

public abstract class DAObase {
    // Nous sommes à la première version de la base
    // Si je décide de la mettre à jour, il faudra changer cet attribut
    protected final static int VERSION = 1;
    // Le nom du fichier qui représente ma base
    protected final static String NOM = "database.db";

    protected SQLiteDatabase mDb = null;
    protected dbQuestion mHandler = null;

    public DAObase(Context pContext) {
        this.mHandler = new dbQuestion(pContext);
    }

    public SQLiteDatabase open() {
        // Pas besoin de fermer la dernière base puisque getWritableDatabase s'en charge
        mDb = mHandler.getWritableDatabase();
        return mDb;
    }

    public void close() {
        mDb.close();
    }

    public SQLiteDatabase getDb() {
        return mDb;
    }
}
