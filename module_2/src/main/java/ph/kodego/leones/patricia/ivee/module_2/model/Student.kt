package ph.kodego.leones.patricia.ivee.module_2.model

import ph.kodego.leones.patricia.ivee.module_2.R


//model is where objects are located
class Student(var firstName:String = "Unknown", var lastName:String = "Unknown", var img:Int) {

    var id: Int = 0
    var yearStarted: Int = 0

//    constructor for default values (firstname, lastname, profile picture)
    constructor(): this("","", R.drawable.photo_placeholder)


}