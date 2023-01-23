package ph.kodego.leones.patricia.ivee.module_2.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteException
import ph.kodego.leones.patricia.ivee.module_2.model.Student


interface StudentDAO {
    fun addStudent(student: Student)
    fun getStudents() : ArrayList<Student>
    fun updateStudent(studentId: Int, student:Student)
    fun deleteStudent(studentId: Int)
}

//context tells which ui component is shown
//when you tell android about classes you pass context(applicationcontext)



//Old Way of Getting a Database In Android Studio
class StudentDAOSQLImpl(var context: Context): StudentDAO{
    override fun addStudent(student: Student) {
//        Create DatabaseHandler for writing and reading
        var databaseHandler: DatabaseHandler = DatabaseHandler(context)
        val db = databaseHandler.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(DatabaseHandler.studentFirstName, student.firstName)
        contentValues.put(DatabaseHandler.studentLastName, student.lastName)

        val success = db.insert(DatabaseHandler.tableStudents,null,contentValues)
//        Always close after use because it will cause memory build up
        db.close()

    }

    override fun getStudents(): ArrayList<Student> {
        val studentList: ArrayList<Student> = ArrayList()

        val selectQuery = "SELECT ${DatabaseHandler.studentLastName}," +
                "${DatabaseHandler.studentFirstName}, " +
                "${DatabaseHandler.studentId} " +
                "FROM ${DatabaseHandler.tableStudents}"

        val databaseHandler:DatabaseHandler = DatabaseHandler(context)
        val db = databaseHandler.readableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(selectQuery,null)
        }catch (e:SQLiteException) {
            db.close()
            return ArrayList()
        }

        var student = Student()
        if (cursor.moveToFirst()) {
            do {
                student = Student()
                student.id = cursor.getInt(2)
                student.lastName = cursor.getString(0)
                student.firstName = cursor.getString(1)

                studentList.add(student)

            }while (cursor.moveToNext())
        }


        db.close()
        return studentList
    }

    override fun updateStudent(studentId: Int, student: Student) {
        var databaseHandler:DatabaseHandler = DatabaseHandler(context)
        val db = databaseHandler.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(DatabaseHandler.studentFirstName, student.firstName)
        contentValues.put(DatabaseHandler.studentLastName, student.lastName)

        val values = arrayOf("$studentId")
        val success = db.update(DatabaseHandler.tableStudents,
        contentValues,
        "${DatabaseHandler.studentId} = ?",
        values)
        db.close()
    }

    override fun deleteStudent(studentId: Int) {
        var databaseHandler:DatabaseHandler = DatabaseHandler(context)
        val db = databaseHandler.writableDatabase

        val values = arrayOf("$studentId")
        val success = db.delete(DatabaseHandler.tableStudents,
            "${DatabaseHandler.studentId} = ?",
            values)
        db.close()
    }
}