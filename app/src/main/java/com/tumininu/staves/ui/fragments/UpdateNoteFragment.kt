package com.tumininu.staves.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.tumininu.staves.R
import com.tumininu.staves.data.viewmodel.NotesViewModel
import com.tumininu.staves.databinding.FragmentUpdateNoteBinding
import com.tumininu.staves.db.Notes
import kotlin.properties.Delegates

class UpdateNoteFragment : Fragment() {

    var binding: FragmentUpdateNoteBinding? = null
    lateinit var viewModel: NotesViewModel
    var noteID by Delegates.notNull<Long>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateNoteBinding.inflate(inflater)
        viewModelSetup()
        inflateData()
        updateNote()
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

    private fun updateNote() {
        binding?.saveNoteButton?.setOnClickListener {
            if (binding?.etTitle?.text?.isEmpty() == true || binding?.etDescription?.text?.isEmpty() == true) {
                Toast.makeText(this.requireContext(), "Pls fill all fields", Toast.LENGTH_SHORT)
                    .show()
            } else {
                // Saves note
                val notes = Notes()

                notes.name = binding?.etTitle?.text.toString()
                notes.body = binding?.etDescription?.text.toString()
                notes.id = noteID

                viewModel.update(notes)

                Navigation.findNavController(this.requireActivity(), R.id.nav_host)
                    .popBackStack()
            }

        }
    }

    private fun viewModelSetup() {
        viewModel = ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
            .create(NotesViewModel::class.java)
        binding?.lifecycleOwner = this.viewLifecycleOwner
    }

    private fun inflateData() {
        val args = arguments?.let { UpdateNoteFragmentArgs.fromBundle(it) }
        val title = args?.title
        val body = args?.body
        noteID = args?.id!!

        binding?.etTitle?.setText(title)
        binding?.etDescription?.setText(body)
    }

}