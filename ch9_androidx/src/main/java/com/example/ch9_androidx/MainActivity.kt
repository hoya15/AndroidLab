package com.example.ch9_androidx

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.ch9_androidx.databinding.ActivityMainBinding
import com.example.ch9_jetpack.OneFragment
import com.example.ch9_jetpack.ThreeFragment
import com.example.ch9_jetpack.TwoFragment
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    lateinit var toggle: ActionBarDrawerToggle

    // viewpager는 adapterView 이다.. 화면을 항목으로본다..
    // 모든 AdapterVIew는 Adapter가 항목을 만들어 주어야 한다.
    class MyFragmentPagerAdapter(activity: FragmentActivity): FragmentStateAdapter(activity){
        val fragments: List<Fragment>
        init {
            fragments = listOf(OneFragment(), TwoFragment(), ThreeFragment())
        }
        // 항목 갯수를 판단하
        override fun getItemCount(): Int {
            return fragments.size
        }
        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // viewpager 코드
        // viewPager를 위한 Adapter 준비, 적용
        val adapter = MyFragmentPagerAdapter(this)
        binding.viewpager.adapter = adapter

        // actionbat
        setSupportActionBar(binding.toolbar)

        // toggle
        toggle = ActionBarDrawerToggle(this, binding.drawer, R.string.drawer_open, R.string.drawer_close)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toggle.syncState()

        // tablayout .. viewpager 연동
        TabLayoutMediator(binding.tabs, binding.viewpager){tab, position->
            tab.text = "Tab${position+1}"
        }.attach()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}