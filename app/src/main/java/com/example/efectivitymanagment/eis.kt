package com.example.efectivitymanagment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView

class eis : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eis)

        /*val addButton=findViewById<Button>(R.id.eisAdd)
        val eisDelete=findViewById<Button>(R.id.eisDel)
        val textInput=findViewById<EditText>(R.id.eisTextEdit)*/
        val uAndVList=findViewById<ListView>(R.id.UandVList)
/*
        uAndVList.setOnClickListener{
            System.out.println("I was clicked")
        }*/
        uAndVList.onItemClickListener
    }

}