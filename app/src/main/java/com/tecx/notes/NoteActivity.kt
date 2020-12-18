package com.tecx.notes

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import com.tecx.notes.databinding.ActivityNoteBinding

class NoteActivity : AppCompatActivity() {

    lateinit var noteBinding: ActivityNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        noteBinding = DataBindingUtil.setContentView(this, R.layout.activity_note)
        setContentView(noteBinding.root)

        noteBinding.toolbarNote.setNavigationOnClickListener {
            noteBinding.drawerLayout.openDrawer(GravityCompat.START)
        }

        noteBinding.fabNotesAdd.setOnClickListener {
            startActivity(Intent(this, AddNotesActivity::class.java))
            finish()
        }

        noteBinding.navigationView.setNavigationItemSelectedListener { menuItem ->
            // Handle menu item selected
            menuItem.isChecked = true
            noteBinding.drawerLayout.closeDrawer(-1)
            true
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishActivity(0)
    }

    private fun hideNoteImage() {
        noteBinding.addNotesImage.visibility = View.GONE
    }
}
