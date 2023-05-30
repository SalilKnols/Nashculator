package com.knoldus.Nashculator.util

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.knoldus.Nashculator.R
import com.knoldus.Nashculator.fragment.BasicCalculatorFragment.Companion.addedBC
import com.knoldus.Nashculator.fragment.ScientificCalculatorFragment.Companion.addedSC

/**
 * Utility class for handling button-related operations.
 */
object ButtonUtil {

    /**
     * Adds the number value of the button to the given text view when clicked.
     *
     * @param context    The context of the application.
     * @param buttonId   The button ID.
     * @param textViewId The text view ID.
     * @param id         The ID associated with the button (0 for BasicCalculator, 1 for ScientificCalculator).
     */
    fun addNumberValueToText(context: Context, buttonId: Button, textViewId: TextView, id: Int?) {
        buttonId.setOnClickListener {
            vibratePhone(context)
            textViewId.text = "${textViewId.text}${buttonId.text}"
            when (id) {
                0 -> addedBC = false
                1 -> addedSC = false
            }
        }
    }

    /**
     * Adds the operator value to the given text view when clicked.
     *
     * @param context    The context of the application.
     * @param buttonId   The button ID.
     * @param textViewId The text view ID.
     * @param text       The operator value.
     * @param id         The ID associated with the button (0 for BasicCalculator, 1 for ScientificCalculator).
     */
    fun addOperatorValueToText(context: Context, buttonId: Button, textViewId: TextView, text: String, id: Int) {
        buttonId.setOnClickListener {
            vibratePhone(context)

            when (id) {
                0 -> {
                    if (addedBC) textViewId.text = textViewId.text.subSequence(0, textViewId.length() - 1)
                    textViewId.text = textViewId.text.toString() + text
                    addedBC = true
                }
                1 -> {
                    if (addedSC) textViewId.text = textViewId.text.subSequence(0, textViewId.length() - 1)
                    textViewId.text = textViewId.text.toString() + text
                    addedSC = true
                }
            }
        }
    }

    /**
     * Vibrates the phone when called.
     *
     * @param context The context of the application.
     */
    fun vibratePhone(context: Context) {
        val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= 29) vibrator.vibrate(VibrationEffect.createPredefined(VibrationEffect.EFFECT_CLICK))
        else vibrator.vibrate(10)
    }

    /**
     * Displays a toast message for entering a number.
     *
     * @param context The context of the application.
     */
    fun enterNumberToast(context: Context) {
        Toast.makeText(context, context.getString(R.string.enter_number), Toast.LENGTH_SHORT).show()
    }

    /**
     * Displays a toast message for invalid input.
     *
     * @param context The context of the application.
     */
    fun invalidInputToast(context: Context) {
        Toast.makeText(context, context.getString(R.string.invalid_input), Toast.LENGTH_SHORT).show()
    }
}