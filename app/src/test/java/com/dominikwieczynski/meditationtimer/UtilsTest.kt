package com.dominikwieczynski.meditationtimer

import junit.framework.TestCase
import org.junit.Test


 class UtilsTest : TestCase(){

    @Test
    fun test_createListDivisibleByFive_minOneMaxHundred_equalToList_returnsTrue()
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
     fun test_createListDivisibleByFive_minOneMaxHundred_notEqualToList_returnsFalse()
     {
         // Given
         val min = 1
         val max = 100
         val list = listOf(5, 10)

         // When
         val result = Utils.createListDivisibleByFive(min, max)

         // Then
         assertEquals(false, list == result)
     }

     @Test
     fun test_createListDivisibleByFive_minTenMaxZero_equalToList_ReturnsTrue()
     {
         // Given
         val min = 10
         val max = 0
         val list = listOf<Int>()

         // When
         val result = Utils.createListDivisibleByFive(min, max)

         // Then
         assertEquals(true, list == result)
     }
     @Test
     fun test_createListDivisibleByFive_minTenMaxZero_equalToList_ReturnsFalse()
     {
         // Given
         val min = 10
         val max = 0
         val list = listOf<Int>(44)

         // When
         val result = Utils.createListDivisibleByFive(min, max)

         // Then
         assertEquals(false, list == result)
     }

     @Test
     fun test_createListDivisibleByFive_minTenMaxZero_notSameSize_ReturnsFalse()
     {
         // Given
         val min = 10
         val max = 0
         val list = listOf<Int>(44)

         // When
         val result = Utils.createListDivisibleByFive(min, max)

         // Then
         assertEquals(false, list.size == result.size)
     }

     @Test
     fun test_createListDivisibleByFive_minTenMaxZero_sameSize_ReturnsTrue()
     {
         // Given
         val min = 1
         val max = 5
         val list = listOf<Int>(44)

         // When
         val result = Utils.createListDivisibleByFive(min, max)

         // Then
         assertEquals(true, list.size == result.size)
     }




 }