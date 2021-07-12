package com.wang.jetpackstudy

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FlowActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flow)
        findViewById<RecyclerView>(R.id.rv).apply {
            layoutManager = GridLayoutManager(this@FlowActivity, 8)
            adapter = MyAdapter(this@FlowActivity)
            addItemDecoration(GridSpacingItemDecoration(8, 10, false))
        }
    }

    class MyAdapter(var context: Context) : RecyclerView.Adapter<MyAdapter.ItemViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
            return ItemViewHolder(view)
        }

        override fun getItemCount() = 17

        override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        }

        class ItemViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {
            var searchItemView: ImageView

            init {
                searchItemView = itemView.findViewById(R.id.image)
            }
        }

    }
}