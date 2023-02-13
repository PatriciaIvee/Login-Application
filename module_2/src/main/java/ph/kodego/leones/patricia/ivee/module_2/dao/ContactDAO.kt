package ph.kodego.leones.patricia.ivee.module_2.dao

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteException
import android.util.Log
import ph.kodego.leones.patricia.ivee.module_2.model.Contact
import ph.kodego.leones.patricia.ivee.module_2.model.Student

interface ContactDAO{
    fun addContact(contact: Contact)
    fun addContacts(contacts:ArrayList<Contact>)
    fun getContacts(studentId:Int):ArrayList<Contact>
    fun updateContact(contact:Contact)
    fun deleteContact(contactId: Int)
}

class ContactDAOSQLImpl(var context: Context): ContactDAO{
    override fun addContact(contact: Contact) {
        var databaseHandler:DatabaseHandler = DatabaseHandler(context)
        val db = databaseHandler.writableDatabase

        val contentValues = ContentValues()
        contentValues.put(DatabaseHandler.studentContactId, contact.studentId)
        contentValues.put(DatabaseHandler.contactType, contact.contactType)
        contentValues.put(DatabaseHandler.contactDetails, contact.contactDetails)

        val success = db.insert(DatabaseHandler.tableContacts,null,contentValues)
        db.close()
    }

    override fun addContacts(contacts: ArrayList<Contact>) {
        for (contact in contacts) {
            addContact(contact)
        }

    }

    override fun getContacts(studentId: Int): ArrayList<Contact> {
        val contactList: ArrayList<Contact> = ArrayList()
        val databaseHandler:DatabaseHandler = DatabaseHandler(context)
        val db = databaseHandler.readableDatabase
        var cursor: Cursor? = null

        val columns  = arrayOf(DatabaseHandler.contactId,
            DatabaseHandler.studentContactId,
            DatabaseHandler.contactType,
            DatabaseHandler.contactDetails)

        try {
//            Search through query(DAtabase) realtime database
            cursor = db.query(DatabaseHandler.tableStudents,
                columns,
//               exact search of finding student's contact
                "${DatabaseHandler.studentContactId} = '${studentId}'",
                null,
                null,
                null,
                null
            )
        }catch (e: SQLiteException) {

            db.close()
            return ArrayList()
        }

        var contact = Contact()
        if (cursor.moveToFirst()) {
            do {
                contact = Contact()
                contact.id = cursor.getInt(0)
                contact.studentId = cursor.getInt(1)
                contact.contactType = cursor.getString(2)
                contact.contactDetails = cursor.getString(3)

                contactList.add(contact)

            }while (cursor.moveToNext())
        }

        db.close()
        return contactList
    }

    override fun updateContact(contact: Contact) {
        var databaseHandler: DatabaseHandler = DatabaseHandler(context)
        val db = databaseHandler.writableDatabase //Open Database as Writeable

        val contentValues = ContentValues()
        contentValues.put(DatabaseHandler.studentContactId, contact.studentId)
        contentValues.put(DatabaseHandler.contactType, contact.contactType)
        contentValues.put(DatabaseHandler.contactDetails, contact.contactDetails)

        val values = arrayOf("${contact.id}")
        val success = db.update(
            DatabaseHandler.tableContacts,
            contentValues,
            "${DatabaseHandler.contactId} = ?",
            values
        )
        db.close()

    }

    override fun deleteContact(contactId: Int) {
        var databaseHandler: DatabaseHandler = DatabaseHandler(context)
        val db = databaseHandler.writableDatabase

        val values = arrayOf("$contactId")
        val success = db.delete(
            DatabaseHandler.tableContacts,
            "${DatabaseHandler.contactId} = ?",
            values
        )
        db.close()

    }

}