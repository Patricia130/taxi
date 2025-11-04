package com.newtaxiprime.user.taxiapp.datamodels

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by Seen Technologies on 12/9/18.
 */

class EmergencyContactModel {

    @SerializedName("id")
    @Expose
    var id: String =""
    @SerializedName("name")
    @Expose
    var name: String=""
    @SerializedName("mobile_number")
    @Expose
    var mobileNumber: String=""
}
