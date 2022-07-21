package com.example.myapplication.model

class User {
    var id : Int=0
    var Last_Name : String =""
    var First_Name: String=""
    var Student_code: String=""
    var Email:String=""
    var Password:String=""
    constructor(Lname:String, Fname:String, Stucode:String, Email:String,Password:String){
        this.First_Name=Fname
        this.Last_Name=Lname
        this.Student_code=Stucode
        this.Email=Email
        this.Password=Password
    }
}