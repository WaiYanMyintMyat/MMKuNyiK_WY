package com.example.waiyan.mmkunyik.activities

import android.os.Bundle
import com.example.waiyan.mmkunyik.R
import com.example.waiyan.mmkunyik.fragments.LoginFragment
import com.example.waiyan.mmkunyik.fragments.RegisterFragment
import kotlinx.android.synthetic.main.activity_account_control.*

class AccountControlActivity : BaseActivity() {

    companion object {
        const val ACTION_TYPE = "action_type"
        const val ACTION_TYPE_LOGIN = 1111
        const val ACTION_TYPE_REGISTER =2222
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account_control)

        val actionType = intent.extras.getInt(ACTION_TYPE)

        if (actionType == ACTION_TYPE_LOGIN) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.flContainer, LoginFragment())
                    .commit()
        } else if (actionType == ACTION_TYPE_REGISTER) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.flContainer, RegisterFragment())
                    .commit()
        }

    }
}