package es.carlosdevops.repository.cache

import android.content.Context
import es.carlosdevops.repository.ErrorClosure
import es.carlosdevops.repository.ErrorCompletion
import es.carlosdevops.repository.db.DbHelper
import es.carlosdevops.repository.db.buildDbHelper
import es.carlosdevops.repository.db.dao.ShopDAO
import es.carlosdevops.repository.model.ActivityEntity
import es.carlosdevops.repository.model.ShopEntity
import es.carlosdevops.repository.thread.MainThread
import java.lang.ref.WeakReference

class CacheImpl(context: Context): Cache {

    val weakContext = WeakReference<Context>(context)

    override fun deleteAllShops(successCompletion: () -> Unit, errorCompletion: ErrorClosure) {
        val dbHelper = cacheDbHelper()
        Thread(Runnable{
            var successDeleting = ShopDAO(dbHelper).deleteAll()
            if (successDeleting) {
                MainThread(Runnable {
                    successCompletion()
                })

            } else {
                errorCompletion("Error deleting")
            }
        }).run()
    }

    override fun getAllShops(successCompletion: (shops: List<ShopEntity>) -> Unit, errorCompletion: ErrorClosure) {
        Thread(Runnable {
            val shopsEntity = ShopDAO(cacheDbHelper()).query()
            MainThread(Runnable {
                if (shopsEntity.count() > 0) {
                    successCompletion(shopsEntity)
                } else {
                    errorCompletion("No shops")
                }
            })
        }).run()

    }

    override fun storeAllShops(shops: List<ShopEntity>, successCompletion: () -> Unit, errorCompletion: ErrorClosure) {
        Thread(Runnable {
            shops.forEach({
                ShopDAO(cacheDbHelper()).insert(it)
            })
        }).run()
    }

    override fun getAllActivities(successCompletion: (activities: List<ActivityEntity>) -> Unit, errorCompletion: ErrorClosure) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun storeAllActivities(activities: List<ActivityEntity>, successCompletion: () -> Unit, errorCompletion: ErrorClosure) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun cacheDbHelper() : DbHelper {
        return buildDbHelper(weakContext.get()!!,"MadridShops.sqlite",1)
    }
}