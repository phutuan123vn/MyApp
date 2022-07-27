package com.example.myapplication.sinhvien

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.myapplication.R
//import com.example.myapplication.databinding.SvdiemdanhBinding
import com.example.myapplication.login.LoginSV
import kotlinx.android.synthetic.main.svdiemdanh.*
import kotlinx.android.synthetic.main.svdiemdanh.view.*

class Svdiemdanh : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //val bind = SvdiemdanhBinding.inflate(layoutInflater)
        //val svdiemdanhqr = Svdiemdanhqr()
        //bind.quetmaqr.setOnClickListener{
        //    fragmentManager?.beginTransaction()?.apply {
        //        replace(R.id.framelayoutsvnav, svdiemdanhqr, Svdiemdanhqr::class.java.simpleName,)
        //            .addToBackStack(null)
        //            .commit()
        //    }
        //}
        //return bind.root
        return inflater.inflate(R.layout.svdiemdanh, container, false)
    }
}