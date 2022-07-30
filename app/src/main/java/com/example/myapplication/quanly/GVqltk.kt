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
import com.example.myapplication.sinhvien.Svdiemdanhqr
import kotlinx.android.synthetic.main.gv_qltk.view.*


class GVqltk : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // chi tiet tai khoan, chuyen sang fragment detail
        val v = inflater.inflate(R.layout.gv_qltk, container, false)
        val detail = v.findViewById<Button>(R.id.detailgva)
        detail.setOnClickListener {
            val Gvqltkdetail = Gvqltkdetail()
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.replace(R.id.framelayoutqlnav, Gvqltkdetail)
            transaction.commit()
        }
        return v
    }

}