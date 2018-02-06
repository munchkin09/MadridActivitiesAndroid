package es.carlosdevops.repository.network

import es.carlosdevops.repository.ErrorCompletion
import es.carlosdevops.repository.SuccessCompletion

interface GetJsonManager {
    fun execute(url: String, success: SuccessCompletion<String>,error: ErrorCompletion)
}