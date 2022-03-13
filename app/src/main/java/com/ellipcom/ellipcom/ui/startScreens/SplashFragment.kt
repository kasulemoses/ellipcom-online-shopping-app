package com.ellipcom.ellipcom.ui.startScreens

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ellipcom.ellipcom.R
import com.ellipcom.ellipcom.databinding.FragmentSplashBinding


class SplashFragment : Fragment() {
    private lateinit var progressBar: ProgressBar
    private lateinit var imageSplash: ImageView

    private var _binding: FragmentSplashBinding? = null
    private val binding get() = _binding!!

    //firebase


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)

        progressBar = binding.progressBarSplash
        imageSplash = binding.imageSplash
        isNetworkConnected()

        // Inflate the layout for this fragment
        return binding.root
    }


    fun isNetworkConnected() {

        Handler(Looper.getMainLooper()).postDelayed({
            findNavController().navigate(R.id.action_splashFragment_to_welcomeFragment)

        }, 3000)


    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}