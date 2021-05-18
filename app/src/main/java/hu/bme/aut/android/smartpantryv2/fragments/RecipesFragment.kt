package hu.bme.aut.android.smartpantry.fragments

import android.os.Bundle
import android.renderscript.ScriptGroup
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.android.smartpantry.adapters.RecipesAdapter
import hu.bme.aut.android.smartpantry.models.Recipe
import hu.bme.aut.android.smartpantry.network.RecipeInteractor
import hu.bme.aut.android.smartpantryv2.R
import hu.bme.aut.android.smartpantryv2.databinding.FragmentRecipesBinding

class RecipesFragment : Fragment() {

    //lateinit var recipes: ArrayList<Recipe>
    //private var adapter: ImagesAdapter? = null
    private var adapter: RecipesAdapter? = null
    //lateinit var adapter: RecipesAdapter
    //private lateinit var binding: ActivityMainBinding
    private lateinit var binding: FragmentRecipesBinding
    //lateinit var binding = Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recipes, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRecipesBinding.bind(view)
        val rvRecipes = binding.recyclerViewRecipes
        //recipes = Recipes // todo itt kell gettelni a recepteket a netrol
        loadRecipes()
        /////
        //adapter = RecipesAdapter(recipes)
        //rvRecipes.adapter = adapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun loadRecipes() {
        val recipeInteractor = RecipeInteractor()
        recipeInteractor.getRecipes(onSuccess = this::showRecipes, onError = this::showError)
    }

    private fun showRecipes(recipes: List<Recipe>) {

        Log.d("SHOWRESULT", "showResult()")
       adapter = RecipesAdapter(recipes)
        val rvRecipes = binding.recyclerViewRecipes
       rvRecipes.adapter = adapter
        rvRecipes.layoutManager = LinearLayoutManager(activity)
       //binding.srlImages.isRefreshing = false
    }

    private fun showError(e: Throwable) {
        e.printStackTrace()
        Log.d("SHOWERROR", "showError()")
        //binding.srlImages.isRefreshing = false
    }

}