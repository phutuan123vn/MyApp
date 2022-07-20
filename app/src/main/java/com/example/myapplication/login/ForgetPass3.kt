package com.example.myapplication.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.R
import kotlinx.android.synthetic.main.forgetpass3.*

class ForgetPass3 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgetpass3)
        forget3bttn.setOnClickListener {
        //nut thay doi kiem tra pass
            if (forget3pass.text.toString().length>0 && forget3passcon.text.toString().length>0){
                if (forget3pass.text.toString() == forget3passcon.text.toString()){
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