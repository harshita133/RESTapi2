package com.developer.harshi.restapi

import android.os.AsyncTask
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class DownloadTask : AsyncTask<String,Unit ,ArrayList<Post>>(){

    interface OnDownloadedListener{
        fun onDownloaded(postList:ArrayList<Post>?)
    }

    lateinit var onDownloadedListener: OnDownloadedListener

   fun setOnDownloadListener(l : OnDownloadedListener){
       onDownloadedListener=l
   }


    override fun doInBackground(vararg p0: String?): ArrayList<Post> {
        var postList = ArrayList<Post>()
        val url = URL(p0[0])
        val connection = url.openConnection()
        val bufferReader = BufferedReader(InputStreamReader(connection.getInputStream()))

        val sb = StringBuilder()
        var buffer:String? = ""
        while(buffer!=null){
            sb.append(buffer)
            buffer = bufferReader.readLine()
        }

        val jsonArray = JSONArray(sb.toString())

        for( i in 0 until jsonArray.length()){
            val postObject = jsonArray.getJSONObject(i)
            val post = Post(
                    postObject.getInt("userId"),
                    postObject.getInt("id"),
                    postObject.getString("title"),
                    postObject.getString("body")
            )
            postList.add(post)
        }

        return  postList

    }

    override fun onPostExecute(result: ArrayList<Post>?) {
        super.onPostExecute(result)
        onDownloadedListener.onDownloaded(result)
    }
}