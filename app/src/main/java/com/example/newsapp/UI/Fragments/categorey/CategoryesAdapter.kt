package com.example.newsapp.UI.Fragments.categorey

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.databinding.ItemCategoryBinding

class CategoryesAdapter(val categores:MutableList<CategoryModel>?)
    : RecyclerView.Adapter<CategoryesAdapter.ViewHolder>(){
    class ViewHolder(val binding: ItemCategoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = categores?.size ?: 0


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val category = categores?.get(position)
       holder.binding.container.setBackgroundResource(category?.backGroundColor?:0)
        holder.binding.categoryName.text = category?.tittle
        holder.binding.category.setImageResource(category?.image?:0)
        category?.let {
            holder.itemView.setOnClickListener{
                setOnCategoryClick?.onItemClick(position,category)
            }
        }
    }
    var setOnCategoryClick: onClick?=null
    fun interface onClick{
        fun onItemClick(position: Int,categoryModel: CategoryModel)
    }
}