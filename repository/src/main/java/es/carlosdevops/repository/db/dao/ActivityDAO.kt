package es.carlosdevops.repository.db.dao

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import es.carlosdevops.repository.db.DBConstants
import es.carlosdevops.repository.db.DbHelper
import es.carlosdevops.repository.model.ActivityEntity

/**
 * Created by carlosledesma on 12/2/18.
 */

class ActivityDAO(dbHelper: DbHelper): DAOPersistable<ActivityEntity> {

    private val dbReadOnlyConnection : SQLiteDatabase = dbHelper.readableDatabase
    private val dbReadWriteConnection: SQLiteDatabase = dbHelper.writableDatabase

    override fun queryCursor(id: Long): Cursor {
        val cursor = dbReadOnlyConnection.query(
                DBConstants.TABLE_ACTIVITIES,
                DBConstants.ALL_COLUMNS_ACTIVITY,
                DBConstants.KEY_SHOP_DATABASE_ID + "= ?",
                arrayOf(id.toString()),
                "",
                "",
                DBConstants.KEY_ACTIVITY_DATABASE_ID)
        return cursor
    }

    override fun query(): List<ActivityEntity> {
        var queryResult = ArrayList<ActivityEntity>()
        val cursor = dbReadOnlyConnection.query(
                DBConstants.TABLE_ACTIVITIES,
                DBConstants.ALL_COLUMNS_ACTIVITY,
                null,
                null,
                "",
                "",
                DBConstants.KEY_ACTIVITY_DATABASE_ID)
        while (cursor.moveToNext()) {
            queryResult.add(entityFromCursor(cursor)!!)
        }
        return queryResult
    }

    override fun update(id: Long, element: ActivityEntity) {
        val numberOfRecordsUpdated = dbReadWriteConnection.update(
                DBConstants.TABLE_ACTIVITIES,
                contentValues(element),
                DBConstants.KEY_ACTIVITY_DATABASE_ID + " = ? ",
                arrayOf(id.toString()))
    }

    override fun delete(element: ActivityEntity): Long {
        return delete(element.databaseId!!)
    }

    override fun delete(id: Long): Long {
        return dbReadWriteConnection.delete(
                DBConstants.TABLE_ACTIVITIES,
                DBConstants.KEY_ACTIVITY_DATABASE_ID + "= ?",
                arrayOf<String>(id.toString())
        ).toLong()
    }

    override fun deleteAll(): Boolean {
        return dbReadWriteConnection.delete(
                DBConstants.TABLE_ACTIVITIES,
                null,
                null
        ).toLong() >= 0
    }


    override fun insert(element: ActivityEntity): Long {
        var id: Long = -1
        id = dbReadWriteConnection.insert(DBConstants.TABLE_ACTIVITIES,null, contentValues(element))
        return id
    }

    override fun query(id: Long): ActivityEntity {
        val cursor = queryCursor(id)
        cursor.moveToFirst()
        return entityFromCursor(cursor)!!
    }



    private fun entityFromCursor(cursor: Cursor) : ActivityEntity? {
        if (cursor.isAfterLast || cursor.isBeforeFirst) {
            return null
        }
        return ActivityEntity(
                cursor.getLong(cursor.getColumnIndex(DBConstants.KEY_ACTIVITY_ID_JSON)),
                cursor.getLong((cursor.getColumnIndex(DBConstants.KEY_ACTIVITY_DATABASE_ID))),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ACTIVITY_NAME)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ACTIVITY_ADDRESS)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ACTIVITY_LATITUDE)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ACTIVITY_LONGITUDE)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ACTIVITY_DESCRIPTION)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ACTIVITY_OPENING_HOURS)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ACTIVITY_LOGO_IMAGE_URL)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_ACTIVITY_IMAGE_URL))
        )
    }

    private fun contentValues(shopEntity: ActivityEntity): ContentValues {
        val content = ContentValues()

        content.put(DBConstants.KEY_ACTIVITY_ID_JSON, shopEntity.id)
        content.put(DBConstants.KEY_ACTIVITY_NAME, shopEntity.name)
        content.put(DBConstants.KEY_ACTIVITY_DESCRIPTION, shopEntity.description)
        content.put(DBConstants.KEY_ACTIVITY_LATITUDE, shopEntity.gps_lat)
        content.put(DBConstants.KEY_ACTIVITY_LONGITUDE, shopEntity.gps_lon)
        content.put(DBConstants.KEY_ACTIVITY_IMAGE_URL, shopEntity.img)
        content.put(DBConstants.KEY_ACTIVITY_LOGO_IMAGE_URL, shopEntity.logo_img)
        content.put(DBConstants.KEY_ACTIVITY_ADDRESS, shopEntity.address)
        content.put(DBConstants.KEY_ACTIVITY_OPENING_HOURS, shopEntity.opening_hours)
        return content
    }

}