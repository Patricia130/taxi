package com.newtaxiprime.user.appcommon.drawpolyline

/**
 * @package com.newtaxiprime.userdriver
 * @subpackage map.drawpolyline
 * @category PolylineOptionsInterface
 * @author Seen Technologies
 * 
 */

import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.PolylineOptions
import com.newtaxiprime.user.taxiapp.datamodels.StepsClass
import java.util.*

interface PolylineOptionsInterface {
    fun getPolylineOptions(output: PolylineOptions, points: ArrayList<LatLng>, distance: String, overviewPolyline: String, arrivalTime: String, stepPoints: ArrayList<StepsClass>, totalDuration : Int)
}
