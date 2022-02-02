package com.example.newsshare

import android.media.Image
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RemoteViews
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class NewsListAdapter( private val listner: NewsItemClicked): RecyclerView.Adapter<NewsViewHolder>() {


    private val items: ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
           val view = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
           val viewHolder = NewsViewHolder(view)
           view.setOnClickListener{
                 listner.onItemClicked(items[viewHolder.adapterPosition])
           }
           return viewHolder                               //returns ViewHolder
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = items[position]
        holder.titleView.text = currentItem.title
        holder.creator.text = currentItem.creator
        Glide.with(holder.itemView.context).load(currentItem.image_url).into(holder.image_url)
    }

    override fun getItemCount(): Int {
              return items.size                //returns size
    }

      fun updateNews(updatedNews:ArrayList<News>) {
           items.clear()
          items.addAll(updatedNews)

          notifyDataSetChanged() //the above three funcitons get called again
      }
}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val titleView: TextView = itemView.findViewById(R.id.title)
    val image_url: ImageView = itemView.findViewById(R.id.image_url)
    val creator: TextView = itemView.findViewById(R.id.creator)
}

interface NewsItemClicked {
     fun onItemClicked(item: News)                             //this is an interface to handle clicks
}