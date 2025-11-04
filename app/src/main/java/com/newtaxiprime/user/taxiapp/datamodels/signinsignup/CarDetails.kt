package com.newtaxiprime.user.taxiapp.datamodels.signinsignup

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Seen Technologies on 6/9/18.
 */

class CarDetails {

    @SerializedName("id")
    @Expose
    var id: String=""
    @SerializedName("carName")
    @Expose
    var carName: String=""
    @SerializedName("description")
    @Expose
    var description: String=""
}
