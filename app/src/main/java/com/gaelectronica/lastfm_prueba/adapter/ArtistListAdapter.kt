package com.gaelectronica.lastfm_prueba.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gaelectronica.lastfm_prueba.DetailsActivity
import com.gaelectronica.lastfm_prueba.R
import com.gaelectronica.lastfm_prueba.model.Artist

class ArtistListAdapter (private val list: ArrayList<Artist>, private val context: Context):
    RecyclerView.Adapter<ArtistListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder != null){
            holder.bindView(list[position])
        }
    }

    override fun getItemCount(): Int {
        return  list.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var name = itemView.findViewById<TextView>(R.id.txtName)

        fun bindView(artist: Artist) {
            name.text = artist.artist_name

            itemView.setOnClickListener {
                var intent = Intent(context, DetailsActivity::class.java)
                intent.putExtra("name", artist.artist_name)
                context.startActivity(intent)
            }
        }
    }
}