package com.example.lomaden

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.api.Api
import com.database.dbSearch
import com.example.data.DataSet
import com.example.lomaden.adapter.SearchAdapter

class SearchActivity : AppCompatActivity() {
    private lateinit var search: SearchView
    private lateinit var recy_history: RecyclerView
    private lateinit var array: ArrayList<DataSet>
    private var adapter: SearchAdapter? = null
    private var db: dbSearch? = null
    private var api: Api? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.search_layout)
        api = Api(this)
        db = dbSearch(this)
        recy_history = findViewById<View>(R.id.search_layout_recy_history) as RecyclerView
        val linearLayoutManager = LinearLayoutManager(this)
        recy_history!!.layoutManager = linearLayoutManager
        array = ArrayList()
        adapter = SearchAdapter(this, array!!)
        recy_history!!.adapter = adapter
        api!!.search(array, adapter)
        search = findViewById(R.id.searchView)
        search.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                performSearch(query)
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                showSearchSuggestions(newText)
                return true
            }
        })
    }

    private fun showSearchSuggestions(newText: String) {}
    private fun performSearch(query: String) {
        // Logic for performing the search
        Toast.makeText(this, query, Toast.LENGTH_SHORT).show()
        if (db!!.addSearch(query) > 0) {
            println("add colums success")
            adapter!!.notifyDataSetChanged()
        } else {
            println("add colums failed!")
        }
    }
}