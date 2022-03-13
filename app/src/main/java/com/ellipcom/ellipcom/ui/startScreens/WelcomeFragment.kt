package com.ellipcom.ellipcom.ui.startScreens

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ellipcom.ellipcom.R
import com.ellipcom.ellipcom.databinding.FragmentWelcomeBinding
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth


class WelcomeFragment : Fragment() {

    //setting up view binding
    private var _binding: FragmentWelcomeBinding? = null
    private val binding get() = _binding!!

    //views
    private lateinit var welcomeBtnRegister: MaterialButton
    private lateinit var welcomeBtnLogin: MaterialButton
    private lateinit var welcomeBtnContinue: MaterialButton

    private lateinit var fireAuth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)

        welcomeBtnRegister = binding.welcomeBtnRegister
        welcomeBtnLogin = binding.welcomeBtnLogin
        welcomeBtnContinue = binding.welcomeBtnContinue

        fireAuth = FirebaseAuth.getInstance()


        //welcome button register
        welcomeBtnRegister.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_registrationFragment)
        }

        //welcome button login
        welcomeBtnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_loginFragment)
        }

        //welcome button continue without login/register
        welcomeBtnContinue.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_navigation_home)
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val currentUser = fireAuth.currentUser
        if (currentUser != null) {
            findNavController().navigate(R.id.action_welcomeFragment_to_navigation_home)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}