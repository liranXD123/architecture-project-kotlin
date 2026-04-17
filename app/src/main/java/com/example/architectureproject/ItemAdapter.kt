package com.example.architectureproject

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.architectureproject.databinding.ItemLayoutBinding
import kotlinx.coroutines.NonDisposableHandle.parent

class ItemAdapter(val items:List<Item>): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    class ItemViewHolder(private val binding: ItemLayoutBinding)
        : RecyclerView.ViewHolder(binding.root) {
            fun bind(item:Item) {
                binding.itemTitle.text=item.title
                binding.itemDescription.text=item.description
                //TODO:Load the image
            }
        }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder = ItemViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context)))

    override fun onBindViewHolder(
        holder: ItemViewHolder,
        position: Int
    ) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

}