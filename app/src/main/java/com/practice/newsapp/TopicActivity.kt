package com.practice.newsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.card.MaterialCardView

class TopicActivity : AppCompatActivity() {

    // All the buttons from our XML File
    private lateinit var skipButton: MaterialCardView

    // All the topicButtons from our XML File
    private lateinit var worldTopic: MaterialCardView
    private lateinit var businessTopic: MaterialCardView
    private lateinit var funTopic: MaterialCardView
    private lateinit var newTopic: MaterialCardView
    private lateinit var healthTopic: MaterialCardView
    private lateinit var scienceTopic: MaterialCardView
    private lateinit var sportsTopic: MaterialCardView
    private lateinit var techTopic: MaterialCardView

    // Constant variable
    private val intentExtraString = "topic"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic)

        // Hiding the action bar from TopicActivity
        supportActionBar?.hide()

        // Initializing all the buttons from our XML File
        skipButton = findViewById(R.id.skipButton)

        // Initializing all the topicButtons from our XML File
        worldTopic = findViewById(R.id.firstTopicCard)
        businessTopic = findViewById(R.id.secondTopicCard)
        funTopic = findViewById(R.id.thirdTopicCard)
        newTopic = findViewById(R.id.fourthTopicCard)
        healthTopic = findViewById(R.id.fifthTopicCard)
        scienceTopic = findViewById(R.id.sixthTopicCard)
        sportsTopic = findViewById(R.id.seventhTopicCard)
        techTopic = findViewById(R.id.eightTopicCard)

        val intent: Intent = Intent(this, MainActivity::class.java)

        skipButton.setOnClickListener {
            intent.putExtra(intentExtraString, "everything")
            startActivity(intent)
        }

        worldTopic.setOnClickListener {
            intent.putExtra(intentExtraString, "everything")
            startActivity(intent)
        }

        businessTopic.setOnClickListener {
            intent.putExtra(intentExtraString, "business")
            startActivity(intent)
        }

        funTopic.setOnClickListener {
            intent.putExtra(intentExtraString, "entertainment")
            startActivity(intent)
        }

        newTopic.setOnClickListener {
            intent.putExtra(intentExtraString, "general")
            startActivity(intent)
        }

        healthTopic.setOnClickListener {
            intent.putExtra(intentExtraString, "health")
            startActivity(intent)
        }

        scienceTopic.setOnClickListener {
            intent.putExtra(intentExtraString, "science")
            startActivity(intent)
        }

        sportsTopic.setOnClickListener {
            intent.putExtra(intentExtraString, "sports")
            startActivity(intent)
        }

        techTopic.setOnClickListener {
            intent.putExtra(intentExtraString, "technology")
            startActivity(intent)
        }
    }
}