package com.example.ransonappcontrol;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CreateDatabase extends SQLiteOpenHelper {

    public static final String DB_NAME = "patient.db";
    public static final String ID = "_id";
    public static final String TABLE = "patient";
    public static final String NAME = "name";
    public static final String AGE = "age";
    public static final String LITIASE_BILIAR = "litiase";
    public static final String LEUCOCITOS = "leucocitos";
    public static final String GLICEMIA = "glicemia";
    public static final String AST_TGO = "ast";
    public static final String LDH = "ldh";
    public static final String POINTS = "points";
    public static final String DEATH = "death";

    private static final int VERSION = 1;

    public CreateDatabase(Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE " + TABLE + " ( "
                + "_id integer primary key autoincrement, " +
                "name text, " +
                "age int, " +
                "leucocitos float," +
                "litiase float," +
                "glicemia float," +
                "ast float," +
                "ldh float," +
                "points int," +
                "death float" +
                ")";

        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE);
        onCreate(db);
    }
}