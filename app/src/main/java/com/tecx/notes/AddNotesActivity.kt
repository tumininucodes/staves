package com.tecx.notes

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.tecx.notes.databinding.ActivityAddNotesBinding
import com.tecx.notes.databinding.ActivityAddNotesBinding.inflate


class AddNotesActivity : AppCompatActivity() {

    private lateinit var binding2: ActivityAddNotesBinding


    @SuppressLint("ShowToast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding2 = inflate(layoutInflater)
        setContentView(binding2.root)
        setSupportActionBar(binding2.addNotesToolbar)

        binding2.addNotesToolbar.setNavigationOnClickListener {

            startActivity(Intent(this, MainActivity::class.java))
            finish()

        }

        binding2.saveNoteButton.setOnClickListener {
            Toast.makeText(this, "Notes added successfully", Toast.LENGTH_SHORT).show()

            startActivity(Intent(this, MainActivity::class.java))
            finish()
            finishActivity(1)
        }


    }

    override fun onBackPressed() {
        super.onBackPressed()

        startActivity(Intent(this, MainActivity::class.java))
        finish()

        finishActivity(1)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_options_notes, menu)

        return super.onCreateOptionsMenu(menu)
    }


}
