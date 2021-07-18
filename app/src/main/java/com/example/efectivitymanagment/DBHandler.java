package com.example.efectivitymanagment;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DB_Name="GoalsSteps";
    private static final String TableName="Steps";
    private static final Integer DB_Version=1;

    private static final String FirstColumn="id";
    private static final String SecondColumn="Goal";
    private static final String ThirdColumn="Description";
    private static final String FourthColumn="IsDone";

    public DBHandler(Context context){
        super(context, DB_Name,null,DB_Version);
    }

    //create Database
    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE "+TableName+ " ("+
                FirstColumn+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                SecondColumn+ " TEXT, "+ThirdColumn+" TEXT, "
                + FourthColumn+ " BOOLEAN)";
        db.execSQL(query);
    }

    public void AddNewStep(String description, String parent){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(SecondColumn,description);
        values.put(ThirdColumn,parent);
        values.put(FourthColumn,false);

        db.insert(TableName,null,values);
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
