package es.carlosdevops.domain.interactor.getallshops

import android.content.Context
import es.carlosdevops.domain.interactor.ErrorCompletion
import es.carlosdevops.domain.interactor.SuccessCompletion
import es.carlosdevops.domain.model.Shop
import es.carlosdevops.domain.model.Shops
import es.carlosdevops.repository.Repository
import es.carlosdevops.repository.RepositoryImpl
import es.carlosdevops.repository.model.ShopEntity
import java.lang.ref.WeakReference



class GetAllShopsInteractorImpl(context: Context) : GetAllShopsInteractor {

    private val weakContext = WeakReference<Context>(context)

    private val repository : Repository = RepositoryImpl(weakContext.get()!!)
    override fun execute(success: SuccessCompletion<Shops>, error: ErrorCompletion) {
        repository.getAllShops(successCompletion = {
            val shops : Shops = entityMapper(it)
            success.successCompletion(shops)
        },errorCompletion = {
            error(it)
        })
    }

    private fun entityMapper(list: List<ShopEntity>): Shops {

        val tempList = ArrayList<Shop>()

        list.forEach {

            val gpsLat = convertStringCoordinateToDouble(it.gps_lat)
            val gpsLon = convertStringCoordinateToDouble(it.gps_lon)
            val shop = Shop(it.id,it.name,it.address,it.description,gpsLat,gpsLon,it.img,it.logo_img,it.opening_hours)

            tempList.add(shop)
        }

        val shops = Shops(tempList)
        return shops
    }

    private fun convertStringCoordinateToDouble(coordinate: String) : Double {
        return coordinate.toDoubleOrNull() ?: 0.0
    }

}


