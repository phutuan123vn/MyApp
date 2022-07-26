package com.example.myapplication.giangvien

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.example.myapplication.R
import androidx.navigation.Navigation
import com.example.myapplication.login.MainActivity
import com.example.myapplication.login.SignUp
import kotlinx.android.synthetic.main.gvinfo.view.*
import kotlinx.android.synthetic.main.gvinfochange.*
import kotlinx.android.synthetic.main.login1.*


class Gvinfo : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.gvinfo, container, false)
    }

}