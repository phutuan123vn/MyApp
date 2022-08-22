package com.example.myapplication.giangvien

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.FragmentTransaction
import com.example.myapplication.R
import androidx.navigation.Navigation
import com.example.myapplication.MYSQLHandler
import com.example.myapplication.model.TEMP
import kotlinx.android.synthetic.main.gvinfo.*
import kotlinx.android.synthetic.main.gvinfochange.*
import kotlinx.android.synthetic.main.gvinfochange.view.*
import kotlinx.android.synthetic.main.svinfochange.*



class Gvinfochange : Fragment() {
    private var ho:String? = ""
    private var ten:String? = ""
    private var address:String? = ""
    private var PID:String? = ""
    private var phone:String? = ""
    private var mail:String? = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.gvinfochange, container, false)
        val back = v.findViewById<Button>(R.id.gvinfochangeback)
        val gvhoedit=v.findViewById<TextView>(R.id.GVhoedit)
        val gvtenedit=v.findViewById<TextView>(R.id.GVtenedit)
        val gvnoiedit=v.findViewById<EditText>(R.id.GVnoiedit)
        val gvPIDedit=v.findViewById<EditText>(R.id.GVcmndedit)
        val gvphonedit=v.findViewById<EditText>(R.id.GVsdtedit)
        val gvmailedit=v.findViewById<EditText>(R.id.GVmailedit)
        val arg=this.arguments
        ho=arg?.getString("Ho")
        ten=arg?.getString("Ten")
        address=arg?.getString("Address")
        PID=arg?.getString("PersonID")
        phone=arg?.getString("Phone")
        mail=arg?.getString("Email")
        gvhoedit.text=ho
        gvtenedit.text=ten
        gvnoiedit.setText(address)
        gvphonedit.setText(phone)
        gvmailedit.setText(mail)
        gvPIDedit.setText(PID)
        back.setOnClickListener {
            val Gvinfo = Gvinfo()
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right,R.anim.slide_in_right,R.anim.slide_out_left)
            transaction.replace(R.id.framelayoutgvnav, Gvinfo)
            transaction.commit()
        }
        val update = v.findViewById<Button>(R.id.gvinfochangeupdate)
        update.setOnClickListener {
            val Gvinfo = Gvinfo()
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.setCustomAnimations(
                R.anim.slide_in_left,
                R.anim.slide_out_right,
                R.anim.slide_in_right,
                R.anim.slide_out_left
            )
            transaction.replace(R.id.framelayoutgvnav, Gvinfo)
            transaction.commit()
        }
        return v
    }

}