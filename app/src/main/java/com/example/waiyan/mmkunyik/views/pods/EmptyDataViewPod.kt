package com.example.waiyan.mmkunyik.views.pods

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.view_pod_empty.view.*

class EmptyDataViewPod : RelativeLayout {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onFinishInflate() {
        super.onFinishInflate()
    }

    fun setEmptyData(emptyImage: Int, noData: String) {
        iv_empty.setImageResource(emptyImage)
        tv_empty.text = noData
    }
}