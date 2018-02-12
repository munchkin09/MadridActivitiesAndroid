package es.carlosdevops.repository.db.dao

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import es.carlosdevops.repository.db.DBConstants
import es.carlosdevops.repository.db.DbHelper
import es.carlosdevops.repository.model.ShopEntity

class ShopDAO(dbHelper: DbHelper): DAOPersistable<ShopEntity> {

    private val dbReadOnlyConnection : SQLiteDatabase = dbHelper.readableDatabase
    private val dbReadWriteConnection: SQLiteDatabase = dbHelper.writableDatabase

    override fun queryCursor(id: Long): Cursor {
        val cursor = dbReadOnlyConnection.query(
                DBConstants.TABLE_SHOP,
                DBConstants.ALL_COLUMNS,
                DBConstants.KEY_SHOP_DATABASE_ID + "= ?",
                arrayOf(id.toString()),
                "",
                "",
                DBConstants.KEY_SHOP_DATABASE_ID)
        return cursor
    }

    override fun query(): List<ShopEntity> {
        var queryResult = ArrayList<ShopEntity>()
        val cursor = dbReadOnlyConnection.query(
                DBConstants.TABLE_SHOP,
                DBConstants.ALL_COLUMNS,
                null,
                null,
                "",
                "",
                DBConstants.KEY_SHOP_DATABASE_ID)
        while (cursor.moveToNext()) {
            queryResult.add(entityFromCursor(cursor)!!)
        }
        return queryResult
    }

    override fun update(id: Long, element: ShopEntity) {
        val numberOfRecordsUpdated = dbReadWriteConnection.update(
                DBConstants.TABLE_SHOP,
                contentValues(element),
                DBConstants.KEY_SHOP_DATABASE_ID  + " = ? ",
                arrayOf(id.toString()))
    }

    override fun delete(element: ShopEntity): Long {
        return delete(element.databaseId!!)
    }

    override fun delete(id: Long): Long {
        return dbReadWriteConnection.delete(
                DBConstants.TABLE_SHOP,
                DBConstants.KEY_SHOP_DATABASE_ID + "= ?",
                arrayOf<String>(id.toString())
        ).toLong()
    }

    override fun deleteAll(): Boolean {
        return dbReadWriteConnection.delete(
                DBConstants.TABLE_SHOP,
                null,
                null
        ).toLong() >= 0
    }


    override fun insert(element: ShopEntity): Long {
        var id: Long = -1
        id = dbReadWriteConnection.insert(DBConstants.TABLE_SHOP,null, contentValues(element))
        return id
    }

    override fun query(id: Long): ShopEntity {
        val cursor = queryCursor(id)
        cursor.moveToFirst()
        return entityFromCursor(cursor)!!
    }



    fun entityFromCursor(cursor: Cursor) : ShopEntity? {
        if (cursor.isAfterLast || cursor.isBeforeFirst) {
            return null
        }
        return ShopEntity(
                cursor.getLong(cursor.getColumnIndex(DBConstants.KEY_ACTIVITY_ID_JSON)),
                cursor.getLong((cursor.getColumnIndex(DBConstants.KEY_SHOP_DATABASE_ID))),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_NAME)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_ADDRESS)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_LATITUDE)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_LONGITUDE)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_DESCRIPTION)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_OPENING_HOURS)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_LOGO_IMAGE_URL)),
                cursor.getString(cursor.getColumnIndex(DBConstants.KEY_SHOP_IMAGE_URL))
        )
    }

    fun contentValues(shopEntity: ShopEntity): ContentValues {
        val content = ContentValues()

        content.put(DBConstants.KEY_ACTIVITY_ID_JSON, shopEntity.id)
        content.put(DBConstants.KEY_SHOP_NAME, shopEntity.name)
        content.put(DBConstants.KEY_SHOP_DESCRIPTION, shopEntity.description)
        content.put(DBConstants.KEY_SHOP_LATITUDE, shopEntity.gps_lat)
        content.put(DBConstants.KEY_SHOP_LONGITUDE, shopEntity.gps_lon)
        content.put(DBConstants.KEY_SHOP_IMAGE_URL, shopEntity.img)
        content.put(DBConstants.KEY_SHOP_LOGO_IMAGE_URL, shopEntity.logo_img)
        content.put(DBConstants.KEY_SHOP_ADDRESS, shopEntity.address)
        content.put(DBConstants.KEY_SHOP_OPENING_HOURS, shopEntity.opening_hours)
        return content
    }

}