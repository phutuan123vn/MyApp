package com.example.myapplication.sinhvien

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation
import com.example.myapplication.MYSQLHandler
import com.example.myapplication.R
import com.example.myapplication.giangvien.Gvpasschange
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.gvpasschange.*
import kotlinx.android.synthetic.main.svpasschange.*
import kotlinx.android.synthetic.main.svpasschange.view.*


class Svpasschange : Fragment() {
    companion object{
        var pass:String=""
        var passnew:String=""
        var passnewcon:String=""
        var al8:Boolean=false
        var num:Boolean=false
        var uc:Boolean=false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.svpasschange, container, false)
        //kiem tra dieu kien ky tu pass
        val SvPassOld = v.findViewById<TextInputEditText>(R.id.SVPassOld)
        val sVpasschangepassHT = v.findViewById<TextInputLayout>(R.id.svpasschangepassHT)
        SvPassOld.doAfterTextChanged {
            sVpasschangepassHT.helperText=null
            pass =SvPassOld.text.toString()
        }
        val SvPassNew = v.findViewById<TextInputEditText>(R.id.SVPassNew)
        val sVpasschangepassnewHT = v.findViewById<TextInputLayout>(R.id.svpasschangepassnewHT)
        SvPassNew.doAfterTextChanged {
            passnew =SvPassNew.text.toString()
            sVpasschangepassnewHT.helperText=null
            ValidatePass(passnew, passnewcon)
        }
        val SvPassNewCon = v.findViewById<TextInputEditText>(R.id.SVPassNewCon)
        val sVpasschangepassconnewHT = v.findViewById<TextInputLayout>(R.id.svpasschangepassconnewHT)
        SvPassNewCon.doAfterTextChanged {
            sVpasschangepassconnewHT.helperText=null
            passnewcon =SvPassNewCon.text.toString()
            ValidatePass(passnew, passnewcon)
        }
        // button cap nhat pass
        val update = v.findViewById<Button>(R.id.updatesvpasschange)
        val list= listOf<TextInputEditText>(SvPassOld,SvPassNew,SvPassNewCon)
        val listht= listOf<TextInputLayout>(sVpasschangepassHT,sVpasschangepassnewHT,sVpasschangepassconnewHT)
        val listzip=list.zip(listht)
        update.setOnClickListener(){
            if (SvPassOld.text.toString().isEmpty()
                ||SvPassNew.text.toString().isEmpty()
                ||SvPassNewCon.text.toString().isEmpty())
            {
                listzip.forEach{
                    if (it.first.text.toString().isEmpty()){
                        it.second.helperText="This can't be Empty"
                    }
                }
            }
            else{
                if (pass!= MYSQLHandler.user.Password){
                    sVpasschangepassHT.helperText="Password not the same old password"
                }
                if (!al8 || !uc ||!num){
                    sVpasschangepassconnewHT.helperText="Password must be correct"
                    sVpasschangepassnewHT.helperText="Password must be correct"
                }
                if (passnew!= passnewcon){
                    sVpasschangepassconnewHT.helperText="Password must match"
                    sVpasschangepassnewHT.helperText="Password must match"
                }
                if(al8 && uc && num && (passnew == passnewcon) && (pass==MYSQLHandler.user.Password)){
                    val db=MYSQLHandler(requireContext())
                    if (passnew==MYSQLHandler.user.Password){
                        sVpasschangepassconnewHT.helperText="Password can't be the same old password"
                        sVpasschangepassnewHT.helperText="Password can't be the same old password"
                    }else {
                        db.updatePass(passnew)
                        val svpasschange = Svpasschange()
                        val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
                        transaction.replace(R.id.framelayoutsvnav, svpasschange)
                        transaction.commit()
                    }
                }
            }
        }
        return v
    }
    private fun ValidatePass(pass:String,passcon:String){
        if (pass.length>=8 && passcon.length>=8 ){
            al8 =true
            SVPassBox1.setCardBackgroundColor(Color.parseColor("#FF03DAC5"))
        }else{
            al8 =false
            SVPassBox1.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"))
        }
        if(pass.matches((".*[A-Z].*").toRegex()) && passcon.matches((".*[A-Z].*").toRegex()) ){
            uc =true
            SVPassBox3.setCardBackgroundColor(Color.parseColor("#FF03DAC5"))
        }else{
            uc =false
            SVPassBox3.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"))
        }
        if (pass.matches((".*[0-9].*").toRegex()) && passcon.matches((".*[0-9].*").toRegex()) ){
            num =true
            SVPassBox2.setCardBackgroundColor(Color.parseColor("#FF03DAC5"))
        }else{
            num =false
            SVPassBox2.setCardBackgroundColor(Color.parseColor("#FFFFFFFF"))
        }
    }

}