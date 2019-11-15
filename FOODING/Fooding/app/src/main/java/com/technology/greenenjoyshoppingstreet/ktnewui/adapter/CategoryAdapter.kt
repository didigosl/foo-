package com.technology.greenenjoyshoppingstreet.ktnewui.adapter

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.view.animation.LayoutAnimationController
import android.view.animation.RotateAnimation
import android.widget.ExpandableListAdapter
import com.bumptech.glide.Glide
import com.technology.greenenjoyshoppingstreet.R

import com.technology.greenenjoyshoppingstreet.ktnewui.model.Category
import com.technology.greenenjoyshoppingstreet.ktnewui.util.inflate
import kotlinx.android.synthetic.main.item_category_kt.view.*

class CategoryAdapter(val data: List<Category.Data>): RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): CategoryViewHolder = CategoryViewHolder(parent.inflate(R.layout.item_category_kt))

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bindView(data[position])
    }

    class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var isClicked:Boolean = false

        val INITIAL_POSITION = 0.0f
        val ROTATED_POSITION = 90f

        fun bindView(category : Category.Data){

            itemView.tvNameCategory.text = category.categoryName
            itemView.ic_arrow_right.rotation = INITIAL_POSITION

            Glide
                    .with(itemView.context)
                    .load(category.categoryCover)
                    .into(itemView.ivCategoryItem)

            category.sons?.let {
                createRecycler(it)
                itemView.setOnClickListener {
                    if (isClicked) {
                        itemView.recyclerSub.visibility = View.GONE
                        itemView.ic_arrow_right.rotation = INITIAL_POSITION
                        rotateArrow(true)
                        runAnim()
                        isClicked = false
                    } else {
                        itemView.recyclerSub.visibility = View.VISIBLE
                        itemView.ic_arrow_right.rotation = ROTATED_POSITION
                        rotateArrow(false)
                        runAnim()
                        isClicked = true
                    }
                }
            }
        }

        fun createRecycler(subCategory: List<Category.Data.Son>){
            val adapter = CategoryExpandedAdapter(subCategory)
            itemView.recyclerSub.layoutManager = LinearLayoutManager(itemView.context)
            itemView.recyclerSub.adapter = adapter
            //runAnimation()

        }

        fun runAnim(){
            val controller : LayoutAnimationController = AnimationUtils.loadLayoutAnimation(itemView.context,R.anim.layout_fall_down)
            itemView.recyclerSub.layoutAnimation = controller
            (itemView.recyclerSub.adapter as CategoryExpandedAdapter).notifyDataSetChanged()
            itemView.recyclerSub.scheduleLayoutAnimation()

        }

        fun rotateArrow(expanded:Boolean){
            val rotateAnimation: RotateAnimation = if (expanded) { // rotate clockwise
                RotateAnimation(ROTATED_POSITION,
                        INITIAL_POSITION,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f)
            } else { // rotate counterclockwise
                RotateAnimation(-1 * ROTATED_POSITION,
                        INITIAL_POSITION,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f)
            }

            rotateAnimation.duration = 200
            rotateAnimation.fillAfter = true
            itemView.ic_arrow_right.startAnimation(rotateAnimation)

        }
    }
}