package com.example.myapplication.model

class User {
    var id : Int=0
    var Last_Name : String =""
    var First_Name: String=""
    var Role: String=""
    var Email:String=""
    var Password:String=""
    constructor(Lname:String, Fname:String, Role: String, Email:String,Password:String){
        this.First_Name=Fname
        this.Last_Name=Lname
        this.Role=Role
        this.Email=Email
        this.Password=Password
    }
    constructor(Password: String,Role:String){
        this.Role=Role
        this.Password=Password
    }
    constructor(){
    }
}