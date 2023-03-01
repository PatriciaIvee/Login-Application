package ph.kodego.leones.patricia.ivee.save_images

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
        private val DATABASENAME = "imagedatabase"

        val TABLE_IMAGES = "table_images"
        val TABLE_IMAGES_ID = "id"
        val TABLE_IMAGES_DATA = "data"

        val TABLE_IMAGES_TEXT = "table_images"
        val TABLE_IMAGES_TEXT_ID = "id"
        val TABLE_IMAGES_TEXT_NAME = "name"
        val TABLE_IMAGES_TEXT_DATA = "data"

    }

//    Everytime you install on Create 1st time app runs
    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_IMAGES =
                "CREATE TABLE $TABLE_IMAGES " +
                "($TABLE_IMAGES_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$TABLE_IMAGES_DATA BLOB)"
//    blob is binary large object saves image as is converts image to binary
        db?.execSQL(CREATE_TABLE_IMAGES)

        val CREATE_TABLE_IMAGES_TEXT =
            "CREATE TABLE $TABLE_IMAGES_TEXT " +
                    "($TABLE_IMAGES_TEXT_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "$TABLE_IMAGES_TEXT_NAME TEXT," +
                    "$TABLE_IMAGES_TEXT_DATA TEXT)"
        db?.execSQL(CREATE_TABLE_IMAGES_TEXT)

//    Difference of these two is when you save image as a text it converts image to text something readable for humans
//    Text Image is also memory efficient meaning you can save more memory with textImage
//    blob converts to binary code also more memory usage
//    Both of these are highly used to save images in the database
//
}
//Primary Key is the UNIQUE IDENTIFIER
//    AUTOINCREMENT automatically sets Primary Key (only works for numerical Primary Key values)

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_IMAGES")
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_IMAGES_TEXT")

        onCreate(db)
    }



}