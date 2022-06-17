package com.example.imdbapplication.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.imdbapplication.Entity.Result;

import java.util.ArrayList;

public class DB extends SQLiteOpenHelper
{
    private final static String databaseName = "WatchListDB";
    private final static int databaseVersion = 1;
    private String WATCH_LIST_TABLE = "watchList";
    private static DB dbInstance = null;


    public DB (Context context)
    {
        super(context,databaseName,null,databaseVersion);
    }

    public synchronized static DB getInstance(Context context)
    {
        if(dbInstance == null)
        {
            dbInstance = new DB(context.getApplicationContext());
        }
        return dbInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createQuery = "CREATE TABLE " + WATCH_LIST_TABLE + " ("
                + " ID INTEGER PRIMARY KEY AUTOINCREMENT,"
                + " imdbID TEXT,"
                + " resultType TEXT,"
                + " title TEXT,"
                + " description TEXT,"
                + " image TEXT"
                + " )";

        sqLiteDatabase.execSQL(createQuery);
    }

    public void AddNewWatchList(String imdbID,String resultType, String title, String description, String image)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("imdbID",imdbID);
        contentValues.put("resultType",resultType);
        contentValues.put("title",title);
        contentValues.put("description",description);
        contentValues.put("image",image);

        sqLiteDatabase.insert(WATCH_LIST_TABLE,null,contentValues);
        sqLiteDatabase.close();
    }

    public ArrayList<Result> GetWatchList()
    {
        ArrayList<Result> watchListArrayList = new ArrayList<Result>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.query(WATCH_LIST_TABLE,null,null,null,null,null,null);
        if(cursor.moveToFirst())
        {
            do {
                watchListArrayList.add(
                        new Result(
                                cursor.getString(cursor.getColumnIndexOrThrow("imdbID")),
                                cursor.getString(cursor.getColumnIndexOrThrow("resultType")),
                                cursor.getString(cursor.getColumnIndexOrThrow("title")),
                                cursor.getString(cursor.getColumnIndexOrThrow("description")),
                                cursor.getString(cursor.getColumnIndexOrThrow("image"))
                        )
                );
            }
            while(cursor.moveToNext());
        }
        sqLiteDatabase.close();
        return watchListArrayList;
}



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion)
    {
        sqLiteDatabase.execSQL("ALTER TABLE "+ WATCH_LIST_TABLE + " ADD COLUMN age INTEGER");
    }


}
