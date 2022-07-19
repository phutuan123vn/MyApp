package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.account.*
import kotlinx.android.synthetic.main.activity_main.*

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
            val intent= Intent(this,LoginSV::class.java)
            startActivity(intent)
        }//GV button
        bttnGV.setOnClickListener {
            val intent= Intent(this,LoginGV::class.java)
            startActivity(intent)
        }
        bttnQL.setOnClickListener {
            val intent= Intent(this,LoginQL::class.java)
            startActivity(intent)
        }
        Acsignup.setOnClickListener{
            val intent=Intent(this,SingUp::class.java)
            startActivity(intent)
        }
        //Text clickable
        Acforget.setOnClickListener{
            val intent=Intent(this,ForgetPass::class.java)
            startActivity(intent)
        }

        }
    }
