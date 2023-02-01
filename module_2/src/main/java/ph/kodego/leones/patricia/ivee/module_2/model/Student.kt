package ph.kodego.leones.patricia.ivee.module_2.model

import ph.kodego.leones.patricia.ivee.module_2.R


//model is where objects are located
class Student(var firstName:String = "Unknown", var lastName:String = "Unknown", var img:Int) {

    var id: Int = 0
    var yearStarted: Int = 0
    var course: String = ""

//    constructor for default values (firstname, lastname, profile picture)
    constructor(): this("","", R.drawable.photo_placeholder)


}

class StudentContacts(){
    var student:Student = Student()
    var contacts = ArrayList<Contact>()
}