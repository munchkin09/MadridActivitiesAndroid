package es.carlosdevops.domain.interactor.deleteallshops

import es.carlosdevops.domain.interactor.CodeClosure
import es.carlosdevops.domain.interactor.ErrorClosure
import es.carlosdevops.domain.interactor.SuccessClosure

/**
 * Created by carlosledesma on 31/1/18.
 */
interface DeleteAllShops {

    fun execute(successClosure: CodeClosure,errorClosure: ErrorClosure)
}