package es.carlosdevops.domain.interactor.internetstatus

import es.carlosdevops.domain.interactor.CodeClosure
import es.carlosdevops.domain.interactor.ErrorClosure

class InternetStatusInteractorImpl : InternetStatusInteractor {
    override fun execute(success: CodeClosure, error: ErrorClosure) {
        success()
    }
}