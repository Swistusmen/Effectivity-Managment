package com.example.efectivitymanagment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView

class progress : AppCompatActivity() {
    var dbHandler: DBHandler=DBHandler(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress)

        var inProgress=findViewById<LinearLayout>(R.id.placeForGoals)
        var finished=findViewById<LinearLayout>(R.id.finishedGoals)

        var ListOfGoals=dbHandler.readGoals()
        var listOfGoalsInProgress= arrayListOf<String>()
        var listOfFinishedGoals= arrayListOf<String>()
        for(i in ListOfGoals){
            if(i.finished==false){
                var totalCount=dbHandler.GetCountOfSteps(i.parent)
                var inProgressCount=dbHandler.GetCountOfFinishedSteps(i.parent)
                listOfGoalsInProgress.add(i.parent+" "+inProgressCount+"/"+totalCount)
            }else{
                listOfFinishedGoals.add(i.parent)
            }
        }

        for(i in listOfGoalsInProgress){
            var newTexView=TextView(this)
            newTexView.setText(i)
            newTexView.setTextSize(20.0f)
            inProgress.addView(newTexView)
        }

        for(i in listOfFinishedGoals){
            var newTexView=TextView(this)
            newTexView.setText(i)
            newTexView.setTextSize(20.0f)
            finished.addView(newTexView)
        }

    }
}