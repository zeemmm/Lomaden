package com.example.lomaden

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.api.Api

class OpenActivity : AppCompatActivity() {
    private lateinit var images: ImageView
    private lateinit var title: TextView
    private lateinit var alamat: TextView
    private lateinit var desc: TextView
    private lateinit var nilai: TextView
    private lateinit var rate: RatingBar
    private lateinit var api: Api
    private lateinit var fasilitas_1: TextView
    private lateinit var fasilitas: TextView
    private var harga: TextView? = null
    private var nomer: TextView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.open_activity)
        api = Api(this)
        images = findViewById<View>(R.id.open_images) as ImageView
        title = findViewById<View>(R.id.open_namakosan) as TextView
        alamat = findViewById<View>(R.id.open_alamat) as TextView
        desc = findViewById<View>(R.id.description) as TextView
        nilai = findViewById<View>(R.id.nilai_rate) as TextView
        rate = findViewById<View>(R.id.rating_bar) as RatingBar
        rate!!.max = 100
        fasilitas_1 = findViewById<View>(R.id.fasilitas_1) as TextView
        fasilitas = findViewById<View>(R.id.fasilitas) as TextView
        harga = findViewById<View>(R.id.harga) as TextView
        nomer = findViewById<View>(R.id.nomer) as TextView
        val bundle = intent.extras
        if (bundle != null) {
            val data = bundle.getString("data", "")
            val id = bundle.getString("id", "")
            val name = bundle.getString("name", "")
            api!!.open(data, id, images, title, alamat, desc, nilai, harga, fasilitas, rate, nomer)
        }
    }
}