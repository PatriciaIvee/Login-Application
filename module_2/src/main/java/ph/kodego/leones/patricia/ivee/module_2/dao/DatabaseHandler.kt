package ph.kodego.leones.patricia.ivee.module_2.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context): SQLiteOpenHelper(context,DATABASENAME,null,DATABASEVERSION) {
//Android Studio uses SQLite
//    DATABASENAME = name of database
//    Database is collection of Schemas and Tables
//    When you change or added a new table anything you do in android you must change DATABASEVERSION
    companion object {
        private val DATABASEVERSION = 3
        private val DATABASENAME = "studentdatabase"

        val tableStudents = "student_table"
        val studentId = "id"
        val studentFirstName = "firstname"
        val studentLastName = "lastname"
        val yearStarted = "year_started"
        val course = "course"
//        val yearStarted = "year_started"


    // Contacts Class
//    Ordinality Students can have multiple contacts or student to no contacts at all
//    Normalization
        val tableContacts = "student_contacts"
        val contactId = "id"
        val studentContactId = "student_id"
        val contactType = "contact_type"
        val contactDetails = "contact_details"
    }

//    Everytime you install on Create 1st time app runs
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATESTUDENTSTABLE =
                "CREATE TABLE $tableStudents " +
                "($studentId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$studentFirstName TEXT, " +
                "$studentLastName TEXT, " +
                "$yearStarted INTEGER, " +
                "$course TEXT)"
        db?.execSQL(CREATESTUDENTSTABLE)


//    Insert Data into the table
        db?.execSQL("Insert into $tableStudents($studentLastName , $studentFirstName , $yearStarted, $course)values('Valmores', 'Marco', 1989,'Computer Science')")
        db?.execSQL("Insert into $tableStudents($studentLastName , $studentFirstName , $yearStarted, $course)values('Leones', 'Patricia', 1999, 'Tourism Management')")
        db?.execSQL("Insert into $tableStudents($studentLastName , $studentFirstName , $yearStarted, $course)values('Yu', 'Victor', 1987, 'Electrical Engineering')")
        db?.execSQL("Insert into $tableStudents($studentLastName , $studentFirstName , $yearStarted, $course)values('Aragon', 'Janreign', 1997, 'IT Communications')")
        db?.execSQL("Insert into $tableStudents($studentLastName , $studentFirstName , $yearStarted, $course)values('Navor', 'Dave', 1994, 'Computer Engineering')")
        db?.execSQL("Insert into $tableStudents($studentLastName , $studentFirstName , $yearStarted, $course)values('Rara', 'James Nico', 1992, '')")
        db?.execSQL("Insert into $tableStudents($studentLastName , $studentFirstName , $yearStarted, $course)values('Soriano', 'JP', 1983, 'Hospitality Management')")
        db?.execSQL("Insert into $tableStudents($studentLastName , $studentFirstName , $yearStarted, $course)values('Sumaya', 'Juan Carlos', 1991, '')")
        db?.execSQL("Insert into $tableStudents($studentLastName , $studentFirstName , $yearStarted, $course)values('Monroe', 'Marilyn', 1953, '')")
        db?.execSQL("Insert into $tableStudents($studentLastName , $studentFirstName , $yearStarted, $course)values('Carlos', 'Juan',1996, 'Business Administration')")

    val CREATECONTACTSTABLE =
        "CREATE TABLE $tableContacts " +
                "($contactId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$studentContactId INTEGER, " +
                "$contactType TEXT, " +
                "$contactDetails TEXT, " +
                "FOREIGN KEY ($studentContactId) REFERENCES $tableStudents($studentId))"
    db?.execSQL(CREATECONTACTSTABLE)

    db?.execSQL("Insert into $tableContacts($studentContactId,$contactType,$contactDetails)values(1, 'FACEBOOK', '@MacValmores/facebook/asdasf')")


}
//Primary Key is the UNIQUE IDENTIFIER
//    AUTOINCREMENT automatically sets Primary Key (only works for numerical Primary Key values)

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $tableStudents")
        db!!.execSQL("DROP TABLE IF EXISTS $tableContacts")
        onCreate(db)
    }



}