package com.practice.newsapp

import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private lateinit var rv_news: RecyclerView
    private lateinit var newsCount: TextView
    private lateinit var progressBar: ProgressBar

    // All the urls
    private var DEFAULT_URL_WITH_CATEGORY: String =
        "https://saurav.tech/NewsAPI/top-headlines/category/"
    private var DEFAULT_URL_EVERYTHING: String = "https://saurav.tech/NewsAPI/everything/cnn.json"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rv_news = findViewById(R.id.rv_news)
        newsCount = findViewById(R.id.newsCount)
        progressBar = findViewById(R.id.progressBar)

        supportActionBar?.hide()

        rv_news.layoutManager = LinearLayoutManager(this)
        rv_news.hasFixedSize()

        val topic: String? = intent.getStringExtra("topic")

        val fetchNews = FetchNews(this)

        when(topic) {
            "everything" -> {
                fetchNews.fetchNewsEverything(object : FetchNews.NewsByTopicListener {
                    override fun onError(message: String) {
                        progressBar.visibility = View.GONE
                        Toast.makeText(
                            applicationContext,
                            "Some Error Occurred, Please try with another Category!",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    override fun onResponse(
                        newsList: ArrayList<News>,
                        status: String,
                        totalResults: String
                    ) {
                        progressBar.visibility = View.GONE
                        newsCount.text = "News: $totalResults"
                        if (status == "ok") {
                            val newsAdapter: NewsAdapter =
                                NewsAdapter(applicationContext, object : VisitListener {
                                    override fun onClick(news: News) {
                                        loadUrlInWebView(news.postUrl)
                                    }
                                }, topic)
                            newsAdapter.updateList(newsList)
                            rv_news.adapter = newsAdapter
                        } else {
                            progressBar.visibility = View.GONE
                            Toast.makeText(
                                applicationContext,
                                "Some Error Occurred, Please try with another Category!",
                                Toast.LENGTH_LONG
                            )
                                .show()
                        }
                    }
                })
            }
            else -> {
                if (topic != null) {
                    fetchNews.fetchNewsByTopic(topic, object : FetchNews.NewsByTopicListener {
                        override fun onError(message: String) {
                            progressBar.visibility = View.GONE
                            Toast.makeText(
                                applicationContext,
                                "Some Error Occurred, Please try with another Category!",
                                Toast.LENGTH_LONG
                            ).show()
                        }

                        override fun onResponse(
                            newsList: ArrayList<News>,
                            status: String,
                            totalResults: String
                        ) {
                            if (status == "ok") {
                                progressBar.visibility = View.GONE
                                newsCount.text = "News: $totalResults"
                                val newsAdapter: NewsAdapter =
                                    NewsAdapter(applicationContext, object : VisitListener {
                                        override fun onClick(news: News) {
                                            loadUrlInWebView(news.postUrl)
                                        }
                                    }, topic)
                                newsAdapter.updateList(newsList)
                                rv_news.adapter = newsAdapter
                            } else {
                                progressBar.visibility = View.GONE
                                Toast.makeText(
                                    applicationContext,
                                    "Some Error Occurred, Please try with another Category!",
                                    Toast.LENGTH_LONG
                                )
                                    .show()
                            }
                        }
                    })
                }
            }
        }
    }

    fun loadUrlInWebView(url: String) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(url))
    }
}