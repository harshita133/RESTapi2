package com.developer.harshi.restapi

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


class PostAdapter(var postList:ArrayList<Post>) : RecyclerView.Adapter<PostAdapter.PostHolder>(){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        return  PostHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.layot_post,parent,false)
        )
    }

    fun updateList(newList: ArrayList<Post>){
        postList=newList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int= postList.size

    override fun onBindViewHolder(holder: PostHolder, position: Int) {
       val thisPost = postList[position]
        holder.bind(thisPost)
    }

    class PostHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        val tvUserId = itemView.findViewById<TextView>(R.id.tvUserId)
        val tvId = itemView.findViewById<TextView>(R.id.tvId)
        val tvTitle = itemView.findViewById<TextView>(R.id.tvTitle)
        val tvBody = itemView.findViewById<TextView>(R.id.tvBody)

        fun bind( post:Post){
            tvUserId.text=post.userId.toString()
            tvId.text=post.id.toString()
            tvTitle.text=post.title.toString()
            tvBody.text=post.body.toString()
        }
    }
}