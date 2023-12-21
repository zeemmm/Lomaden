package com.example.lomaden

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.api.Api
import com.example.data.Data

class TransportasiActivity : AppCompatActivity() {
    private lateinit var back: ImageView
    private val title: TextView? = null
    private val recy: RecyclerView? = null
    private var api: Api? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.kos2)
        api = Api(this)
        back = findViewById(R.id.back)
        back.setOnClickListener(View.OnClickListener { finish() })
        val bundle = intent.extras
        if (bundle != null) {
            val data = bundle.getString("data", "")
            val dataArrayList = ArrayList<Data>()
            api!!.listWithArray(data, dataArrayList)
        }
    }
}