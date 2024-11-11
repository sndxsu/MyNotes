package com.sndx.mynotes.db

import android.provider.BaseColumns

object DbNames:BaseColumns {
    const val TABLE_NAME = "notes_table"
    const val COLUMN_NAME_TITLE = "title"
    const val COLUMN_NAME_CONTENT = "content"
    const val COLUMN_NAME_IMPORTANT = "important"

    const val DATABASE_VERSION = 1

    const val CREATE_TABLE = "CREATE TABLE IF NOT EXISTS $TABLE_NAME(" +
            "${BaseColumns._ID} INTEGER PRIMARY KEY,$COLUMN_NAME_TITLE TEXT,$COLUMN_NAME_CONTENT TEXT,$COLUMN_NAME_IMPORTANT INTEGER)"

    const val SQL_DELETE_TABLE = "DROP TABLE IF EXISTS $TABLE_NAME"

//    const val SET_IMPORTANT = "UPDATE $TABLE_NAME SET $COLUMN_NAME_IMPORTANT 1 WHERE " //+note's id
}