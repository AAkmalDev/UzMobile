package uz.pdp.uzmobile.utils

import android.content.Context
import android.content.SharedPreferences

object MySharedpreference {



    var sharedPreferences: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null

    fun getInstance(context: Context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences("name", Context.MODE_PRIVATE)
            editor = sharedPreferences!!.edit()
        }
    }

    fun getLanguage(): String {
        return sharedPreferences!!.getString("a", "")!!
    }

    fun setLanguage(string: String) {
        editor!!.putString("a", string).commit()
    }


//    private const val NAME = "travel_app"
//    private const val MODE = Context.MODE_PRIVATE
//    private lateinit var preferences: SharedPreferences
//
//    fun init(context: Context) {
//        preferences = context.getSharedPreferences(NAME, MODE)
//    }
//
//    private inline fun SharedPreferences.edit(operation: (SharedPreferences.Editor) -> Unit) {
//        val editor = edit()
//        operation(editor)
//        editor.apply()
//    }
//
//    var language: String?
//        get() = preferences.getString("language", "uz")
//        set(value) = preferences.edit {
//            if (value != null) {
//                it.putString("language", value)
//            }
//        }



}