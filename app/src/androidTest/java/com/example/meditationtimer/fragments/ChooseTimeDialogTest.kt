package com.example.meditationtimer.fragments

import android.view.InputDevice
import android.view.MotionEvent
import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.GeneralClickAction
import androidx.test.espresso.action.GeneralLocation
import androidx.test.espresso.action.Press
import androidx.test.espresso.action.Tap
import androidx.test.espresso.action.ViewActions.actionWithAssertions
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
    //Test what happens when cancel is pressed
    // Test what happens when ok is pressed


    /* Both clickTopCenter and clickBottomCentre methods were derived from this tutorial
    https://blog.stylingandroid.com/numberpicker-espresso-testing/
     */

    //Function for clicking on top of number picker(decrements the number)
    private val clickTopCentre =
        actionWithAssertions(
            GeneralClickAction(
                Tap.SINGLE,
                GeneralLocation.TOP_CENTER,
                Press.FINGER,
                InputDevice.SOURCE_UNKNOWN,
                MotionEvent.BUTTON_PRIMARY)
            )

    //Function for clicking on bottom of number picker(increments the number)
    private val clickBottomCentre =
        actionWithAssertions(
            GeneralClickAction(
                Tap.SINGLE,
                GeneralLocation.BOTTOM_CENTER,
                Press.FINGER,
                InputDevice.SOURCE_UNKNOWN,
                MotionEvent.BUTTON_PRIMARY
            )
        )

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    lateinit var chooseTimeDialogFragment : ChooseTimeDialog

@Before
fun init()
 {
    with(launchFragment<ChooseTimeDialog>())
    {
       onFragment{
           fragment ->
           chooseTimeDialogFragment = fragment
       }
    }

}

    //yoo is this good?
    @Test
     fun testChooseTimeDialogFragment()
    {
      assertEquals(true, chooseTimeDialogFragment.dialog != null)
      assertEquals(true, chooseTimeDialogFragment.requireDialog().isShowing)
      chooseTimeDialogFragment.dismiss()
        chooseTimeDialogFragment.parentFragmentManager.executePendingTransactions()
      assertEquals(true, chooseTimeDialogFragment.dialog == null)


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