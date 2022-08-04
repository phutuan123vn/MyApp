package com.example.myapplication.quanly

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.myapplication.R
import kotlinx.android.synthetic.main.sv_dkmhchange.view.*


class Quanlysvdkmonchange : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.sv_dkmhchange, container, false)
        val back = v.findViewById<Button>(R.id.quanlysvdkmonchangeback)
        back.setOnClickListener {
            val quanlysvdkmon = Quanlysvdkmon()
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.framelayoutqlnav, quanlysvdkmon)
            transaction.commit()
        }
        return v
    }

}