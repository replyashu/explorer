package com.ashu.explorer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.ashu.explorer.R
import com.ashu.explorer.data.BlackLiveMatterData
import com.bumptech.glide.Glide

class BlackLiveMatterAdapter(private val blackLiveMatterData: List<BlackLiveMatterData>): RecyclerView.Adapter<BlackLiveMatterAdapter.RecyclerViewAdapter>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter {
        return RecyclerViewAdapter(parent)
    }

    override fun onBindViewHolder(holder: RecyclerViewAdapter, position: Int) {
        holder.bind(blackLiveMatterData[position])
    }

    override fun getItemCount(): Int {
        return blackLiveMatterData.size
    }

    inner class RecyclerViewAdapter constructor(itemView: View): RecyclerView.ViewHolder(itemView) {
        constructor(parent: ViewGroup) : this(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_black_live_matter,
                parent, false
            )
        )

        fun bind(blackLiveMatterData: BlackLiveMatterData) {
            with(itemView) {
                Glide.with(context.applicationContext).load(blackLiveMatterData.image).centerCrop().into(findViewById(R.id.iv_image))
                findViewById<AppCompatTextView>(R.id.tv_blm).text = blackLiveMatterData.text

            }
        }

    }
}