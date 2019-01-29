package com.example.myapplication.service.load;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.myapplication.service.model.Note;
import com.example.myapplication.service.model.NoteIterator;

import java.util.ArrayList;

public class NoteIteratorImpl implements NoteIterator {
    ArrayList<Note> notes = new ArrayList<>();

    private final String DB_NAME= "NOTE_DB";
    private final String TABLE_NAME= "NOTE";
    private final int VERSION = 1;
    private final String CREATE_QUERY = "CREATE TABLE IF NOT EXISTS ";
    public final String[] columns = new String[]{"id","note"};

    SQLiteDatabase database;


    public NoteIteratorImpl(Context context){
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
    private long addTask(ContentValues values){
        Log.d("AAA","add in sqlite");
        long id = database.insert(TABLE_NAME,null,values);
        return id;


    }
    private void delTask(String whereClause){
        database.delete(TABLE_NAME,whereClause,null);
    }
    public Cursor getAll(){
        Cursor cursor = database.query(TABLE_NAME,null,
                null,null,null,null,null);

        return cursor;
    }


    @Override
    public void load(LoadItemListener loadItemListener) {
        Cursor cursor = getAll();

        notes.clear();
        if(cursor.moveToFirst()){
            while (cursor.moveToNext()){

                Note note = new Note(cursor.getString(1),cursor.getLong(0));
                notes.add(note);
            }
        }

        loadItemListener.onLoadFinish(notes);
    }

    @Override
    public long addNote(LoadItemListener itemListener, String title) {

        ContentValues values = new ContentValues();
        values.put(columns[1],title);

        long id = addTask(values);

        notes.add(new Note(title,id));

        itemListener.onChangeFinish();
        return id;

    }

    @Override
    public void delNote(LoadItemListener itemListener, long id) {
        //delTask(columns[0]+ " = " + notes.get(position).getId());
        delTask(columns[0]+ " = " + id);
        //notes.remove(position);
        itemListener.onChangeFinish();
    }
}
