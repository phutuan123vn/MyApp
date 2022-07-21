package com.example.myapplication.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.R
import kotlinx.android.synthetic.main.forgetpass2.*


class ForgetPass2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgetpass2)
        forget2bttn.setOnClickListener {
        //nut thay doi kiem tra pass
            if (forget2pass.text.toString().length>0 && forget2pass.text.toString().length>0){
                if (forget2pass.text.toString() == forget2passcon.text.toString()){
                    //Hai password match
                    startActivity(Intent(this, MainActivity::class.java))
                }else{
                    Toast.makeText(this,"Password not match",Toast.LENGTH_SHORT).show()
                }
            }
            //ko co pass
            else{
                Toast.makeText(this,"Please Fill Your Password",Toast.LENGTH_SHORT).show()
            }
        }
    }
}