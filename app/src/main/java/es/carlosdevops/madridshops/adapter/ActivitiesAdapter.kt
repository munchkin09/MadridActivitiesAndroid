package es.carlosdevops.madridshops.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import es.carlosdevops.domain.model.Activities
import es.carlosdevops.domain.model.Activity
import es.carlosdevops.domain.model.Shop
import es.carlosdevops.madridshops.R

/**
 * Created by carlosledesma on 14/2/18.
 */
class ActivitiesAdapter(private val mDataset: Activities): RecyclerView.Adapter<ActivitiesAdapter.ViewHolder>() {

    var onClickListener : View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.cardview_shop,parent,false)

        v.setOnClickListener(onClickListener)
        return ViewHolder(v)
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name = itemView.findViewById<TextView>(R.id.title)
        val opening_hours = itemView.findViewById<TextView>(R.id.description)
        val image_shop = itemView.findViewById<ImageView>(R.id.imageView)

        fun bindActivity(activity: Activity) {
            name.text = activity.name
            opening_hours.text = activity.logo_img
            Picasso.with(itemView.context).load(activity.opening_hours).into(image_shop)

        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(mDataset != null) {
            holder?.bindActivity(mDataset.get(position))
        }
    }

    override fun getItemCount(): Int {
        return mDataset.count()
    }
}
