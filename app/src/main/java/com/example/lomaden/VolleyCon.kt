package com.example.lomaden

import android.content.Context
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

class VolleyCon(var context: Context) {
    var requestQueue = resuestQueue
    val resuestQueue: RequestQueue?
        get() {
            if (requestQueue == null) {
                requestQueue = Volley.newRequestQueue(context.applicationContext)
            }
            return requestQueue
        }

    fun <T> addToRequest(request: Request<T>?) {
        resuestQueue!!.add(request)
    }

    companion object {
        private var volleyConnector: VolleyCon? = null
        @Synchronized
        fun getInstance(context: Context): VolleyCon? {
            var volleyCon: VolleyCon?
            synchronized(VolleyCon::class.java) {
                if (volleyConnector == null) {
                    volleyConnector = VolleyCon(context)
                }
                volleyCon = volleyConnector
            }
            return volleyCon
        }
    }
}