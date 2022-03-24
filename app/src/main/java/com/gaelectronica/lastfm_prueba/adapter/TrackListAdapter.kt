package com.gaelectronica.lastfm_prueba.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gaelectronica.lastfm_prueba.R
import com.gaelectronica.lastfm_prueba.model.Track

class TrackListAdapter (private val list: ArrayList<Track>, private val context: Context):
    RecyclerView.Adapter<TrackListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_details, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder != null){
            holder.bindView(list[position])
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var track = itemView.findViewById<TextView>(R.id.txtSong)
        var url = itemView.findViewById<TextView>(R.id.txtUrl)

        fun bindView(tracks: Track) {
            track.text = "Cancion: " + tracks.track_name
            url.text = tracks.url_name
        }

    }

}