package com.reciperartgen.burgeratorart.recyclerviev
import androidx.recyclerview.widget.DiffUtil
import com.reciperartgen.burgeratorart.entitys.ResponseApiSingleRecipe

class RecipesDiffUtill: DiffUtil.ItemCallback<ResponseApiSingleRecipe>() {
    override fun areItemsTheSame(oldItem: ResponseApiSingleRecipe, newItem: ResponseApiSingleRecipe): Boolean {
        return oldItem.title == newItem.title
    }

    override fun areContentsTheSame(oldItem: ResponseApiSingleRecipe, newItem: ResponseApiSingleRecipe): Boolean {
        return oldItem == newItem
    }
}