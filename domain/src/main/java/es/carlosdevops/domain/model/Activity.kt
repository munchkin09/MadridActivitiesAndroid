package es.carlosdevops.domain.model

import java.io.Serializable
import android.os.Parcelable

/**
 * Created by carlosledesma on 12/2/18.
 */


data class Activity(val id: Long,
                    val name:String,
                    val address: String,
                    val description: String,
                    val gps_lat: Double,
                    val gps_lon: Double,
                    val img: String,
                    val logo_img: String,
                    val opening_hours: String) : Serializable {
}

class Activities(var activities: MutableList<Activity>): Aggregate<Activity> {

    override fun count(): Int {
        return activities.size
    }

    override fun all(): List<Activity> {
        return activities
    }

    override fun get(position: Int): Activity{
        return activities.get(position)
    }

    override fun add(element: Activity) {
        activities.add(element)
    }

    override fun delete(position: Int) {
        activities.removeAt(position)
    }

    override fun delete(element: Activity) {
        activities.remove(element)
    }

}