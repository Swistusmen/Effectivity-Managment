package com.example.efectivitymanagment

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

        if(savedInstanceState==null)
            return

        var bundle=intent
        var parentGoal: String?=bundle.getStringExtra("Goal")
        title.text=parentGoal

        var adapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,Steps)

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
}