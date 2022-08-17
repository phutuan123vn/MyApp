package com.example.myapplication

import android.app.Activity
import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.widget.Toast
import com.android.volley.*
import com.android.volley.Response.Listener
import com.android.volley.toolbox.*
import com.example.myapplication.login.LoadingDialog

import com.example.myapplication.model.User
import org.json.JSONObject
import kotlin.collections.ArrayList
import kotlin.collections.HashMap
import com.android.volley.Response as VolleyResponse


class MYSQLHandler(var context: Context){
    public companion object{
        var respnseDone:Boolean=false
        val user = User()
        var DataUser: ArrayList<User> = ArrayList<User>()
        val BASE_URL="http://192.168.231.112/android/"
        var DataG= arrayListOf<String>()
     }
    fun insertUser(user: User){
        val url=BASE_URL + "insertUser.php"
        val requestQueue= Volley.newRequestQueue(context)
        val StringRequest=object : StringRequest(
            Request.Method.POST,url,
            Listener { response ->
                Toast.makeText(context,response.toString(),Toast.LENGTH_SHORT).show()
            },
            VolleyResponse.ErrorListener { error ->
                try {
                    Toast.makeText(context,error.toString(),Toast.LENGTH_SHORT).show()

                }catch (e: NetworkError){
                    e.printStackTrace()
                }catch (e1: NoConnectionError){
                    e1.printStackTrace()
                }catch (e2:ServerError){
                    e2.printStackTrace()
                }
            }){
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
    fun passEmail(Email: String){
        var count: Boolean=false
        var Ulist=ArrayList<User>()
        Ulist.clear()
        val load= LoadingDialog(context as Activity)
        var doAsynct=object : doAsync({}) {
            override fun onPreExecute() {
                load.startLoading()
                count=true
            }
            override fun doInBackground(vararg params: Void?): Void? {
                return null
            }

            override fun onPostExecute(result: Void?) {
                do {
                    if (respnseDone==true){
                        break
                    }
                }while (count==true)
                load.dismissDialog()
                Log.d("LOGINTHEARD", DataUser.get(0).Role+" "+ DataUser.get(0).Password)
            }
        }
        doAsynct.execute()
    }
    fun getPassNRole(Email:String,callback: VolleyCallback){
        val url=BASE_URL + "getPass.php"
        val loading=LoadingDialog(context as Activity)
        val requestQueue = Volley.newRequestQueue(context)
        val StringRequest = object : StringRequest(
            Request.Method.POST,
            url,
            Listener { response ->
                try {
                    val jsonObject = JSONObject(response)
                    if (jsonObject.get("response").equals("Success")) {
                        val jsonArray = jsonObject.getJSONArray("data")
                        val data = jsonArray.getJSONObject(0)
                        user.Password = data.get("PASSWORD").toString()
                        user.Role = data.get("ROLE").toString()
                        DataUser.clear()
                        DataUser.add(user)
                    }else{
                        DataUser.clear()
                    }
                }finally {
                    Log.d("TAG", DataUser.toString())
                }
                callback.onSuccess(DataUser)
            },
            VolleyResponse.ErrorListener { error ->
            }
        ){
            override fun getParams(): HashMap<String, String> {
                val map=HashMap<String,String>()
                map["EMAIL"]=Email
                return map
            }

        }
        // xong chuoit data
        requestQueue.add(StringRequest)
    }
    fun testPassNRole(callback: VolleyCallback){
        val url=BASE_URL+"getPass.php"
        val requestQueue = Volley.newRequestQueue(context)
        val StringRequest = object : StringRequest(
            Request.Method.POST,
            url,
            Listener { response ->
                try {
                    val jsonObject = JSONObject(response)
                    if (jsonObject.get("response").equals("Success")) {
                        val jsonArray = jsonObject.getJSONArray("data")
                        val data = jsonArray.getJSONObject(0)
                        user.Email = data.get("EMAIL").toString()
                        DataUser.clear()
                        DataUser.add(user)
                    }
                }finally {
                    respnseDone = true
                }
                callback.onSuccess(DataUser)
            },
            VolleyResponse.ErrorListener { error ->
            }
        ){
            override fun getParams(): HashMap<String, String> {
                val map=HashMap<String,String>()
                map["EMAIL"]="sinhvien"
                return map
            }

        }
        requestQueue.add(StringRequest)
    }
    fun checkmail(email:String,callback:VolleyCallback){
        val url= BASE_URL + "checkMailDB.php"
        val requestQueue=Volley.newRequestQueue(context)
        val StringRequest = object : StringRequest(
            Request.Method.POST,
            url,
            Listener { response ->
                try {
                    var jsonObject = JSONObject(response)
                    if (jsonObject.get("response").equals("Success")) {
                        val jsonArray = jsonObject.getJSONArray("data")
                        val data = jsonArray.getJSONObject(0)
                        user.Email = data.get("EMAIL").toString()
                        DataUser.clear()
                        DataUser.add(user)
                    }else{
                        DataUser.clear()
                    }
                }finally {
                    Log.d("TAG", DataUser.toString())
                }
                callback.onSuccess(DataUser)
            },
            VolleyResponse.ErrorListener { error ->
            }
        ){
            override fun getParams(): HashMap<String, String> {
                val map=HashMap<String,String>()
                map["EMAIL"]=email
                return map
            }

        }
        requestQueue.add(StringRequest)
    }
    // get data
    fun getSub(){
        val url= BASE_URL + "getSub.php"
        val requestQueue=Volley.newRequestQueue(context)
        val stringRequest=StringRequest(url, { response ->
            try {
                val jsonObject = JSONObject(response)
                if (jsonObject.get("response").equals("Success")) {
                    val jsonArray = jsonObject.getJSONArray("data")
                    for (i in 0 until jsonArray.length()){
                        var data = jsonArray.getJSONObject(i)
                        var mmh=data.get("MMH").toString()
                        var name=data.get("NAME").toString()
                        var clas=data.get("CLASS").toString()
                        DataG.clear()
                        DataG.add(i,mmh)
                        DataG.add(i,name)
                        DataG.add(i,clas)
                        Log.d("Tea","$mmh $name $clas")
                    }
                }
                else{
                    DataG.clear()
                }
            }finally {
                Log.d("DATA", DataG.toString() )
            }
        }, { error ->

        })
    }
    public interface VolleyCallback{
        fun onSuccess(Data:ArrayList<User>) {

        }
        fun onError(result: String) {

        }
    }
}


open class doAsync(function: () -> Unit) : AsyncTask<Void, Void, Void>() {
     internal var ulist= ArrayList<User>()
    override fun doInBackground(vararg params: Void?): Void? {
        onPostExecute(null)
        return null
    }

    override fun onPostExecute(result: Void?) {
        super.onPostExecute(result)

    }

    override fun onPreExecute() {
        super.onPreExecute()
    }
}