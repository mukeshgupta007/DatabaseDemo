package com.example.databasedemo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
public class DataManager {
    public SQLiteDatabase db;
    public static final String TABLE_ROW_ID = "_id";
    public static final String TABLE_ROW_NAME = "name";
    public static final String TABLE_ROW_AGE = "age";
    public static final String DB_NAME = "Stud.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "Student";

    public DataManager(Context context) {

        CustomSQLiteOpenHelper helper = new CustomSQLiteOpenHelper(context);
        db = helper.getWritableDatabase();
    }
        public void insert (String name, String age){

            String query = "INSERT INTO " + TABLE_NAME + " (" +
                    TABLE_ROW_NAME + ", " +
                    TABLE_ROW_AGE + ") " +
                    "VALUES (" +
                    "'" + name + "'" + ", " +
                    "'" + age + "'" +
                    "); ";

            Log.i("insert() = ", query);
            db.execSQL(query);
        }
        public void delete (String name){

            String query = "DELETE FROM " + TABLE_NAME +
                    " WHERE " + TABLE_ROW_NAME +
                    " = '" + name + "';";
            Log.i("delete() = ", query);
            db.execSQL(query);
        }
        public Cursor selectAll () {
            Cursor c = db.rawQuery("SELECT *" + " from " +
                    TABLE_NAME, null);
            System.out.println(c.getString(1));
            return c;
        }
        public Cursor searchName (String name){
            String query = "SELECT " +
                    TABLE_ROW_ID + ", " +
                    TABLE_ROW_NAME +
                    ", " + TABLE_ROW_AGE +
                    " from " +
                    TABLE_NAME + " WHERE " +
                    TABLE_ROW_NAME + " = '" + name + "';";
            Log.i("searchName() = ", query);
            Cursor c = db.rawQuery(query, null);
            System.out.print(c.getString(1));
            System.out.print(c.getString(2));
            return c;
        }
        public class CustomSQLiteOpenHelper extends SQLiteOpenHelper {
            public CustomSQLiteOpenHelper(Context context) {
                super(context, DB_NAME, null, DB_VERSION);
            }

            @Override
            public void onCreate(SQLiteDatabase db) {
                String newTableQueryString = "create table "
                        + TABLE_NAME + " ("
                        + TABLE_ROW_ID
                        + " integer primary key autoincrement not null,"
                        + TABLE_ROW_NAME
                        + " text not null,"
                        + TABLE_ROW_AGE
                        + " text not null);";
                db.execSQL(newTableQueryString);
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
                onCreate(db);
            }
        }


    }

