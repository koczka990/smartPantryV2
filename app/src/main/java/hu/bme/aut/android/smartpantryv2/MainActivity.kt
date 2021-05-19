package hu.bme.aut.android.smartpantryv2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import hu.bme.aut.android.smartpantry.adapters.GroceriesAdapter
import hu.bme.aut.android.smartpantry.fragments.PantryFragment
import hu.bme.aut.android.smartpantry.fragments.RecipesFragment
import hu.bme.aut.android.smartpantry.fragments.ShoppingFragment
import hu.bme.aut.android.smartpantry.models.Grocery
import hu.bme.aut.android.smartpantryv2.adapters.ShoppingAdapter
import hu.bme.aut.android.smartpantryv2.databinding.ActivityMainBinding
import hu.bme.aut.android.smartpantryv2.fragments.GroceryCreateFragment
import hu.bme.aut.android.smartpantryv2.viewmodel.GroceryViewModel

class MainActivity : AppCompatActivity(), GroceryCreateFragment.GroceryCreatedListener {

    private lateinit var binding : ActivityMainBinding

    lateinit var groceries: ArrayList<Grocery>
    lateinit var groceryViewModel: GroceryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //////////////// IDE jon most a proba

        ////////////////
        groceries = Grocery.createBaseList(20)
        val sAdapter = ShoppingAdapter(groceries)
        val gAdapter = GroceriesAdapter(groceries)
        val PantryFragment = PantryFragment(groceries, gAdapter)
        val RecipesFragment = RecipesFragment()
        val ShoppingFragment = ShoppingFragment(groceries, sAdapter)

        /////////////
        groceryViewModel = ViewModelProvider(this).get(GroceryViewModel::class.java)
        groceryViewModel.allGroceries.observe(this, {grocer ->
            groceries = grocer as ArrayList<Grocery>
            gAdapter.notifyDataSetChanged()
            sAdapter.notifyDataSetChanged()
        })
        //////////////////

        makeCurrentFragment(PantryFragment)

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.pantry_page -> {
                    // Respond to navigation item 1 click
                    makeCurrentFragment(PantryFragment)
                }
                R.id.recipes_page -> {
                    // Respond to navigation item 2 click
                    //itt most siman atmegy es csak vissza gombbal lehet tovabb menni
                    //val intent = Intent(this, RecipeListActivity::class.java).apply {}
                    //startActivity(intent)
                    //ez a fragmentes
                    makeCurrentFragment(RecipesFragment)
                }
                R.id.list_page -> {
                    // Respond to navigation item 2 click
                    makeCurrentFragment(ShoppingFragment)
                }
            }
            true

        }

    }

    private fun makeCurrentFragment(fragment: Fragment) =
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fl_wrapper, fragment)
                commit()
            }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_grocery, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.itemCreateGrocery){
            //TODO implement a creation fragment than create new grocery
            val groceryCreateFragment = GroceryCreateFragment()
            groceryCreateFragment.show(supportFragmentManager, "TAG")
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onGroceryCreated(grocery: Grocery){
        groceryViewModel.insert(grocery)
    }

}