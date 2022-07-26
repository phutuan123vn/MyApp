package com.example.myapplication.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.DatabaseHandler
import com.example.myapplication.R
import com.example.myapplication.model.User
import kotlinx.android.synthetic.main.signupgv.*

class SignUpGV : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signupgv)
        SUGback.setOnClickListener { startActivity(Intent(this,MainActivity::class.java)) }
        SUGbttn.setOnClickListener {
            if (
                SUGLname.text.toString().isEmpty()
                || SUGFname.text.toString().isEmpty()
                || SUGE.text.toString().isEmpty()
                || SUGPass.text.toString().isEmpty()
                || SUGPassCon.text.toString().isEmpty()
            ){
                Toast.makeText(this,"Please Fill",Toast.LENGTH_SHORT).show()
            }else{
                if (SUGPass.text.toString()>=8 && SUGPassCon.text.toString().length>=8){
                    if (SUGPass.text.toString()==SUGPassCon.text.toString()){
                        var dulieu= User(
                            SUGLname.text.toString(),
                            SUGFname.text.toString(),
                            1.toString(),
                            SUGE.text.toString(),
                            SUGPass.text.toString()
                        )
                        val db=DatabaseHandler(this)
                        db.insertData(dulieu)
                        Toast.makeText(this,"Success Sign In",Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this,LoginGV::class.java))
                    }else{
                        Toast.makeText(this,"Password not match",Toast.LENGTH_SHORT).show()
                    }
                }else{
                    Toast.makeText(this,"Password required at least 8 character")
                }
            }
        }
    }
}