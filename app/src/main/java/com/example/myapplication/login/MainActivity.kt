package com.example.myapplication.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import com.example.myapplication.giangvien.GVnavigation
import com.example.myapplication.quanly.QLnavigation
import com.example.myapplication.sinhvien.SVnavigation
import kotlinx.android.synthetic.main.account.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.account)
//        var context = this
//        button_add.setOnClickListener {
//            if (etvFname.text.toString().length > 0
//                && etvLname.text.toString().length > 0
//            ) {
//                var dulieu = User(etvLname.text.toString(),etvFname.text.toString(),etvStucode.text.toString())
//                var db = DatabaseHandler(context)
//                db.insertData(dulieu)
//            } else {
//                Toast.makeText(context, "Please fill information", Toast.LENGTH_SHORT).show()
//            }
        bttnSV.setOnClickListener {
            val intent= Intent(this, LoginSV::class.java)
            startActivity(intent)
        }//GV button
        bttnGV.setOnClickListener {
            val intent= Intent(this, LoginGV::class.java)
            startActivity(intent)
        }
        bttnQL.setOnClickListener {
            val intent= Intent(this, LoginQL::class.java)
            startActivity(intent)
        }


        }
    }
