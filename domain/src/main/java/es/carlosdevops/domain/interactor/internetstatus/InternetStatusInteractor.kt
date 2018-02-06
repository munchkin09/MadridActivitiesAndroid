package es.carlosdevops.domain.interactor.internetstatus

import es.carlosdevops.domain.interactor.CodeClosure
import es.carlosdevops.domain.interactor.ErrorClosure

/**
 * Created by carlosledesma on 24/1/18.
 */

interface InternetStatusInteractor {
    fun execute(success: CodeClosure, error: ErrorClosure)
}
