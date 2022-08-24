package com.example.myapplication.sinhvien

import android.content.res.Resources
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class PaperAdapter(fragmentActivity: Svlichhoc) : FragmentStateAdapter(fragmentActivity){
    override fun getItemCount() = 7

    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> { thu2() }
            1 -> { thu3() }
            2 -> { thu4() }
            3 -> { thu5() }
            4 -> { thu6() }
            5 -> { thu7() }
            6 -> { chunhat() }
            else -> { throw Resources.NotFoundException("Position Not Found")}
    }
    }
}