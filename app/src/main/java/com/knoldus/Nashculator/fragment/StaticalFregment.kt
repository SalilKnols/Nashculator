package com.knoldus.Nashculator.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.knoldus.Nashculator.util.ButtonUtil
import com.knoldus.Nashculator.databinding.FregmentStaticsCaculatorBinding

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

        // Populate the conversion options dropdown
        val options = arrayOf("Mean", "Median", "Mode")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, options)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerOperations.adapter = adapter

        binding.btnConvert.setOnClickListener {
            ButtonUtil.vibratePhone(requireContext())

            val input = binding.etInput.text.toString()
            val numbers = extractNumbers(input)

            when (binding.spinnerOperations.selectedItemPosition) {
                0 -> calculateMean(numbers)
                1 -> calculateMedian(numbers)
                2 -> calculateMode(numbers)
            }
        }
    }

    private fun extractNumbers(input: String): List<Float> {
        return input.split(",")
            .mapNotNull { it.trim().toFloatOrNull() }
            .ifEmpty { listOf() }
    }

    private fun calculateMean(numbers: List<Float>) {
        if (numbers.isNotEmpty()) {
            val mean = numbers.sum() / numbers.size
            binding.tvOutput.text = "Mean: $mean"
        } else {
            binding.tvOutput.text = "No valid numbers entered."
        }
    }

    private fun calculateMedian(numbers: List<Float>) {
        if (numbers.isNotEmpty()) {
            numbers.sorted().let { sortedNumbers ->
                val middle = sortedNumbers.size / 2
                val median: Float = if (sortedNumbers.size % 2 == 0) {
                    (sortedNumbers[middle - 1] + sortedNumbers[middle]) / 2
                } else {
                    sortedNumbers[middle]
                }
                binding.tvOutput.text = "Median: $median"
            }
        } else {
            binding.tvOutput.text = "No valid numbers entered."
        }
    }

    private fun calculateMode(numbers: List<Float>) {
        if (numbers.isNotEmpty()) {
            val frequencyMap = mutableMapOf<Float, Int>()

            numbers.forEach { number ->
                frequencyMap[number] = frequencyMap.getOrDefault(number, 0) + 1
            }

            var maxFrequency = 0
            var mode: Float? = null

            for ((number, frequency) in frequencyMap) {
                if (frequency > maxFrequency) {
                    maxFrequency = frequency
                    mode = number
                }
            }

            if (mode != null) {
                binding.tvOutput.text = "Mode: $mode (Frequency: $maxFrequency)"
            } else {
                binding.tvOutput.text = "No mode found."
            }
        } else {
            binding.tvOutput.text = "No valid numbers entered."
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
