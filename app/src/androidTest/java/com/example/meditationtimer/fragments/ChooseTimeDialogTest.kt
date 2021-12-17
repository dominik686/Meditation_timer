package com.example.meditationtimer.fragments

import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.meditationtimer.MainActivity
import com.example.meditationtimer.R
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class ChooseTimeDialogTest
{

    // Test if number picker is visible
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    lateinit var chooseTimeDialogFragment : ChooseTimeDialog
 //   @get:Rule
   // val fragmentRule =
@Before
fun init()
 {
    with(launchFragment<ChooseTimeDialog>())
    {

    }

}

    @Test
     fun testChooseTimeDialogFragment()
    {

        with(launchFragment<ChooseTimeDialog>()){
            onFragment{ fragment ->
                assertEquals(true, fragment.dialog != null)
                assertEquals(true, fragment.requireDialog().isShowing)
                fragment.dismiss()
                fragment.parentFragmentManager.executePendingTransactions()
                assertEquals(true, fragment.dialog == null)
            }
        }
    }

    @Test
     fun testNumberPicker()
    {

        onNumberPicker().check(matches(isDisplayed()))
    }
    @Test
     fun pressTheOKButton()
    {
        onView(withId(R.id.dialog_number_picker))


    }

    private fun onNumberPicker()  =  onView(withId(R.id.dialog_number_picker))
    private fun onNumberPickerInput()  = onView(withParent(withId(R.id.dialog_number_picker)))
}