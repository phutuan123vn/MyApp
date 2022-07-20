package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.signup.*

class SingUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signupsv)
        SUback.setOnClickListener {
            intent=Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
        SUbttn.setOnClickListener {
            //nut nhan dang ky kiem tra input
            if (
                SULname.text.toString().length>0 &&
                SUFname.text.toString().length>0 &&
                SUE.text.toString().length>0 &&
                SUPass.text.toString().length>0 &&
                SUPassCon.text.toString().length>0
            ){
                if (SUPass.text.toString() == SUPassCon.text.toString()) {
                    var dulieu = User(
                        SULname.text.toString(),
                        SUFname.text.toString(),
                        0.toString(),
                        SUE.text.toString(),
                        SUPass.text.toString()
                    )
                    var db = DatabaseHandler(this)
                    db.insertData(dulieu)
                    startActivity(Intent(this,MainActivity::class.java))
                } //KIem tra Password match
                else{
                    Toast.makeText(this,"Password not Correct",Toast.LENGTH_SHORT).show()
                }
            }// Need to fill all input
            else{
                Toast.makeText(this,"Please Fill Information",Toast.LENGTH_SHORT).show()
            }
        }
    }
}