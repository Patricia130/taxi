package com.newtaxiprime.user.appcommon.interfaces

/**
 * @package com.newtaxiprime.user
 * @subpackage interfaces
 * @category ServiceListener
 * @author Seen Technologies
 * 
 */

import com.newtaxiprime.user.appcommon.datamodels.JsonResponse

/*****************************************************************
 * ServiceListener
 */
interface ServiceListener {

    fun onSuccess(jsonResp: JsonResponse, data: String)

    fun onFailure(jsonResp: JsonResponse, data: String)
}
