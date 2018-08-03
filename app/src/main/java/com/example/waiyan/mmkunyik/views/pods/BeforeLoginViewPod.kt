package com.example.waiyan.mmkunyik.views.pods

import android.content.Context
import android.util.AttributeSet
import android.widget.RelativeLayout
import com.example.waiyan.mmkunyik.delegates.BeforeLoginDelegate
import kotlinx.android.synthetic.main.view_pod_before_login.view.*

//primary constructor
class BeforeLoginViewPod : RelativeLayout {

    lateinit var mDelegate: BeforeLoginDelegate

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onFinishInflate() {
        super.onFinishInflate()
        btnRegister.setOnClickListener(){
            mDelegate.onTapRegister()
        }
        btnLogin.setOnClickListener(){
            mDelegate.onTapLogin()
        }
    }

    fun setDelegate(beforeLoginDelegate: BeforeLoginDelegate) {
        mDelegate = beforeLoginDelegate
    }
}