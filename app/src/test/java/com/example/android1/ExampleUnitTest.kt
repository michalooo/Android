package com.example.android1

import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun bmi_values_correct() {
        val bmikg = BMICount(80.0, 160.0)
        val bmilb = BMICount(5.0, 10.0)
        assertEquals(31.250, bmikg.bmi_kg(), 0.001)
        assertEquals(35.150, bmilb.bmi_lb(), 0.001)
    }

    @Test(expected = Exception::class)
    fun bmi_values_incorrect() {
        val bmikg = BMICount(0.0, 175.0)
        val bmilb = BMICount(-1.0, -5.0)
        bmikg.bmi_kg()
        bmilb.bmi_lb()
    }
}