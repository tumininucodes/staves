package com.tumininu.staves.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.tumininu.staves.data.viewmodel.NotesViewModel
import com.tumininu.staves.db.Notes
import com.tumininu.staves.R
import com.tumininu.staves.databinding.FragmentAddNotesBinding


class AddNotesFragment : Fragment() {

    var binding: FragmentAddNotesBinding? = null
    lateinit var viewModel: NotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAddNotesBinding.inflate(inflater)

        viewModel = ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
            .create(NotesViewModel::class.java)
        binding?.lifecycleOwner = this.viewLifecycleOwner

        inflateData()

        binding?.saveNoteButton?.setOnClickListener {
            // Saves note
            val notes = Notes()

            notes.name = binding?.etTitle?.text.toString()
            notes.body = binding?.etDescription?.text.toString()

            viewModel.insert(notes)

            Navigation.findNavController(this.requireActivity(), R.id.nav_host)
                .popBackStack()
        }

        binding?.toolbarNote?.setNavigationOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.nav_host).navigateUp()
        }

        binding?.deleteNote?.setOnClickListener {
            viewModel.delete(binding?.etTitle?.text.toString())
        }

        return binding?.root
    }

    private fun inflateData() {
        val args = arguments?.let { AddNotesFragmentArgs.fromBundle(it) }
        val title = args?.title
        val body = args?.body

        binding?.etTitle?.setText(title)
        binding?.etDescription?.setText(body)
    }
}