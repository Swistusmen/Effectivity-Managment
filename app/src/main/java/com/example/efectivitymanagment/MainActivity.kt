package com.example.efectivitymanagment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val eisenhowerButton= findViewById<Button>(R.id.eisenhowerButton)
        val eisBackToMain=findViewById<Button>(R.id.eisBackToMain)

        eisenhowerButton.setOnClickListener{
            openEiseinhowerLayout()
        }

        eisBackToMain.setOnClickListener{
            openMainLayout()
        }

    }

    private fun openEiseinhowerLayout() {
        setContentView(R.layout.eisenhower_matrix)
    }

    private fun openMainLayout(){
        setContentView(R.layout.activity_main)
    }

}