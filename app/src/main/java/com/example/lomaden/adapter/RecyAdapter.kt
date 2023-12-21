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

class RecyAdapter(private val context: Context, private val data: ArrayList<Data>) :
    RecyclerView.Adapter<RecyAdapter.Rec>() {
    private lateinit var view: View
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Rec {
        view = LayoutInflater.from(context).inflate(R.layout.kosancustom, parent, false)
        return Rec(view)
    }

    override fun onBindViewHolder(holder: Rec, position: Int) {
        val d = data[position]
        ImageTask(holder.foto_ksoan).execute(d.images)
        view!!.setOnClickListener {
            val open = Intent(context, OpenActivity::class.java)
            open.putExtra("data", d.data)
            open.putExtra("id", d.id)
            context.startActivity(open)
        }
        holder.total_like.text = d.price
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class Rec(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var foto_ksoan: ImageView
        var total_like: TextView

        init {
            foto_ksoan = itemView.findViewById(R.id.foto_kosan)
            total_like = itemView.findViewById(R.id.jumlah_like)
        }
    }
}