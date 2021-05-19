package hu.bme.aut.android.smartpantry.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.smartpantry.models.Recipe
import hu.bme.aut.android.smartpantryv2.R

class RecipesAdapter(private val mRecipes: List<Recipe>) : RecyclerView.Adapter<RecipesAdapter.ViewHolder>() {

    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val nameV = listItemView.findViewById<TextView>(R.id.RecipeNameLabel)
        val categoryV = listItemView.findViewById<TextView>(R.id.RecipeCategoryLabel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val recipeFragment = inflater.inflate(R.layout.recipe_item, parent, false)
        return ViewHolder(recipeFragment)
    }

    override fun onBindViewHolder(holder: RecipesAdapter.ViewHolder, position: Int) {
        val recipe: Recipe = mRecipes.get(position)
        val nameT = holder.nameV
        val categoryT = holder.categoryV
        nameT.text = recipe.strMeal
        categoryT.text = recipe.strCategory
    }

    override fun getItemCount(): Int {
        return mRecipes.size
    }
}