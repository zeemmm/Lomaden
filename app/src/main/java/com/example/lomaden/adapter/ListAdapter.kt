package com.example.lomaden.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.data.DataSet
import com.example.lomaden.R
import com.image.download.ImageTask

class ListAdapter(var context: Context, var dataSets: ArrayList<DataSet>) :
    RecyclerView.Adapter<ListAdapter.List>() {
    private lateinit var view: View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): List {
        view = LayoutInflater.from(context).inflate(R.layout.list_custom, parent, false)
        return List(view)
    }

    override fun onBindViewHolder(holder: List, position: Int) {
        val d = dataSets[position]
        holder.harga.text = d.price
        val addr = d.address
        var address: String? = ""
        if (addr.length > 20) {
            address = addr.substring(0, 20)
            println(address)
        } else {
            address = d.address
            println(address)
        }
        holder.alamat.text = address
        val title = d.title
        var namakosan: String? = ""
        if (title.length > 17) {
            namakosan = title.substring(0, 17)
            println(namakosan)
        } else {
            namakosan = d.title
            println(namakosan)
        }
        holder.title.text = title
        holder.waktu.text = d.date
        holder.sisa.text = d.count
        ImageTask(holder.images).execute(d.images)
    }

    override fun getItemCount(): Int {
        return dataSets.size
    }

    inner class List(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var harga: TextView
        var alamat: TextView
        var title: TextView
        var waktu: TextView
        var sisa: TextView
        var images: ImageView

        init {
            title = itemView.findViewById(R.id.nama_kosan)
            harga = itemView.findViewById<View>(R.id.harga) as TextView
            alamat = itemView.findViewById<View>(R.id.alamat) as TextView
            waktu = itemView.findViewById<View>(R.id.waktu) as TextView
            sisa = itemView.findViewById<View>(R.id.sisa_kamar) as TextView
            images = itemView.findViewById<View>(R.id.images) as ImageView
        }
    }
}