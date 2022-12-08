package com.example.ch8_menu

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    // 메뉴 구성을 위한 자동호출 ..
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu) // 메뉴 화면 출력..
        // menu에 연결된 actionclass 획득..
        val menuItem = menu?.findItem(R.id.menu3)
        val searchView = menuItem?.actionView as SearchView // 명시적 casting

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                // 검색어 한자한자 입력시마다
                return true
            }
            override fun onQueryTextSubmit(query: String?): Boolean {
                // 유저가 키보드의 검색키를 누른 순간..
                Toast.makeText(this@MainActivity, query, Toast.LENGTH_SHORT).show()
                searchView.setQuery("",false)
                searchView.isIconified = true
                return true
            }

        })
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu1 -> Toast.makeText(this, "menu1", Toast.LENGTH_SHORT).show()
        }

        return super.onOptionsItemSelected(item)
    }
}