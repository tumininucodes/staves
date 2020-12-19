package com.tecx.notes

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.tecx.notes.databinding.ActivityAboutBinding

class AboutActivity : AppCompatActivity() {

    private lateinit var aboutBinding: ActivityAboutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        aboutBinding = DataBindingUtil.setContentView(this, R.layout.activity_about)

        aboutBinding.aboutToolbar.setNavigationOnClickListener {
            startActivity(Intent(this, NoteActivity::class.java))
            finishActivity(0)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startActivity(Intent(this, NoteActivity::class.java))
        finishActivity(0)
    }
}
