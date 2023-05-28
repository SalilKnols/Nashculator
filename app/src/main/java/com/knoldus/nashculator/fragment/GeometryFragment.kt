package com.knoldus.nashculator.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.knoldus.nashculator.databinding.FregmentGeometryCalculatorBinding
import com.knoldus.nashculator.util.ButtonUtil
import com.knoldus.nashculator.util.CalculationUtil

class GeometryFragment : Fragment()  {


    private var _binding: FregmentGeometryCalculatorBinding? = null
    private val binding get() = _binding!!

    companion object {
        var addedBC = false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FregmentGeometryCalculatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ButtonUtil.addNumberValueToText(
            requireContext(),
            binding.btnCalculate,
            binding.tvGeoOutput,
            0
        )

//
//        ButtonUtil.addOperatorValueToText(
//            requireContext(),
//            binding.btDivisionBC,
//            binding.tvPrimaryBC,
//            "/",
//            0
//        )

        binding.btnCalculate.setOnClickListener {
            ButtonUtil.vibratePhone(requireContext())
            if (!binding.tvGeoOutput.text.contains(".")) binding.tvGeoOutput.text =
                binding.tvGeoOutput.text.toString() + "."
        }

        binding.btnCalculate.setOnClickListener {
            ButtonUtil.vibratePhone(requireContext())

            try {
                if (binding.tvGeoOutput.text.isEmpty()) ButtonUtil.enterNumberToast(requireContext())
                else {
                    val input = binding.tvGeoOutput.text.toString()
                    val result = (input.toFloat() / 100).toString()
                    binding.tvGeoOutput.text = CalculationUtil.trimResult(result)
                    binding.tvGeoOutput.text = "${input}%"
                }
            } catch (e: Exception) {
                ButtonUtil.invalidInputToast(requireContext())
            }
        }


    }
}