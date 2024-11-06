package com.sndx.mynotes.db

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase

class DbManager(val context: Context) {
    val dbHelper = DbHelper(context)
    var db: SQLiteDatabase? = null

    fun openDB(){
        db = dbHelper.writableDatabase
    }
    fun addToDB(title:String, content:String){
        val values = ContentValues().apply {
            put(DbNames.COLUMN_NAME_TITLE, title)
            put(DbNames.COLUMN_NAME_CONTENT, content)
        }
        db?.insert(DbNames.TABLE_NAME, null, values)
    }

    fun closeDb(){
        dbHelper.close()
    }

    @SuppressLint("Range")
    fun readDbData() : ArrayList<String>{
        val dataList = ArrayList<String>()
        val cursor = db?.query(DbNames.TABLE_NAME, null, null, null, null, null, null)

        with(cursor){
            while(this?.moveToNext()!!){
                val dataText = cursor?.getString(cursor.getColumnIndex(DbNames.COLUMN_NAME_TITLE))
//                val description = cursor?.getString(cursor.getColumnIndex(DbNames.COLUMN_NAME_CONTENT))
                if (dataText != null) {
                    dataList.add(dataText)
                }
            }
        }
        cursor?.close()
        return dataList
    }
}