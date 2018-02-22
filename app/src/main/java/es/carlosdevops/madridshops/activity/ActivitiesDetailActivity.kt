package es.carlosdevops.madridshops.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import es.carlosdevops.domain.model.Activity
import es.carlosdevops.madridshops.R
import es.carlosdevops.utils.INTENT_ACTIVITIES_DETAIL

class ActivitiesDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_activities_detail)

        val activity = intent.getSerializableExtra(INTENT_ACTIVITIES_DETAIL) as Activity
        setupUI(activity)

    }

    private fun setupUI(activity: Activity) {


    }
}
