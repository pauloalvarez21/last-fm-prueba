package com.gaelectronica.lastfm_prueba

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.gaelectronica.lastfm_prueba.adapter.TrackListAdapter
import com.gaelectronica.lastfm_prueba.model.Track
import org.json.JSONException
import org.json.JSONObject

class DetailsActivity : AppCompatActivity() {

    var volleyRequest: RequestQueue? = null
    var arrayList: ArrayList<Track>? = null
    var trackAdapter: TrackListAdapter? = null
    var layoutManager: RecyclerView.LayoutManager? = null
    var recliclyerTrack: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        var extras = intent.extras
        var name = extras!!.get("name")

        recliclyerTrack = findViewById<RecyclerView>(R.id.rvTrack)

        arrayList = ArrayList<Track>()

        volleyRequest = Volley.newRequestQueue(this)

        if(extras != null) {
            val stringLink = "https://ws.audioscrobbler.com/2.0/?method=artist.gettoptracks&artist=$name&limit=10&api_key=cf2894b9c73a323e24f5c6a9aab1eb85&format=json"
            Log.d("Get", "Aqui 1")
            getJsonObject(stringLink)
        }
    }

    fun getJsonObject(Url: String) {
        val jsonObjectReq = JsonObjectRequest(
            Request.Method.GET, Url,
            Response.Listener {
                    response: JSONObject ->
                try {

                    var tracks = response.getJSONObject("toptracks")

                    var arrayTracks = tracks.getJSONArray("track")

                    for (i in 0..arrayTracks.length() -1){
                        //
                        var trackObject = arrayTracks.getJSONObject(i).getString("name")
                        var urlObject = arrayTracks.getJSONObject(i).getString("url")

                        var track = Track()

                        track.track_name = trackObject
                        track.url_name = urlObject

                        arrayList!!.add(track)

                        trackAdapter = TrackListAdapter(arrayList!!, this)
                        layoutManager = LinearLayoutManager(this)

                        recliclyerTrack!!.layoutManager = layoutManager
                        recliclyerTrack!!.adapter = trackAdapter
                    }

                    trackAdapter!!.notifyDataSetChanged()

                }catch (e:  JSONException){e.printStackTrace()}
            },
            Response.ErrorListener {
                    error: VolleyError? ->
                try {
                    Log.d("Error", error.toString())
                }catch (e: JSONException){
                    e.printStackTrace()
                }
            }
        )

        volleyRequest!!.add(jsonObjectReq)
    }
}