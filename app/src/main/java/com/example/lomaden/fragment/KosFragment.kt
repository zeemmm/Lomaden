package com.example.lomaden.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.api.Api
import com.example.data.Data
import com.example.lomaden.KosActivity
import com.example.lomaden.R
import com.example.lomaden.adapter.RecyAdapter

class KosFragment : Fragment() {
    private lateinit var recy: RecyclerView
    private lateinit var data: ArrayList<Data>
    private lateinit var adapter: RecyAdapter
    private lateinit var kos_campur: RelativeLayout
    private lateinit var kos_laki: RelativeLayout
    private lateinit var kos_perempuan: RelativeLayout
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_kos, container, false)
        val api = Api(context)
        recy = view.findViewById(R.id.recy)
        val gridLayoutManager = LinearLayoutManager(context)
        recy.setLayoutManager(gridLayoutManager)
        data = ArrayList()
        adapter = RecyAdapter(requireContext(), data!!)
        recy.setAdapter(adapter)
        api.list_2("kosan", data, adapter)
        kos_campur = view.findViewById(R.id.kosan_campuran)
        kos_campur.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, KosActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            intent.putExtra("data", "kos campur")
            startActivity(intent)
        })
        kos_laki = view.findViewById(R.id.kos_laki)
        kos_laki.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, KosActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            intent.putExtra("data", "kos laki laki")
            startActivity(intent)
        })
        kos_perempuan = view.findViewById(R.id.kos_perempuan)
        kos_perempuan.setOnClickListener(View.OnClickListener {
            val intent = Intent(context, KosActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            intent.putExtra("data", "kos perempuan")
            startActivity(intent)
        })
        return view
    }
}