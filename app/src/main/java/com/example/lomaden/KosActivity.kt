package com.example.lomaden

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.api.Api
import com.example.data.DataSet
import com.example.lomaden.adapter.ListAdapter

class KosActivity : AppCompatActivity() {
    private lateinit var recy: RecyclerView
    private lateinit var array: ArrayList<DataSet>
    private var adapter: ListAdapter? = null
    private var api: Api? = null
    private var title: TextView? = null
    private var back: ImageView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kos2)
        api = Api(this)
        title = findViewById<View>(R.id.kosan_title) as TextView
        title!!.isAllCaps = true
        back = findViewById<View>(R.id.back) as ImageView
        back!!.setOnClickListener { finish() }
        recy = findViewById<View>(R.id.recy) as RecyclerView
        val linearLayoutManager = LinearLayoutManager(this)
        recy!!.layoutManager = linearLayoutManager
        array = ArrayList()
        adapter = ListAdapter(this, array!!)
        recy!!.adapter = adapter
        val bundle = intent.extras
        if (bundle != null) {
            val data = bundle.getString("data", "")
            title!!.text = data
            if (data == "sepeda") {
                api!!.sepeda(array, adapter)
            }
            if (data == "motor") {
                api!!.motor(array, adapter)
            }
            if (data == "mobil") {
                api!!.mobil(array, adapter)
            }
            if (data == "kos campur") {
                api!!.kosan(array, adapter)
            }
            if (data == "kos laki laki") {
                api!!.kosan(array, adapter)
            }
            if (data == "kos perempuan") {
                api!!.kosan(array, adapter)
            }
        }
    }
}