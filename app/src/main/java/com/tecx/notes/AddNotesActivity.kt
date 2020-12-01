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

        // onclick listener
        binding2.addNotesToolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back)

        binding2.addNotesToolbar.setOnClickListener {
            overridePendingTransition(R.anim.fragment_fade_enter, R.anim.fragment_fade_exit)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        binding2.saveNoteButton.setOnClickListener {
            Toast.makeText(this, "Notes added successfully", Toast.LENGTH_LONG)
            overridePendingTransition(R.anim.fragment_fade_enter, R.anim.fragment_fade_exit)
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }


    }

    override fun onBackPressed() {
        super.onBackPressed()

        overridePendingTransition(R.anim.fragment_fade_enter, R.anim.fragment_fade_exit)
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_options_notes, menu)

        return super.onCreateOptionsMenu(menu)
    }


}
