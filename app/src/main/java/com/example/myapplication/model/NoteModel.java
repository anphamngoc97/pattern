package com.example.myapplication.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.print.PrinterId;

public class NoteModel {
    private final String DB_NAME= "NOTE_DB";
    private final String TABLE_NAME= "NOTE";
    private final int VERSION = 1;
    private final String CREATE_QUERY = "CREATE TABLE IF NOT EXISTS ";
    public final String[] columns = new String[]{"id","note"};

    SQLiteDatabase database;

    public NoteModel(Context context){
        SQLiteOpenHelper helper = new SQLiteOpenHelper(context,DB_NAME,null,VERSION) {
            @Override
            public void onCreate(SQLiteDatabase db) {
                db.execSQL("CREATE TABLE " + TABLE_NAME +
                        "( "+columns[0]+" integer primary key autoincrement,"+columns[1]+" text not null)");
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
                this.onCreate(db);

            }
        };
        database = helper.getWritableDatabase();


    }
    public long addNote(ContentValues values){
        long id = database.insert(TABLE_NAME,null,values);
        return id;


    }
    public void delTask(String whereClause,long id){
        database.delete(TABLE_NAME,whereClause +" = " + id,null);
    }
    public Cursor getAll(){
        Cursor cursor = database.query(TABLE_NAME,null,
                null,null,null,null,null);

        return cursor;
    }

}
