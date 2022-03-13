package com.ellipcom.ellipcom.ui.navigationProfile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ellipcom.ellipcom.R

class BottomNavigationProfileFragment : Fragment() {

    companion object {
        fun newInstance() = BottomNavigationProfileFragment()
    }

    private lateinit var viewModel: BottomNavigationProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_navigation_profile_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BottomNavigationProfileViewModel::class.java)
        // TODO: Use the ViewModel
    }


}