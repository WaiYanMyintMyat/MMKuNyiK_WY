package com.example.waiyan.mmkunyik.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.waiyan.mmkunyik.R

class RegisterFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val registerView = inflater.inflate(R.layout.fragment_register,container,false)
        return registerView
    }
}