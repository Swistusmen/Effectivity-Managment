package com.example.efectivitymanagment

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class goalInsights : AppCompatActivity() {
    var dbHandler: DBHandler=DBHandler(this)
    var Steps= arrayListOf<String>()

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

        var parentGoal:String?=null
        if(intent.extras!=null) {
            var bundle=intent.extras
            val parentGoal = bundle.getString("Goal")
        }

        title.text=parentGoal

        var adapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,Steps)

        if(parentGoal!=null) {
            ReadStepsFromDB(parentGoal)
            list.adapter=adapter
            adapter.notifyDataSetChanged()
        }

        create.setOnClickListener {
            var newStep=input.text.toString()
            input.text.clear()
            if(newStep!=""&&newStep!=null){
                Steps.add(newStep)
                list.adapter=adapter
                adapter.notifyDataSetChanged()
                dbHandler.AddNewStep(newStep,parentGoal)
            }
        }
    }

    private fun ReadStepsFromDB(parent: String){
        var ListOfSteps=dbHandler.readGoalSteps(parent)
        for(i in ListOfSteps){
            Steps.add(i.desc)
        }
    }


}