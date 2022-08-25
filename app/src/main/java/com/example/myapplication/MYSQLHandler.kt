package com.example.myapplication

import android.app.Activity
import android.content.Context
import android.os.AsyncTask
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.android.volley.*
import com.android.volley.Response.Listener
import com.android.volley.toolbox.*
import com.example.myapplication.login.LoadingDialog
import com.example.myapplication.model.TEMP

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
        val BASE_URL="http://192.168.34.112/android/"
        val DataG: ArrayList<TEMP> = ArrayList<TEMP>()
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
                        user.id= data.get("ID").toString().toInt()
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
    fun getSub(volleyCallback1: VolleyCallback1){
        val url= BASE_URL + "getSub.php"
        val requestQueue=Volley.newRequestQueue(context)
        data class SUB(var mmh:String,var name:String,var clas:String)
        val stringRequest=StringRequest(url, { response ->
            try {
                val jsonObject = JSONObject(response)
                if (jsonObject.get("response").equals("Success")) {
                    val jsonArray = jsonObject.getJSONArray("data")
                    DataG.clear()
                    for (i in 0..jsonArray.length()-1){
                        var data = jsonArray.getJSONObject(i)
                        var temp=TEMP()
                        temp.t1=data.get("MMH").toString()
                        temp.t2=data.get("NAME").toString()
                        temp.t3=data.get("CLASS").toString()
                        temp.t4=data.get("ID").toString()
                        DataG.add(temp)
                        Log.d("CHEKC", DataG.get(i).t1 +" "+DataG.get(i).t2+" "+ DataG.get(i).t3)
                    }
                }
                else{
                    DataG.clear()
                }
            }finally {
                for (i in 0..DataG.size-1) {
                    Log.d("CHEKC", DataG.get(i).t1 + " " + DataG.get(i).t2 + " " + DataG.get(i).t3)
                }
                volleyCallback1.onSuccess(DataG)
            }
        }, { error ->

        })
        requestQueue.add(stringRequest)
    }
    // getInfo
    fun getStuInfo(id:Int,volleyCallback1: VolleyCallback1){
        val url= BASE_URL + "getStuInfo.php"
        val requestQueue=Volley.newRequestQueue(context)
        val stringRequest = object : StringRequest(
            Request.Method.POST,
            url,
            Listener { response ->
                try {
                    val jsonObject = JSONObject(response)
                    DataG.clear()
                    if (jsonObject.get("response").equals("Success")) {
                        val jsonArray = jsonObject.getJSONArray("data")
                        var data=jsonArray.getJSONObject(0)
                        var temp=TEMP()
                        temp.t1=data.get("LAST_NAME").toString()
                        temp.t2=data.get("FIRST_NAME").toString()
                        temp.t3=data.get("CONCAT(CAREER,CODE)").toString()
                        temp.t4=data.get("ADDRESS").toString()
                        temp.t5=data.get("PERSON_ID").toString()
                        temp.t6=data.get("PHONE").toString()
                        temp.t7=data.get("EMAIL").toString()
                        DataG.add(temp)
                        Log.d("CHECK", DataG.get(0).t1+" "+DataG.get(0).t2+" "+DataG.get(0).t3+" "+DataG.get(0).t4+" "+DataG.get(0).t5+" "+DataG.get(0).t6+" "+DataG.get(0).t7)
                    }
                }finally {
                    volleyCallback1.onSuccess(DataG)
                }
            },
            VolleyResponse.ErrorListener { error ->
            }
        ){
            override fun getParams(): HashMap<String,String> {
                val map=HashMap<String,String>()
                map["ID"]=id.toString()
                return map
            }
        }
        requestQueue.add(stringRequest)
    }
    fun getStuInfoDS(volleyCallback1: VolleyCallback1){
        val url= BASE_URL + "getAllData.php" // sua link php
        val requestQueue=Volley.newRequestQueue(context)
        val stringRequest = StringRequest(url,
            { response ->
                try {
                    val jsonObject = JSONObject(response)
                    DataG.clear()
                    if (jsonObject.get("response").equals("Success")) {
                        val jsonArray = jsonObject.getJSONArray("data")
                        for (i in 0 until jsonArray.length()) {
                            var data = jsonArray.getJSONObject(i)
                            var temp = TEMP()
                            temp.t1 = data.get("LAST_NAME").toString()
                            temp.t2 = data.get("FIRST_NAME").toString()
                            temp.t3=data.get("CONCAT(CAREER,CODE)").toString()
                            temp.t4 = data.get("ID").toString()
                            DataG.add(temp)
                            Log.d(
                                "CHECK",
                                DataG.get(0).t1 + " " + DataG.get(0).t2 + " " + DataG.get(0).t3
                            )
                        }
                    }
                }finally {
                    volleyCallback1.onSuccess(DataG)
                }
            },
            VolleyResponse.ErrorListener { error ->
            }
        )
        requestQueue.add(stringRequest)
    }
    fun getTeachInfo(id:Int,volleyCallback1: VolleyCallback1){
        val url= BASE_URL + "getTeachInfo.php"
        val requestQueue=Volley.newRequestQueue(context)
        val stringRequest = object : StringRequest(
            Request.Method.POST,
            url,
            Listener { response ->
                try {
                    val jsonObject = JSONObject(response)
                    DataG.clear()
                    if (jsonObject.get("response").equals("Success")) {
                        val jsonArray = jsonObject.getJSONArray("data")
                        var data=jsonArray.getJSONObject(0)
                        var temp=TEMP()
                        temp.t1=data.get("LAST_NAME").toString()
                        temp.t2=data.get("FIRST_NAME").toString()
                        temp.t3=data.get("CONCAT(CAREER,CODE)").toString()
                        temp.t4=data.get("SUBJECT").toString()
                        temp.t5=data.get("ADDRESS").toString()
                        temp.t6=data.get("PERSON_ID").toString()
                        temp.t7=data.get("PHONE").toString()
                        temp.t8=data.get("EMAIL").toString()
                        DataG.add(temp)
                        Log.d("CHECK", DataG.get(0).t1+" "+DataG.get(0).t2+" "+DataG.get(0).t3+" "+DataG.get(0).t4+" "+DataG.get(0).t5+" "+DataG.get(0).t6+" "+DataG.get(0).t7+" "+ DataG.get(0).t8)
                    }
                }finally {
                    volleyCallback1.onSuccess(DataG)
                }
            },
            VolleyResponse.ErrorListener { error ->
            }
        ){
            override fun getParams(): HashMap<String,String> {
                val map=HashMap<String,String>()
                map["ID"]=id.toString()
                return map
            }
        }
        requestQueue.add(stringRequest)
    }
    // lay du lieu dsGV
    fun getTeachInfoDS(volleyCallback1: VolleyCallback1){
        val url= BASE_URL + "getAllTeach.php" // sua link php
        val requestQueue=Volley.newRequestQueue(context)
        val stringRequest = StringRequest(url,
            { response ->
                try {
                    val jsonObject = JSONObject(response)
                    DataG.clear()
                    if (jsonObject.get("response").equals("Success")) {
                        val jsonArray = jsonObject.getJSONArray("data")
                        for (i in 0 until jsonArray.length()) {
                            var data = jsonArray.getJSONObject(i)
                            var temp = TEMP()
                            temp.t1 = data.get("LAST_NAME").toString()
                            temp.t2 = data.get("FIRST_NAME").toString()
                            temp.t3=data.get("CONCAT(CAREER,CODE)").toString()
                            temp.t4 = data.get("ID").toString()
                            DataG.add(temp)
                            Log.d("CHECK", DataG.get(0).t1 + " " + DataG.get(0).t2 + " " + DataG.get(0).t3)
                        }
                    }
                }finally {
                    volleyCallback1.onSuccess(DataG)
                }
            },
            VolleyResponse.ErrorListener { error ->
            }
        )
        requestQueue.add(stringRequest)
    }
    fun updatePass(pass:String){
        val url= BASE_URL+"updatePassUser.php"
        val requestQueue=Volley.newRequestQueue(context)
        val stringRequest=object :StringRequest(Request.Method.POST,
        url,
        Listener { response ->
            user.Password = pass
            Toast.makeText(context,response.toString(),Toast.LENGTH_SHORT).show()
        },
        VolleyResponse.ErrorListener { error ->
            //
            Toast.makeText(context,error.toString(),Toast.LENGTH_SHORT).show()
        }){
            override fun getParams(): HashMap<String, String>? {
                var map:HashMap<String,String> = HashMap()
                map["ID"]= user.id.toString()
                map["PASSWORD"]=pass
                return map
            }
        }
        requestQueue.add(stringRequest)
    }

    fun checkInfo(phone: String,pid: String,callback:VolleyCallback1){
        val url= BASE_URL + "checkDataInfo.php"
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
                        var temp=TEMP()
                        temp.t1=data.get("COLPHONE").toString()
                        temp.t2=data.get("COLPID").toString()
                        DataG.clear()
                        DataG.add(temp)
                        Log.d("id","${temp.t1} ${temp.t2} ${user.id}")
                    }else{
                        DataG.clear()
                    }
                }finally {
                    callback.onSuccess(DataG)
                }
            },
            VolleyResponse.ErrorListener { error ->
            }
        ){
            override fun getParams(): HashMap<String, String> {
                val map=HashMap<String,String>()
                map["PHONE"]=phone
                map["PERSON_ID"]=pid
                return map
            }

        }
        requestQueue.add(StringRequest)
    }
    fun updateStuInfo(phone: String,pid: String,address:String){
        val url= BASE_URL+"updateStuInfo.php"
        val requestQueue=Volley.newRequestQueue(context)
        val stringRequest=object :StringRequest(
            Request.Method.POST,
            url,
            Listener { response ->
                Toast.makeText(context,response,Toast.LENGTH_SHORT).show()
            }, com.android.volley.Response.ErrorListener {error ->
                Toast.makeText(context,"${error(error)}",Toast.LENGTH_SHORT).show()
            }
        ){
            override fun getParams(): HashMap<String, String>? {
                val map:HashMap<String,String> =HashMap()
                map["ID"]= user.id.toString()
                map["PHONE"]=phone
                map["ADDRESS"]=address
                map["PID"]=pid
                return map
            }
        }
        requestQueue.add(stringRequest)
    }
    fun updateTeachInfo(phone: String,pid: String,address:String){
        val url= BASE_URL+"updateTeachInfo.php"
        val requestQueue=Volley.newRequestQueue(context)
        val stringRequest=object :StringRequest(
            Request.Method.POST,
            url,
            Listener { response ->
                Toast.makeText(context,response,Toast.LENGTH_SHORT).show()
            }, com.android.volley.Response.ErrorListener {error ->
                Toast.makeText(context,"${error(error)}",Toast.LENGTH_SHORT).show()
            }
        ){
            override fun getParams(): HashMap<String, String>? {
                val map:HashMap<String,String> =HashMap()
                map["ID"]= user.id.toString()
                map["PHONE"]=phone
                map["ADDRESS"]=address
                map["PID"]=pid
                return map
            }
        }
        requestQueue.add(stringRequest)
    }

    fun dkmh(temp: TEMP) {
        val url= BASE_URL+"dkmh.php"
        val requestQueue=Volley.newRequestQueue(context)
        val stringRequest=object :StringRequest(
            Request.Method.POST,
            url,
            Listener { response ->
                Log.d("SC",response)
            },com.android.volley.Response.ErrorListener{error ->

            }
        ){
            override fun getParams(): MutableMap<String, String>? {
                val map:HashMap<String,String> = HashMap()
                map["ID"]= user.id.toString()
                map["SID"]=temp.t4
                return map
            }
        }
        requestQueue.add(stringRequest)
    }
    fun diemdanh(Str:String){
        val url=Str+"&id=${user.id}"
        val requestQueue=Volley.newRequestQueue(context)
        val stringRequest=StringRequest(url,
            {response ->
                //
            },
            {error->
                //
            })
        requestQueue.add(stringRequest)
    }
    fun datadiemdanh(callback:VolleyCallback1){
        val url= BASE_URL+"getDiemdanh.php"+"?id=${user.id}"
        val requestQueue=Volley.newRequestQueue(context)
        val stringRequest=StringRequest(url,
            {
            try {
                DataG.clear()
                val jsonObject=JSONObject(it)
                if (jsonObject.get("response").equals("Success")){
                    val jsonArray=jsonObject.getJSONArray("data")
                    for (i in 0 until jsonArray.length()) {
                        var data = jsonArray.getJSONObject(i)
                        var temp = TEMP()
//                        temp.t1 = data.get("SUB_ID").toString()
//                        temp.t3=data.get("CONCAT(CAREER,CODE)").toString()
                        temp.t1 = data.get("MMH").toString()
                        temp.t2 = data.get("NAME").toString()
                        temp.t3 = data.get("CLASS").toString()
                        temp.t4 = data.get("ATTEND").toString()
                        DataG.add(temp)
                        Log.d("CHECK", DataG.get(0).t1 + " " + DataG.get(0).t2 + " " + DataG.get(0).t3)
                    }
                }
            }finally {
                callback.onSuccess(DataG)
            }
            },{

            })
        requestQueue.add(stringRequest)
    }

    interface VolleyCallback{
        fun onSuccess(Data:ArrayList<User>) {

        }
        fun onError(result: String) {

        }
    }
    public interface VolleyCallback1{
        fun onSuccess(Data:ArrayList<TEMP>) {

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