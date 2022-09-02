package com.example.eventic


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView

class EventsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_events)


        val headingEvents : TextView = findViewById(R.id.heading)
        val detailEvents : TextView = findViewById(R.id.tvDetails)
        val imageEvents : ImageView = findViewById(R.id.image_heading)

        val bundle : Bundle? = intent.extras
        val heading = bundle!!.getString("heading")
        val imageId = bundle.getInt("imageId")
        val events = bundle.getString("events")


        headingEvents.text = heading
        detailEvents.text = events
        imageEvents.setImageResource(imageId)


    }
}