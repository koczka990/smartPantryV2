package hu.bme.aut.android.smartpantry.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import hu.bme.aut.android.smartpantry.adapters.GroceriesAdapter
import hu.bme.aut.android.smartpantry.models.Grocery
import hu.bme.aut.android.smartpantryv2.R
import hu.bme.aut.android.smartpantryv2.databinding.FragmentPantryBinding
import hu.bme.aut.android.smartpantryv2.viewmodel.GroceryViewModel

class PantryFragment : Fragment() {

   // private var pantryFragmentBinding: FragmentPantryBinding? = null
    lateinit var groceries: ArrayList<Grocery>

    private lateinit var groceryViewModel: GroceryViewModel
    private lateinit var rvGroceries: GroceriesAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pantry, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentPantryBinding.bind(view)
        //FragmentPantryBinding = binding
        val rvGroceries = binding.recyclerViewPantry
        groceries = Grocery.createBaseList(20)
        val adapter = GroceriesAdapter()

        groceryViewModel = ViewModelProvider(this).get(GroceryViewModel::class.java)
        groceryViewModel.allGroceries.observe(this, {groceries ->
            adapter.submitList(groceries)
        })

        rvGroceries.adapter = adapter
        rvGroceries.layoutManager = LinearLayoutManager(activity)
    }

    override fun onDestroyView() {
        // Consider not storing the binding instance in a field
        // if not needed.
        //FragmentPantryBinding = null
        super.onDestroyView()
    }
}