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
import kotlinx.android.synthetic.main.fragment_basic_calculator.*

/**
 * The BasicCalculatorFragment class represents a fragment that provides basic calculator functionality.
 * It extends the Fragment class and handles the user interface and calculations.
 */
class BasicCalculatorFragment : Fragment() {

    /**
     * Represents the state of whether the BasicCalculatorFragment is added.
     */
    companion object {
        var addedBC = false
    }

    /**
     * Creates the view for the BasicCalculatorFragment.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container          The parent view that the fragment's UI should be attached to.
     * @param savedInstanceState The saved instance state of the fragment.
     * @return The View for the fragment's UI.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        /**
         *  Inflate the layout for this fragment
         */
        return inflater.inflate(R.layout.fragment_basic_calculator, container, false)
    }

    /**
     * Called when the fragment's view has been created.
     *
     * @param view               The created view of the fragment.
     * @param savedInstanceState The saved instance state of the fragment.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addNumberValueToText(requireContext(), bt0BC, tvPrimaryBC, 0)
        addNumberValueToText(requireContext(), bt1BC, tvPrimaryBC, 0)
        addNumberValueToText(requireContext(), bt2BC, tvPrimaryBC, 0)
        addNumberValueToText(requireContext(), bt3BC, tvPrimaryBC, 0)
        addNumberValueToText(requireContext(), bt4BC, tvPrimaryBC, 0)
        addNumberValueToText(requireContext(), bt5BC, tvPrimaryBC, 0)
        addNumberValueToText(requireContext(), bt6BC, tvPrimaryBC, 0)
        addNumberValueToText(requireContext(), bt7BC, tvPrimaryBC, 0)
        addNumberValueToText(requireContext(), bt8BC, tvPrimaryBC, 0)
        addNumberValueToText(requireContext(), bt9BC, tvPrimaryBC, 0)
        addNumberValueToText(requireContext(), btBracketOpenBC, tvPrimaryBC, 0)
        addNumberValueToText(requireContext(), btBracketCloseBC, tvPrimaryBC, 0)

        addOperatorValueToText(requireContext(), btAdditionBC, tvPrimaryBC, "+", 0)
        addOperatorValueToText(requireContext(), btSubtractionBC, tvPrimaryBC, "-", 0)
        addOperatorValueToText(requireContext(), btMultiplicationBC, tvPrimaryBC, "*", 0)
        addOperatorValueToText(requireContext(), btDivisionBC, tvPrimaryBC, "/", 0)

        btDotBC.setOnClickListener {
            vibratePhone(requireContext())
            if (!tvPrimaryBC.text.contains(".")) tvPrimaryBC.text = tvPrimaryBC.text.toString() + "."
        }

        btPercentage.setOnClickListener {
            vibratePhone(requireContext())

            try {
                if (tvPrimaryBC.text.isEmpty()) enterNumberToast(requireContext())
                else {
                    val input = tvPrimaryBC.text.toString()
                    val result = (input.toFloat() / 100).toString()
                    tvPrimaryBC.text = CalculationUtil.trimResult(result)
                    tvSecondaryBC.text = "${input}%"
                }
            } catch (e: Exception) {
                invalidInputToast(requireContext())
            }
        }

        btACBC.setOnClickListener {
            vibratePhone(requireContext())

            tvPrimaryBC.text = ""
            tvSecondaryBC.text = ""
            addedBC = false
        }

        btDeleteBC.setOnClickListener {
            vibratePhone(requireContext())

            if (tvPrimaryBC.text.contains("+") ||
                tvPrimaryBC.text.contains("-") ||
                tvPrimaryBC.text.contains("*") ||
                tvPrimaryBC.text.contains("/")
            ) addedBC = false

            if (tvPrimaryBC.text.isNotEmpty()) tvPrimaryBC.text = tvPrimaryBC.text.subSequence(0, tvPrimaryBC.length() - 1)
        }

        btEqualBC.setOnClickListener {
            vibratePhone(requireContext())

            try {
                if (tvPrimaryBC.text.isNotEmpty()) {
                    val input = tvPrimaryBC.text.toString()
                    val result = CalculationUtil.evaluate(input).toString()
                    tvPrimaryBC.text = CalculationUtil.trimResult(result)
                    tvSecondaryBC.text = input
                    addedBC = false
                }
            } catch (e: Exception) {
                invalidInputToast(requireContext())
            }
        }
    }
    /**
     * Called when the fragment is visible to the user and actively running.
     * This is generally tied to the fragment's lifecycle.
     */
    override fun onStart() {
        super.onStart()

        tvPrimaryBC.text = PrefUtil.getPrimaryTextBC(requireContext())
        tvSecondaryBC.text = PrefUtil.getSecondaryTextBC(requireContext())
    }
    /**
     * Called when the Fragment is no longer started.
     * This is generally tied to the fragment's lifecycle.
     */
    override fun onStop() {
        super.onStop()

        PrefUtil.setPrimaryTextBC(requireContext(), tvPrimaryBC.text.toString())
        PrefUtil.setSecondaryTextBC(requireContext(), tvSecondaryBC.text.toString())
    }
}