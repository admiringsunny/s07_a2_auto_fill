package com.acadgild.s07a2autofill;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by sunny on 9/13/16.
 */
public class DB extends SQLiteOpenHelper {

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "AUTO_FILL_DB";

    private static final String TABLE_NAME = "AUTO_FILL_NAMES";

    private static final String NAME_COLUMN = "NAMES";

    private static final String[] COLUMNS = {"NAMES"};


    public DB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(  "Create table " + TABLE_NAME + " (" + NAME_COLUMN + " text );"  );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }

    public void createAnEntry(Pojo pojo){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(NAME_COLUMN, pojo.getName());

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public List<Pojo> getAllEntries(){
        List<Pojo> pojoList = new LinkedList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from " + TABLE_NAME, null);


        Pojo pojo;
        if (cursor.moveToFirst()){
            do {
                pojo = new Pojo();
                pojo.setName(cursor.getString(0));

                pojoList.add(pojo);
            }while (cursor.moveToNext());
        }

        return pojoList;
    }
}
