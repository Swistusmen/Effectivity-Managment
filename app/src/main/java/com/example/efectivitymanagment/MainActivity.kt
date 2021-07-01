package com.example.efectivitymanagment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val add= findViewById<Button>(R.id.add_button)

        add.setOnClickListener{
            System.out.println("I was clicked")
            openSecondLayout();
        }

    }

    private fun openSecondLayout() {
        setContentView(R.layout.eisenhower_matrix)
    }


}