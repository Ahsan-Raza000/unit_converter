package com.example.unitconverter

import junit.framework.TestCase.assertEquals
import org.junit.Test
class UnitConverterLogic {

    fun convertUnits(inputUnit: String, outputUnit: String, inputValue: Double): Double {
        val conversionFactors = mapOf(
            "Milligrams" to 0.001,
            "Grams" to 1.0,
            "Kilograms" to 1000.0,
            "Ounces" to 28.3495,
            "Pounds" to 453.592
        )

        val inputFactor = conversionFactors[inputUnit] ?: 1.0
        val outputFactor = conversionFactors[outputUnit] ?: 1.0

        return (inputValue * inputFactor / outputFactor)
    }
}

class UnitConverterTest {

    private val converter = UnitConverterLogic()

    @Test
    fun testWeightConversions() {
        // Milligrams to Grams
        var result = converter.convertUnits("Milligrams", "Grams", 1000.0)
        assertEquals(1.0, result)

        // Grams to Kilograms
        result = converter.convertUnits("Grams", "Kilograms", 2000.0)
        assertEquals(2.0, result)

        // Ounces to Pounds
        result = converter.convertUnits("Ounces", "Pounds", 16.0)
        assertEquals(1.0, result)
    }
}