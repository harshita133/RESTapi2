package com.developer.harshi.restapi

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val adapter = PostAdapter(ArrayList())
        rvPosts.adapter=adapter
        rvPosts.layoutManager= LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)

        val downloadTask = DownloadTask()

        downloadTask.setOnDownloadListener(object : DownloadTask.OnDownloadedListener{
            override fun onDownloaded(postList: ArrayList<Post>?) {
                postList?.let {
                    adapter.updateList(it)
                }?:Toast.makeText(this@MainActivity,"List came null",Toast.LENGTH_SHORT).show()
            }

        })

    }
}
