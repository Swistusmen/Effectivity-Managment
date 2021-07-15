package com.example.efectivitymanagment

import android.content.Context
import android.media.MediaParser
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseBooleanArray
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import java.io.*
import java.lang.StringBuilder

class toDoList : AppCompatActivity() {
    var Activities= arrayListOf<String>()
    var fileName:String="ToDoList"
    var lineSeparator = System.getProperty("line.separator")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_to_do_list)

        var add=findViewById<Button>(R.id.todoAdd)
        var del=findViewById<Button>(R.id.todoDel)
        var done=findViewById<Button>(R.id.todoDone)
        var text=findViewById<EditText>(R.id.todoInput)
        var list=findViewById<ListView>(R.id.todoList)

        var adapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice, Activities)

        add.setOnClickListener {
            if(Activities!=null&&adapter!=null){
                Activities.add(text.text.toString())
                list.adapter=adapter
                adapter.notifyDataSetChanged()
                text.text.clear()
            }
        }

        del.setOnClickListener {
            if(Activities!=null&&adapter!=null){
                var count=list.count
                val position: SparseBooleanArray =list.checkedItemPositions
                var item=count-1
                while(item>=0){
                    if(position.get(item)){
                        adapter.remove(Activities.get(item))
                    }
                    item--
                }
                position.clear()
                adapter.notifyDataSetChanged()
            }
        }

        ReadDataFromFile()
        list.adapter=adapter
        adapter.notifyDataSetChanged()
    }

    override fun onPause() {
        super.onPause()
        SaveToFile()
    }

    fun ReadDataFromFile(){
        var path=this.getExternalFilesDir(null)
        var file:File= File(path,fileName)
        if(file.exists()){
            var fileInput:FileInputStream?=null
            fileInput= openFileInput(fileName)
            var inputReader: InputStreamReader=InputStreamReader(fileInput)
            var bufferedReader: BufferedReader=BufferedReader(inputReader)
            var builder: StringBuilder=StringBuilder()
            var text:String?=null
            while({text=bufferedReader.readLine();text}()!=null){
                builder.append(text)
                Activities.add(builder.toString())
                builder.clear()
            }
        }
    }

    fun SaveToFile(){
        var path=this.getExternalFilesDir(null)
        var file= File(path,fileName)
        if(file.exists()) {
            file.delete()
        }
        file.createNewFile()
        var fileOutput:FileOutputStream?=null
        fileOutput=openFileOutput(fileName, Context.MODE_PRIVATE)
        for(i in Activities){
            fileOutput.write(i.toByteArray())
            fileOutput.write(lineSeparator.toByteArray())
        }
        fileOutput.close()
    }
}