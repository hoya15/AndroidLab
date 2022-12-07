package com.example.ch7_database

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.ch7_database.databinding.ActivityAddBinding

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.addButton.setOnClickListener {
            val inputData = binding.addEditView.text.toString()
            val db = DBHelper(this).writableDatabase
            db.execSQL("insert into TB_TODO (todo) values (?)", arrayOf(inputData))
            db.close()

            //MainActivity -> AddActivty
            //화면을 자동으로 MainActivity 로 되돌리면서.. 데이터 전달...
            intent.putExtra("result", inputData)
            setResult(RESULT_OK, intent)//상태정보 등록..
            //자신을 종료시켜서.. 시스템에 의해 이전 화면으로 되돌아가게..
            finish()
        }
    }
}