package com.novia.sanitymate.view.adapter

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.novia.sanitymate.R
import com.novia.sanitymate.model.ItemBook


class ListBookAdapter(
    private var listBook: List<ItemBook>
) : RecyclerView.Adapter<ListBookAdapter.ListBookViewHolder>() {

    inner class ListBookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        var tvLink: TextView = itemView.findViewById(R.id.tv_link)

        fun bind(item: ItemBook) {
            tvTitle.text = item.title
            tvLink.text = item.link

            tvLink.setOnClickListener {
                val context = itemView.context
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.link))
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListBookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        return ListBookViewHolder(view)
    }

    override fun getItemCount(): Int = listBook.size

    override fun onBindViewHolder(holder: ListBookViewHolder, position: Int) {
        val book = listBook[position]
        if (book != null) {
            holder.bind(book)
        } else {
            Log.d("trace", "list null")
        }
    }
}