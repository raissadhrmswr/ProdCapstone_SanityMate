package com.novia.sanitymate.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.novia.sanitymate.R
import com.novia.sanitymate.model.Team


class TeamAdapter (
    var listTeam: List<Team>
) :RecyclerView.Adapter<TeamAdapter.TeamViewHolder>(){
    inner class TeamViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_name)
        var tvUniv: TextView = itemView.findViewById(R.id.tv_univ)
        var tvLearningPath: TextView = itemView.findViewById(R.id.tv_learning_path)
        var ivAvatar: ImageView = itemView.findViewById(R.id.iv_avatar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_team, parent, false)
        return TeamViewHolder(view)
    }

    override fun getItemCount(): Int = listTeam.size

    override fun onBindViewHolder(holder: TeamViewHolder, position: Int) {
        val currentItem = listTeam[position]
        holder.tvName.text = currentItem.name
        holder.tvUniv.text = currentItem.university
        holder.tvLearningPath.text = currentItem.learningPath
        Glide.with(holder.itemView)
            .load(currentItem.photo)
            .into(holder.ivAvatar)
    }
}