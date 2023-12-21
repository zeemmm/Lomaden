package com.example.lomaden.fragment

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.api.Api
import com.example.data.Data
import com.example.lomaden.R
import com.example.lomaden.SearchActivity
import com.example.lomaden.adapter.HomeAdapter
import com.example.lomaden.adapter.SlideImages
import com.google.android.material.tabs.TabLayout

class HomeFragment : Fragment() {
    private lateinit var recy: RecyclerView
    private lateinit var array: ArrayList<Data>
    private lateinit var rec_1: HomeAdapter
    private lateinit var view: View
    private lateinit var sewaRec: RecyclerView
    private lateinit var sepeda: RecyclerView
    private lateinit var arraySewa: ArrayList<Data>
    private lateinit var viewpager: ViewPager
    private lateinit var tbLayout: TabLayout
    private lateinit var arrayPager: ArrayList<Data>
    private var api: Api? = null
    private lateinit var search: LinearLayout
    private var handler: Handler? = null
    private var runnable: Runnable? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        view = inflater.inflate(R.layout.fragment_home, container, false)
        api = Api(requireContext()) // Use requireContext() instead of context
        search = view.findViewById(R.id.ll_search)
        search.setOnClickListener { intentSearch() } // Simplify the click listener
        viewPager()
        load_kosan()
        load_transportasi()
        load_sepeda()
        return view
    }

    private fun load_kosan() {
        recy = view.findViewById(R.id.userlist)
        val gridLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        recy.layoutManager = gridLayoutManager
        array = ArrayList()
        rec_1 = HomeAdapter(requireContext(), array)
        recy.adapter = rec_1
        api?.list_data("kosan", array, rec_1)
    }

    private fun load_transportasi() {
        sewaRec = view.findViewById(R.id.rec_sewa_kendaraan)
        val gridLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        sewaRec.layoutManager = gridLayoutManager
        arraySewa = ArrayList()
        val rec_1_sewa = HomeAdapter(requireContext(), arraySewa)
        sewaRec.adapter = rec_1_sewa
        api?.list_data("transportasi", arraySewa, rec_1_sewa)
    }

    private fun load_sepeda() {
        sepeda = view.findViewById(R.id.rec_sepeda)
        val gridLayoutManager = GridLayoutManager(requireContext(), 2)
        sepeda.layoutManager = gridLayoutManager
        val sepeda_array = ArrayList<Data>()
        val rec_1_sewa = HomeAdapter(requireContext(), sepeda_array)
        sepeda.adapter = rec_1_sewa
        api?.list_data("sepeda", sepeda_array, rec_1_sewa)
    }

    private fun viewPager() {
        viewpager = view.findViewById(R.id.view_pager)
        tbLayout = view.findViewById(R.id.tablayout)
        arrayPager = ArrayList()
        val slide_data_1 = Data()
        slide_data_1.images =
            "https://www.chaerul.biz.id/kosan/gambar/whatsapp_image_2021_05_07_at_1__1620375101647__1621930113681.jpg"
        arrayPager.add(slide_data_1)
        val slide_data_2 = Data()
        slide_data_2.images =
            "https://www.chaerul.biz.id/kosan/gambar/whatsapp_image_2021_05_07_at_1__1620375101647__1621930113681.jpg"
        arrayPager.add(slide_data_2)
        val viewAdapter = SlideImages(requireContext(), arrayPager)
        viewpager.adapter = viewAdapter
        tbLayout.setupWithViewPager(viewpager)
        handler = Handler()
        runnable = object : Runnable {
            override fun run() {
                val currentItem = viewpager.currentItem
                val totalItems = viewpager.adapter?.count ?: 0
                val nextItem = (currentItem + 1) % totalItems
                viewpager.currentItem = nextItem
                handler?.postDelayed(this, 3000) // Repeat after 3 seconds
            }
        }
        handler?.postDelayed(runnable!!, 3000)
    }

    private fun intentSearch() {
        val intent = Intent(requireContext(), SearchActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler?.removeCallbacks(runnable!!)
    }
}
