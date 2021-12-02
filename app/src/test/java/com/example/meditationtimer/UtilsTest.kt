package com.example.meditationtimer

import org.junit.Assert.*

import org.junit.Test

 class UtilsTest {

    @Test
    fun createListDivisibleByFive_minOneMaxHundred_twentyElements_returnsTrue()
    {
        // Given
        val min = 1
        val max = 100
        val list = listOf(5, 10, 15, 20, 25, 30, 35, 40, 45, 50, 55, 60, 65, 70, 75, 80, 85, 90, 95, 100)

        // When
        val result = Utils.createListDivisibleByFive(min, max)

        // Then
        assertEquals(true, list == result)
    }

     @Test
     fun createListDivisibleByFive_minOneMaxHundred_twentyElements_returnsFalse()
     {
         // Given
         val min = 1
         val max = 100
         val list = listOf(5, 10)

         // When
         val result = Utils.createListDivisibleByFive(min, max)
         val bool = list == result
         // Then
         assertEquals(false, list == result)
     }
}