package com.example.myapplication.sinhvien

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.example.myapplication.R
//import com.example.myapplication.databinding.SvdiemdanhqrBinding
import com.example.myapplication.login.LoginSV
import kotlinx.android.synthetic.main.svdiemdanhqr.*
import kotlinx.android.synthetic.main.svdiemdanhqr.view.*


class Svdiemdanhqr : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
    //    val bind = SvdiemdanhqrBinding.inflate(layoutInflater)
    //    val svdiemdanh = Svdiemdanh()
    //    bind.quetmaqrback.setOnClickListener{
    //        fragmentManager?.beginTransaction()?.apply {
    //            replace(R.id.framelayoutsvnav, svdiemdanh, Svdiemdanh::class.java.simpleName,)
    //                .addToBackStack(null)
    //                .commit()
    //        }
    //    }
    //    return bind.root
        return inflater.inflate(R.layout.svdiemdanhqr, container, false)
    }
}