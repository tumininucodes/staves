package com.tumininu.staves.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.tumininu.staves.R
import com.tumininu.staves.databinding.FragmentAboutBinding

class AboutFragment : Fragment() {

    var binding: FragmentAboutBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentAboutBinding.inflate(inflater)

        binding?.toolbarNote?.setNavigationOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.nav_host).navigateUp()
        }


        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}