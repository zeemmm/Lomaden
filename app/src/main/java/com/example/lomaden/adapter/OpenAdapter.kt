package com.example.lomaden.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.data.Data
import com.example.lomaden.R
import com.image.download.ImageTask

class OpenAdapter(private val context: Context, private val data: ArrayList<Data>) :
    RecyclerView.Adapter<OpenAdapter.Rec>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Rec {
        val view = LayoutInflater.from(context).inflate(R.layout.kost_custom_1, parent, false)
        return Rec(view)
    }

    override fun onBindViewHolder(holder: Rec, position: Int) {
        val d = data[position]
        ImageTask(holder.foto_ksoan).execute(d.images)
        holder.title.text = d.title
        holder.alamat.text = d.address
        holder.harga.text = d.price + "/bulan"
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class Rec(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var foto_ksoan: ImageView
        var title: TextView
        var alamat: TextView
        var harga: TextView

        init {
            foto_ksoan = itemView.findViewById(R.id.foto_kosan)
            title = itemView.findViewById(R.id.nama_kosan)
            alamat = itemView.findViewById(R.id.alamat_kosan)
            harga = itemView.findViewById(R.id.harga_kosan)
        }
    }
}