package com.example.android1

data class historyRecord(var bmi: Double, var mass:Double, var height: Double, var date: String, var unit:Int){

    fun getLine1():String{
        var massUnit = "kg"
        var heightUnit = "cm"
        if(unit == 2){
            massUnit = "lbs"
            heightUnit = "in"
        }
        val builder = "Bmi: $bmi;\nDate: $date;\nMass: $mass $massUnit;\nHeight: $height $heightUnit;"
        return builder
    }

}