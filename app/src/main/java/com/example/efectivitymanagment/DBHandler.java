package com.example.efectivitymanagment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {
    private static final String DB_Name="GoalsSteps";
    private static final String TableName="Steps";
    private static final String GoalsTable="Goals";
    private static final Integer DB_Version=1;

    private static final String FirstColumn="id";
    private static final String SecondColumn="Goal";
    private static final String ThirdColumn="Description";
    private static final String FourthColumn="IsDone";

    public DBHandler(Context context){
        super(context, DB_Name,null,DB_Version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE "+GoalsTable+ " ("+
                FirstColumn+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                SecondColumn+ " TEXT Not NULL, "+ThirdColumn+" TEXT, "
                + FourthColumn+ " TEXT)";
        db.execSQL(query);
        query="CREATE TABLE "+TableName+ " ("+
                FirstColumn+ " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                SecondColumn+ " TEXT REFERENCES "+ GoalsTable+
                "("+SecondColumn+"), "+ThirdColumn+" TEXT, "
                + FourthColumn+ " TEXT)";
        db.execSQL(query);
    }

    public void AddNewStep(String description, String parent){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(ThirdColumn,description);
        values.put(SecondColumn,parent);
        values.put(FourthColumn,"FALSE");

        db.insert(TableName,null,values);
        db.close();
    }

    public void AddNewGoal(String description, String parent) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(ThirdColumn, description);
        values.put(SecondColumn, parent);
        values.put(FourthColumn, "FALSE");

        db.insert(GoalsTable, null, values);
        db.close();
    }

    public ArrayList<Step> readGoalSteps(String parent){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from "+TableName+ " where Goal=\""+ parent+"\";",null);

        ArrayList<Step> stepList= new ArrayList<Step>();

        if(cursor.moveToFirst()){
            do{
                stepList.add(new Step(cursor.getString(2),
                        cursor.getString(1),cursor.getString(3),cursor.getString(0)));
            }while(cursor.moveToNext()); //tutaj
        }
        cursor.close();
        return stepList;
    }

    public ArrayList<Goal> readGoals(){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from "+GoalsTable+ ";",null);

        ArrayList<Goal> goalList= new ArrayList<Goal>();

        if(cursor.moveToFirst()){
            do{
                goalList.add(new Goal(cursor.getString(2),
                        cursor.getString(1),cursor.getString(3),cursor.getString(0)));
            }while(cursor.moveToNext()); //tutaj
        }
        cursor.close();
        return goalList;
    }

    public void deleteGoalSteps(String goal){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TableName,"Goal=?",new String[]{goal});
        db.close();
    }

    public void deleteStep(String step){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TableName, "Description=?",new String[]{step});
        db.close();
    }

    public void deleteGoal(String goal){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(GoalsTable, "Description=?",new String[]{goal});
        db.close();
    }

    public String readGoalDescription(String goal){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from "+GoalsTable+" where (Goal=\""+goal+"\");",null);
        String desc="";
        if(cursor.moveToFirst()){
            desc=cursor.getString(2);
        }
        return desc;
    }

   public void markStepAsDone(String step,String goal){

       SQLiteDatabase write=this.getWritableDatabase();
       SQLiteDatabase db=this.getReadableDatabase();
       Cursor cursor=db.rawQuery("Select * from "+TableName+ " where( Goal=\""+ goal+
               "\" AND Description=\"" +step+"\");",null);

       if(cursor.moveToFirst()){
           do{
               ContentValues values=new ContentValues();
               values.put(ThirdColumn,cursor.getString(2));
               values.put(SecondColumn,cursor.getString(1));
               values.put(FourthColumn,"TRUE");
               values.put(FirstColumn,cursor.getString(0));
               write.update(TableName,values,"Description=?", new String[]{step});
           }while(cursor.moveToNext());
       }
       write.close();
       cursor.close();
    }

    public void markGoalAsDone(String goal){

        SQLiteDatabase write=this.getWritableDatabase();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from "+GoalsTable+ " where( Goal=\""+
                goal+"\");",null);

        if(cursor.moveToFirst()){
            do{
                ContentValues values=new ContentValues();
                values.put(ThirdColumn,cursor.getString(2));
                values.put(SecondColumn,cursor.getString(1));
                values.put(FourthColumn,"TRUE");
                values.put(FirstColumn,cursor.getString(0));
                write.update(GoalsTable,values,"Goal=?", new String[]{goal});
            }while(cursor.moveToNext());
        }
        write.close();
        cursor.close();
    }

    public void saveDescription(String goal, String desc){
        SQLiteDatabase write=this.getWritableDatabase();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("Select * from "+GoalsTable+ " where( Goal=\""+
                goal+"\");",null);

        if(cursor.moveToFirst()){
            do{
                ContentValues values=new ContentValues();
                values.put(ThirdColumn,desc);
                values.put(SecondColumn,cursor.getString(1));
                values.put(FourthColumn,cursor.getString(3));
                values.put(FirstColumn,cursor.getString(0));
                write.update(GoalsTable,values,"Goal=?", new String[]{goal});
            }while(cursor.moveToNext());
        }
        write.close();
        cursor.close();
    }

    public int GetCountOfSteps(String parent){
        SQLiteDatabase db=this.getReadableDatabase();
        String query="SELECT COUNT("+SecondColumn+") FROM "+TableName+" WHERE "+
                SecondColumn+"= \""+parent+"\";";
        Cursor cursor= db.rawQuery(query,null);
        int count=0;
        if(cursor.moveToFirst()){
            count=cursor.getInt(0);
        }
        cursor.close();
        return count;
    }

    public int GetCountOfFinishedSteps(String parent){
        SQLiteDatabase db=this.getReadableDatabase();
        String query="SELECT COUNT("+SecondColumn+") FROM "+TableName+" WHERE "+
                SecondColumn+"= \""+parent+"\" AND "+FourthColumn+"=\"TRUE\";";
        Cursor cursor= db.rawQuery(query,null);
        int count=0;
        if(cursor.moveToFirst()){
            count=cursor.getInt(0);
        }
        cursor.close();
        return count;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
