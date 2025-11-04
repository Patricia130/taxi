package com.newtaxiprime.user.appcommon.interfaces

/**
 * @package com.newtaxiprime.user
 * @subpackage interfaces
 * @category ImageListener
 * @author Seen Technologies
 * 
 */

import okhttp3.RequestBody

/*****************************************************************
 * ImageListener
 */

interface ImageListener {
    fun onImageCompress(filePath: String, requestBody: RequestBody?)
}
