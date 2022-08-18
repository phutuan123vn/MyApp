package com.example.myapplication.giangvien

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.clearFragmentResult
import com.example.myapplication.R
import androidx.navigation.Navigation
import com.example.myapplication.MYSQLHandler
import com.example.myapplication.giangvien.Gvpasschange.Companion.pass
import com.example.myapplication.giangvien.Gvpasschange.Companion.passnew
import com.example.myapplication.login.SignUpSV
import com.example.myapplication.model.TEMP
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.android.synthetic.main.gvpasschange.*
import kotlinx.android.synthetic.main.gvpasschange.view.*
import kotlinx.android.synthetic.main.signupsv.*


class Gvpasschange : Fragment() {
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
        val v =  inflater.inflate(R.layout.gvpasschange, container, false)
        // kiểm tra điều kiện ký tự pass
        val GvPassOld = v.findViewById<TextInputEditText>(R.id.GVPassOld)
        val gVpasschangepassHT = v.findViewById<TextInputLayout>(R.id.gvpasschangepassHT)
        GvPassOld.doAfterTextChanged {
            gVpasschangepassHT.helperText=null
            pass=GvPassOld.text.toString()
        }
        val GvPassNew = v.findViewById<TextInputEditText>(R.id.GVPassNew)
        val gVpasschangepassnewHT = v.findViewById<TextInputLayout>(R.id.gvpasschangepassnewHT)
        GvPassNew.doAfterTextChanged {
            passnew=GvPassNew.text.toString()
            gVpasschangepassnewHT.helperText=null
            ValidatePass(passnew, passnewcon)
        }
        val GvPassNewCon = v.findViewById<TextInputEditText>(R.id.GVPassNewCon)
        val gVpasschangepassconnewHT = v.findViewById<TextInputLayout>(R.id.gvpasschangepassconnewHT)
        GvPassNewCon.doAfterTextChanged {
            gVpasschangepassconnewHT.helperText=null
            passnewcon=GvPassNewCon.text.toString()
            ValidatePass(passnew, passnewcon)
        }
        // button cap nhat pass
        val update = v.findViewById<Button>(R.id.updategvpasschange)
        val list= listOf<TextInputEditText>(GvPassOld,GvPassNew,GvPassNewCon)
        val listht= listOf<TextInputLayout>(gVpasschangepassHT,gVpasschangepassnewHT,gVpasschangepassconnewHT)
        val listzip=list.zip(listht)
        update.setOnClickListener(){
            if (GvPassOld.text.toString().isEmpty()
                ||GvPassNew.text.toString().isEmpty()
                ||GvPassNewCon.text.toString().isEmpty())
            {
                listzip.forEach{
                    if (it.first.text.toString().isEmpty()){
                        it.second.helperText="This can't be Empty"
                    }
                }
            }
            else{
                if (pass!=MYSQLHandler.user.Password){
                    gVpasschangepassHT.helperText="Password not the same old password"
                }
                if (!al8 || !uc ||!num){
                    gVpasschangepassconnewHT.helperText="Password must be correct"
                    gVpasschangepassnewHT.helperText="Password must be correct"
                }
                if (passnew!= passnewcon){
                    gVpasschangepassconnewHT.helperText="Password must match"
                    gVpasschangepassnewHT.helperText="Password must match"
                }
                if(al8 && uc && num && (passnew == passnewcon) && (pass==MYSQLHandler.user.Password)){
                    val db=MYSQLHandler(requireContext())
                    if (passnew==MYSQLHandler.user.Password){
                        gVpasschangepassconnewHT.helperText="Password can't be the same old password"
                        gVpasschangepassnewHT.helperText="Password can't be the same old password"
                    }else {
                        db.updatePass(passnew)
                        val gvpasschange = Gvpasschange()
                        val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
                        transaction.replace(R.id.framelayoutgvnav, gvpasschange)
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
            GVPassBox1.setCardBackgroundColor(Color.parseColor("#FF03DAC5"))
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
