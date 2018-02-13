package es.carlosdevops.domain.interactor.getallactivities


import es.carlosdevops.domain.interactor.ErrorCompletion
import es.carlosdevops.domain.interactor.SuccessCompletion
import es.carlosdevops.domain.model.Activities

/**
 * Created by carlosledesma on 12/2/18.
 */
interface GetAllActivities: SuccessCompletion<Activities>,ErrorCompletion {
    fun execute(success:SuccessCompletion<Activities>, error: ErrorCompletion)
}