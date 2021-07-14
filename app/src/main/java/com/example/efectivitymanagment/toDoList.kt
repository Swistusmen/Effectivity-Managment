package com.example.efectivitymanagment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseBooleanArray
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView

class toDoList : AppCompatActivity() {
    var Activities= arrayListOf<String>()
    var fileName:String="ToDoList"

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
    }

    override fun onPause() {
        super.onPause()

    }
}