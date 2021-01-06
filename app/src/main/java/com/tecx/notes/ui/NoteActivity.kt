package com.tecx.notes.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.tecx.notes.R
import com.tecx.notes.adapter.NoteAdapter
import com.tecx.notes.databinding.ActivityNoteBinding
import com.tecx.notes.databinding.RecyclerViewChildBinding
import com.tecx.notes.db.DataBaseHandler
import com.tecx.notes.db.Notes

class NoteActivity : AppCompatActivity() {

    private lateinit var noteBinding: ActivityNoteBinding
    private lateinit var dbHandler: DataBaseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        noteBinding = DataBindingUtil.setContentView(this, R.layout.activity_note)

        val isFirstRun: Boolean =
            getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE).getBoolean("isFirstRun", true)

        if (!isFirstRun) {
            hideNoteImage()
        }

        getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE).edit()
            .putBoolean("isFirstRun", false).apply()

        dbHandler = DataBaseHandler(this)
        noteBinding.notesRecyclerView?.layoutManager =
            StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)
        noteBinding.notesRecyclerView?.addItemDecoration(SpacingForRecyclerChild(8))
        refreshList()

        noteBinding.toolbarNote.setNavigationOnClickListener {
            noteBinding.drawerLayout.openDrawer(noteBinding.navigationView)
        }

        noteBinding.fabNotesAdd.setOnClickListener {
            startActivity(Intent(this, AddNotesActivity::class.java))
            finish()
        }

        noteBinding.navigationView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            when (menuItem.itemId) {
                R.id.theme -> {
                    AppCompatDelegate.MODE_NIGHT_YES
                }
                R.id.about -> {
                    startActivity(Intent(this, AboutActivity::class.java))
                    finish()
                }
            }
            noteBinding.drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
        finish()
    }

    private fun refreshList() {
        noteBinding.notesRecyclerView?.adapter =
            NoteAdapter(dbHandler.getNotes(), this)
    }

    override fun onResume() {
        super.onResume()
        refreshList()
    }

    private fun hideNoteImage() {
        noteBinding.addNotesImage.visibility = View.GONE
    }

}