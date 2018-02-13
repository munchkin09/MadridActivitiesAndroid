package es.carlosdevops.domain.interactor.getallactivities

import android.content.Context
import es.carlosdevops.domain.interactor.ErrorCompletion
import es.carlosdevops.domain.interactor.SuccessCompletion
import es.carlosdevops.domain.model.Activities
import es.carlosdevops.domain.model.Activity
import es.carlosdevops.repository.Repository
import es.carlosdevops.repository.RepositoryImpl
import es.carlosdevops.repository.model.ActivityEntity
import java.lang.ref.WeakReference

class GetAllActivitiesImpl(context: Context) : GetAllActivities {

    private val weakContext = WeakReference<Context>(context)

    private val repository: Repository = RepositoryImpl(weakContext.get()!!)

    override fun execute(success: SuccessCompletion<Activities>, error: ErrorCompletion) {
        repository.getAllActivities(successCompletion = {
            val activities = entityMapper(it)
            success.successCompletion(activities)
        }, errorCompletion = {
            error(it)
        })
    }

    private fun entityMapper(list: List<ActivityEntity>): Activities {

        val tempList = ArrayList<Activity>()


        list.forEach {
            val gpsLat = convertStringCoordinateToDouble(it.gps_lat)
            val gpsLon = convertStringCoordinateToDouble(it.gps_lon)

            val activity = Activity(it.id,it.name,it.address,it.description,gpsLat,gpsLon,it.img,it.logo_img,it.opening_hours)
            tempList.add(activity)
        }
        val activities = Activities(tempList)
        return activities
    }

    private fun convertStringCoordinateToDouble(coordinate: String) : Double {
        return coordinate.toDoubleOrNull() ?: 0.0
    }
}