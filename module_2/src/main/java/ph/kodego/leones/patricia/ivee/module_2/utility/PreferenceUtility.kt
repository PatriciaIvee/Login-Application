package ph.kodego.leones.patricia.ivee.module_2.utility

import android.content.Context
import android.content.SharedPreferences

class PreferenceUtility {
    private var appPreferences: SharedPreferences? = null
    private val PREFS  = "appPreferences"

    constructor(context: Context){
        appPreferences = context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)

    }

   fun saveStringPreferences(key:String?, value:String){
       val prefsEditor = appPreferences!!.edit()
       prefsEditor.putString(key,value)
       prefsEditor.apply()
//       You can save alot here different data types
//       prefsEditor.commit()
//       when you use commit() the app will save it immediately
   }


//    writable database

   fun getStringPreferences(key: String?):String? {
       return appPreferences!!.getString(key,"")
   }
}