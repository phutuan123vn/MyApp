//package com.example.myapplication.quanly
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import com.example.myapplication.DatabaseHandler
//import com.example.myapplication.R
////import com.example.myapplication.login.MYSQLDataHandler
//import com.example.myapplication.model.User
////import kotlinx.android.synthetic.main.test.*
//
//class Test : AppCompatActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.test)
////        val sq:MYSQLDataHandler =MYSQLDataHandler()
//        val db=DatabaseHandler(this)
//        TESTBttn.setOnClickListener {
//            val user=User()
//            user.Email=TESTE.text.toString()
//            user.Password=TESTPass.text.toString()
//            db.changePass(user)
//
//        }
//        TESTRead.setOnClickListener {
//            var data=db.getAllData()
//            TestResult.text=""
//            for (i in 0..data.size-1){
//                TestResult.append(data.get(i).Last_Name + " " +data.get(i).First_Name + " " +data.get(i).Email + "\n")
//
//            }
////            sq.getConnection()
////            sq.executeMySQLQuery()
//        }
//
//    }
//}