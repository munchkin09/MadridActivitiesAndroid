package es.carlosdevops.repository

import android.content.Context
import es.carlosdevops.repository.cache.Cache
import es.carlosdevops.repository.cache.CacheImpl
import es.carlosdevops.repository.model.ShopEntity
import es.carlosdevops.repository.model.ShopResponseEntity
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

    private fun populateCache(successCompletion: (shops: List<ShopEntity>) -> Unit, errorCompletion: ErrorClosure) {

        val jsonManager : GetJsonManager = GetJsonManagerVolleyImpl(weakContext.get()!!)
        jsonManager.execute(BuildConfig.MS_SERVER_URL, success = object: SuccessCompletion<String> {
            override fun successCompletion(e: String) {
                val parser = JsonEntitiesParser()
                val responseEntity = parser.parse<ShopResponseEntity>(e)
                cache.storeAllShops(responseEntity.result, successCompletion = {
                    successCompletion(responseEntity.result)
                }, errorCompletion = {
                    errorCompletion("Algo ha pasado")
                })
            }
        }, error = object : ErrorCompletion {

        })

    }
}