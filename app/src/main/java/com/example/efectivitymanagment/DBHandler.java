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
                + FourthColumn+ " TEXT)";
        db.execSQL(query);
    }

    public void AddNewStep(String description, String parent){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(ThirdColumn,description);
        values.put(SecondColumn,parent);
        values.put(FourthColumn,"0");

        db.insert(TableName,null,values);
        db.close();
    }

    public ArrayList<Step> readGoalSteps(String parent){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from "+TableName+ " where Goal=\""+ parent+"\";",null);

        ArrayList<Step> stepList= new ArrayList<Step>();

        if(cursor.moveToFirst()){
            do{
                String a=cursor.getString(1);
                String a1=cursor.getString(2);
                String a2=cursor.getString(3);
                stepList.add(new Step(cursor.getString(2),
                        cursor.getString(1),cursor.getString(3)));
            }while(cursor.moveToNext()); //tutaj
        }
        cursor.close();
        return stepList;
    }

    public void deleteGoalSteps(String goal){
        SQLiteDatabase db=this.getWritableDatabase();
        String query="DELETE FROM "+TableName+" WHERE Goal="+goal;
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
