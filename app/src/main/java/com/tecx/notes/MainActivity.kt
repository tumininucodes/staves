package com.tecx.notes

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.tecx.notes.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setContentView(mainBinding.root)
//        setSupportActionBar(mainBinding.toolbarNote)

        mainBinding.fabNotesAdd.setOnClickListener {
            startActivity(Intent(this, AddNotesActivity::class.java))
            finish()
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finishActivity(0)
    }

    private fun hideNoteImage() {
        mainBinding.addNotesImage.visibility = View.GONE
    }
}
