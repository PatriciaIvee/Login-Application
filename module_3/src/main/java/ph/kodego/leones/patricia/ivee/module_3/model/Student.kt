package ph.kodego.leones.patricia.ivee.module_3.model

import ph.kodego.leones.patricia.ivee.module_3.R

class Student(var firstName:String = "Unknown", var lastName:String = "Unknown", var img:Int) {

    var id : Int = 0

    //    constructor for default values (firstname, lastname, profile picture)
    constructor() : this("", "", R.drawable.photo_placeholder){}
}