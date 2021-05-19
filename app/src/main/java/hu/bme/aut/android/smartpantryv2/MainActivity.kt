package hu.bme.aut.android.smartpantryv2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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

    //lateinit var groceries: List<Grocery>
    lateinit var groceryViewModel: GroceryViewModel

    //lateinit var pantryFragment: PantryFragment
    //lateinit var shoppingFragment: ShoppingFragment
    //private lateinit var groceriesAdapter: GroceriesAdapter
    //private lateinit var shoppingAdapter: ShoppingAdapter

    private lateinit var pantryFragment: PantryFragment
    private lateinit var shoppingFragment: ShoppingFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        groceryViewModel = ViewModelProvider(this).get(GroceryViewModel::class.java)
        val RecipesFragment = RecipesFragment()
        pantryFragment = PantryFragment(groceryViewModel)
        shoppingFragment = ShoppingFragment(groceryViewModel)
        setSupportActionBar(binding.toolbar)
        binding.toolbar.title = title

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.pantry_page -> {
                    // Respond to navigation item 1 click
                    makeCurrentFragment(pantryFragment)
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
                    makeCurrentFragment(shoppingFragment)
                }
            }
            true
        }

        //setupRecyclerViews()
        makeCurrentFragment(pantryFragment)
    }

//    private fun setupRecyclerViews(){
//        groceriesAdapter = GroceriesAdapter()
//        pantryFragment.rvGroceries.adapter = groceriesAdapter
//
//        shoppingAdapter = ShoppingAdapter()
//        //shoppingFragment.view!!.findViewById<RecyclerView>(R.id.recycler_view_pantry).adapter = shoppingAdapter
//        //shoppingFragment.view!!.findViewById<RecyclerView>(R.id.recycler_view_pantry).layoutManager = LinearLayoutManager(this)
//        shoppingFragment.rvGroceries.adapter = shoppingAdapter
//    }

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

//    fun onGroceryUpdated(grocery: Grocery){
//        groceryViewModel.update(grocery)
//    }


}