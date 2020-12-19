package com.tecx.notes

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import com.tecx.notes.databinding.ActivityNoteBinding

class NoteActivity : AppCompatActivity() {

    lateinit var noteBinding: ActivityNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        noteBinding = DataBindingUtil.setContentView(this, R.layout.activity_note)

        noteBinding.toolbarNote.setNavigationOnClickListener {
            noteBinding.drawerLayout.openDrawer(noteBinding.navigationView)
        }

        noteBinding.fabNotesAdd.setOnClickListener {
            startActivity(Intent(this, AddNotesActivity::class.java))
            finish()
        }

        noteBinding.navigationView.setNavigationItemSelectedListener { menuItem ->
            // Handle menu item selected
            menuItem.isChecked = true
            when (menuItem.itemId) {

                R.id.backup -> {
                    Toast.makeText(this, "coming soon", Toast.LENGTH_SHORT).show()
                }

                R.id.export -> {
                    Toast.makeText(this, "coming soon", Toast.LENGTH_SHORT).show()
                }

                R.id.theme -> {
                    Toast.makeText(this, "coming soon", Toast.LENGTH_SHORT).show()
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
