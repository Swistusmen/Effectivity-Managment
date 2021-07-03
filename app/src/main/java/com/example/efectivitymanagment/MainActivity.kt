package com.example.efectivitymanagment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val eisenhowerButton= findViewById<Button>(R.id.eisenhowerButton)

        eisenhowerButton.setOnClickListener{
            openEiseinhowrLayout();
        }

    }

    private fun openEiseinhowrLayout() {
        setContentView(R.layout.eisenhower_matrix)
    }


}