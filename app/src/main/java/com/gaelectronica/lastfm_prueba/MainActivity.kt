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
import com.gaelectronica.lastfm_prueba.adapter.ArtistListAdapter
import com.gaelectronica.lastfm_prueba.model.Artist
import org.json.JSONException
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    var volleyRequest: RequestQueue? = null
    var arrayList: ArrayList<Artist>? = null
    var artistAdapter: ArtistListAdapter? = null
    var layoutManager: RecyclerView.LayoutManager? = null
    var recliclyerArtist: RecyclerView? = null

    var stringLink = "https://ws.audioscrobbler.com/2.0/?method=geo.gettopartists&country=colombia&limit=10&page=1&api_key=cf2894b9c73a323e24f5c6a9aab1eb85&format=json"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recliclyerArtist = findViewById<RecyclerView>(R.id.rvArtist)

        arrayList = ArrayList<Artist>()
        volleyRequest = Volley.newRequestQueue(this)

        getJsonObject(stringLink)
    }

    fun getJsonObject(Url: String) {
        val jsonObjectReq = JsonObjectRequest(
            Request.Method.GET, Url,
            Response.Listener {
                    response: JSONObject ->
                try {

                    var artist = response.getJSONObject("topartists")

                    var arrayArtist = artist.getJSONArray("artist")

                    for (i in 0..arrayArtist.length() -1){
                        //
                        var nameObject = arrayArtist.getJSONObject(i).getString("name")

                        var artist = Artist()

                        artist.artist_name = nameObject

                        arrayList!!.add(artist)

                        artistAdapter = ArtistListAdapter(arrayList!!, this)
                        layoutManager = LinearLayoutManager(this)

                        recliclyerArtist?.layoutManager = layoutManager
                        recliclyerArtist?.adapter = artistAdapter
                    }

                    artistAdapter!!.notifyDataSetChanged()


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