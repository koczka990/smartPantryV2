package hu.bme.aut.android.smartpantry.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.smartpantry.adapters.GroceriesAdapter
import hu.bme.aut.android.smartpantry.models.Grocery
import hu.bme.aut.android.smartpantryv2.R
import hu.bme.aut.android.smartpantryv2.adapters.ShoppingAdapter
import hu.bme.aut.android.smartpantryv2.databinding.FragmentPantryBinding
import hu.bme.aut.android.smartpantryv2.viewmodel.GroceryViewModel

class ShoppingFragment(/*private val adapter: ShoppingAdapter*/ var groceryViewModel: GroceryViewModel) : Fragment() {

    //lateinit var groceries: ArrayList<Grocery>

    lateinit var adapter: ShoppingAdapter

    //lateinit var rvGroceries: RecyclerView
    //val rvGroceries: RecyclerView = view!!.findViewById(R.id.recycler_view_pantry)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shopping, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = ShoppingAdapter(groceryViewModel)
        //rvGroceries.adapter = adapter
        //rvGroceries.layoutManager = LinearLayoutManager(activity)

        groceryViewModel = ViewModelProvider(this).get(GroceryViewModel::class.java)
        groceryViewModel.allGroceries.observe(this, {groceries ->
            adapter.submitList(groceries)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentPantryBinding.bind(view)
        //FragmentPantryBinding = binding
        val rvGroceries = binding.recyclerViewPantry
        //groceries = Grocery.createBaseList(20)
        //val adapter = GroceriesAdapter(groceries)



//        groceryViewModel = ViewModelProvider(this).get(GroceryViewModel::class.java)
//        groceryViewModel.allGroceries.observe(this, {grocer ->
//            groceries = grocer as ArrayList<Grocery>
//            adapter.notifyDataSetChanged()
//       })
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