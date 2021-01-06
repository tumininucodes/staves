package com.tecx.notes.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.tecx.notes.*
import java.util.*

class DataBaseHandler(private val context: Context) :
    SQLiteOpenHelper(
        context,
        DB_NAME, null,
        DB_VERSION
    ) {

    override fun onCreate(db: SQLiteDatabase) {
        val createNoteTable = " CREATE TABLE $TABLE_NOTE (" +
                "$COL_ID integer PRIMARY KEY AUTOINCREMENT," +
                "$COL_CREATED_AT datetime DEFAULT CURRENT_TIMESTAMP," +
                "$COL_NAME varchar);"

        val createNoteItemTable = "CREATE TABLE $TABLE_NOTE_ITEM (" +
                "$COL_ID integer PRIMARY KEY AUTOINCREMENT," +
                "$COL_CREATED_AT datetime DEFAULT CURRENT_TIMESTAMP," +
                "$COL_NOTE_ID integer," +
                "$COL_ITEM_NAME varchar," +
                "$COL_IS_COMPLETED integer);"

        db.execSQL(createNoteTable)
        db.execSQL(createNoteItemTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }

    fun addNote(note: Notes): Boolean {

        val db = writableDatabase
        val cv = ContentValues()

        cv.put(COL_NAME, note.name)

        val result = db.insert(TABLE_NOTE, null, cv)

        return result != (-1).toLong()
    }

    fun updateNote(note: Notes) {

        val db = writableDatabase
        val cv = ContentValues()

        cv.put(COL_NAME, note.name)

        db.update(

            TABLE_NOTE, cv, "$COL_ID=?", arrayOf(
                note.id
                    .toString()
            )

        )
    }

    fun deleteNote(todoId: Long) {

        val db = writableDatabase

        db.delete(TABLE_NOTE, "$COL_ID=?", arrayOf(todoId.toString()))

    }

    fun getNotes(): MutableList<Notes> {

        val result: MutableList<Notes> = ArrayList()
        val db = readableDatabase
        val queryResult = db.rawQuery("SELECT * from $TABLE_NOTE", null)

        if (queryResult.moveToFirst()) {

            do {

                val note = Notes()

                note.id = queryResult.getLong(queryResult.getColumnIndex(COL_ID))
                note.name = queryResult.getString(queryResult.getColumnIndex(COL_NAME))

                result.add(note)

            } while (queryResult.moveToNext())
        }

        queryResult.close()

        return result
    }
}