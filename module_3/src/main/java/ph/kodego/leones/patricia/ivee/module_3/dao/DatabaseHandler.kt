package ph.kodego.leones.patricia.ivee.module_3.dao

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler(context: Context): SQLiteOpenHelper(context,DATABASENAME,null,DATABASEVERSION) {

    companion object {
        private val DATABASEVERSION = 1
        private val DATABASENAME = "studentdatabase"

        val tableStudents = "student_table"
        val studentId = "id"
        val studentFirstName = "firstname"
        val studentLastName = "lastname"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATESTUDENTSTABLE =
                "CREATE TABLE $tableStudents " +
                "($studentId INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$studentFirstName TEXT, " +
                "$studentLastName TEXT)"
        db?.execSQL(CREATESTUDENTSTABLE)


//    Insert Data into the table
        db?.execSQL("Insert into $tableStudents ($studentLastName, $studentFirstName) values ('Valmores', 'Marco')")
        db?.execSQL("Insert into $tableStudents ($studentLastName, $studentFirstName) values ('Leones', 'Patricia')")
        db?.execSQL("Insert into $tableStudents ($studentLastName, $studentFirstName) values ('Yu', 'Victor')")
        db?.execSQL("Insert into $tableStudents ($studentLastName, $studentFirstName) values ('Aragon', 'Janreign')")
        db?.execSQL("Insert into $tableStudents ($studentLastName, $studentFirstName) values ('Navor', 'Dave')")
        db?.execSQL("Insert into $tableStudents ($studentLastName, $studentFirstName) values ('Rara', 'James Nico')")
        db?.execSQL("Insert into $tableStudents ($studentLastName, $studentFirstName) values ('Soriano', 'JP')")
        db?.execSQL("Insert into $tableStudents ($studentLastName, $studentFirstName) values ('Sumaya', 'Juan Carlos')")
        db?.execSQL("Insert into $tableStudents ($studentLastName, $studentFirstName) values ('Monroe', 'Marilyn')")
        db?.execSQL("Insert into $tableStudents ($studentLastName, $studentFirstName) values ('Carlos', 'Juan')")

    }

     override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $tableStudents")
        onCreate(db)
    }

}