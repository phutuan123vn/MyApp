package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

val DATABASE_NAME="MYDB"
val TABLE_NAME="StudentInfo"
val COL_LNAME="Last_name"
val COL_FNAME="First_name"
val COL_STUCODE="Student_code"
val COL_ID="id"
class DatabaseHandler (var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null,1){
    override fun onCreate(db: SQLiteDatabase?) {
        val creatTable= " CREATE TABLE "+ TABLE_NAME + "(" + COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT , " + COL_LNAME +" TEXT, " +  COL_FNAME +" TEXT, " + COL_STUCODE + " TEXT " + ");";
        db?.execSQL(creatTable)

    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
    fun insertData(user: User){
        val db=this.writableDatabase
        var content=ContentValues()
        content.put(COL_LNAME,user.Last_Name)
        content.put(COL_FNAME,user.First_Name)
        content.put(COL_STUCODE,user.Student_code)
        var result = db.insert(TABLE_NAME,null,content)
        if(result == -1.toLong()){
            Toast.makeText(context,"failed",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show()
        }
    }


}