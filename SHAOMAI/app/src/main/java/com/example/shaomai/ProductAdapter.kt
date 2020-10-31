package com.example.shaomai

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ProductAdapter (val items : List<Product>, val context: Context) :
RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ViewHolder {
        val view_item = LayoutInflater.from(parent.context).inflate(R.layout.product_item_layout, parent, false)
        return ViewHolder(view_item)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.product_name?.text = items[position].product_name
        holder.product_price_rent?.text = items[position].product_price_rent.toString()
        Glide.with(context)
            .load(items[position].product_img)
            .into(holder.product_img)
    }
    override fun getItemCount(): Int {
        return items.size
    }
}
