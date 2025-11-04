package com.newtaxiprime.user.taxiapp.datamodels.main

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Seen Technologies on 7/9/18.
 */

class LocationModel {

    @SerializedName("latitude")
    @Expose
    var latitude: String=""

    @SerializedName("longitude")
    @Expose
    var longitude: String=""
}
