package com.tumininu.staves.ui.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputBinding
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.tumininu.staves.R
import com.tumininu.staves.data.viewmodel.NotesViewModel
import com.tumininu.staves.databinding.FragmentNoteBinding
import com.tumininu.staves.ui.adapter.NoteAdapter
import com.tumininu.staves.util.SpacingForRecyclerChild


class NoteFragment : Fragment() {

    var binding: FragmentNoteBinding? = null
    lateinit var viewModel: NotesViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteBinding.inflate(inflater)
        viewModelSetup()
        recyclerViewSetup()
        refreshList()
        openDrawer()
        navigationItemClicks()
        fabClick()
        return binding?.root
    }

    override fun onResume() {
        super.onResume()
        hideKeyboard()
    }

    private fun Fragment.hideKeyboard() {
        view?.let { activity?.hideKeyboard(it) }
    }

    private fun Context.hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }


    private fun refreshList() {
        binding?.lifecycleOwner?.let {
            viewModel.getAllNotes().observe(it, Observer { list ->
                binding?.notesRecyclerView?.adapter = NoteAdapter(list, this.requireContext())
                binding?.notesRecyclerView?.adapter?.notifyDataSetChanged()
                if (list.isEmpty()) {
                    binding?.addNotesImage?.visibility = View.VISIBLE
                } else {
                    binding?.addNotesImage?.visibility = View.GONE
                    binding?.fabNotesAdd?.visibility = View.VISIBLE
                }
            })
        }

    }

    private fun navigationItemClicks() {
        binding?.navigationView?.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = false
            when (menuItem.itemId) {

                R.id.dark_theme -> {
                    this.activity?.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)?.edit()
                        ?.putBoolean("isDarkOn", true)?.apply()
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }

                R.id.light_theme -> {
                    this.activity?.getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE)?.edit()
                        ?.putBoolean("isDarkOn", false)?.apply()
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }

                R.id.about -> {
                    Navigation.findNavController(this.requireActivity(), R.id.nav_host)
                        .navigate(NoteFragmentDirections.actionNoteFragmentToAboutFragment())
                }
            }

            binding?.drawerLayout?.closeDrawer(GravityCompat.START)
            true
        }

    }

    private fun openDrawer() {
        binding?.toolbarNote?.setNavigationOnClickListener {
            binding?.drawerLayout?.open()
        }
    }

    private fun recyclerViewSetup() {
        binding?.notesRecyclerView?.layoutManager = StaggeredGridLayoutManager(
            2, LinearLayoutManager.VERTICAL
        )
        // Spacing for ViewHolders in RecyclerView
        binding?.notesRecyclerView?.addItemDecoration(SpacingForRecyclerChild(8))
        binding?.notesRecyclerView?.layoutManager =
            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        binding?.notesRecyclerView?.setHasFixedSize(true)
    }

    private fun fabClick() {
        binding?.fabNotesAdd?.setOnClickListener {
            Navigation.findNavController(this.requireActivity(), R.id.nav_host).navigate(
                NoteFragmentDirections.actionNoteFragmentToAddNotesFragment("", "")
            )
        }
    }

    private fun viewModelSetup() {
        viewModel = ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
            .create(NotesViewModel::class.java)
        binding?.lifecycleOwner = this.viewLifecycleOwner
    }


}