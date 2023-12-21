package com.example.lomaden.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.data.Data
import com.example.lomaden.OpenActivity
import com.example.lomaden.R
import com.image.download.ImageTask

class HomeAdapter(private val context: Context, private val data: ArrayList<Data>) :
    RecyclerView.Adapter<HomeAdapter.Recy>() {
    private lateinit var view: View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Recy {
        view = LayoutInflater.from(context).inflate(R.layout.rec_custom_1, parent, false)
        return Recy(view)
    }

    override fun onBindViewHolder(holder: Recy, position: Int) {
        val d = data[position]
        val address = d.address
        var alamat: String? = ""
        if (address.length > 20) {
            alamat = address.substring(0, 20)
            println("text lebih 20")
            println(address)
        } else {
            //text jika kurang 20
            alamat = d.address
            println("text kurang 20")
            println(address)
        }
        val title = d.title
        var namakosan: String? = ""
        if (title.length > 17) {
            namakosan = title.substring(0, 17)
            println("text lebih 17")
            println(namakosan)
        } else {
            //text jika kurang 20
            namakosan = d.title
            println("text kurang 17")
            println(namakosan)
        }
        ImageTask(holder.image).execute(d.images)
        holder.judul.text = namakosan
        holder.alamat.text = alamat
        holder.harga.text = d.price
        holder.kamar.text = d.room
        holder.waktu.text = d.date
        holder.icon.setImageResource(d.icon)
        view!!.setOnClickListener {
            val open = Intent(context, OpenActivity::class.java)
            open.putExtra("data", d.data)
            open.putExtra("id", d.id)
            context.startActivity(open)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class Recy(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView
        var icon: ImageView
        var judul: TextView
        var alamat: TextView
        var harga: TextView
        var kamar: TextView
        var waktu: TextView

        init {
            image = itemView.findViewById(R.id.rec_1_images_kosan)
            judul = itemView.findViewById(R.id.rec_1_nama)
            alamat = itemView.findViewById(R.id.rec_1_alamat)
            harga = itemView.findViewById(R.id.rec_1_harga)
            kamar = itemView.findViewById(R.id.kamar)
            waktu = itemView.findViewById(R.id.waktu)
            icon = itemView.findViewById(R.id.icon_bed)
        }
    }
}