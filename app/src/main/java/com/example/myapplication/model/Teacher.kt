package com.example.myapplication.model

import javax.security.auth.Subject

class Teacher {
    var id : Int=0
    var Last_Name : String =""
    var First_Name: String=""
    var Email:String=""
    var Subject:String=""

    constructor(Lname:String,Fname:String,Emai:String,subject:String){
        this.First_Name=Fname
        this.Last_Name=Lname
        this.Email=Email
        this.Subject=subject
    }
}