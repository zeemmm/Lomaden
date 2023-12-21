package com.image.download

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.widget.ImageView
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL

class ImageTask(
    private val imageView: ImageView
) : AsyncTask<String?, Void?, Bitmap?>() {
    protected override fun doInBackground(vararg p0: String?): Bitmap? {
        val imageUrl = p0[0]
        var bitmap: Bitmap? = null
        try {
            val url = URL(imageUrl)
            val connection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input = connection.inputStream
            bitmap = BitmapFactory.decodeStream(input)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return bitmap
    }

    override fun onPostExecute(result: Bitmap?) {
        if (result != null) {
            imageView.setImageBitmap(result);
            //Toast.makeText(imageView.getContext(), "Image downloaded and saved", Toast.LENGTH_SHORT).show();
        }
    }
}