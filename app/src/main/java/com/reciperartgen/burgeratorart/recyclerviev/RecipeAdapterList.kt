package com.reciperartgen.burgeratorart.recyclerviev

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.reciperartgen.burgeratorart.R
import com.reciperartgen.burgeratorart.databinding.OneSingleRecipeBinding
import com.reciperartgen.burgeratorart.entitys.ResponseApiSingleRecipe


class RecipeAdapterList() :
    ListAdapter<ResponseApiSingleRecipe, RecipeAdapterList.HolidaysVievHolder>(RecipesDiffUtill()) {

    private var onItemClickListener: ((holiday: ResponseApiSingleRecipe) -> Unit)? = null
    private var addToFavorite: ((recipe: ResponseApiSingleRecipe) -> Unit)? = null

    class HolidaysVievHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = OneSingleRecipeBinding.bind(itemView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HolidaysVievHolder {
        LayoutInflater.from(parent.context)
            .inflate(R.layout.one_single_recipe, parent, false).also {
                return HolidaysVievHolder(it)
            }
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: HolidaysVievHolder, position: Int) {
        val currentItem = getItem(position)
        val currentImg = listImages.random()
        holder.binding.apply {
            tvName.text = currentItem._title
            imgPhotoFood.setImageResource(currentImg)
            root.setOnClickListener {
                onItemClickListener?.invoke(currentItem)
            }
            imgAddToFavorite.setOnClickListener {
                addToFavorite?.invoke(currentItem)
                Log.d("favorit", "pressed ${currentItem._title}")
                Snackbar.make(
                    this.root,
                    "Added to favorite ♥♥♥",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
    }

    fun setOnItemClickListener(listener: (holidayName: ResponseApiSingleRecipe) -> Unit) {
        onItemClickListener = listener
    }

    fun setOnItemClickListenerHeart(listener: (holidayName: ResponseApiSingleRecipe) -> Unit) {
        addToFavorite = listener
    }

    val listImages = listOf(
        R.drawable.random1,
        R.drawable.random2,
        R.drawable.random3,
        R.drawable.random4,
        R.drawable.random5,

    )
}