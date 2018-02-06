package es.carlosdevops.domain.interactor.getallshops

import es.carlosdevops.domain.interactor.ErrorCompletion
import es.carlosdevops.domain.interactor.SuccessCompletion
import es.carlosdevops.domain.model.Shop
import es.carlosdevops.domain.model.Shops


class GetAllShopsInteractorFakeImpl: GetAllShopsInteractor {
    override fun execute(success: SuccessCompletion<Shops>, error: ErrorCompletion) {
        var allOk = true

        //Conexión al repositorio
        if (allOk) {
            val shops = createFakeListOfShops()
            success.successCompletion(shops)
        } else {
            error.errorCompletion("Error")
        }
    }

    fun createFakeListOfShops() : Shops {
        val list = ArrayList<Shop>()

        /*for (i in 0..100) {
            val shop = Shop(i.toLong(),"shop "+i, "fake address " +i)
            list.add(shop)
        }*/

        val shops = Shops(list)
        return shops
    }

}