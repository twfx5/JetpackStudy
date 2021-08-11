package com.wang.recycleview

import android.content.Context
import android.content.res.Resources
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.hypot

val Int.dp
    get() = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        this.toFloat(),
        Resources.getSystem().displayMetrics
    ).toInt()

const val TAG = "MainActivity"

/**
 *  自定义 RecycleView 的分隔线
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val list = mutableListOf<String>()
        for (index in 0 until 26) {
            list.add("我是第 $index  个元素")
        }

        val recyclerView = findViewById<RecyclerView>(R.id.rv).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = MyAdapter(list)
            addItemDecoration(MyItemDecoration().apply {
                setDrawable(ResourcesCompat.getDrawable(resources, R.drawable.bg_item, null)!!)
            })
        }
    }

    class MyAdapter(var list: MutableList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

        init {
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_item, parent, false)
            return MyViewHolder(view)
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            if (holder is MyViewHolder) {
                holder.textView.text = list[position]
            }
        }

        override fun getItemCount(): Int = list.size

        class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
            var textView: TextView = itemView.findViewById(R.id.text)
        }
    }

    class MyItemDecoration : RecyclerView.ItemDecoration() {

        private lateinit var drawable: Drawable
        private val mBounds = Rect()

        fun setDrawable(drawable: Drawable) {
            this.drawable = drawable
        }

        // 绘制不顶头的分隔线
        /*override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            canvas.save()
            val left: Int = 15.dp
            val right = parent.width

            val childCount = parent.childCount
            Log.d(TAG, "onDraw: $childCount")
            for (i in 0 until childCount) {
                val child = parent.getChildAt(i)
                parent.getDecoratedBoundsWithMargins(child, mBounds)
                val bottom = mBounds.bottom + Math.round(child.translationY)
                val top = bottom - drawable.intrinsicHeight
                drawable.setBounds(left, top, right, bottom)
                drawable.draw(canvas)
            }
            canvas.restore()
        }*/

        /*override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            Log.d(TAG, "getItemOffsets: ${parent.adapter?.itemCount}")
            outRect.left = 0
            outRect.top = 0
            outRect.right = 0
            outRect.bottom = drawable.intrinsicHeight
        }*/

        // 绘制分组的分隔线
        override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
            val left: Int = 15.dp
            val right = parent.width

            val childCount = parent.childCount
            for (index in 0 until childCount) {
                val child = parent.getChildAt(index)
                val adapterPosition = parent.getChildViewHolder(child).adapterPosition
                // 第3和第5的位置绘制分隔线
                if (adapterPosition == 3 || adapterPosition == 5) {
                    parent.getDecoratedBoundsWithMargins(child, mBounds)
                    val bottom = mBounds.bottom + Math.round(child.translationY)
                    val top = bottom - drawable.intrinsicHeight
                    drawable.setBounds(left, top, right, bottom)
                    drawable.draw(canvas)
                }
            }
        }

        override fun getItemOffsets(
            outRect: Rect,
            view: View,
            parent: RecyclerView,
            state: RecyclerView.State
        ) {
            outRect.left = 0
            outRect.top = 0
            outRect.right = 0
            val itemPosition = (view.layoutParams as RecyclerView.LayoutParams).viewAdapterPosition
            // 第3和第5的位置设置偏移
            if (itemPosition == 3 || itemPosition == 5) {
                outRect.bottom = drawable.intrinsicHeight
            } else {
                outRect.bottom = 0
            }
        }
    }
}