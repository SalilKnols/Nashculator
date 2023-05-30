package com.knoldus.Nashculator.fragment

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.knoldus.Nashculator.util.ButtonUtil
import com.knoldus.Nashculator.databinding.FregmentStaticsCaculatorBinding

/**
 * A fragment representing the Statistical Calculator screen.
 */
class StaticalFregment : Fragment() {

    private var _binding: FregmentStaticsCaculatorBinding? = null
    private val binding get() = _binding!!

    /**
     * Indicates whether the Statistical Calculator fragment is added.
     */
    companion object {
        var addedBC = false
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
        _binding = FregmentStaticsCaculatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * Called when the fragment's view is created.
     *
     * @param view               The View returned by onCreateView(LayoutInflater, ViewGroup, Bundle)
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state
     */
    @RequiresApi(Build.VERSION_CODES.N)
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

    /**
     * Extracts a list of floating-point numbers from the input string.
     *
     * @param input The input string containing comma-separated numbers
     * @return The list of extracted numbers as floats, or an empty list if no valid numbers are found
     */
    private fun extractNumbers(input: String): List<Float> {
        return input.split(",")
            .mapNotNull { it.trim().toFloatOrNull() }
            .ifEmpty { listOf() }
    }

    /**
     * Calculates the mean (average) of a list of numbers and displays the result.
     *
     * @param numbers The list of numbers
     */
    private fun calculateMean(numbers: List<Float>) {
        if (numbers.isNotEmpty()) {
            val mean = numbers.sum() / numbers.size
            binding.tvOutput.text = "Mean: $mean"
        } else {
            binding.tvOutput.text = "No valid numbers entered."
        }
    }

    /**
     * Calculates the median of a list of numbers and displays the result.
     *
     * @param numbers The list of numbers
     */
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

    /**
     * Calculates the mode of a list of numbers and displays the result.
     *
     * @param numbers The list of numbers
     */
    @RequiresApi(Build.VERSION_CODES.N)
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

    /**
     * Called when the view hierarchy associated with the fragment is about to be destroyed.
     * Performs necessary cleanup tasks and releases references to views and bindings.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
