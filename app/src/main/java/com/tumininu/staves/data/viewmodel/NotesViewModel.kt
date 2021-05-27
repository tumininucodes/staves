package com.tumininu.staves.data.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.tumininu.staves.data.repository.NotesRepository
import com.tumininu.staves.db.Notes
import kotlinx.coroutines.Job

class NotesViewModel(application: Application) : AndroidViewModel(application) {

    private var viewModelJob = Job()
    private val repository = NotesRepository(application)
    private val notes = repository.getAll()

    fun getAllNotes(): LiveData<List<Notes>> {
        return notes
    }

    fun insert(notes: Notes) {
        repository.insert(notes)
    }

    fun delete(title: String) {
        repository.delete(title)
    }

    fun update(notes: Notes) {
        repository.update(notes)
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}