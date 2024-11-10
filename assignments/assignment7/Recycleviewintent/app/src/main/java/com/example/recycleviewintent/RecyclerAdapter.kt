package com.example.recycleviewintent

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class RecyclerAdapter(private val items: List<Triple<String, String, Int>>) :
    RecyclerView.Adapter<RecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleText: TextView = view.findViewById(R.id.titleText)
        val detailText: TextView = view.findViewById(R.id.detailText)
        val imageView: ImageView = view.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.titleText.text = item.first
        holder.detailText.text = item.second
        holder.imageView.setImageResource(item.third)

        // Set up click listener
        holder.itemView.setOnClickListener { v ->
            val i = Intent(v.context, SecondActivity::class.java)
            i.putExtra("title", item.first)
            i.putExtra("detail", item.second)
            i.putExtra("image", item.third)
            ContextCompat.startActivity(v.context, i, null)
        }
    }

    override fun getItemCount(): Int = items.size
}
