package com.example.efectivitymanagment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.InputStreamReader
import java.lang.StringBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val eisenhowerButton= findViewById<Button>(R.id.eisenhowerButton)
        val toDoButton=findViewById<Button>(R.id.toDoButton)
        //val calendarButton=findViewById<Button>(R.id.calendarButton)
        val goalsButton=findViewById<Button>(R.id.goalsButton)
        /*
        var myList = arrayListOf<String>()

        var path=this.getExternalFilesDir(null)
        val filename: String= "EisenhowerMaritx"
        var fileNameInput: FileInputStream?= null
        var file:File=File(path,filename)
        if(file.exists()) {
            fileNameInput = openFileInput(filename)
            var reader: InputStreamReader = InputStreamReader(fileNameInput)
            var bufferedReader: BufferedReader = BufferedReader(reader)
            var builder: StringBuilder = StringBuilder()
            var text: String? = null
            while ({ text = bufferedReader.readLine();text }() != null) {
                builder.append(text)
                myList.add(builder.toString())
                builder.clear()
            }
        }

        if(myList.count()>0) {
            var displayer = findViewById<ListView>(R.id.displayList)
            var adapter = ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_multiple_choice,
                myList
            )
            displayer.adapter = adapter
            adapter.notifyDataSetChanged()
        }
        */

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