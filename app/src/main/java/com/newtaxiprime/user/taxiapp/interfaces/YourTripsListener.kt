package com.newtaxiprime.user.taxiapp.interfaces

/**
 * @package com.newtaxiprime.usereatsdriver
 * @subpackage interfaces
 * @category YourTripsListener
 * @author Seen Technologies
 * 
 */

import android.content.res.Resources

import com.newtaxiprime.user.taxiapp.sidebar.trips.YourTrips


/*****************************************************************
 * YourTripsListener
 */

interface YourTripsListener {

    val res: Resources

    val instance: YourTrips
}
