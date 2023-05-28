package com.knoldus.nashculator.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.knoldus.nashculator.databinding.FregmentStaticsCaculatorBinding
import com.knoldus.nashculator.util.ButtonUtil
import com.knoldus.nashculator.util.CalculationUtil

class StaticalFregment : Fragment() {

    private var _binding: FregmentStaticsCaculatorBinding? = null
    private val binding get() = _binding!!

    companion object {
        var addedBC = false
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FregmentStaticsCaculatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        ButtonUtil.addNumberValueToText(
            requireContext(),
            binding.btnConvert,
            binding.tvStaticOutput,
            0
        )

        ButtonUtil.addNumberValueToText(
            requireContext(),
            binding.btnConvert,
            binding.tvStaticOutput,
            0
        )

        binding.btnConvert.setOnClickListener {
            ButtonUtil.vibratePhone(requireContext())
            if (!binding.tvStaticOutput.text.contains(".")) binding.tvStaticOutput.text =
                binding.tvStaticOutput.text.toString() + "."
        }

        binding.btnConvert.setOnClickListener {
            ButtonUtil.vibratePhone(requireContext())

            try {
                if (binding.tvStaticOutput.text.isEmpty()) ButtonUtil.enterNumberToast(
                    requireContext()
                )
                else {
                    val input = binding.tvStaticOutput.text.toString()
                    val result = (input.toFloat() / 100).toString()
                    binding.tvStaticOutput.text = CalculationUtil.trimResult(result)
                    binding.tvStaticOutput.text = "${input}%"
                }
            } catch (e: Exception) {
                ButtonUtil.invalidInputToast(requireContext())
            }
        }
    }
}








