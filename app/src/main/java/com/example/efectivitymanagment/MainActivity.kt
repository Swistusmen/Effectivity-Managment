package com.example.efectivitymanagment

import SyncResponse
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import okhttp3.HttpUrl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.HTTP
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
                .baseUrl("http://192.168.0.143:8080").
                addConverterFactory(GsonConverterFactory.create()).build()
            val service=retrofit.create(SyncService::class.java)
            val call=service.hello()
            call.enqueue(object: Callback<SyncResponse>{
                override fun onResponse(
                    call: Call<SyncResponse>?,
                    response: Response<SyncResponse>?
                ) {
                    if(response!=null)
                    if(response.code()==200){
                        val body=response.body()
                        syncButton.text=body.welcomeMessage
                    }
                }

                override fun onFailure(call: Call<SyncResponse>?, t: Throwable?) {
                    syncButton.text="nie udalo sie"
                }
            })
        }
    }


}