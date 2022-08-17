package com.example.myapplication.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.R
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.giangvien.GVnavigation
import com.example.myapplication.quanly.QLnavigation
import com.example.myapplication.sinhvien.SVnavigation
import kotlinx.android.synthetic.main.account.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        supportActionBar?.hide()
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
            val intent= Intent(this, SVnavigation::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.no_animation)
        }
        bttnGV.setOnClickListener {
            val intent= Intent(this, GVnavigation::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.no_animation)
        }
        bttnQL.setOnClickListener {
            val intent= Intent(this, QLnavigation::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_in_right, R.anim.no_animation)
        }
    }
    override fun onBackPressed() {
        AlertDialog.Builder(this).apply {
            setTitle("WARNING!")
            setMessage("Are you sure you want to exit?")

            setPositiveButton("Yes") { _,_ ->
                // if user press yes, then finish the current activity
                super.onBackPressed()
            }
            setNegativeButton("NO"){ _,_ ->
            }
            setCancelable(true)
        }.create().show()
    }
}
