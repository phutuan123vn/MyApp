package com.example.myapplication

class User {
    var id : Int=0
    var Last_Name : String =""
    var First_Name: String=""
    var Student_code: String=""
    constructor(Lname:String, Fname:String, Stucode:String){
        this.First_Name=Fname
        this.Last_Name=Lname
        this.Student_code=Stucode
    }
}