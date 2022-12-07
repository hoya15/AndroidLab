package com.example.ch6_resource

import android.content.ContentValues
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val values = ContentValues()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}