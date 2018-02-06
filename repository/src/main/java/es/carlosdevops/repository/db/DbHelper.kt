package es.carlosdevops.repository.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/**
 * Created by carlosledesma on 24/1/18.
 */

fun buildDbHelper(context:Context, name: String, version: Int) : DbHelper {
    return DbHelper(context,name,null, version)
}

class DbHelper(context: Context?,
               name: String?,
               factory: SQLiteDatabase.CursorFactory?,
               version: Int) : SQLiteOpenHelper(context, name, factory, version) {



    override fun onOpen(db: SQLiteDatabase?) {
        super.onOpen(db)
        db?.setForeignKeyConstraintsEnabled(true)
    }

    override fun onCreate(db: SQLiteDatabase?) {
        DBConstants.CREATE_DATABASE_SCRIPTS.forEach {
            db?.execSQL(it)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

}

// Helpers

fun convert(boolean: Boolean) : Int {
    if(boolean) {
        return 1
    }
    return 0
}

fun convert(int: Int) : Boolean {
    if(int == 1) {
        return true
    }
    return false
}