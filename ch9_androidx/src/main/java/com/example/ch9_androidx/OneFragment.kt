package com.example.ch9_jetpack

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ch9_androidx.R
import com.example.ch9_androidx.databinding.FragmentOneBinding
import com.example.ch9_androidx.databinding.ItemRecyclerviewBinding

// 항목을 구성하기 위한 뷰객체를 가지는 역활자..
class MyViewHolder(val binding: ItemRecyclerviewBinding): RecyclerView.ViewHolder(binding.root)

// 항목 구성 역활
class MyAdapter(val datas: MutableList<String>): RecyclerView.Adapter<MyViewHolder>(){
    override fun getItemCount(): Int {
        return datas.size
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(
            parent.context
        ), parent, false))
    }
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val binding = holder.binding
        binding.itemData.text = datas[position]
    }
}

// decoration
class MyDecoration(val context: Context): RecyclerView.ItemDecoration(){
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
        // RecyclerView의 사이즈 획득
        val width = parent.width
        val height = parent.height

        val dr: Drawable? = ResourcesCompat.getDrawable(context.resources, R.drawable.kbo, null)
        val drWidth = dr?.intrinsicWidth
        val drHeigth = dr?.intrinsicHeight
        // 이미지 그리는 좌표 계산..
        val left = width / 2 - drWidth?.div(2) as Int
        val top = height / 2 - drHeigth?.div(2) as Int
        c.drawBitmap(
            BitmapFactory.decodeResource(context.resources, R.drawable.kbo),
            left.toFloat(),
            top.toFloat(),
            null
        )
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        // 항목 index 획득..
        val index = parent.getChildAdapterPosition(view)+1 // 계산을 1에서 시작
        if(index % 3 == 0)
            outRect.set(10,10,10,60)
        else
            outRect.set(10,10,10,0)

        view.setBackgroundColor(Color.parseColor("#28A0ff"))
        ViewCompat.setElevation(view, 20.0f)

    }
}

class OneFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding =  FragmentOneBinding.inflate(inflater, container, false)

        // 항목 구성 데이터 준비
        // DB select

        val datas = mutableListOf<String>()
        for(i in 1..20){
            datas.add("item  + $i")
        }

        binding.recycler.layoutManager = LinearLayoutManager(activity)
        binding.recycler.adapter = MyAdapter(datas)
        binding.recycler.addItemDecoration(MyDecoration(activity as Context))
        return binding.root
    }
}