package com.example.shaomai

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.product_item_layout.view.*


class ViewHolder(view:View): RecyclerView.ViewHolder(view) {

    val product_name = view.product_name
    val product_img = view.product_img
    val product_price_rent = view.product_price_rent
}