package com.example.ch7_database

import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.ch7_database.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val datas: MutableList<String> = mutableListOf()//db select data

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = DBHelper(this).readableDatabase
        val cursor = db.rawQuery("select * from TB_TODO", null)
        cursor.run {
            while (moveToNext()){
                datas.add(getString(1))
            }
        }
        db.close()

        var result = ""
        datas.forEach {
            result += "$it \n"
        }
        binding.mainTextView.text = result

        //AddActivity 실행.. 되돌아 왔을때 결과 획득..
        val launcher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()//요청 처리자..
        ){
            //되돌아 왔을때의 콜백...
            //전달된 데이터 획득하고..
            it.data!!.getStringExtra("result")?.let {
                //전달된 데이터가 있다면... ?.let {} null 이 아니라며..
                datas.add(it)
                var result = ""
                datas.forEach {
                    result += "$it \n"
                }
                binding.mainTextView.text = result
            }
        }

        binding.mainButton.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            launcher.launch(intent)//화면 전환 순간..
        }

    }
}







