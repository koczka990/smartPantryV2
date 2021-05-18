package hu.bme.aut.android.smartpantryv2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.renderscript.ScriptGroup
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import hu.bme.aut.android.smartpantry.fragments.PantryFragment
import hu.bme.aut.android.smartpantry.fragments.RecipesFragment
import hu.bme.aut.android.smartpantry.fragments.ShoppingFragment
import hu.bme.aut.android.smartpantryv2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //////////////// IDE jon most a proba
        ////////////////

        val PantryFragment = PantryFragment()
        val RecipesFragment = RecipesFragment()
        val ShoppingFragment = ShoppingFragment()

        makeCurrentFragment(PantryFragment)

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            when(it.itemId) {
                R.id.pantry_page -> {
                    // Respond to navigation item 1 click
                    makeCurrentFragment(PantryFragment)
                }
                R.id.recipes_page -> {
                    // Respond to navigation item 2 click
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

}