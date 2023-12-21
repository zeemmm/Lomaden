package com.example.lomaden

import android.content.Intent
import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import com.example.lomaden.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.btn_1
import kotlinx.android.synthetic.main.activity_main.btn_2
import java.io.File


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    btnLoginListener()
     btnRegisterListeners()



    }
    private fun btnLoginListener(){
        btn_1.setOnClickListener{
            startActivity(Intent(this, LoginActivity::class.java))
        }
    }
    private fun btnRegisterListeners(){
        btn_2.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }



}
