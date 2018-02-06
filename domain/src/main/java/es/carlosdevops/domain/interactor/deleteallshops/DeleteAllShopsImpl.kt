package es.carlosdevops.domain.interactor.deleteallshops

import android.content.Context
import es.carlosdevops.domain.interactor.CodeClosure
import es.carlosdevops.domain.interactor.ErrorClosure
import es.carlosdevops.repository.Repository
import es.carlosdevops.repository.RepositoryImpl
import java.lang.ref.WeakReference

class DeleteAllShopsImpl(context: Context) : DeleteAllShops {

    val context = WeakReference<Context>(context)

    override fun execute(successClosure: CodeClosure, errorClosure: ErrorClosure) {
        val repo = RepositoryImpl(context.get()!!)
        repo.deleteAllShops(successClosure,errorClosure)
    }
}