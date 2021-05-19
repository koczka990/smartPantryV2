package hu.bme.aut.android.smartpantryv2.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.smartpantry.adapters.GroceriesAdapter
import hu.bme.aut.android.smartpantry.models.Grocery
import hu.bme.aut.android.smartpantryv2.R

class ShoppingAdapter (private val mGroceries: List<Grocery>) : RecyclerView.Adapter<ShoppingAdapter.ViewHolder>()
{
    //private lateinit var binding : PantryItemBinding

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
        val nameV = listItemView.findViewById<TextView>(R.id.grocery_name)
        val quantityV = listItemView.findViewById<EditText>(R.id.grocery_quantity)
        val measurementV = listItemView.findViewById<TextView>(R.id.grocery_measure)
    }

    // ... constructor and member variables
    // Usually involves inflating a layout from XML and returning the holder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val pantryFragment = inflater.inflate(R.layout.pantry_item, parent, false)
        // Return a new holder instance
        return ViewHolder(pantryFragment)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(viewHolder: ShoppingAdapter.ViewHolder, position: Int) {
        // Get the data model based on position
        val grocery: Grocery = mGroceries.get(position)
        // Set item views based on your views and data model
        val textView1 = viewHolder.nameV
        textView1.text = grocery.name
        val textView2 = viewHolder.quantityV
        textView2.setText(grocery.toBuy.toString())
        val textView3 = viewHolder.measurementV
        textView3.text = grocery.measreIn
    }

    // Returns the total count of items in the list
    override fun getItemCount(): Int {
        return mGroceries.size
    }
}