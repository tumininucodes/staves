package com.tumininu.staves.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
        // Inflate the layout for this fragment
        binding = FragmentUpdateNoteBinding.inflate(inflater)

        viewModel = ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
            .create(NotesViewModel::class.java)
        binding?.lifecycleOwner = this.viewLifecycleOwner

        inflateData()

        binding?.saveNoteButton?.setOnClickListener {
            // Saves note
            val notes = Notes()

            notes.name = binding?.etTitle?.text.toString()
            notes.body = binding?.etDescription?.text.toString()
            notes.id = noteID

            viewModel.update(notes)

            Navigation.findNavController(this.requireActivity(), R.id.nav_host)
                .popBackStack()
        }

        binding?.toolbarNote?.setNavigationOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.nav_host).navigateUp()
        }

        binding?.deleteNote?.setOnClickListener {
            viewModel.delete(binding?.etTitle?.text.toString())
            Navigation.findNavController(this.requireActivity(), R.id.nav_host)
                .popBackStack()
        }


        return binding?.root
    }

    override fun onDestroy() {
        binding = null
        super.onDestroy()
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