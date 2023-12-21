package com.example.lomaden.fragment

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.database.dbUsers
import com.example.lomaden.MainActivity
import com.example.lomaden.R
import org.json.JSONException
import org.json.JSONObject

class ProfileFragment : Fragment() {
    private val db: dbUsers? = null
    private val userName: TextView? = null
    private val waktu: TextView? = null
    private val nomer: TextView? = null
    private val alamat: TextView? = null
    private val email: TextView? = null
    private lateinit var shared: SharedPreferences
    private lateinit var btn_logout: RelativeLayout
    private var txt_user: TextView? = null
    private var txt_number: TextView? = null
    private var txt_email: TextView? = null
    private lateinit var txt_status: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        shared = requireContext().getSharedPreferences("users", Context.MODE_PRIVATE)
        txt_user = view.findViewById(R.id.username)
        txt_number = view.findViewById(R.id.number_phone)
        txt_email = view.findViewById(R.id.email)
        txt_status = view.findViewById(R.id.waktu)
        txt_status.setText("Online")
        profile(shared.getString("email", ""), shared.getString("password", ""))
        val change_profile = view.findViewById<ImageView>(R.id.change_profile)
        change_profile.setColorFilter(resources.getColor(R.color.white))
        val icon_exit = view.findViewById<ImageView>(R.id.icon_exit)
        icon_exit.setColorFilter(resources.getColor(R.color.white))
        btn_logout = view.findViewById(R.id.logout)
        btn_logout.setOnClickListener(View.OnClickListener {
            val editor = shared.edit()
            editor.putString("email", "")
            editor.putString("password", "")
            editor.apply()
            val intent = Intent(context, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
            requireActivity().finish()
        })
        return view
    }

    fun profile(email: String?, password: String?) {
        val requestQueue = Volley.newRequestQueue(context)
        val stringRequest = StringRequest(
            Request.Method.GET,
            "https://www.chaerul.biz.id/kosan/profile.php?email=" + email.toString() + "&&pass=" + password.toString(),
            { response: String? ->
                try {
                    val jsonArray = JSONObject(response).getJSONArray("data")
                    for (i in 0 until jsonArray.length()) {
                        val json = jsonArray.getJSONObject(i)
                        val user = json.getString("username")
                        val pass = json.getString("password")
                        val email_ = json.getString("email")
                        println("username = $user")
                        println("password = $pass")
                        txt_user!!.text = user
                        txt_email!!.text = email_
                    }
                } catch (e: JSONException) {
                    e.printStackTrace()
                    println(e)
                }
            }
        ) { error: VolleyError ->
            Toast.makeText(context, "Gagal terhubung", Toast.LENGTH_SHORT).show()
            println("error: " + error.message)
        }
        requestQueue.add(stringRequest)
    }
}