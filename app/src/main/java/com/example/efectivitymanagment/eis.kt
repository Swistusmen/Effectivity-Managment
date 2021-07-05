package com.example.efectivitymanagment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class eis : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eis)

        val eisBackToMain=findViewById<Button>(R.id.eisBackToMain)

        eisBackToMain.setOnClickListener{
            openMainLayout()
        }
    }

    private fun openMainLayout(){
        Intent intent= new Intent(eis.this,MainActivity.class)
        setContentView(R.layout.activity_main)
    }
}