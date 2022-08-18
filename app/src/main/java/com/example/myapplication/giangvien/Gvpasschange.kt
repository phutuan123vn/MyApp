package com.example.myapplication.giangvien

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import com.example.myapplication.R
import androidx.navigation.Navigation
import com.example.myapplication.giangvien.Gvpasschange.Companion.pass
import com.example.myapplication.giangvien.Gvpasschange.Companion.passnew
import kotlinx.android.synthetic.main.gvpasschange.*
import kotlinx.android.synthetic.main.gvpasschange.view.*



class Gvpasschange : Fragment() {
    companion object{
        var pass:String=""
        var passnew:String=""
        var passnewcon:String=""
        var al8:Boolean=false
        var num:Boolean=false
        var uc:Boolean=false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        this.onTextChange()
        val v =  inflater.inflate(R.layout.gvpasschange, container, false)
    }

    private fun onTextChange() {
        GVPassOld.doAfterTextChanged {
            pass=GVPassOld.text.toString()
        }
        GVPassNew.doAfterTextChanged {
            passnew=GVPassNew.text.toString()
            ValidatePass(passnew, passnewcon)
        }
        GVPassNewCon.doAfterTextChanged {
            passnewcon=GVPassNewCon.text.toString()
            ValidatePass(passnew, passnewcon)
        }

    }
    private fun ValidatePass(pass:String,passcon:String){
        if (pass.length>=8 && passcon.length>=8 ){
            GVPassBox1.setCardBackgroundColor(Color.parseColor("#FF03DAC5"))
            al8 =true
        }else{
            al8 =false
            GVPassBox1.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"))
        }
        if(pass.matches((".*[A-Z].*").toRegex()) && passcon.matches((".*[A-Z].*").toRegex()) ){
            uc =true
            GVPassBox3.setCardBackgroundColor(Color.parseColor("#FF03DAC5"))
        }else{
            uc =false
            GVPassBox3.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"))
        }
        if (pass.matches((".*[0-9].*").toRegex()) && passcon.matches((".*[0-9].*").toRegex()) ){
            num =true
            GVPassBox2.setCardBackgroundColor(Color.parseColor("#FF03DAC5"))
        }else{
            num =false
            GVPassBox2.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"))
        }
    }
}
