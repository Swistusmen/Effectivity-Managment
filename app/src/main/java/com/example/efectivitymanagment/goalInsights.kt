package com.example.efectivitymanagment

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseBooleanArray
import android.widget.*

class goalInsights : AppCompatActivity() {
    var dbHandler: DBHandler=DBHandler(this)
    var Steps= arrayListOf<String>()
    var Steps2= arrayListOf<Step>()
    var StepsFinished= arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal_insights)

        var title=findViewById<TextView>(R.id.giTitle)
        var desc=findViewById<EditText>(R.id.giDesc)
        var create=findViewById<Button>(R.id.giCreate)
        var delete=findViewById<Button>(R.id.giDel)
        var done=findViewById<Button>(R.id.giDone)
        var input=findViewById<EditText>(R.id.giStep)
        var list=findViewById<ListView>(R.id.giStepsList)
        var finishedSteps=findViewById<ListView>(R.id.giDoneSteps)

        var parentGoal:String?=null
        var bundle=intent.extras
        if(bundle!=null) {
            parentGoal = bundle.getString("Goal")
            if(parentGoal==null)
                parentGoal="Dupa"
        }

        title.text=parentGoal

        var adapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,Steps)
        var adapter2=ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,StepsFinished)

        if(parentGoal!=null) {
            ReadStepsFromDB(parentGoal)
            list.adapter=adapter
            adapter.notifyDataSetChanged()
            finishedSteps.adapter=adapter2
            adapter2.notifyDataSetChanged()
        }

        create.setOnClickListener {
            var newStep=input.text.toString()
            if(newStep!=""&&newStep!=null){
                Steps.add(newStep)
                list.adapter=adapter
                adapter.notifyDataSetChanged()
                dbHandler.AddNewStep(newStep,parentGoal)
            }
            input.text.clear()
        }

        delete.setOnClickListener {
            val count=list.count
            val position: SparseBooleanArray =list.checkedItemPositions
            var item=count-1
            while(item>=0){
                if(position.get(item)){
                    val goal=Steps.get(item)
                    adapter.remove(Steps.get(item))
                    dbHandler.deleteStep(goal)
                }
                item--
            }
            position.clear()
            adapter.notifyDataSetChanged()
        }

        done.setOnClickListener {
            val count=list.count
            val position: SparseBooleanArray =list.checkedItemPositions
            var item=count-1
            while(item>=0){
                if(position.get(item)){
                    var step=Steps.get(item)
                    StepsFinished.add(step)
                    dbHandler.markStepAsDone(step,parentGoal)
                    adapter.remove(Steps.get(item))
                }
                item--
            }
            position.clear()
            adapter.notifyDataSetChanged()
            adapter2.notifyDataSetChanged()
        }
    }

    private fun ReadStepsFromDB(parent: String){
        Steps2=dbHandler.readGoalSteps(parent)
        for(i in Steps2){
            if(i.finished==false)
                Steps.add(i.desc)
            else
                StepsFinished.add(i.desc)
        }
    }


}