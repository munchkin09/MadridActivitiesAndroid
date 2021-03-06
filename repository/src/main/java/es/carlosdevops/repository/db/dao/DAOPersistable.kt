package es.carlosdevops.repository.db.dao

import android.database.Cursor
import es.carlosdevops.repository.model.ShopEntity

interface DAOReadOperations<T> {
    fun query(id: Long): T
    fun query(): List<T>
    fun queryCursor(id: Long): Cursor
}

interface DAOWriteOperation<T> {
    fun insert(element: T): Long
    fun update(id: Long, element: T)
    fun delete(element: T) : Long
    fun delete(id: Long) : Long
    fun deleteAll(): Boolean
}

interface DAOPersistable<T>: DAOReadOperations<T>, DAOWriteOperation<T>