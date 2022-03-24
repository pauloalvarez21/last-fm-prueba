package com.gaelectronica.lastfm_prueba.model

class Track() {
    var track_name:String? = null
    var url_name:String? = null

    constructor(track_name: String, url_name: String) : this() {
        this.track_name = track_name
        this.url_name = url_name
    }
}