package com.tumininu.staves.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.tumininu.staves.R
import com.tumininu.staves.databinding.FragmentWelcomeBinding

class WelcomeFragment : Fragment() {

    var binding: FragmentWelcomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentWelcomeBinding.inflate(inflater)

        binding?.continueButton?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.nav_host)
                .navigate(R.id.noteFragment)
        }

        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

}