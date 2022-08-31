package com.practice.newsapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import java.text.SimpleDateFormat
import java.util.Calendar

interface VisitListener {
    fun onClick(news: News);
}

class NewsAdapter(private val context: Context, val listener: VisitListener, val topic: String) : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    private var newsList: ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = newsList[position]
        Glide.with(context).load(currentItem.imageUrl).into(holder.newsImage)
        holder.titleTv.text = currentItem.title
        holder.authorTv.text = currentItem.author
        holder.dateTv.text = "Unknown"

        holder.visitBtn.setOnClickListener {
            listener.onClick(currentItem)
        }

        holder.topicTv.text = topic;
    }

    override fun getItemCount(): Int {
        return newsList.size
    }

    fun updateList(updatedList: ArrayList<News>) {
        this.newsList.clear()
        this.newsList.addAll(updatedList)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // News Image
        val newsImage: ImageView = itemView.findViewById(R.id.newsImage)

        // All the textViews
        val topicTv: TextView = itemView.findViewById(R.id.topicTV)
        val titleTv: TextView = itemView.findViewById(R.id.titleTV)
        val authorTv: TextView = itemView.findViewById(R.id.authorTV)
        val dateTv: TextView = itemView.findViewById(R.id.dateTv)

        // Button to visit the textView
        val visitBtn: MaterialCardView = itemView.findViewById(R.id.visitBtn)
    }
}