package com.example.ch3_view

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    lateinit var button: Button
    lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)
        editText = findViewById(R.id.edit)
//        edit
        //  뷰어 이벤트 등록
        button.setOnClickListener{
            val date = editText.text.toString()
            Log.d("hoyaTag", date)

        }
//        setContentView(R.drawable.imagetest)


    }
}