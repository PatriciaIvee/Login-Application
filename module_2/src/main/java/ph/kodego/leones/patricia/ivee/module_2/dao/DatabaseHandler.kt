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
        private val DATABASEVERSION = 1
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
                "$studentLastName TEXT)"
        db?.execSQL(CREATESTUDENTSTABLE)


//    Insert Data into the table
        db?.execSQL("Insert into $tableStudents($studentLastName,$studentFirstName)values('Valmores', 'Marco')")
        db?.execSQL("Insert into $tableStudents($studentLastName,$studentFirstName)values('Leones', 'Patricia')")
        db?.execSQL("Insert into $tableStudents($studentLastName,$studentFirstName)values('Yu', 'Victor')")
        db?.execSQL("Insert into $tableStudents($studentLastName,$studentFirstName)values('Aragon', 'Janreign')")
        db?.execSQL("Insert into $tableStudents($studentLastName,$studentFirstName)values('Navor', 'Dave')")
        db?.execSQL("Insert into $tableStudents($studentLastName,$studentFirstName)values('Rara', 'James Nico')")
        db?.execSQL("Insert into $tableStudents($studentLastName,$studentFirstName)values('Soriano', 'JP')")
        db?.execSQL("Insert into $tableStudents($studentLastName,$studentFirstName)values('Sumaya', 'Juan Carlos')")
        db?.execSQL("Insert into $tableStudents($studentLastName,$studentFirstName)values('Monroe', 'Marilyn')")
        db?.execSQL("Insert into $tableStudents($studentLastName,$studentFirstName)values('Carlos', 'Juan')")

    val CREATECONTACTSTABLE =
        "CREATE TABLE $tableContacts " +
                "($contactId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$studentContactId INTEGER, " +
                "$contactType TEXT, " +
                "$contactDetails TEXT)"
    db?.execSQL(CREATECONTACTSTABLE)

    db?.execSQL("Insert into $tableContacts($studentContactId,$contactType,$contactDetails)values(1, 'FACEBOOK', '@MacValmores/facebook/asdasf')")


}
//Primary Key is the UNIQUE IDENTIFIER
//    AUTOINCREMENT automatically sets Primary Key (only works for numerical Primary Key values)

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $tableStudents")
        onCreate(db)
    }



}