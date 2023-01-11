package ph.kodego.leones.patricia.ivee.module_2.model

import ph.kodego.leones.patricia.ivee.module_2.R


//model is where objects are located
class Student(var firstname:String = "Unknown", var lastname:String = "Unknown",val img:Int) {


//    constructor for default values (firstname, lastname, profile picture)
    constructor(): this("","", R.drawable.photo_placeholder)


}