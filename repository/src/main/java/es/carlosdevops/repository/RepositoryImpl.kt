package es.carlosdevops.repository

import android.content.Context
import es.carlosdevops.repository.cache.Cache
import es.carlosdevops.repository.cache.CacheImpl
import es.carlosdevops.repository.model.ActivityEntity
import es.carlosdevops.repository.model.ShopEntity
import es.carlosdevops.repository.model.ResponseEntity
import es.carlosdevops.repository.network.GetJsonManager
import es.carlosdevops.repository.network.GetJsonManagerVolleyImpl
import es.carlosdevops.repository.network.json.JsonEntitiesParser

import java.lang.ref.WeakReference

class RepositoryImpl(context: Context): Repository {



    private val weakContext = WeakReference<Context>(context)
    private val cache : Cache = CacheImpl(weakContext.get()!!)


    override fun deleteAllShops(successCompletion: () -> Unit, errorCompletion: ErrorClosure) {
        cache.deleteAllShops(successCompletion, errorCompletion)
    }

    override fun getAllShops(successCompletion: (shops: List<ShopEntity>) -> Unit, errorCompletion: ErrorClosure) {
        //read all shops from cache
        cache.getAllShops(successCompletion = {
             successCompletion(it)
        }, errorCompletion = {

            populateCache(successCompletion, errorCompletion)
        })
    }

    override fun getAllActivities(successCompletion: (activities: List<ActivityEntity>) -> Unit, errorCompletion: ErrorClosure) {

        cache.getAllActivities(successCompletion = {
            successCompletion(it)
        }, errorCompletion = {
            populateCacheActivities(successCompletion,errorCompletion)
        })

    }

    private fun populateCache(successCompletion: (shops: List<ShopEntity>) -> Unit, errorCompletion: ErrorClosure) {

        val jsonManager : GetJsonManager = GetJsonManagerVolleyImpl(weakContext.get()!!)
        jsonManager.execute(BuildConfig.MS_SERVER_URL, success = object: SuccessCompletion<String> {
            override fun successCompletion(e: String) {
                val parser = JsonEntitiesParser()
                val responseEntity = parser.parse<ResponseEntity<ShopEntity>>(e)
                cache.storeAllShops(responseEntity.result, successCompletion = {
                    cache.getAllShops(successCompletion,errorCompletion)
                }, errorCompletion = {
                    errorCompletion("Algo ha pasado")
                })
            }
        }, error = object : ErrorCompletion {

        })

    }

    private fun populateCacheActivities(successCompletion: (activities: List<ActivityEntity>) -> Unit, errorCompletion: ErrorClosure) {

        val jsonManager : GetJsonManager = GetJsonManagerVolleyImpl(weakContext.get()!!)
        jsonManager.execute(BuildConfig.MS_SERVER_URL_2, success = object: SuccessCompletion<String> {
            override fun successCompletion(e: String) {
                val parser = JsonEntitiesParser()
                val responseEntity = parser.parse<ResponseEntity<ActivityEntity>>(e)
                cache.storeAllActivities(responseEntity.result, successCompletion = {
                    cache.getAllActivities(successCompletion,errorCompletion)
                }, errorCompletion = {
                    errorCompletion("Algo ha pasado")
                })
            }
        }, error = object : ErrorCompletion {

        })

    }
}