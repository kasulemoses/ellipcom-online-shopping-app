package com.ellipcom.ellipcom.ui.foodAndDrinks

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ellipcom.ellipcom.R
import com.ellipcom.ellipcom.databinding.FragmentFoodAndDrinksSubCategoriesBinding
import com.google.android.material.card.MaterialCardView

class FoodAndDrinksSubCategoriesFragment : Fragment() {

    //view binding
    private var _binding: FragmentFoodAndDrinksSubCategoriesBinding? = null
    private val binding get() = _binding!!

    //views
    private lateinit var foodCardView: MaterialCardView
    private lateinit var foodContent: MaterialCardView
    private lateinit var foodLinearLayout: LinearLayout

    private lateinit var drinksCardView: MaterialCardView
    private lateinit var drinksContent: MaterialCardView
    private lateinit var drinksLinearLayout: LinearLayout

    private lateinit var foodAndDrinksLinearBack: LinearLayout

    //food views
    private lateinit var riceFood: TextView
    private lateinit var flourFood: TextView
    private lateinit var sugarFood: TextView
    private lateinit var cookingOilFood: TextView

    //drink view
    private lateinit var mineralWaterDrink: TextView
    private lateinit var fruitJuicesDrink: TextView
    private lateinit var sodaDrink: TextView
    private lateinit var energyDrink: TextView
    private lateinit var alcoholicDrink: TextView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFoodAndDrinksSubCategoriesBinding.inflate(inflater, container, false)


        initViews()
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun initViews() {
        //food view
        riceFood = binding.foodRice
        flourFood = binding.foodFlour
        sugarFood = binding.foodSugar
        cookingOilFood = binding.foodCookingOil

        clickListenerForFood()

        //drink view
        mineralWaterDrink = binding.drinkMineralWater
        fruitJuicesDrink = binding.drinkFruitJuices
        sodaDrink = binding.drinkSoda
        energyDrink = binding.drinkEnergyDrinks
        alcoholicDrink = binding.drinkAlcoholicDrinks

        clickListenerForDrink()



        foodCardView = binding.foodCard
        foodContent = binding.foodContent
        foodLinearLayout = binding.linearFood

        drinksCardView = binding.drinksCard
        drinksContent = binding.drinksContent
        drinksLinearLayout = binding.linearDrinks

        foodAndDrinksLinearBack = binding.foodAndDrinksLinearBack

        givingViewsActions()

    }

    private fun clickListenerForDrink() {

        mineralWaterDrink.setOnClickListener {
            findNavController().navigate(R.id.action_foodAndDrinksSubCategoriesFragment_to_softDrinksFragment)

        }

        fruitJuicesDrink.setOnClickListener {
            findNavController().navigate(R.id.action_foodAndDrinksSubCategoriesFragment_to_softDrinksFragment)

        }

        sodaDrink.setOnClickListener {

            findNavController().navigate(R.id.action_foodAndDrinksSubCategoriesFragment_to_softDrinksFragment)
        }

        energyDrink.setOnClickListener {
            findNavController().navigate(R.id.action_foodAndDrinksSubCategoriesFragment_to_hardDrinksFragment)

        }

        alcoholicDrink.setOnClickListener {
            findNavController().navigate(R.id.action_foodAndDrinksSubCategoriesFragment_to_hardDrinksFragment)

        }
    }

    private fun clickListenerForFood() {

        riceFood.setOnClickListener {
            findNavController().navigate(R.id.action_foodAndDrinksSubCategoriesFragment_to_riceFragment)

        }

        flourFood.setOnClickListener {
            findNavController().navigate(R.id.action_foodAndDrinksSubCategoriesFragment_to_flourFragment)

        }
        sugarFood.setOnClickListener {
            findNavController().navigate(R.id.action_foodAndDrinksSubCategoriesFragment_to_sugarFragment)

        }
        cookingOilFood.setOnClickListener {
            findNavController().navigate(R.id.action_foodAndDrinksSubCategoriesFragment_to_cookingOilsFragment)

        }
    }

    private fun givingViewsActions() {

        foodAndDrinksLinearBack.setOnClickListener {
            findNavController().navigate(R.id.action_foodAndDrinksSubCategoriesFragment_to_navigation_categories)
        }

        //food
        foodCardView.setOnClickListener {
            foodCardView.visibility = View.GONE
            foodContent.visibility = View.VISIBLE

            drinksCardView.visibility = View.VISIBLE
            drinksContent.visibility = View.GONE
        }

        foodLinearLayout.setOnClickListener {
            foodCardView.visibility = View.VISIBLE
            foodContent.visibility = View.GONE
        }

        //drinks
        drinksCardView.setOnClickListener {
            drinksCardView.visibility = View.GONE
            drinksContent.visibility = View.VISIBLE

            foodCardView.visibility = View.VISIBLE
            foodContent.visibility = View.GONE
        }

        drinksLinearLayout.setOnClickListener {
            drinksCardView.visibility = View.VISIBLE
            drinksContent.visibility = View.GONE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}