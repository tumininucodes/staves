package com.tumininu.staves.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.tumininu.staves.db.Notes
import com.tumininu.staves.db.NotesDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesRepository(application: Application) {

    private val db = NotesDatabase.getInstance(application)
    private val dao = db.notesDao
    private val notes = dao.getNotes()

    fun getAll(): LiveData<List<Notes>> {
        return notes
    }

    fun insert(notes: Notes) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.insert(notes)
        }
    }

    fun update(notes: Notes) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.update(notes)
        }
    }

    fun delete(title: String) {
        CoroutineScope(Dispatchers.IO).launch {
            dao.delete(title)
        }
    }
}