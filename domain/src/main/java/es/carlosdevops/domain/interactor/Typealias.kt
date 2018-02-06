package es.carlosdevops.domain.interactor

import es.carlosdevops.domain.model.Shops

/**
 * Created by carlosledesma on 24/1/18.
 */

typealias CodeClosure = () -> Unit
typealias SuccessClosure = (shops: Shops) -> Unit
typealias ErrorClosure = (msg: String) -> Unit
typealias Variant = Any