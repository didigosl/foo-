package com.technology.greenenjoyshoppingstreet.ktnewui.adapter

import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.duma.ld.baselibarary.util.Constants
import com.technology.greenenjoyshoppingstreet.R
import com.technology.greenenjoyshoppingstreet.ktnewui.model.Category
import com.technology.greenenjoyshoppingstreet.ktnewui.util.inflate
import com.technology.greenenjoyshoppingstreet.newui.view.GoodsListActivity
import kotlinx.android.synthetic.main.item_subcategory_kt.view.*

class CategoryExpandedAdapter(val list:List<Category.Data.Son>):RecyclerView.Adapter<CategoryExpandedAdapter.CategoryExpandedViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): CategoryExpandedViewHolder
            = CategoryExpandedViewHolder(parent.inflate(R.layout.item_subcategory_kt))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: CategoryExpandedViewHolder, position: Int) {
        holder.bindView(list[position])
    }

    class CategoryExpandedViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(category:Category.Data.Son){
            itemView.tvNameSub.text = category.categoryName
            itemView.setOnClickListener {
                val intent = Intent(itemView.context,GoodsListActivity::class.java)
                intent.putExtra(Constants.intent_id,category.categoryId)
                itemView.context.startActivity(intent)
            }
        }
    }
}