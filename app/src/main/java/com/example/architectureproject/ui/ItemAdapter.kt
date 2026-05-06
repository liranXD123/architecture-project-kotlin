package com.example.architectureproject.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.architectureproject.data.model.Item
import com.example.architectureproject.databinding.ItemLayoutBinding

class ItemAdapter(val items:List<Item>, val callBack: ItemListener): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {
    interface ItemListener{
        fun onItemClicked(index:Int)
        fun onItemLongClicked(index:Int)
    }

    inner class ItemViewHolder(private val binding: ItemLayoutBinding)
        : RecyclerView.ViewHolder(binding.root), View.OnClickListener, View.OnLongClickListener {

            init {
                binding.root.setOnClickListener(this)
                binding.root.setOnLongClickListener(this)
            }
        override fun onClick(p0: View?) {
            callBack.onItemClicked(bindingAdapterPosition)
        }

        override fun onLongClick(p0: View?): Boolean {
            callBack.onItemLongClicked(bindingAdapterPosition)
            return false
        }

        fun bind(item: Item) {
            binding.itemTitle.text = item.content
            binding.itemDescription.text=item.description
                //binding.itemImage.setImageURI(Uri.parse(item.photo))
            Glide.with(binding.root.context).load(item.image).circleCrop().into(binding.itemImage)            }
        }

    fun itemAt(position: Int) = items[position]

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder = ItemViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(
        holder: ItemViewHolder,
        position: Int
    ) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

}