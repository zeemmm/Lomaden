package com.example.lomaden.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.data.DataSet
import com.example.lomaden.R

class SearchAdapter(private val context: Context, private val data: ArrayList<DataSet>) :
    RecyclerView.Adapter<SearchAdapter.Recy>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Recy {
        val view = LayoutInflater.from(context).inflate(R.layout.search_custom, parent, false)
        return Recy(view)
    }

    @SuppressLint("RecyclerView")
    override fun onBindViewHolder(holder: Recy, position: Int) {
        val d = data[position]
        holder.textSearch.text = d.title
        holder.delete.setOnClickListener { }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class Recy(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var textSearch: TextView
        lateinit var delete: ImageView

        init {
            textSearch = itemView.findViewById(R.id.text_search)
            delete = itemView.findViewById(R.id.delete_search)
        }
    }
}