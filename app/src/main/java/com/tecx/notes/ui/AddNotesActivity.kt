package com.tecx.notes.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.tecx.notes.db.DataBaseHandler
import com.tecx.notes.db.Notes
import com.tecx.notes.R
import com.tecx.notes.databinding.ActivityAddNotesBinding

class AddNotesActivity : AppCompatActivity() {

    // Objects to be used later are initialised here
    private lateinit var addNotesBinding: ActivityAddNotesBinding
    private lateinit var dbHandler: DataBaseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        dbHandler = DataBaseHandler(this)
        addNotesBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_notes)

        addNotesBinding.addNotesToolbar.setNavigationOnClickListener {
            val intent = Intent(this, NoteActivity::class.java)
            startActivity(intent)
            finish()
        }

        addNotesBinding.saveNoteButton.setOnClickListener {
            if (addNotesBinding.titleEditText.editText?.text?.isNotEmpty()!!) {
                val note = Notes()
                note.name = addNotesBinding.etTitle.text.toString()
                dbHandler.addNote(note)

                val intent = Intent(this, NoteActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Note is empty, pls add a note", Toast.LENGTH_SHORT)
                    .show()
            }
        }

    }

    fun updateNote(note: Notes) {
        addNotesBinding.titleEditText.editText?.setText(note.name)
        note.name = addNotesBinding.titleEditText.editText?.text.toString()
        dbHandler.updateNote(note)
    }

    override fun onBackPressed() {
        overridePendingTransition(R.anim.left_to_right, R.anim.left_to_right)
        super.onBackPressed()
        val intent = Intent(this, NoteActivity::class.java)
        startActivity(intent)
        finish()
    }

}