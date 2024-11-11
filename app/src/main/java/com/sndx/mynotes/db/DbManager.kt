package com.sndx.mynotes.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

fun Boolean.toInt() = if (this) 1 else 0

class DbManager(private val context: Context) {
    private val dbHelper = DbHelper(context)
    private var db: SQLiteDatabase? = null

    fun openDB(){
        db = dbHelper.writableDatabase
    }
    fun addToDB(note: Note){
        val values = ContentValues().apply {
            put(DbNames.COLUMN_NAME_TITLE, note.name)
            put(DbNames.COLUMN_NAME_CONTENT, note.content)
            put(DbNames.COLUMN_NAME_IMPORTANT, note.isImportant)
        }
        db?.insert(DbNames.TABLE_NAME, null, values)
    }

    fun closeDb(){
        dbHelper.close()
    }

    fun updateNote(note: Note){
        openDB()
        val contentValues = ContentValues().apply {
            put(DbNames.COLUMN_NAME_TITLE, "test!")
            put(DbNames.COLUMN_NAME_CONTENT, note.content)
            put(DbNames.COLUMN_NAME_IMPORTANT, note.isImportant.toInt())
        }
        db?.update(DbNames.TABLE_NAME, contentValues, "${BaseColumns._ID} = ?", arrayOf(note.id.toString()))
        closeDb()
    }

    @SuppressLint("Range")
    fun readDbData() : ArrayList<Note>{
        val dataList = ArrayList<Note>()
        val cursor = db?.query(DbNames.TABLE_NAME, null, null, null, null, null, null)

        with(cursor){
            while(this?.moveToNext()!!){
                val id = cursor?.getString(cursor.getColumnIndex(BaseColumns._ID))
                val dataText = cursor?.getString(cursor.getColumnIndex(DbNames.COLUMN_NAME_TITLE))
                val description = cursor?.getString(cursor.getColumnIndex(DbNames.COLUMN_NAME_CONTENT))
                val important = cursor?.getString(cursor.getColumnIndex(DbNames.COLUMN_NAME_IMPORTANT))
                if (dataText != null && description != null) {
                    if (id != null) {
                        dataList.add(Note(id.toInt(), dataText, description, important.toBoolean()))
                    }
                }
            }
        }
        cursor?.close()
        return dataList
    }
}