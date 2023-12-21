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
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.api.Api
import com.database.dbUsers
import org.json.JSONException
import org.json.JSONObject

class RegisterActivity : AppCompatActivity() {
    private lateinit var btnRegister: Button
    private lateinit var username: EditText
    private lateinit var email: EditText
    private lateinit var password: EditText
    private lateinit var password_conf: EditText
    private lateinit var shared: SharedPreferences
    private lateinit var back: ImageView
    private val api: Api? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        shared = getSharedPreferences("users", MODE_PRIVATE)
        back = findViewById(R.id.arrow_back)
        back.setOnClickListener(View.OnClickListener { finish() })
        btnRegister = findViewById(R.id.register)
        username = findViewById(R.id.username)
        email = findViewById(R.id.email)
        password = findViewById(R.id.password)
        password_conf = findViewById(R.id.password_conf)
        btnRegister.setOnClickListener(View.OnClickListener {
            val users = username.getText().toString()
            val gmail = email.getText().toString()
            val pass = password.getText().toString()
            val pass_conf = password_conf.getText().toString()
            register(users, gmail, pass, pass_conf)
        })
    }

    private fun register(users: String, gmail: String, pass: String, pass_conf: String) {
        if (users.isEmpty()) {
            username!!.error = "Username field is required"
            username!!.requestFocus()
            return
        }
        if (gmail.isEmpty()) {
            email!!.error = "Email field is required"
            email!!.requestFocus()
            return
        }
        if (pass.isEmpty()) {
            password!!.error = "Password field is required"
            password!!.requestFocus()
            return
        }
        if (pass_conf.isEmpty()) {
            password_conf!!.error = "Confirm Password field is required"
            password_conf!!.requestFocus()
            return
        }

        // Perform the registration logic here
        // ...
        if (pass == pass_conf) {
            if (users != "" && gmail != "" && pass != "" && pass_conf != "") {
                val data = """
                    users: $users
                    gmail: $gmail
                    password: $pass
                    password confirm: $pass_conf
                    """.trimIndent()
                println(data)
                register(users, gmail, pass)
            }
        }
    }

    fun register(username: String, email: String, password: String) {
        val requestQueue = Volley.newRequestQueue(this)
        val stringRequest: StringRequest = object : StringRequest(
            Method.POST,
            "https://www.chaerul.biz.id/kosan/register.php",
            Response.Listener { response: String? ->
                try {
                    val jsonArray = JSONObject(response).getJSONArray("status")
                    for (i in 0 until jsonArray.length()) {
                        val json = jsonArray.getJSONObject(i)
                        val status =
                            json.getString("msg") //check status login jika true login berasilk
                        println("output = $status")
                        if (status == "berhasil") {
                            val editor = shared!!.edit()
                            editor.putString("email", email)
                            editor.putString("password", password)
                            editor.apply()
                            println("buat akun berhasil")
                            Toast.makeText(this, "welcome", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, HomePageActivity::class.java)
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
                            this.startActivity(intent)
                            //finis
                            finish()
                            break
                        } else if (status == "akun sudah ada") {
                            println("akun sudah ada")
                            Toast.makeText(this, "akun sudah ada", Toast.LENGTH_SHORT).show()
                        }
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                    println(e)
                }
            },
            Response.ErrorListener { error: VolleyError ->
                Toast.makeText(this, "Gagal terhubung", Toast.LENGTH_SHORT).show()
                println("error: " + error.message)
            }
        ) {
            // Override the getParams() method to include the data you want to post
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String>? {
                val params: MutableMap<String, String> = HashMap()
                params["user"] = username
                params["email"] = email
                params["pass"] = password
                return params
            }
        }
        requestQueue.add(stringRequest)
    }
}