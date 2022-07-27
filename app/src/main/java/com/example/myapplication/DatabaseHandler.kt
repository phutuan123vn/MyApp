package com.example.myapplication

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import com.example.myapplication.model.User

val DATABASE_NAME="MYDB"
val TABLE_NAME="StudentInfo"
val COL_LNAME="Last_name"
val COL_FNAME="First_name"
val COL_ROLE="Role_id"
val COL_EMAIL="EMAIL"
val COL_PASS="Password"
val COL_ID="id"
class DatabaseHandler (var context: Context) : SQLiteOpenHelper(context, DATABASE_NAME,null,1){
    override fun onCreate(db: SQLiteDatabase?) {
        val creatTable= " CREATE TABLE "+ TABLE_NAME + "(" + COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT , " + COL_LNAME + " TEXT, " +  COL_FNAME + " TEXT, " +  COL_EMAIL + " TEXT, " +  COL_PASS + " TEXT, " + COL_ROLE + " TEXT " + ");";
        db?.execSQL(creatTable);
        db?.execSQL(" INSERT INTO $TABLE_NAME ($COL_EMAIL,$COL_PASS,$COL_ROLE) VALUES ( 'admin', '123', '2' ); ");
        db?.execSQL(" INSERT INTO $TABLE_NAME ($COL_EMAIL,$COL_PASS,$COL_ROLE) VALUES ( 'sinhvien', '123', '0' ); ");
        db?.execSQL(" INSERT INTO $TABLE_NAME ($COL_EMAIL,$COL_PASS,$COL_ROLE) VALUES ( 'giangvien', '123', '1' ); ");


    }


    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        onCreate(db=db);
        db?.execSQL(" DROP TABLE IF EXISTS $TABLE_NAME ");
        onCreate(db = db);
    }
    // data vao User
    fun insertData(user: User){
        val db=this.writableDatabase
        var content=ContentValues()
        content.put(COL_LNAME,user.Last_Name)
        content.put(COL_FNAME,user.First_Name)
        content.put(COL_ROLE,user.Role)
        content.put(COL_EMAIL,user.Email)
        content.put(COL_PASS,user.Password)
        var result = db.insert(TABLE_NAME,null,content)
        if(result == -1.toLong()){
            Toast.makeText(context,"failed",Toast.LENGTH_SHORT).show()
        }else{
            Toast.makeText(context,"Success",Toast.LENGTH_SHORT).show()
        }
    }
    // Test Data
    fun CheckUser(email:String,pass:String):Boolean{
        val db=this.readableDatabase
        val query ="SELECT * FROM $TABLE_NAME WHERE EMAIL = ? AND $COL_PASS = ? "
        var arg= listOf<String>(email,pass).toTypedArray()
        val cursor=db.rawQuery(query,arg)
        if(cursor.moveToNext()){
            cursor.close()
            return true
        }else {
            cursor.close()
            return false
        }
    }
    //kiem tra va lay data
    fun ViewPass(email: String):ArrayList<User>{
        val db=this.readableDatabase
        var Ulist:ArrayList<User> = ArrayList<User>()
        val query="SELECT * FROM $TABLE_NAME WHERE EMAIL = ? "
        var arg= listOf<String>(email).toTypedArray()
        var cursor : Cursor? = null
        cursor=db.rawQuery(query,arg)
        if (cursor.moveToNext()){
            var pass:String
            var role:String
            pass=cursor.getString(cursor.getColumnIndex(COL_PASS))
            role=cursor.getString(cursor.getColumnIndex(COL_ROLE))
            Ulist.add(User(Password = pass, Role = role))
            return Ulist
        }
        Ulist.isEmpty()
        return Ulist
    }
    fun checkmail(email: String):String{
        val db=this.readableDatabase
        var list:ArrayList<User> = ArrayList<User>()
        var valueR:String
        val query = " SELECT * FROM $TABLE_NAME WHERE EMAIL = ? "
        val arg= listOf<String>(email).toTypedArray()
        var cursor:Cursor? = null
        cursor=db.rawQuery(query,arg)
        if(cursor.moveToNext()){
            val user=User()
            user.Email=cursor.getString(cursor.getColumnIndex(COL_EMAIL))
            list.add(user)
            valueR=list.get(0).Email
            return valueR
        }else{
            valueR= 0.toString()
            return valueR
        }
    }
}