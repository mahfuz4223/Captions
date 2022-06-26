package com.dark.captions.Adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.dark.captions.AllCaption
import com.dark.captions.MainActivity
import com.dark.captions.Model.CatagoryModel
import com.dark.captions.Start
import com.dark.captions.databinding.ItemCatagoryBinding

class CategoryAdapter(val mainActivity: MainActivity, val list: ArrayList<CatagoryModel>) : RecyclerView.Adapter<CategoryAdapter.CatViewHolder>() {

    class CatViewHolder(val binding: ItemCatagoryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        return CatViewHolder(ItemCatagoryBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        holder.binding.itemText.text = list[position].name.toString()
        holder.binding.root.setOnClickListener {
            val intent=Intent(mainActivity, AllCaption::class.java)
            intent.putExtra("id",list[position].id)
            intent.putExtra("name",list[position].name)

            mainActivity.startActivity(intent)
        }

    }

    override fun getItemCount() = list.size
}