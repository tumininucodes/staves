package com.tecx.notes

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.tecx.notes.databinding.ActivityAddNotesBinding

const val REMOVE_IMAGE = "false"

class AddNotesActivity : AppCompatActivity() {

    private lateinit var addNotesBinding: ActivityAddNotesBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addNotesBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_notes)
        setContentView(addNotesBinding.root)


        addNotesBinding.saveNoteButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
        finishActivity(0)
    }
}
