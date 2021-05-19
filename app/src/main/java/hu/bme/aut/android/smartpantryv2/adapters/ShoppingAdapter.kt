package hu.bme.aut.android.smartpantryv2.adapters

import android.annotation.SuppressLint
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.smartpantry.models.Grocery
import hu.bme.aut.android.smartpantryv2.MainActivity
import hu.bme.aut.android.smartpantryv2.databinding.PantryItemBinding
import hu.bme.aut.android.smartpantryv2.viewmodel.GroceryViewModel
import kotlinx.coroutines.MainScope

class ShoppingAdapter(var groceryViewModel: GroceryViewModel) : ListAdapter<Grocery, ShoppingAdapter.ViewHolder>(itemCallBack){

    //private lateinit var listener: GroceryUpdatedListener

    companion object{
        object itemCallBack : DiffUtil.ItemCallback<Grocery>(){
            override fun areItemsTheSame(oldItem: Grocery, newItem: Grocery): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Grocery, newItem: Grocery): Boolean {
                return oldItem == newItem
                //return false
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        PantryItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val grocery = this.getItem(position)

        holder.grocery = grocery

        holder.binding.groceryName.text = grocery.name
        holder.binding.groceryQuantity.setText(grocery.toBuy.toString())
        holder.binding.groceryMeasure.text = grocery.measreIn



        holder.binding.groceryQuantity.addTextChangedListener(object: TextWatcher{
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                groceryViewModel.updater(
                    Grocery(
                        id = grocery.id,
                        name = grocery.name,
                        quantity = grocery.quantity,
                        toBuy = holder.binding.groceryQuantity.text.toString().toInt(),
                        measreIn = grocery.measreIn
                )
                )
            }
        })
    }


    inner class ViewHolder(val binding: PantryItemBinding) : RecyclerView.ViewHolder(binding.root){
        var grocery: Grocery? = null

    }

//    interface GroceryUpdatedListener{
//        fun onGroceryUpdated(grocery: Grocery)
//    }

}

//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.EditText
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import hu.bme.aut.android.smartpantry.adapters.GroceriesAdapter
//import hu.bme.aut.android.smartpantry.models.Grocery
//import hu.bme.aut.android.smartpantryv2.R
//
//class ShoppingAdapter (private val mGroceries: List<Grocery>) : RecyclerView.Adapter<ShoppingAdapter.ViewHolder>()
//{
//    //private lateinit var binding : PantryItemBinding
//
//    // Provide a direct reference to each of the views within a data item
//    // Used to cache the views within the item layout for fast access
//    inner class ViewHolder(listItemView: View) : RecyclerView.ViewHolder(listItemView) {
//        val nameV = listItemView.findViewById<TextView>(R.id.grocery_name)
//        val quantityV = listItemView.findViewById<EditText>(R.id.grocery_quantity)
//        val measurementV = listItemView.findViewById<TextView>(R.id.grocery_measure)
//    }
//
//    // ... constructor and member variables
//    // Usually involves inflating a layout from XML and returning the holder
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingAdapter.ViewHolder {
//        val context = parent.context
//        val inflater = LayoutInflater.from(context)
//        // Inflate the custom layout
//        val pantryFragment = inflater.inflate(R.layout.pantry_item, parent, false)
//        // Return a new holder instance
//        return ViewHolder(pantryFragment)
//    }
//
//    // Involves populating data into the item through holder
//    override fun onBindViewHolder(viewHolder: ShoppingAdapter.ViewHolder, position: Int) {
//        // Get the data model based on position
//        val grocery: Grocery = mGroceries.get(position)
//        // Set item views based on your views and data model
//        val textView1 = viewHolder.nameV
//        textView1.text = grocery.name
//        val textView2 = viewHolder.quantityV
//        textView2.setText(grocery.toBuy.toString())
//        val textView3 = viewHolder.measurementV
//        textView3.text = grocery.measreIn
//    }
//
//    // Returns the total count of items in the list
//    override fun getItemCount(): Int {
//        return mGroceries.size
//    }
//}