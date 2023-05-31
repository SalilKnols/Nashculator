package com.knoldus.Nashculator.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.knoldus.Nashculator.R
import com.knoldus.Nashculator.util.ButtonUtil.addNumberValueToText
import com.knoldus.Nashculator.util.ButtonUtil.addOperatorValueToText
import com.knoldus.Nashculator.util.ButtonUtil.enterNumberToast
import com.knoldus.Nashculator.util.ButtonUtil.invalidInputToast
import com.knoldus.Nashculator.util.ButtonUtil.vibratePhone
import com.knoldus.Nashculator.util.CalculationUtil
import com.knoldus.Nashculator.util.PrefUtil
import kotlinx.android.synthetic.main.fragment_scientific_calculator.*
import kotlin.math.sqrt

/**
 * A fragment representing the Scientific Calculator screen.
 */
class ScientificCalculatorFragment : Fragment() {

    /**
     * Indicates whether the Scientific Calculator fragment is added.
     */
    companion object {
        var addedSC = false
    }

    /**
     * Called when the fragment is created.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate any views in the fragment
     * @param container          The parent view that the fragment's UI should be attached to
     * @param savedInstanceState The previously saved state of the fragment
     * @return The View for the fragment's UI, or null
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scientific_calculator, container, false)
    }
    /**
     * Called when the fragment's view is created.
     *
     * @param view               The View returned by onCreateView(LayoutInflater, ViewGroup, Bundle)
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addNumberValueToText(requireContext(), bt0SC, tvPrimarySC, 1)
        addNumberValueToText(requireContext(), bt1SC, tvPrimarySC, 1)
        addNumberValueToText(requireContext(), bt2SC, tvPrimarySC, 1)
        addNumberValueToText(requireContext(), bt3SC, tvPrimarySC, 1)
        addNumberValueToText(requireContext(), bt4SC, tvPrimarySC, 1)
        addNumberValueToText(requireContext(), bt5SC, tvPrimarySC, 1)
        addNumberValueToText(requireContext(), bt6SC, tvPrimarySC, 1)
        addNumberValueToText(requireContext(), bt7SC, tvPrimarySC, 1)
        addNumberValueToText(requireContext(), bt8SC, tvPrimarySC, 1)
        addNumberValueToText(requireContext(), bt9SC, tvPrimarySC, 1)
        addNumberValueToText(requireContext(), btSin, tvPrimarySC, 1)
        addNumberValueToText(requireContext(), btCos, tvPrimarySC, 1)
        addNumberValueToText(requireContext(), btTan, tvPrimarySC, 1)
        addNumberValueToText(requireContext(), btLog, tvPrimarySC, 1)
        addNumberValueToText(requireContext(), btLn, tvPrimarySC, 1)
        addNumberValueToText(requireContext(), btBracketOpenSC, tvPrimarySC, 1)
        addNumberValueToText(requireContext(), btBracketCloseSC, tvPrimarySC, 1)

        addOperatorValueToText(requireContext(), btAdditionSC, tvPrimarySC, "+", 1)
        addOperatorValueToText(requireContext(), btSubtractionSC, tvPrimarySC, "-", 1)
        addOperatorValueToText(requireContext(), btMultiplicationSC, tvPrimarySC, "*", 1)
        addOperatorValueToText(requireContext(), btDivisionSC, tvPrimarySC, "/", 1)
        /**
         * ...add similar click listeners for other buttons
         */
        btDotSC.setOnClickListener {
            vibratePhone(requireContext())
            if (!tvPrimarySC.text.contains(".")) tvPrimarySC.text = tvPrimarySC.text.toString() + "."
        }

        btPi.setOnClickListener {
            vibratePhone(requireContext())

            tvPrimarySC.text = tvPrimarySC.text.toString() + "3.142"
            tvSecondarySC.text = btPi.text.toString()
        }

        btFactorial.setOnClickListener {
            vibratePhone(requireContext())

            try {
                if (tvPrimarySC.text.isEmpty()) enterNumberToast(requireContext())
                else {
                    val input = tvPrimarySC.text.toString()
                    var factorial = 1.0
                    for (i in 1..input.toLong()) {
                        factorial *= i
                    }
                    tvPrimarySC.text = CalculationUtil.trimResult(factorial.toString())
                    tvSecondarySC.text = "$input!"
                }
            } catch (e: Exception) {
                invalidInputToast(requireContext())
            }
        }

        btSquare.setOnClickListener {
            vibratePhone(requireContext())

            try {
                if (tvPrimarySC.text.isEmpty()) enterNumberToast(requireContext())
                else {
                    val input = tvPrimarySC.text.toString()
                    val result = (input.toFloat() * input.toFloat()).toString()
                    tvPrimarySC.text = CalculationUtil.trimResult(result)
                    tvSecondarySC.text = "$input²"
                }
            } catch (e: Exception) {
                invalidInputToast(requireContext())
            }
        }

        btInverted.setOnClickListener {
            vibratePhone(requireContext())

            try {
                if (tvPrimarySC.text.isEmpty()) enterNumberToast(requireContext())
                else {
                    val input = tvPrimarySC.text.toString()
                    val result = (1 / input.toFloat()).toString()
                    tvPrimarySC.text = CalculationUtil.trimResult(result)
                    tvSecondarySC.text = "1/$input"
                }
            } catch (e: Exception) {
                invalidInputToast(requireContext())
            }
        }

        btSqrt.setOnClickListener {
            vibratePhone(requireContext())

            try {
                if (tvPrimarySC.text.isEmpty()) enterNumberToast(requireContext())
                else {
                    val input = tvPrimarySC.text.toString()
                    val result = (sqrt(input.toFloat())).toString()
                    tvPrimarySC.text = CalculationUtil.trimResult(result)
                    tvSecondarySC.text = "√$input"
                }
            } catch (e: Exception) {
                invalidInputToast(requireContext())
            }
        }

        btACSC.setOnClickListener {
            vibratePhone(requireContext())

            tvPrimarySC.text = ""
            tvSecondarySC.text = ""
            addedSC = false
        }

        btDeleteSC.setOnClickListener {
            vibratePhone(requireContext())

            if (tvPrimarySC.text.contains("+") ||
                tvPrimarySC.text.contains("-") ||
                tvPrimarySC.text.contains("*") ||
                tvPrimarySC.text.contains("/")
            ) addedSC = false

            if (tvPrimarySC.text.isNotEmpty()) tvPrimarySC.text = tvPrimarySC.text.subSequence(0, tvPrimarySC.length() - 1)
        }

        btEqualSC.setOnClickListener {
            vibratePhone(requireContext())

            try {
                if (tvPrimarySC.text.isNotEmpty()) {
                    val input = tvPrimarySC.text.toString()
                    val result = CalculationUtil.evaluate(input).toString()
                    tvPrimarySC.text = CalculationUtil.trimResult(result)
                    tvSecondarySC.text = input
                    addedSC = false
                }
            } catch (e: Exception) {
                invalidInputToast(requireContext())
            }
        }
    }

    /**
     * Called when the fragment is about to become visible.
     */
    override fun onStart() {
        super.onStart()

        tvPrimarySC.text = PrefUtil.getPrimaryTextSC(requireContext())
        tvSecondarySC.text = PrefUtil.getSecondaryTextSC(requireContext())
    }
    /**
     * Called when the fragment is no longer started.
     */
    override fun onStop() {
        super.onStop()

        PrefUtil.setPrimaryTextSC(requireContext(), tvPrimarySC.text.toString())
        PrefUtil.setSecondaryTextSC(requireContext(), tvSecondarySC.text.toString())
    }
}