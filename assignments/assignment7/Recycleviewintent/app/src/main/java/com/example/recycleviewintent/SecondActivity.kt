package com.example.recycleviewintent

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        // Set up the toolbar
        val toolbar: MaterialToolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)



        // Get references to the views in the layout
        val titleText: TextView = findViewById(R.id.titleText)
        val detailText: TextView = findViewById(R.id.detailText)
        val imageView: ImageView = findViewById(R.id.imageView)

        // Retrieve data from intent
        val title = intent.getStringExtra("title")
        val detail = intent.getStringExtra("detail")
        val imageResId = intent.getIntExtra("image", 0)

        // Set data to views
        titleText.text = title
        detailText.text = detail
        imageView.setImageResource(imageResId)

    }


}
