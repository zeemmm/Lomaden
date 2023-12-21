package com.example.lomaden

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.database.dbUsers
import org.json.JSONException
import org.json.JSONObject

class LoginActivity : AppCompatActivity() {
    private lateinit var btnLogin: Button
    private lateinit var input_email: EditText
    private lateinit var input_pass: EditText
    private var db: dbUsers? = null
    private lateinit var back: ImageView
    private lateinit var shared: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        shared = getSharedPreferences("users", MODE_PRIVATE)
        back = findViewById(R.id.arrow_back)
        back.setOnClickListener(View.OnClickListener { finish() })
        db = dbUsers(this)
        input_email = findViewById(R.id.email)
        input_email.setText(shared.getString("email", ""))
        input_pass = findViewById(R.id.password)
        btnLogin = findViewById(R.id.r_btn_1)
        btnLogin.setOnClickListener(View.OnClickListener {
            val email = input_email.getText().toString()
            val password = input_pass.getText().toString()
            login(email, password)
        })
    }

    fun login(email: String, password: String) {
        val requestQueue = Volley.newRequestQueue(this)
        val stringRequest = StringRequest(
            Request.Method.GET,
            "https://www.chaerul.biz.id/kosan/login.php?login&&email=$email&&pass=$password",
            { response: String? ->
                println(response)
                try {
                    val jsonArray = JSONObject(response).getJSONArray("login")
                    for (i in 0 until jsonArray.length()) {
                        val json = jsonArray.getJSONObject(i)
                        val status = json.getString("msg")
                        if (status == "berhasil") {
                            val editor = shared!!.edit()
                            editor.putString("email", email)
                            editor.putString("password", password)
                            editor.apply()
                            val intent = Intent(this, HomePageActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                            this.startActivity(intent)
                            //finis
                            finish()
                            break
                        } else if (status == "gagal") {
                            Toast.makeText(this, "password salah", Toast.LENGTH_SHORT).show()
                        }
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                    println(e)
                }
            }
        ) { error: VolleyError ->
            Toast.makeText(this, "Gagal terhubung", Toast.LENGTH_SHORT).show()
            println("error: " + error.message)
        }
        requestQueue.add(stringRequest)
    }

    private fun home() {
        val intent = Intent(this@LoginActivity, HomePageActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        startActivity(intent)
        finish()
    }
}