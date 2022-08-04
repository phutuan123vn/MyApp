package com.example.myapplication

import android.content.Context
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.myapplication.model.User

class MYSQLHandler(var context: Context){
    private val BASE_URL="http://192.168.1.6/android/"
    fun insertUser(user: User){
        val url=BASE_URL + "insertUser.php"
        val requestQueue= Volley.newRequestQueue(context)
        val StringRequest=object : StringRequest(
            Request.Method.POST,url,
            Response.Listener { response ->  },
            Response.ErrorListener { error -> }){
            override fun getParams():HashMap<String,String>{
                val map=HashMap<String,String>()
                map["LAST_NAME"]=user.Last_Name
                map["FIRST_NAME"]=user.First_Name
                map["EMAIL"]=user.Email
                map["PASS"]=user.Password
                map["ROLEID"]=user.Role
                return map
            }
        }
        requestQueue.add(StringRequest)
    }
}