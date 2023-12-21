package com.example.lomaden

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.lomaden.fragment.HomeFragment
import com.example.lomaden.fragment.KosFragment
import com.example.lomaden.fragment.ProfileFragment
import com.example.lomaden.fragment.TransportasiFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomePageActivity : AppCompatActivity() {
    private var bottomNavi: BottomNavigationView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)
        supportFragmentManager.beginTransaction().replace(R.id.content, HomeFragment()).commit()
        bottomNavi = findViewById<View>(R.id.bottom_navigation_view) as BottomNavigationView
        bottomNavi!!.setOnNavigationItemSelectedListener(object :
            BottomNavigationView.OnNavigationItemSelectedListener {
            private var fragment: Fragment? = null
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                fragment = Fragment()
                println("selected => " + item.itemId)
                when (item.itemId) {
                    R.id.itemHome -> fragment = HomeFragment()
                    R.id.itemKos -> fragment = KosFragment()
                    R.id.itemTransportasi -> fragment = TransportasiFragment()
                    R.id.itemProfile -> fragment = ProfileFragment()
                }
                supportFragmentManager.beginTransaction().replace(R.id.content, fragment!!).commit()
                return true
            }
        })
    }
}