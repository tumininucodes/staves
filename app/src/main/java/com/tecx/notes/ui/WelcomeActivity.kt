package com.tecx.notes.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.tecx.notes.R
import com.tecx.notes.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    lateinit var welcomeBinding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        welcomeBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_welcome
        )
        setContentView(welcomeBinding.root)


        welcomeBinding.continueButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            overridePendingTransition(
                R.anim.right_to_left,
                R.anim.left_to_right
            )
        }
    }
}
