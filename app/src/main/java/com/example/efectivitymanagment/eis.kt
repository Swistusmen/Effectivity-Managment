package com.example.efectivitymanagment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.SparseBooleanArray
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.io.*

class eis : AppCompatActivity() {
    var filename: String="EisenhowerMaritx"
    var uvContainer= arrayListOf<String>()
    var unvContainer= arrayListOf<String>()
    var nuvContainer= arrayListOf<String>()
    var nunvContainer= arrayListOf<String>()
    var Containers=mapOf<String,ArrayList<String>>(
        "uv" to uvContainer,
        "unv" to unvContainer,
        "nuv" to nuvContainer,
        "nunv" to nunvContainer
    )
    var lineSeparator = System.getProperty("line.separator")

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

        var uvAdapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,uvContainer)

        var unvAdapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,unvContainer)

        var nuvAdapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,nuvContainer)

        var nunvAdapter=ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice,nunvContainer)

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

        fullMatrixButton.setOnClickListener {
            val intent=Intent(this, eisFullView::class.java)
            val bundle=Bundle()
            bundle.putStringArrayList("uv",uvContainer)
            bundle.putStringArrayList("nuv",nuvContainer)
            bundle.putStringArrayList("unv",unvContainer)
            bundle.putStringArrayList("nunv",nunvContainer)
            intent.putExtras(bundle)
            startActivity(intent)
        }

        ReadInitialData()
        val adapter=Adapters["uv"]
        val listView=Lists["uv"]
        if(adapter!=null&&listView!=null) {
            listView.adapter = adapter
            adapter.notifyDataSetChanged()
        }

        val adapter1=Adapters["unv"]
        val listView1=Lists["unv"]
        if(adapter1!=null&&listView1!=null) {
            listView1.adapter = adapter1
            adapter1.notifyDataSetChanged()
        }

        val adapter2=Adapters["nuv"]
        val listView2=Lists["nuv"]
        if(adapter2!=null&&listView2!=null) {
            listView2.adapter = adapter2
            adapter2.notifyDataSetChanged()
        }

        val adapter3=Adapters["nunv"]
        val listView3=Lists["nunv"]
        if(adapter3!=null&&listView3!=null) {
            listView3.adapter = adapter3
            adapter3.notifyDataSetChanged()
        }

        }

    override fun onPause() {
        super.onPause()

        var path=this.getExternalFilesDir(null)
        var file:File= File(path,filename)
        if(!file.exists()){
            file.createNewFile()
        }else{
            file.delete()
            file.createNewFile()
        }

        var fileNameOutput: FileOutputStream?= null
        fileNameOutput = openFileOutput(filename, Context.MODE_PRIVATE)

        for((k,v) in Containers){
            var name=k
            fileNameOutput.write((name).toByteArray())
            fileNameOutput.write(lineSeparator.toByteArray())
            for(i in v){
                fileNameOutput.write((i).toByteArray())
                fileNameOutput.write(lineSeparator.toByteArray())
            }
        }
        fileNameOutput.flush()
        fileNameOutput.close()
    }

    fun ReadInitialData(){
        var path=this.getExternalFilesDir(null)
        var file:File=File(path,filename)
        var fileNameInput: FileInputStream?= null
        var myList= arrayListOf<String>()
        if(file.exists()) {
            fileNameInput = openFileInput(filename)
            var reader: InputStreamReader = InputStreamReader(fileNameInput)
            var bufferedReader: BufferedReader = BufferedReader(reader)
            var builder: StringBuilder = StringBuilder()
            var text: String? = null
            while ({ text = bufferedReader.readLine();text }() != null) {
                builder.append(text)
                myList.add(builder.toString())
                builder.clear()
            }
        }

        var shortcuts=Containers.keys
        var currentShortcut:String=""
        for(e in myList){
            if(e in shortcuts){
                currentShortcut=e
            }
            else if(currentShortcut=="uv")
                uvContainer.add(e)
            else if(currentShortcut=="nunv")
                nunvContainer.add(e)
            else if(currentShortcut=="nuv")
                nuvContainer.add(e)
            else if(currentShortcut=="unv")
                unvContainer.add(e)
            }

        }
    }
