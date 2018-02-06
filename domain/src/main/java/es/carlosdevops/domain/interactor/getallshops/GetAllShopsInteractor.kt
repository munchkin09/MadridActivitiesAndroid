package es.carlosdevops.domain.interactor.getallshops

import es.carlosdevops.domain.interactor.ErrorCompletion
import es.carlosdevops.domain.interactor.SuccessCompletion
import es.carlosdevops.domain.model.Shops


interface GetAllShopsInteractor: SuccessCompletion<Shops>, ErrorCompletion {

    fun execute(success: SuccessCompletion<Shops>, error: ErrorCompletion)
}