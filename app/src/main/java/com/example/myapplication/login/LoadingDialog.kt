package com.example.myapplication.login

import android.app.Activity
import android.app.AlertDialog
import android.app.ProgressDialog
import android.os.Handler
import android.util.Log
import com.example.myapplication.MYSQLHandler
import com.example.myapplication.R
import com.example.myapplication.model.User

class LoadingDialog (val mactivity: Activity) {
    lateinit var isDialog: AlertDialog
    fun startLoading(){
        val infalter= mactivity.layoutInflater
        val view= infalter.inflate(R.layout.loading,null)
        val builder = AlertDialog.Builder(mactivity)
        builder.setView(view)
        builder.setCancelable(true)
        isDialog = builder.create()
        isDialog.show()
        builder.setCancelable(true)

    }

     fun checkShow():Boolean {
         if (isDialog.isShowing()){
             return true
         }
         return false
    }

    fun isDissMiss(){
        isDialog.dismiss()
    }
    fun dismissDialog() {
        if (::isDialog.isInitialized) {
            isDialog.dismiss()
        }
    }
}