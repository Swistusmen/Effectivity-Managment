package com.example.efectivitymanagment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

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

    public ArrayList<Step> readGoalSteps(String parent){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select id, Goal, Description, IsDone from "+TableName+ " where Goal="+ parent,null);

        ArrayList<Step> stepList= new ArrayList<Step>();

        if(cursor.moveToFirst()){
            do{
                stepList.add(new Step(cursor.getString(1),
                        cursor.getString(3),cursor.getString(2),cursor.getString(4)));
            }while(cursor.moveToNext());
        }
        cursor.close();
        return stepList;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
