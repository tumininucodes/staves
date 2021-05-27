package com.tumininu.staves.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.tumininu.staves.db.Notes
import com.tumininu.staves.db.NotesDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NotesRepository(application: Application) {

    private val db = NotesDatabase.getInstance(application)
    private val dao = db.notesDao
    private val notes = dao.getNotes()

    fun getAll(): LiveData<List<Notes>> {
        return notes
    }

    fun insert(notes: Notes) {
        GlobalScope.launch {
            dao.insert(notes)
        }
    }

    fun update(notes: Notes) {
        GlobalScope.launch {
            dao.update(notes)
        }
    }

    fun delete(title: String) {
        GlobalScope.launch {
            dao.delete(title)
        }
    }
}