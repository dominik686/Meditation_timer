package com.example.meditationtimer.fragments

import android.view.InputDevice
import android.view.MotionEvent
import android.widget.NumberPicker
import androidx.fragment.app.testing.launchFragment
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.GeneralClickAction
import androidx.test.espresso.action.GeneralLocation
import androidx.test.espresso.action.Press
import androidx.test.espresso.action.Tap
import androidx.test.espresso.action.ViewActions.actionWithAssertions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.meditationtimer.R
import kotlinx.coroutines.*
import org.hamcrest.Matchers.allOf
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@DelicateCoroutinesApi
@RunWith(AndroidJUnit4::class)
@LargeTest
class ChooseTimeDialogTest : NumberPicker.OnValueChangeListener
{

    /* Both clickTopCenter and clickBottomCentre methods were derived from this tutorial
    https://blog.stylingandroid.com/numberpicker-espresso-testing/
     */

    /*
    When ran together some tests fail, but when you run them separately they pass.
    I think it might have to do something with the minutes variable

     */
    //Variable for clicking on top of number picker(increments the number)
    private val clickTopCentre =
        actionWithAssertions(
            GeneralClickAction(
                Tap.SINGLE,
                GeneralLocation.TOP_CENTER,
                Press.FINGER,
                InputDevice.SOURCE_UNKNOWN,
                MotionEvent.BUTTON_PRIMARY)
            )

    //Variable for clicking on bottom of number picker(decrements the number)
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



    private lateinit var chooseTimeDialogFragment : ChooseTimeDialog
    private var minutes = 0

@Before
fun init()
 {
    with(launchFragment<ChooseTimeDialog>())
    {
       onFragment{

           fragment ->
           chooseTimeDialogFragment = fragment
           GlobalScope.launch(Dispatchers.Main) {
               chooseTimeDialogFragment.parentFragmentManager.executePendingTransactions()
               chooseTimeDialogFragment.setValueChangeListener(this@ChooseTimeDialogTest)
           }
       }
    }

     minutes = 0
 }

    @Test
     fun testChooseTimeDialogFragment()
    {
      assertEquals(true, chooseTimeDialogFragment.dialog != null)
      assertEquals(true, chooseTimeDialogFragment.requireDialog().isShowing)

      GlobalScope.launch(Dispatchers.Main){

          chooseTimeDialogFragment.dismiss()
          chooseTimeDialogFragment.parentFragmentManager.executePendingTransactions()
          assertEquals(true, chooseTimeDialogFragment.dialog == null)
      }



    }

    @Test
     fun test_numberPicker_isDisplayed()
    {

        onNumberPicker().check(matches(isDisplayed()))
    }

    @Test
    fun test_defaultValue_okButton()
    {
        onView(allOf(withId(R.id.dialog_number_picker), isDisplayed()))

        onView(withText("OK")).perform(click())

        assertEquals(5, minutes)

    }

    @Test
    fun test_decrementValue_okButton()
    {
        onNumberPicker().perform(clickBottomCentre)

        onView(withText("OK")).perform(click())

        assertEquals(120, minutes)
    }
    @Test
    fun test_incrementValue_okButton()
    {
        onNumberPicker().perform(clickTopCentre)

        onView(withText("OK")).perform(click())

        assertEquals(10, minutes)
    }
    @Test
    fun test_incrementValue_cancelButton()
    {
        onNumberPicker().perform(clickTopCentre)

        onView(withText("CANCEL")).perform(click())

        assertEquals(0, minutes)
    }
    @Test
    fun test_decrementValue_cancelButton()
    {
        onNumberPicker().perform(clickBottomCentre)

        onView(withText("CANCEL")).perform(click())

       assertEquals(0, minutes)
    }

    @Test
    fun test_defaultValue_cancelButton()
    {

        onView(withText("CANCEL")).perform(click())

        assertEquals(0, minutes)
    }
    private fun onNumberPicker()  =  onView(withId(R.id.dialog_number_picker))

    override fun onValueChange(p0: NumberPicker?, p1: Int, p2: Int) {
        if (p0 != null) {

             minutes = (p0.value + 1) * 5
            //Add one because the list position started at 0, and multiply by 5 because positions were increments of 5

        }
    }
}

