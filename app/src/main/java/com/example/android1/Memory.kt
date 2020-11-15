package com.example.android1

import android.content.Context
import com.google.gson.reflect.TypeToken
const val HISTORY = "BMI_HISTORY"
const val JSON = "JSON_DATA"

object Memory {

    var bmiHistory = ArrayList<historyRecord>()

    fun addLogToHistory(log: historyRecord){
        if(bmiHistory.size == 10){
            bmiHistory.removeAt(bmiHistory.size - 1)
        }
        bmiHistory.add(0,log)
    }

    fun loadData(applicationContext:Context) {
        val sharedPref = applicationContext.getSharedPreferences(HISTORY, Context.MODE_PRIVATE )
        val gson = com.google.gson.Gson()
        val json = sharedPref.getString(JSON, null)
        val type = object: TypeToken<ArrayList<historyRecord>>() {}.type
        if(!json.isNullOrBlank()){
            bmiHistory = gson.fromJson(json,type)
        }else {
            bmiHistory = ArrayList()
        }
    }

    fun saveData( applicationContext: Context){
        val sharedPref = applicationContext.getSharedPreferences(HISTORY, Context.MODE_PRIVATE )
        val editor = sharedPref.edit()
        val gson = com.google.gson.Gson()
        val json = gson.toJson(bmiHistory)
        editor.putString(JSON, json)
        editor.apply()
    }

}