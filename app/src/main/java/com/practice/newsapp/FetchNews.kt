package com.practice.newsapp

import android.content.Context
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import org.json.JSONObject

class FetchNews(val ctx: Context) {

    private var DEFAULT_URL_WITH_CATEGORY: String =
        "https://saurav.tech/NewsAPI/top-headlines/category/"
    private var DEFAULT_URL_EVERYTHING: String = "https://saurav.tech/NewsAPI/everything/cnn.json"

    interface NewsByTopicListener {
        fun onResponse(
            newsList: ArrayList<News>,
            status: String,
            totalResults: String
        );

        fun onError(message: String);
    }

    fun fetchNewsByTopic(topic: String, listener: NewsByTopicListener) {
        val newsList: ArrayList<News> = ArrayList<News>()
        var status: String
        var totalResults: String
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            "$DEFAULT_URL_WITH_CATEGORY$topic/in.json",
            null, {
                status = it.getString("status")
                totalResults = it.getString("totalResults")

                val articleArray = it.getJSONArray("articles")

                for (i in 0 until articleArray.length()) {
                    val tempObj: JSONObject = articleArray.get(i) as JSONObject
                    val news = News(
                        title = tempObj.getString("title"),
                        description = tempObj.getString("description"),
                        postUrl = tempObj.getString("url"),
                        imageUrl = tempObj.getString("urlToImage"),
                        publishedDate = tempObj.getString("publishedAt"),
                        author = tempObj.getString("author")
                    )
                    newsList.add(news)
                }
                listener.onResponse(newsList, status, totalResults)
//                Toast.makeText(ctx, totalResults, Toast.LENGTH_LONG).show()
            },
            {
                listener.onError(it.message.toString())
            }
        )
        VolleySingleton.getInstance(ctx).addToRequestQueue(jsonObjectRequest)
    }

    fun fetchNewsEverything(listener: NewsByTopicListener) {
        val newsList: ArrayList<News> = ArrayList<News>()
        var status: String
        var totalResults: String
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET,
            DEFAULT_URL_EVERYTHING,
            null, {
                status = it.getString("status")
                totalResults = it.getString("totalResults")

                val articleArray = it.getJSONArray("articles")

                for (i in 0 until articleArray.length()) {
                    val tempObj: JSONObject = articleArray.get(i) as JSONObject
                    val news = News(
                        title = tempObj.getString("title"),
                        description = tempObj.getString("description"),
                        postUrl = tempObj.getString("url"),
                        imageUrl = tempObj.getString("urlToImage"),
                        publishedDate = tempObj.getString("publishedAt"),
                        author = tempObj.getString("author")
                    )
                    newsList.add(news)
                }
                listener.onResponse(newsList, status, totalResults)
//                Toast.makeText(ctx, totalResults, Toast.LENGTH_LONG).show()
            },
            {
                listener.onError(it.message.toString())
            }
        )
        VolleySingleton.getInstance(ctx).addToRequestQueue(jsonObjectRequest)
    }
}