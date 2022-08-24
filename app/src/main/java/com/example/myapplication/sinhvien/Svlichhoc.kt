package com.example.myapplication.sinhvien

import android.content.res.Resources
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.example.myapplication.R
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class Svlichhoc : Fragment() {
    private lateinit var tabLayout: TabLayout
    private lateinit var viewPaper: ViewPager2
    private val tabTitles = arrayListOf("T2","T3","T4","T5","T6","T7","CN")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v =  inflater.inflate(R.layout.tablichoc, container, false)

        tabLayout = v.findViewById(R.id.tabLayout)
        viewPaper = v.findViewById(R.id.viewPaper)
        viewPaper.adapter = PaperAdapter(this)

        TabLayoutMediator(tabLayout,viewPaper){ tab,position ->
            tab.text =tabTitles[position]
//            tab.text = when(position){
//                0 -> { "T2" }
//                1 -> { "T3" }
//                2 -> { "T4" }
//                3 -> { "T5" }
//                4 -> { "T6" }
//                5 -> { "T7" }
//                6 -> { "CN" }
//                else -> { throw Resources.NotFoundException("Position Not Found")}
//            }
        }.attach()
        for (i in 0..6){
            val textview = LayoutInflater.from(requireContext()).inflate(R.layout.tab_title,null)
                    as TextView
            tabLayout.getTabAt(i)?.customView = textview
        }
        return v
    }

}