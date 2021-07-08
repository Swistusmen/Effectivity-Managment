package com.example.efectivitymanagment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val eisenhowerButton= findViewById<Button>(R.id.eisenhowerButton)
        val toDoButton=findViewById<Button>(R.id.toDoButton)
        //val calendarButton=findViewById<Button>(R.id.calendarButton)
        val goalsButton=findViewById<Button>(R.id.goalsButton)

        eisenhowerButton.setOnClickListener{
            val eisenHowerActivity= Intent(this, eis::class.java)
            startActivity(eisenHowerActivity)
        }

        goalsButton.setOnClickListener{
            val goalsActivity=Intent(this, goals::class.java)
            startActivity(goalsActivity)
        }

        toDoButton.setOnClickListener{
            val todoActivity=Intent(this,toDoList::class.java)
            startActivity(todoActivity)
        }

    }


}