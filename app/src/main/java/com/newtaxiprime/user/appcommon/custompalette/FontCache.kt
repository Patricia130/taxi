package com.newtaxiprime.user.appcommon.custompalette

/**
 * @package com.newtaxiprime.user
 * @subpackage custompalette
 * @category FontCache
 * @author Seen Technologies
 *
 */

import android.content.Context
import android.graphics.Typeface

import java.util.HashMap

/* ************************************************************
                FontCache
Used for custom font from assets folder
*************************************************************** */
object FontCache {

    private val fontCache = HashMap<String, Typeface>()

    /**
     * Return typeface font
     */
    fun getTypeface(fontname: String, context: Context): Typeface? {
        var typeface = fontCache[fontname]

        if (typeface == null) {
            try {
                typeface = Typeface.createFromAsset(context.assets, fontname)
            } catch (e: Exception) {
                return null
            }

            fontCache[fontname] = typeface
        }

        return typeface
    }
}