package com.tumininu.staves.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.tumininu.staves.data.viewmodel.NotesViewModel
import com.tumininu.staves.db.Notes
import com.tumininu.staves.R
import com.tumininu.staves.databinding.FragmentAddNotesBinding
import kotlin.properties.Delegates


class AddNotesFragment : Fragment() {

    var binding: FragmentAddNotesBinding? = null
    lateinit var viewModel: NotesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddNotesBinding.inflate(inflater)
        viewModelSetup()
        inflateData()
        saveNote()
        toolBarClick()
        deleteNote()
        return binding?.root
    }






    override fun onDestroy() {
        binding = null
        super.onDestroy()
    }

    private fun deleteNote() {
        binding?.deleteNote?.setOnClickListener {
            viewModel.delete(binding?.etTitle?.text.toString())
            Navigation.findNavController(this.requireActivity(), R.id.nav_host)
                .popBackStack()
        }
    }

    private fun toolBarClick() {
        binding?.toolbarNote?.setNavigationOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.nav_host).navigateUp()
        }
    }

    private fun viewModelSetup() {
        viewModel = ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
            .create(NotesViewModel::class.java)
        binding?.lifecycleOwner = this.viewLifecycleOwner
    }

    private fun saveNote() {
        binding?.saveNoteButton?.setOnClickListener {
            if (binding?.etTitle?.text?.isEmpty() == true || binding?.etDescription?.text?.isEmpty() == true) {
                Toast.makeText(this.requireContext(), "Pls fill all fields", Toast.LENGTH_SHORT).show()
            } else {
                // Saves note
                val notes = Notes()

                notes.name = binding?.etTitle?.text.toString()
                notes.body = binding?.etDescription?.text.toString()

                viewModel.insert(notes)

                Navigation.findNavController(this.requireActivity(), R.id.nav_host)
                    .popBackStack()
            }
        }
    }

    private fun inflateData() {
        val args = arguments?.let { AddNotesFragmentArgs.fromBundle(it) }
        val title = args?.title
        val body = args?.body

        binding?.etTitle?.setText(title)
        binding?.etDescription?.setText(body)
    }
}