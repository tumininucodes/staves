package com.tecx.notes

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.tecx.notes.databinding.ActivityAddNotesBinding

class AddNotesActivity : AppCompatActivity() {

    // Objects to be used later are initialised here
    private lateinit var addNotesBinding: ActivityAddNotesBinding
    lateinit var dbHandler: DataBaseHandler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Use data binding to set content view
        addNotesBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_notes)

        // An onclick listener is created for this activity's toolbar icon that opens the activity
        // which is in the back stack. In this case, the NoteActivity
        addNotesBinding.addNotesToolbar.setNavigationOnClickListener {
            val intent = Intent(this, NoteActivity::class.java)
            startActivity(intent)
            finish()
        }

        // An on click listener is set for the save button that saves the title and description of
        // the note to the database
        addNotesBinding.saveNoteButton.setOnClickListener {

//            if(addNotesBinding.etTitle.isNotEmpty()) {
//
//            }

            val intent = Intent(this, NoteActivity::class.java)
            startActivity(intent)
            finish()
        }

    }


    // This override method opens NoteActivity and closes this activity
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, NoteActivity::class.java)
        startActivity(intent)
        finish()
        finishActivity(0)
    }

}