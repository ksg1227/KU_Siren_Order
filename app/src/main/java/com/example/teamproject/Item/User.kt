package com.example.teamproject.Item

data class User(
    val id:String,
    val passwd:String,
    val name:String,
    val phoneNum:String,
    var emailAddress:String,
    val studentId:String,
    val department:String)
{
    constructor():this("noinfo",
        "nopasswd",
        "noname",
        "nophoneNum",
        "noemailAddress",
        "nostudentId",
        "nodepartment")
}
