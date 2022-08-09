package com.example.myapplication.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import kotlinx.android.synthetic.main.forgetpass1.*
import kotlinx.android.synthetic.main.forgetpass2.*


class ForgetPass2 : AppCompatActivity() {

    private val runningActivities = mutableSetOf<ForgetPass2>()

    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgetpass2)
        thoat.setOnClickListener{onBackPressed()}
        forget2bttn.setOnClickListener {
            hidekeyboard()
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
    fun hidekeyboard() {
        val xem = this.currentFocus
        if (xem != null) {
            val hide = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            hide.hideSoftInputFromWindow(xem.windowToken, 0)
        }
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
        forget1code.clearFocus()
    }
    fun closekeyboard(view: View) {
        hidekeyboard()
    }

}
