package com.example.simpleauth.helpers

import android.content.Context
import android.content.SharedPreferences

object PreferenceHelper {

    const val USER_TOKEN = "USER_TOKEN"
    const val IS_AUTHORIZED = "IS_AUTHORIZED"

    /*fun defaultPreference(context: Context): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)*/

    fun customPreference(context: Context, name: String): SharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE)

    inline fun SharedPreferences.editMe(operation: (SharedPreferences.Editor) -> Unit) {
        val editMe = edit()
        operation(editMe)
        editMe.apply()
    }

    fun SharedPreferences.Editor.put(pair: Pair<String, Any>) {
        val key = pair.first
        val value = pair.second
        when (value) {
            is String -> putString(key, value)
            is Int -> putInt(key, value)
            is Boolean -> putBoolean(key, value)
            is Long -> putLong(key, value)
            is Float -> putFloat(key, value)
            else -> error("Only primitive types can be stored in SharedPreferences")
        }
    }

    var SharedPreferences.user_token
        get() = getInt(USER_TOKEN, 0)
        set(value) {
            editMe {
                it.putInt(USER_TOKEN, value)
            }
        }

    var SharedPreferences.isAuth
        get() = getBoolean(IS_AUTHORIZED, false)
        set(value) {
            editMe {
                it.putBoolean(IS_AUTHORIZED, value)
            }
        }


    var SharedPreferences.clearValues
        get() = { }
        set(value) {
            editMe {
                it.clear()
            }
        }

}