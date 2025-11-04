package com.newtaxiprime.user.appcommon.custompalette

/**
 * @package com.newtaxiprime.user
 * @subpackage custompalette
 * @category FontEditText
 * @author Seen Technologies
 *
 */

import android.content.Context
import androidx.appcompat.widget.AppCompatEditText
import android.util.AttributeSet

/* ************************************************************
                FontEditText
Used for custom Edit Text when you want use custom fonts in edit text
*************************************************************** */
class FontEditText : AppCompatEditText {

    constructor(context: Context) : super(context) {

        CustomFontUtils.applyCustomFont(this, context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {

        CustomFontUtils.applyCustomFont(this, context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet, defStyle: Int) : super(context, attrs, defStyle) {

        CustomFontUtils.applyCustomFont(this, context, attrs)
    }
}