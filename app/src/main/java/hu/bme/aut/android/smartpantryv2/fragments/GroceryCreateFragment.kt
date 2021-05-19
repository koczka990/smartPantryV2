package hu.bme.aut.android.smartpantryv2.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import hu.bme.aut.android.smartpantry.models.Grocery
import hu.bme.aut.android.smartpantryv2.R
import hu.bme.aut.android.smartpantryv2.databinding.FragmentGroceryCreateBinding
import java.lang.ClassCastException
import kotlin.random.Random


class GroceryCreateFragment : DialogFragment() {

    private lateinit var listener: GroceryCreatedListener
    private lateinit var binding: FragmentGroceryCreateBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)

        try{
            listener = if (targetFragment != null){
                targetFragment as GroceryCreatedListener
            }else{
                activity as GroceryCreatedListener
            }
        }catch (e: ClassCastException){
            throw RuntimeException(e)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentGroceryCreateBinding.inflate(inflater, container, false)
        dialog?.setTitle("New Grocery")
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnCreate.setOnClickListener{
            listener.onGroceryCreated(Grocery(
                id = Random.nextInt(),
                name = binding.editTextGroceryName.text.toString(),
                quantity = 0,
                toBuy = 0,
                measreIn = "g" //TODO atallitani hogy be lehessen allitani
            ))
            dismiss()
        }
        //val id: Int, val name: String, val quantity: Int, val toBuy: Int, val measreIn: String

        binding.btnCancel.setOnClickListener{
            dismiss()
        }
    }

    interface GroceryCreatedListener{
        fun onGroceryCreated(grocery: Grocery)
    }

}