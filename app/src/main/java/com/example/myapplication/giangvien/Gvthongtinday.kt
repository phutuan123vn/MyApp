package com.example.myapplication.giangvien

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.myapplication.R
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.gvthongtinday.view.*


class Gvthongtinday : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.gvthongtinday, container, false)
    }

}