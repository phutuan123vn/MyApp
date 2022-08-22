package com.example.myapplication.sinhvien

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation
import com.example.myapplication.MYSQLHandler
import com.example.myapplication.R
import com.example.myapplication.model.TEMP
import kotlinx.android.synthetic.main.svinfo.*
import kotlinx.android.synthetic.main.svinfochange.*
import kotlinx.android.synthetic.main.svinfochange.view.*
import java.util.regex.Pattern


class Svinfochange : Fragment() {
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
        val v = inflater.inflate(R.layout.svinfochange, container, false)
        val back = v.findViewById<Button>(R.id.svinfochangeback)
        val update = v.findViewById<Button>(R.id.svinfochangeupdate)
        val svho=v.findViewById<TextView>(R.id.SVhoedit)
        val svten=v.findViewById<TextView>(R.id.SVtenedit)
        val svnoiedit=v.findViewById<EditText>(R.id.SVnoiedit)
        val svPIDedit=v.findViewById<EditText>(R.id.SVcmndedit)
        val svphone=v.findViewById<EditText>(R.id.SVsdtedit)
        val svmail=v.findViewById<EditText>(R.id.SVmailedit)
        val arg=this.arguments
        Log.d("SAD","$ho $ten")
        ho=arg?.getString("Ho")
        ten=arg?.getString("Ten")
        address=arg?.getString("Address")
        PID=arg?.getString("PersonID")
        phone=arg?.getString("Phone")
        mail=arg?.getString("Email")
        svho.text=ho
        svten.text=ten
        svnoiedit.setText(address)
        svPIDedit.setText(PID)
        svphone.setText(phone)
        svmail.setText(mail)
        back.setOnClickListener {
            val Svinfo = Svinfo()
            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
            transaction.setCustomAnimations(R.anim.slide_in_left,R.anim.slide_out_right,R.anim.slide_in_right,R.anim.slide_out_left)
            transaction.replace(R.id.framelayoutsvnav, Svinfo)
            transaction.commit()
        }
        update.setOnClickListener {
            if (svnoiedit.text.toString().isEmpty()
                || svPIDedit.text.toString().isEmpty()
                || svphone.text.toString().isEmpty()
                || svmail.text.toString().isEmpty()){
                Toast.makeText(requireContext(),"Please fill all your Information",Toast.LENGTH_SHORT).show()
            }else{
                var emailcheck:Boolean=false
                var phonecheck:Boolean=false
                var PIDcheck:Boolean=false
                if (isEmailValid(svmail.text.toString().trim())){
                    emailcheck=true
                }else{
                    Toast.makeText(requireContext(),"Email is Invalid",Toast.LENGTH_SHORT).show()
                }
                if (svPIDedit.text.toString().trim().length==10){
                    PIDcheck=true
                }else{
                    Toast.makeText(requireContext(),"Person ID is Invalid",Toast.LENGTH_SHORT).show()
                }
                if (svphone.text.toString().trim().length==10){
                    phonecheck=true
                }else{
                    Toast.makeText(requireContext(),"Number is Invalid",Toast.LENGTH_SHORT).show()
                }
                if (emailcheck && phonecheck && PIDcheck){
                    val db=MYSQLHandler(requireContext())
                    db.checkmail()
                }
            }
//            val Svinfo = Svinfo()
//            val transaction: FragmentTransaction = fragmentManager!!.beginTransaction()
//            transaction.setCustomAnimations(
//                R.anim.slide_in_left,
//                R.anim.slide_out_right,
//                R.anim.slide_in_right,
//                R.anim.slide_out_left
//            )
//            transaction.replace(R.id.framelayoutsvnav, Svinfo)
//            transaction.commit()
        }

        return v
    }
    fun isEmailValid(email: String): Boolean {
        return Pattern.compile(
            "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))"
                    + "@gmail.com"  //thay doi email tai day
//                     "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
//                    "\\@" +
//                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
//                    "(" +
//                    "\\." +
//                    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
//                    ")+"
        ).matcher(email).matches()
    }
}

