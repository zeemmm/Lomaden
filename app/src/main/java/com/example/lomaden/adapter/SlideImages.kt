package com.example.lomaden.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.example.data.Data
import com.example.lomaden.R
import com.image.download.ImageTask

class SlideImages(private val context: Context, private val data: ArrayList<Data>) :
    PagerAdapter() {
    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val d = data[position]
        val view = LayoutInflater.from(context).inflate(R.layout.slide_custom, container, false)
        val imageSlider = view.findViewById<ImageView>(R.id.image_slider)
        ImageTask(imageSlider).execute(d.images)
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    override fun getCount(): Int {
        return data.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }
}