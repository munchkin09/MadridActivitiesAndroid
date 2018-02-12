package es.carlosdevops.madridshops.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import es.carlosdevops.domain.model.Shop
import es.carlosdevops.domain.model.Shops

import es.carlosdevops.madridshops.R


/**
 * Created by carlosledesma on 12/2/18.
 */

class ShopsAdapter(private val mDataset: Shops) : RecyclerView.Adapter<ShopsAdapter.ViewHolder>() {

    var onClickListener: View.OnClickListener? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name = itemView.findViewById<TextView>(R.id.title)
        val opening_hours = itemView.findViewById<TextView>(R.id.description)
        val image_shop = itemView.findViewById<ImageView>(R.id.imageView)

        fun bindShop(shop: Shop, position: Int) {
            name.text = shop.name
            opening_hours.text = shop.logo_img
            Picasso.with(itemView.context).load(shop.opening_hours).into(image_shop)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ShopsAdapter.ViewHolder {

        val v = LayoutInflater
                .from(parent.context)
                .inflate(R.layout.cardview_shop, parent, false)
        v.setOnClickListener(onClickListener)
        return ViewHolder(v)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        if(mDataset != null) {
            holder?.bindShop(mDataset.get(position),position)
        }
    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount(): Int {
        return mDataset.count()
    }
}
