package com.knoldus.nashculator.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.knoldus.nashculator.util.ButtonUtil.addNumberValueToText
import com.knoldus.nashculator.util.ButtonUtil.addOperatorValueToText
import com.knoldus.nashculator.util.ButtonUtil.enterNumberToast
import com.knoldus.nashculator.util.ButtonUtil.invalidInputToast
import com.knoldus.nashculator.util.ButtonUtil.vibratePhone
import com.knoldus.nashculator.util.CalculationUtil
import com.knoldus.nashculator.util.PrefUtil
import com.knoldus.nashculator.databinding.FragmentBasicCalculatorBinding

class BasicCalculatorFragment : Fragment() {

    private var _binding: FragmentBasicCalculatorBinding? = null
    private val binding get() = _binding!!

    companion object {
        var addedBC = false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBasicCalculatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addNumberValueToText(requireContext(), binding.bt0BC, binding.tvPrimaryBC, 0)
        addNumberValueToText(requireContext(), binding.bt1BC, binding.tvPrimaryBC, 0)
        addNumberValueToText(requireContext(), binding.bt2BC, binding.tvPrimaryBC, 0)
        addNumberValueToText(requireContext(), binding.bt3BC, binding.tvPrimaryBC, 0)
        addNumberValueToText(requireContext(), binding.bt4BC, binding.tvPrimaryBC, 0)
        addNumberValueToText(requireContext(), binding.bt5BC, binding.tvPrimaryBC, 0)
        addNumberValueToText(requireContext(), binding.bt6BC, binding.tvPrimaryBC, 0)
        addNumberValueToText(requireContext(), binding.bt7BC, binding.tvPrimaryBC, 0)
        addNumberValueToText(requireContext(), binding.bt8BC, binding.tvPrimaryBC, 0)
        addNumberValueToText(requireContext(), binding.bt9BC, binding.tvPrimaryBC, 0)
        addNumberValueToText(requireContext(), binding.btBracketOpenBC, binding.tvPrimaryBC, 0)
        addNumberValueToText(requireContext(), binding.btBracketCloseBC, binding.tvPrimaryBC, 0)

        addOperatorValueToText(requireContext(), binding.btAdditionBC, binding.tvPrimaryBC, "+", 0)
        addOperatorValueToText(requireContext(), binding.btSubtractionBC, binding.tvPrimaryBC, "-", 0)
        addOperatorValueToText(requireContext(), binding.btMultiplicationBC, binding.tvPrimaryBC, "*", 0)
        addOperatorValueToText(requireContext(), binding.btDivisionBC, binding.tvPrimaryBC, "/", 0)

        binding.btDotBC.setOnClickListener {
            vibratePhone(requireContext())
            if (!binding.tvPrimaryBC.text.contains(".")) binding.tvPrimaryBC.text = binding.tvPrimaryBC.text.toString() + "."
        }

        binding.btPercentage.setOnClickListener {
            vibratePhone(requireContext())

            try {
                if (binding.tvPrimaryBC.text.isEmpty()) enterNumberToast(requireContext())
                else {
                    val input = binding.tvPrimaryBC.text.toString()
                    val result = (input.toFloat() / 100).toString()
                    binding.tvPrimaryBC.text = CalculationUtil.trimResult(result)
                    binding.tvSecondaryBC.text = "${input}%"
                }
            } catch (e: Exception) {
                invalidInputToast(requireContext())
            }
        }

        binding.btACBC.setOnClickListener {
            vibratePhone(requireContext())

            binding.tvPrimaryBC.text = ""
            binding.tvSecondaryBC.text = ""
            addedBC = false
        }

        binding.btDeleteBC.setOnClickListener {
            vibratePhone(requireContext())

            if (binding.tvPrimaryBC.text.contains("+") ||
                binding.tvPrimaryBC.text.contains("-") ||
                binding.tvPrimaryBC.text.contains("*") ||
                binding.tvPrimaryBC.text.contains("/")
            ) addedBC = false

            if (binding.tvPrimaryBC.text.isNotEmpty()) binding.tvPrimaryBC.text = binding.tvPrimaryBC.text.subSequence(0, binding.tvPrimaryBC.length() - 1)
        }

        binding.btEqualBC.setOnClickListener {
            vibratePhone(requireContext())

            try {
                if (binding.tvPrimaryBC.text.isNotEmpty()) {
                    val input = binding.tvPrimaryBC.text.toString()
                    val result = CalculationUtil.evaluate(input).toString()
                    binding.tvPrimaryBC.text = CalculationUtil.trimResult(result)
                    binding.tvSecondaryBC.text = input
                    addedBC = false
                }
            } catch (e: Exception) {
                invalidInputToast(requireContext())
            }
        }
    }

    override fun onStart() {
        super.onStart()

        binding.tvPrimaryBC.text = PrefUtil.getPrimaryTextBC(requireContext())
        binding.tvSecondaryBC.text = PrefUtil.getSecondaryTextBC(requireContext())
    }

    override fun onStop() {
        super.onStop()

        PrefUtil.setPrimaryTextBC(requireContext(), binding.tvPrimaryBC.text.toString())
        PrefUtil.setSecondaryTextBC(requireContext(), binding.tvSecondaryBC.text.toString())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
