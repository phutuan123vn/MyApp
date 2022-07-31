package com.example.myapplication.quanly

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation
import com.example.myapplication.R
import kotlinx.android.synthetic.main.gv_qldl.view.*


class GVqldl : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.gv_qldl, container, false)
        val detail = v.findViewById<Button>(R.id.qldldetailgva)
        detail.setOnClickListener {
            val GVqldldetail = GVqldldetail()
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.framelayoutqlnav, GVqldldetail)
            transaction.commit()
        }
        return v
    }

}