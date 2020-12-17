package com.tecx.notes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.tecx.notes.databinding.ActivityAddNotesBinding


class AddNotesActivity : AppCompatActivity() {

    private lateinit var addNotesBinding: ActivityAddNotesBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addNotesBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_notes)
        setContentView(addNotesBinding.root)

    }
}
