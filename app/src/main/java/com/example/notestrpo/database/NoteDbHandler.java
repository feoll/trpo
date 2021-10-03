package com.example.notestrpo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
import com.example.notestrpo.database.DatabaseContracts.NoteContract;
import com.example.notestrpo.model.NoteModel;

import java.util.ArrayList;
import java.util.List;

public class NoteDbHandler extends SQLiteOpenHelper {

    private static NoteDbHandler sInstance;

    private NoteDbHandler(@Nullable Context context) {
        super(context, DatabaseContracts.DATABASE_NAME, null, DatabaseContracts.DATABASE_VERSION);
    }

    public static synchronized NoteDbHandler getsInstance(Context context) {
        if (sInstance == null) {
            sInstance = new NoteDbHandler(context.getApplicationContext());
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS "
                + NoteContract.TABLE_NAME + " ("
                + NoteContract._ID + " INTEGER PRIMARY KEY,"
                + NoteContract._TITLE + " TEXT,"
                + NoteContract._SUBTITLE + " TEXT,"
                + NoteContract._DESCRIPTION + " TEXT,"
                + NoteContract._TIME + " TEXT,"
                + NoteContract._COLOR + " TEXT,"
                + NoteContract._URI + " TEXT)";
        sqLiteDatabase.execSQL(CREATE_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + NoteContract.TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public void add(NoteModel model) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues  = new ContentValues();
        contentValues.put(NoteContract._TITLE, model.getTitle());
        contentValues.put(NoteContract._SUBTITLE, model.getSubtitle());
        contentValues.put(NoteContract._DESCRIPTION , model.getDescription());
        contentValues.put(NoteContract._TIME, model.getTime());
        contentValues.put(NoteContract._COLOR, model.getColor());
        contentValues.put(NoteContract._URI, model.getUri());

        sqLiteDatabase.insert(NoteContract.TABLE_NAME, null, contentValues);
        sqLiteDatabase.close();
    }

    public List<NoteModel> getAll() {
        List<NoteModel> list = new ArrayList<>();

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(NoteContract.TABLE_NAME,null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndex(NoteContract._TITLE));
            String subtitle = cursor.getString(cursor.getColumnIndex(NoteContract._SUBTITLE));
            String description = cursor.getString(cursor.getColumnIndex(NoteContract._DESCRIPTION));
            String time = cursor.getString(cursor.getColumnIndex(NoteContract._TIME));
            String color = cursor.getString(cursor.getColumnIndex(NoteContract._COLOR));
            String uri = cursor.getString(cursor.getColumnIndex(NoteContract._URI));
            int id = cursor.getInt(cursor.getColumnIndex(NoteContract._ID));

            NoteModel noteModel = new NoteModel(title, subtitle, description, time, uri, Integer.valueOf(color));
            noteModel.setId(id);
            list.add(noteModel);
        }
        cursor.close();
        sqLiteDatabase.close();
        return list;
    }

    public void update(long id, NoteModel model) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(NoteContract._TITLE, model.getTitle());
        contentValues.put(NoteContract._SUBTITLE, model.getSubtitle());
        contentValues.put(NoteContract._DESCRIPTION , model.getDescription());
        contentValues.put(NoteContract._TIME, model.getTime());
        contentValues.put(NoteContract._COLOR, model.getColor());
        contentValues.put(NoteContract._URI, model.getUri());

        sqLiteDatabase.update(NoteContract.TABLE_NAME, contentValues, NoteContract._ID + "=" + id, null);
        sqLiteDatabase.close();
    }

    public void delete(long id) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        sqLiteDatabase.delete(NoteContract.TABLE_NAME, NoteContract._ID + "=" + id, null);

        sqLiteDatabase.close();
    }
}
