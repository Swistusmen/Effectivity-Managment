package com.example.efectivitymanagment

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.TextView

class eis : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eis)

        val addButton=findViewById<Button>(R.id.eisAdd)
        val eisDelete=findViewById<Button>(R.id.eisDel)
        val textInput=findViewById<EditText>(R.id.eisTextEdit)

        val uAndVList=findViewById<ListView>(R.id.UandVList)

        val uAndVText=findViewById<TextView>(R.id.eisUandVText)
        val nuAndVText=findViewById<TextView>(R.id.eisNUandVText)
        val nuAndNVText=findViewById<TextView>(R.id.eisNUandNVText)
        val uAndNVText=findViewById<TextView>(R.id.eisUandNVText)

        val texts: MutableList<Button>= mutableListOf(uAndVText)

        nuAndVText.setOnClickListener {
            setCurrentPieceOfMatrixAsActive(nuAndVText.id)
        }

       uAndVText.setOnClickListener { uAndVText.text="Clicked" };

    }

    private fun setCurrentPieceOfMatrixAsActive(id: String) {

    }

}