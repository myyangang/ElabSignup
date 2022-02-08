package com.elab.elabsignup.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SignupSQLiteHelper extends SQLiteOpenHelper {
    @Override public void onCreate(SQLiteDatabase db) {
        String command = "create table " + SignupSchema.TABLE_NAME +
                "( _id integer primary key autoincrement, " +
                SignupSchema.Columns.uuid + ", " +
                SignupSchema.Columns.latitude + ", " +
                SignupSchema.Columns.longitude + ", " +
                SignupSchema.Columns.date + ", " +
                SignupSchema.Columns.isValidLocation + ", " +
                SignupSchema.Columns.isSuccessSubmit + ")" ;
        db.execSQL(command);
    }

    @Override public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public SignupSQLiteHelper(Context context){
        super(context,SignupSchema.DATABASE_NAME,null,SignupSchema.VERSION);
    }
}
