package com.tumininu.staves.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.tumininu.staves.db.Notes

@Dao
interface NotesDao {

    @Insert
    fun insert(notes: Notes)

    @Delete
    fun delete(notes: Notes)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(notes: Notes)

    @Query("SELECT * FROM notes_table")
    fun getNotes(): LiveData<List<Notes>>
}