package com.example.android1

import kotlin.math.pow

class BMICount(
    private val mass: Double,
    private val height: Double
) {
    fun bmi_kg(): Double {
        require(mass>0)
        require(height>0)
        return (mass / height.pow(2.0)) * 10000
    }

    fun bmi_lb(): Double {
        require(mass>0)
        require(height>0)
        return (mass / height.pow(2.0) * 703)
    }
}