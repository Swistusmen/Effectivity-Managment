package com.example.efectivitymanagment

import android.content.Intent
import android.graphics.Color
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.SparseBooleanArray
import android.view.View
import android.widget.*
import androidx.core.view.forEach
import org.w3c.dom.Text

class eis : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eis)

        var uvAdd=findViewById<Button>(R.id.uvAdd)
        var unvAdd=findViewById<Button>(R.id.unvAdd)
        var nuvAdd=findViewById<Button>(R.id.nuvAdd)
        var nunvAdd=findViewById<Button>(R.id.nunvAdd)
        val AddButtons= mapOf<String,Button>(
            "uv" to uvAdd,
            "unv" to unvAdd,
            "nuv" to nuvAdd,
            "nunv" to nunvAdd
        )

        var uvDel=findViewById<Button>(R.id.uvDel)
        var unvDel=findViewById<Button>(R.id.unvDel)
        var nuvDel=findViewById<Button>(R.id.nuvDel)
        var nunvDel=findViewById<Button>(R.id.nunvDel)
        val DelButtons= mapOf<String,Button>(
            "uv" to uvDel,
            "unv" to unvDel,
            "nuv" to nuvDel,
            "nunv" to nunvDel
        )

        var uvTextLabel=findViewById<TextView>(R.id.uvTextLabel)
        var nuvTextLabel=findViewById<TextView>(R.id.nuvTextLabel)
        var nunvTextLabel=findViewById<TextView>(R.id.nunvTextLabel)
        var unvTextLabel=findViewById<TextView>(R.id.unvTextLabel)
        val TextLabels=mapOf<String,TextView>(
            "uv" to uvTextLabel,
            "unv" to unvTextLabel,
            "nuv" to nuvTextLabel,
            "nunv" to nunvTextLabel
        )

        val fullMatrixButton=findViewById<Button>(R.id.fullMatrixButton)

        fullMatrixButton.setOnClickListener {
            val intent=Intent(this, eisFullView::class.java)
            startActivity(intent)
        }

       for((k,v) in TextLabels){
           v.setOnClickListener{
               v.text="clicked"
           }
       }

        var uvText=findViewById<EditText>(R.id.uvText)
        var nuvText=findViewById<EditText>(R.id.nuvText)
        var nunvText=findViewById<EditText>(R.id.nunvText)
        var unvText=findViewById<EditText>(R.id.unvText)
        val TextEdits=mapOf<String,EditText>(
            "uv" to uvText,
            "unv" to unvText,
            "nuv" to nuvText,
            "nunv" to nunvText
        )

        var uvPane=findViewById<LinearLayout>(R.id.uvPane)
        var nuvPane=findViewById<LinearLayout>(R.id.nuvPane)
        var nunvPane=findViewById<LinearLayout>(R.id.nunvPane)
        var unvPane=findViewById<LinearLayout>(R.id.unvPane)
        var Panes=mapOf<String,LinearLayout>(
            "uv" to uvPane,
            "unv" to unvPane,
            "nuv" to nuvPane,
            "nunv" to nunvPane
        )

        var uvList=findViewById<ListView>(R.id.uvList)
        var nuvList=findViewById<ListView>(R.id.nuvList)
        var nunvList=findViewById<ListView>(R.id.nunvList)
        var unvList=findViewById<ListView>(R.id.unvList)
        var Lists=mapOf<String,ListView>(
            "uv" to uvList,
            "unv" to unvList,
            "nuv" to nuvList,
            "nunv" to nunvList
        )

        for((k,v)in TextLabels){
            v.setOnClickListener{
                val pane=Panes[k]
                val list=Lists[k]
                if(pane!=null&&list!=null) {
                    if (pane.visibility == View.GONE) {
                        pane.visibility = View.VISIBLE
                        list.visibility=View.VISIBLE

                    }else{
                        pane.visibility=View.GONE
                        list.visibility=View.GONE
                    }

                }
            }
        }
        
        var uvContainer= arrayListOf<String>()
        var uvAdapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,uvContainer)

        var unvContainer= arrayListOf<String>()
        var unvAdapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,unvContainer)

        var nuvContainer= arrayListOf<String>()
        var nuvAdapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,nuvContainer)

        var nunvContainer= arrayListOf<String>()
        var nunvAdapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,nunvContainer)

        var Containers=mapOf<String,ArrayList<String>>(
            "uv" to uvContainer,
            "unv" to unvContainer,
            "nuv" to nuvContainer,
            "nunv" to nunvContainer
        )

        var Adapters=mapOf<String,ArrayAdapter<String>>(
            "uv" to uvAdapter,
            "unv" to unvAdapter,
            "nuv" to nuvAdapter,
            "nunv" to nunvAdapter,
        )


        for((k,v)in AddButtons){
            v.setOnClickListener {
                val myList=Containers[k]
                val textEdit=TextEdits[k]
                val adapter=Adapters[k]
                val listView=Lists[k]
                if(myList!=null&& textEdit!=null&&adapter!=null&&listView!=null){
                    myList.add(textEdit.text.toString())
                    listView.adapter=adapter
                    adapter.notifyDataSetChanged()
                    textEdit.text.clear()
                }
            }
            }

        for((k,v) in DelButtons){
            v.setOnClickListener{
                val adapter=Adapters[k]
                val listView=Lists[k]
                val myList=Containers[k]
                if(adapter!=null&&listView!=null&&myList!=null){
                    val count=listView.count
                    val position: SparseBooleanArray=listView.checkedItemPositions
                    var item=count-1
                    while(item>=0){
                        if(position.get(item)){
                            adapter.remove(myList.get(item))
                        }
                        item--
                    }
                    position.clear()
                    adapter.notifyDataSetChanged()
                }
            }
        }


        }


}