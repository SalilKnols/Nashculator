package com.knoldus.nashculator.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.knoldus.nashculator.util.ButtonUtil
import com.knoldus.nashculator.util.CalculationUtil
import com.knoldus.nashculator.util.PrefUtil
import com.knoldus.nashculator.databinding.FragmentScientificCalculatorBinding
import kotlin.math.sqrt

class ScientificCalculatorFragment : Fragment() {

    companion object {
        var addedSC = false
    }

    private var binding: FragmentScientificCalculatorBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScientificCalculatorBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {
            ButtonUtil.addNumberValueToText(requireContext(), bt0SC, tvPrimarySC, 1)
            ButtonUtil.addNumberValueToText(requireContext(), bt1SC, tvPrimarySC, 1)
            ButtonUtil.addNumberValueToText(requireContext(), bt2SC, tvPrimarySC, 1)
            ButtonUtil.addNumberValueToText(requireContext(), bt3SC, tvPrimarySC, 1)
            ButtonUtil.addNumberValueToText(requireContext(), bt4SC, tvPrimarySC, 1)
            ButtonUtil.addNumberValueToText(requireContext(), bt5SC, tvPrimarySC, 1)
            ButtonUtil.addNumberValueToText(requireContext(), bt6SC, tvPrimarySC, 1)
            ButtonUtil.addNumberValueToText(requireContext(), bt7SC, tvPrimarySC, 1)
            ButtonUtil.addNumberValueToText(requireContext(), bt8SC, tvPrimarySC, 1)
            ButtonUtil.addNumberValueToText(requireContext(), bt9SC, tvPrimarySC, 1)
            ButtonUtil.addNumberValueToText(requireContext(), btSin, tvPrimarySC, 1)
            ButtonUtil.addNumberValueToText(requireContext(), btCos, tvPrimarySC, 1)
            ButtonUtil.addNumberValueToText(requireContext(), btTan, tvPrimarySC, 1)
            ButtonUtil.addNumberValueToText(requireContext(), btLog, tvPrimarySC, 1)
            ButtonUtil.addNumberValueToText(requireContext(), btLn, tvPrimarySC, 1)
            ButtonUtil.addNumberValueToText(requireContext(), btBracketOpenSC, tvPrimarySC, 1)
            ButtonUtil.addNumberValueToText(requireContext(), btBracketCloseSC, tvPrimarySC, 1)

            ButtonUtil.addOperatorValueToText(requireContext(), btAdditionSC, tvPrimarySC, "+", 1)
            ButtonUtil.addOperatorValueToText(requireContext(), btSubtractionSC, tvPrimarySC, "-", 1)
            ButtonUtil.addOperatorValueToText(requireContext(), btMultiplicationSC, tvPrimarySC, "*", 1)
            ButtonUtil.addOperatorValueToText(requireContext(), btDivisionSC, tvPrimarySC, "/", 1)

            btDotSC.setOnClickListener {
                ButtonUtil.vibratePhone(requireContext())
                if (!tvPrimarySC.text.contains(".")) tvPrimarySC.text = tvPrimarySC.text.toString() + "."
            }

            btPi.setOnClickListener {
                ButtonUtil.vibratePhone(requireContext())
                tvPrimarySC.text = tvPrimarySC.text.toString() + "3.142"
                tvSecondarySC.text = btPi.text.toString()
            }

            btFactorial.setOnClickListener {
                ButtonUtil.vibratePhone(requireContext())
                try {
                    if (tvPrimarySC.text.isEmpty()) ButtonUtil.enterNumberToast(requireContext())
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
                    ButtonUtil.invalidInputToast(requireContext())
                }
            }

            btSquare.setOnClickListener {
                ButtonUtil.vibratePhone(requireContext())
                try {
                    if (tvPrimarySC.text.isEmpty()) ButtonUtil.enterNumberToast(requireContext())
                    else {
                        val input = tvPrimarySC.text.toString()
                        val result = (input.toFloat() * input.toFloat()).toString()
                        tvPrimarySC.text = CalculationUtil.trimResult(result)
                        tvSecondarySC.text = "$input²"
                    }
                } catch (e: Exception) {
                    ButtonUtil.invalidInputToast(requireContext())
                }
            }

            btInverted.setOnClickListener {
                ButtonUtil.vibratePhone(requireContext())
                try {
                    if (tvPrimarySC.text.isEmpty()) ButtonUtil.enterNumberToast(requireContext())
                    else {
                        val input = tvPrimarySC.text.toString()
                        val result = (1 / input.toFloat()).toString()
                        tvPrimarySC.text = CalculationUtil.trimResult(result)
                        tvSecondarySC.text = "1/$input"
                    }
                } catch (e: Exception) {
                    ButtonUtil.invalidInputToast(requireContext())
                }
            }

            btSqrt.setOnClickListener {
                ButtonUtil.vibratePhone(requireContext())
                try {
                    if (tvPrimarySC.text.isEmpty()) ButtonUtil.enterNumberToast(requireContext())
                    else {
                        val input = tvPrimarySC.text.toString()
                        val result = (sqrt(input.toFloat())).toString()
                        tvPrimarySC.text = CalculationUtil.trimResult(result)
                        tvSecondarySC.text = "√$input"
                    }
                } catch (e: Exception) {
                    ButtonUtil.invalidInputToast(requireContext())
                }
            }

            btACSC.setOnClickListener {
                ButtonUtil.vibratePhone(requireContext())
                tvPrimarySC.text = ""
                tvSecondarySC.text = ""
                addedSC = false
            }

            btDeleteSC.setOnClickListener {
                ButtonUtil.vibratePhone(requireContext())
                if (tvPrimarySC.text.contains("+") ||
                    tvPrimarySC.text.contains("-") ||
                    tvPrimarySC.text.contains("*") ||
                    tvPrimarySC.text.contains("/")
                ) addedSC = false

                if (tvPrimarySC.text.isNotEmpty()) tvPrimarySC.text = tvPrimarySC.text.subSequence(
                    0,
                    tvPrimarySC.length() - 1
                )
            }

            btEqualSC.setOnClickListener {
                ButtonUtil.vibratePhone(requireContext())
                try {
                    if (tvPrimarySC.text.isNotEmpty()) {
                        val input = tvPrimarySC.text.toString()
                        val result = CalculationUtil.evaluate(input).toString()
                        tvPrimarySC.text = CalculationUtil.trimResult(result)
                        tvSecondarySC.text = input
                        addedSC = false
                    }
                } catch (e: Exception) {
                    ButtonUtil.invalidInputToast(requireContext())
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()

        binding?.apply {
            tvPrimarySC.text = PrefUtil.getPrimaryTextSC(requireContext())
            tvSecondarySC.text = PrefUtil.getSecondaryTextSC(requireContext())
        }
    }

    override fun onStop() {
        super.onStop()

        binding?.apply {
            PrefUtil.setPrimaryTextSC(requireContext(), tvPrimarySC.text.toString())
            PrefUtil.setSecondaryTextSC(requireContext(), tvSecondarySC.text.toString())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
