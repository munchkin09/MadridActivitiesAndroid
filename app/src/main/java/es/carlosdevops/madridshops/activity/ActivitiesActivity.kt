package es.carlosdevops.madridshops.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.Toast
import es.carlosdevops.domain.interactor.getallactivities.GetAllActivities
import es.carlosdevops.domain.interactor.getallactivities.GetAllActivitiesImpl
import es.carlosdevops.domain.model.Activities
import es.carlosdevops.domain.interactor.ErrorCompletion
import es.carlosdevops.domain.interactor.SuccessCompletion
import es.carlosdevops.madridshops.R
import es.carlosdevops.madridshops.adapter.ActivitiesAdapter
import kotlinx.android.synthetic.main.content_main_activities.*

class ActivitiesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activities)

        setupMap()
    }

    private fun setupMap() {

        var getAllActivities : GetAllActivities = GetAllActivitiesImpl(this)

        getAllActivities.execute(success = object :SuccessCompletion<Activities> {
            override fun successCompletion(e: Activities) {
                setupList(e)
                initializeMap(e)
            }
        }, error = object : ErrorCompletion{
            override fun errorCompletion(errorMessage: String) {
                Toast.makeText(baseContext,errorMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun setupList(activities: Activities) {
        var activitiesAdapter = ActivitiesAdapter(activities)
        cma_list_fragment.layoutManager = LinearLayoutManager(this)

    }

    private fun initializeMap(activities: Activities) {

    }
}
