package com.tecx.notes

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import com.tecx.notes.databinding.ActivityNoteBinding

class NoteActivity : AppCompatActivity() {

    lateinit var noteBinding: ActivityNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Use dataBinding to inflate the view
        noteBinding = DataBindingUtil.setContentView(this, R.layout.activity_note)

        // An onclick listener is set for the hamburger icon on the action bar that slides
        // the navigation view into place when clicked
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
        finishActivity(0)
    }

    private fun hideNoteImage() {
        noteBinding.addNotesImage.visibility = View.GONE
    }
}
