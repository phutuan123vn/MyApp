package com.example.myapplication.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.forgetpass2.*


class ForgetPass2 : AppCompatActivity() {

    private val runningActivities = mutableSetOf<ForgetPass2>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgetpass2)
        thoat.setOnClickListener{onBackPressed()}
        forget2bttn.setOnClickListener {
        //nut thay doi kiem tra pass
            if (forget2pass.text.toString().length>0 && forget2pass.text.toString().length>0){
                if (forget2pass.text.toString() == forget2passcon.text.toString()){
                    //Hai password match
                    finish()
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
    override fun onBackPressed() {
        AlertDialog.Builder(this).apply {
            setTitle("ĐÃ CHẮC CHẮN CHƯA")
            setMessage("Muốn Thoát Thật Không?")

            setPositiveButton("Thoát") { _,_ ->
                // if user press yes, then finish the current activity
                finish()
                super.onBackPressed()
            }
            setNegativeButton("Không"){ _,_ ->
            }
            setCancelable(true)
        }.create().show()
    }
    override fun finish() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)}

}
