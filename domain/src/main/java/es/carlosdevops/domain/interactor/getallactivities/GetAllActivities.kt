package es.carlosdevops.domain.interactor.getallactivities

import android.app.Activity
import es.carlosdevops.domain.interactor.ErrorCompletion
import es.carlosdevops.domain.interactor.SuccessCompletion

/**
 * Created by carlosledesma on 12/2/18.
 */
interface GetAllActivities: SuccessCompletion<Activity>,ErrorCompletion {
    fun execute(success:SuccessCompletion<Activity>, error: ErrorCompletion)
}