package com.ashu.explorer.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.Group
import androidx.recyclerview.widget.RecyclerView
import com.ashu.explorer.R
import com.ashu.explorer.data.HelpFriendsData
import com.bumptech.glide.Glide

class HelpFriendsAdapter(private val helpFriendsData: List<HelpFriendsData>): RecyclerView.Adapter<HelpFriendsAdapter.HelpFriendsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HelpFriendsViewHolder {
        return HelpFriendsViewHolder(parent)
    }

    override fun onBindViewHolder(holder: HelpFriendsViewHolder, position: Int) {
        holder.bind(helpFriendsData[position])
    }

    override fun getItemCount(): Int {
        return helpFriendsData.size
    }

    inner class HelpFriendsViewHolder constructor(itemView: View): RecyclerView.ViewHolder(itemView) {
        constructor(parent: ViewGroup) : this(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.item_help_friends,
                        parent, false
                )
        )

        fun bind(helpFriendsData: HelpFriendsData) {
            with(itemView) {
                Glide.with(context.applicationContext).load(helpFriendsData.image).placeholder(R.drawable.img_help_friend).centerCrop().into(findViewById(R.id.iv_help_friend_photo))
                (helpFriendsData.interestedIn + " is Interested in").also { findViewById<AppCompatTextView>(R.id.tv_interested_in).text = it }
                findViewById<AppCompatTextView>(R.id.tv_title).text = helpFriendsData.title
                findViewById<AppCompatTextView>(R.id.tv_location).text = helpFriendsData.place
                findViewById<AppCompatTextView>(R.id.tv_location_offered).text = helpFriendsData.location
                if (helpFriendsData.rate == null) {
                    findViewById<Group>(R.id.gp_rate).visibility = View.GONE
                } else {
                    findViewById<Group>(R.id.gp_rate).visibility = View.VISIBLE
                }
                findViewById<AppCompatTextView>(R.id.tv_rate).text = helpFriendsData.rate.toString()
                findViewById<AppCompatTextView>(R.id.tv_rate_star).text = helpFriendsData.rating.toString()

            }
        }

    }
}
