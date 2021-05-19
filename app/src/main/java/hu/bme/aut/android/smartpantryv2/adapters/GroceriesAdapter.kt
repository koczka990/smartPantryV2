package hu.bme.aut.android.smartpantry.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.smartpantry.fragments.PantryFragment
import hu.bme.aut.android.smartpantry.models.Grocery
import hu.bme.aut.android.smartpantryv2.R
import hu.bme.aut.android.smartpantryv2.databinding.PantryItemBinding

class GroceriesAdapter : ListAdapter<Grocery, GroceriesAdapter.ViewHolder>(itemCallback)
{

    companion object{
        object itemCallback : DiffUtil.ItemCallback<Grocery>(){
            override fun areItemsTheSame(oldItem: Grocery, newItem: Grocery): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Grocery, newItem: Grocery): Boolean {
                return false
                //return oldItem == newItem // itt valami hiba van
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        PantryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        val grocery = this.getItem(position)

        holder.grocery = grocery

        holder.binding.groceryName.text = grocery.name
        holder.binding.groceryQuantity.setText(grocery.quantity.toString())
        holder.binding.groceryMeasure.text = grocery.measreIn
    }

    inner class ViewHolder(val binding: PantryItemBinding) : RecyclerView.ViewHolder(binding.root){
        var grocery: Grocery? = null
        val nameV = binding.groceryName
        val quantityV = binding.groceryQuantity
        val measurementV = binding.groceryMeasure
    }

//    //private lateinit var binding : PantryItemBinding
//
//    // Provide a direct reference to each of the views within a data item
//    // Used to cache the views within the item layout for fast access
//    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
//        val nameV = listItemView.findViewById<TextView>(R.id.grocery_name)
//        val quantityV = listItemView.findViewById<EditText>(R.id.grocery_quantity)
//        val measurementV = listItemView.findViewById<TextView>(R.id.grocery_measure)
//        //val quantityV = binding.groceryQuantity
//       // val measurementV = binding.groceryMeasure
//    }
//
//    // ... constructor and member variables
//    // Usually involves inflating a layout from XML and returning the holder
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceriesAdapter.ViewHolder {
//        val context = parent.context
//        val inflater = LayoutInflater.from(context)
//        // Inflate the custom layout
//        val pantryFragment = inflater.inflate(R.layout.pantry_item, parent, false)
//        // Return a new holder instance
//        return ViewHolder(pantryFragment)
//    }
//
//    // Involves populating data into the item through holder
//    override fun onBindViewHolder(viewHolder: GroceriesAdapter.ViewHolder, position: Int) {
//        // Get the data model based on position
//        val grocery: Grocery = mGroceries.get(position)
//        // Set item views based on your views and data model
//        val textView1 = viewHolder.nameV
//        textView1.text = grocery.name
//        val textView2 = viewHolder.quantityV
//        textView2.setText(grocery.quantity.toString())
//        //textView2. //TODO valahogy mashogy megjeleniteni az adatot az adatot ha van jobb
//        val textView3 = viewHolder.measurementV
//        textView3.text = grocery.measreIn
//    }
//
//    // Returns the total count of items in the list
//    override fun getItemCount(): Int {
//        return mGroceries.size
//    }
}