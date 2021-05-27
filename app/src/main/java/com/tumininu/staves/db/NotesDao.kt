package com.tumininu.staves.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(notes: Notes)

    @Query("DELETE from notes_table where name=:title")
    fun delete(title: String)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(notes: Notes)
    

    @Query("SELECT * FROM notes_table")
    fun getNotes(): LiveData<List<Notes>>
}