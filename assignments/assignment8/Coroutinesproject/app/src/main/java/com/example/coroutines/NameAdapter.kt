package com.example.coroutines

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.coroutines.databinding.ItemNameBinding

class NameAdapter(private val nameList: MutableList<NameItem>) :
    RecyclerView.Adapter<NameAdapter.NameViewHolder>() {

    inner class NameViewHolder(private val binding: ItemNameBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NameItem) {
            binding.textViewName.text = "The name is ${item.name} and the delay was ${item.timeMillis} milliseconds."
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NameViewHolder {
        val binding = ItemNameBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return NameViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NameViewHolder, position: Int) {
        holder.bind(nameList[position])
    }

    override fun getItemCount(): Int = nameList.size
}
