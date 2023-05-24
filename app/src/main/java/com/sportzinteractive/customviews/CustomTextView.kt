package com.sportzinteractive.customviews

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.textview.MaterialTextView

class CustomTextView : MaterialTextView {
    constructor(context: Context?) : super(context!!) {
        init(context)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    ) {
        init(context)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!, attrs, defStyleAttr
    ) {
        init(context)
    }

    fun init(context: Context?) {
    }
}