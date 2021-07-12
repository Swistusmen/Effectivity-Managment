package com.example.efectivitymanagment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView

class eisFullView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_eis_full_view)

        var uvList=findViewById<ListView>(R.id.uvListView)
        var unvList=findViewById<ListView>(R.id.unvListView)
        var nunvList=findViewById<ListView>(R.id.nunvListView)
        var nuvList=findViewById<ListView>(R.id.nuvListView)

        var bundle=intent.extras

        if(bundle!=null) {
            var uv = arrayListOf<String>()
            var temp=    bundle.getStringArrayList("uv")
            if(temp!=null) {
                for (i in temp) {
                    if (i != null)
                        uv.add(i)
                    else {
                        uv.add("pole")
                    }
                }
                var adapter = ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_multiple_choice,
                    uv
                )
                uvList.adapter = adapter
                adapter.notifyDataSetChanged()
            }
            var nuv = arrayListOf<String>()
            var temp1=    bundle.getStringArrayList("nuv")
            if(temp1!=null) {
                for (i in temp1) {
                    if (i != null)
                        nuv.add(i)
                    else {
                        nuv.add("pole")
                    }
                }
                var adapter1 = ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_multiple_choice,
                    nuv
                )
                nuvList.adapter = adapter1
                adapter1.notifyDataSetChanged()
            }

            var unv = arrayListOf<String>()
            var temp2=    bundle.getStringArrayList("unv")
            if(temp2!=null) {
                for (i in temp2) {
                    if (i != null)
                        unv.add(i)
                    else {
                        unv.add("pole")
                    }
                }
                var adapter2 = ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_multiple_choice,
                    unv
                )
                unvList.adapter = adapter2
                adapter2.notifyDataSetChanged()
            }
            var nunv = arrayListOf<String>()
            var temp3=    bundle.getStringArrayList("nunv")
            if(temp3!=null) {
                for (i in temp3) {
                    if (i != null)
                        nunv.add(i)
                    else {
                        nunv.add("pole")
                    }
                }
                var adapter3 = ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_multiple_choice,
                    nunv
                )
                nunvList.adapter = adapter3
                adapter3.notifyDataSetChanged()
            }
        }

    }
}