package com.example.efectivitymanagment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
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
        val goalsButton=findViewById<Button>(R.id.goalsButton)
        val progressButton=findViewById<Button>(R.id.progressButton)
        val syncButton=findViewById<Button>(R.id.syncButton)

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

        progressButton.setOnClickListener {
            val todoActivity=Intent(this,progress::class.java)
            startActivity(todoActivity)
        }

        syncButton.setOnClickListener {
            val retrofit= Retrofit.Builder()
                .baseUrl("127.0.0.1:8000/").
                addConverterFactory(GsonConverterFactory.create()).build()
            val service=retrofit.create(SyncService::class.java)
            val call=service.hello()
            syncButton.text=call.welcomeMessage
        }
    }


}