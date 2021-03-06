package com.example.efectivitymanagment

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseBooleanArray
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import java.io.*
import java.lang.StringBuilder

class goals : AppCompatActivity() {
    var Goals= arrayListOf<String>()
    var dbHandler: DBHandler=DBHandler(this)
    var fileName= "Goals"
    var SQLDataBase="GoalsSteps"

    var lineSeparator = System.getProperty("line.separator")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goals2)

        var createButton=findViewById<Button>(R.id.goalsCreate)
        var deleteButton=findViewById<Button>(R.id.goalsDelete)
        var selectButton=findViewById<Button>(R.id.goalsEnter)
        var list=findViewById<ListView>(R.id.goalsList)
        var input=findViewById<EditText>(R.id.goalsTextEdit)

        var adapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,Goals)

        createButton.setOnClickListener {
            var nextText:String?=null
            nextText=input.text.toString()
            if(nextText!=null&&nextText!=""&&adapter!=null&&!CheckIfGoalExists(nextText)){
                Goals.add(nextText)
                list.adapter=adapter
                adapter.notifyDataSetChanged()
                dbHandler.AddNewGoal("Description",nextText)
            }
            input.text.clear()
        }

        selectButton.setOnClickListener {
            if(adapter!=null&&list!=null){
                var choosedAction: Int=list.checkedItemPosition
                if(choosedAction!=-1) {
                    var GoalToOpen: String = Goals[choosedAction]
                    if (CheckIfGoalExists(GoalToOpen)) {
                        var intent = Intent(this, goalInsights::class.java)
                        var bundle = Bundle()
                        bundle.putString("Goal", GoalToOpen)
                        intent.putExtras(bundle)
                        startActivity(intent)
                    }
                }
            }
        }

        deleteButton.setOnClickListener {
            if(adapter!=null&&list!=null){
                var choosenGoal:Int=list.checkedItemPosition
                if(choosenGoal!=-1) {
                    if (choosenGoal != null) {
                        val goal = Goals.get(choosenGoal)
                        adapter.remove(Goals.get(choosenGoal))
                        adapter.notifyDataSetChanged()
                        dbHandler.deleteGoalSteps(goal)
                        dbHandler.deleteGoal(goal)
                    }
                }
            }
        }

        ReadGoals()
        list.adapter=adapter
        adapter.notifyDataSetChanged()
    }

    override fun onPause() {
        super.onPause()
        //SaveGoals()
    }

    fun ReadGoals(){
        var GoalsDAO=dbHandler.readGoals()
        for(i in GoalsDAO){
            if(i.finished==false)
                Goals.add(i.parent)
        }
    }

    fun CheckIfGoalExists(GoalTitle:String):Boolean{
        for(i in Goals){
            if (i==GoalTitle){
                return true
            }
        }
        return false
    }

}