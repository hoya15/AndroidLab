package com.example.androidlab

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_test)
//        setContentView(R.layout.activity_main)

        /*
            1. view 객체마다 선언을 해줘야 함
             findViewById
             노가다가 점점 심해짐
         */
        val textView1: TextView = findViewById(R.id.txtHeader)
//        textView1.setTextSize()
        textView1.text = "234234234234 !!!"

        /*
             2. ButterKnife ?? 사용
             annotation : 현재사용안함, 더 좋은거 나옴
             3. kotlin Extend
                구글에서 제공, 지원중지 됨
        */

        /*

            현재 : 뷰 바인딩 기법사용
                gradle 모듈 파일에 선언
                tool에서 자동으로 class 생성
                activity_main.xml -> activity_main_binding
        */

    }

}