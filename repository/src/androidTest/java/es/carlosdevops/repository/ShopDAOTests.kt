package es.carlosdevops.repository

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import es.carlosdevops.repository.db.DbHelper
import es.carlosdevops.repository.db.buildDbHelper
import es.carlosdevops.repository.db.dao.ShopDAO
import es.carlosdevops.repository.model.ShopEntity

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
@RunWith(AndroidJUnit4::class)
class ShopDAOTests {
    val appContext = InstrumentationRegistry.getTargetContext()
    val dbHelper = buildDbHelper(appContext,"mydb.sqlite",1)
    @Test
    @Throws(Exception::class)
    fun useAppContext() {
        // Context of the app under test.


        val shop = ShopEntity(1,1,"My shop","", 1.0f,1.0f,"","","","")

        val shopEntityDao = ShopDAO(dbHelper)
        shopEntityDao.deleteAll()
        val id = shopEntityDao.insert(shop)

        assertTrue(id > 0)
    }

    @Test
    @Throws(Exception::class)
    fun deleteAll() {
        val shopEntityDao = ShopDAO(dbHelper)

        shopEntityDao.deleteAll()
    }
}
