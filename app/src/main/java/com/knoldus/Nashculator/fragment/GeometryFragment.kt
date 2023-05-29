package com.knoldus.Nashculator.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.knoldus.Nashculator.util.ButtonUtil

import com.knoldus.Nashculator.databinding.FregmentStaticsCaculatorBinding

class GeometryFragment : Fragment() {

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

        // Perform operations when buttons are clicked
        binding.btnConvert.setOnClickListener {
            ButtonUtil.vibratePhone(requireContext())

            try {
                if (binding.tvStaticOutput.text.isEmpty()) {
                    ButtonUtil.enterNumberToast(requireContext())
                } else {
                    val input = binding.tvStaticOutput.text.toString().toFloat()

                    // Calculate perimeter
                    val perimeter = calculatePerimeter(input)
                    displayResult("Perimeter: $perimeter")

                    // Calculate area
                    val area = calculateArea(input)
                    displayResult("Area: $area")

                    // Calculate volume
                    val volume = calculateVolume(input)
                    displayResult("Volume: $volume")
                }
            } catch (e: Exception) {
                ButtonUtil.invalidInputToast(requireContext())
            }
        }
    }

    private fun calculatePerimeter(side: Float): Float {
        // Calculate perimeter of a shape (e.g., square, rectangle)
        return 4 * side
    }

    private fun calculateArea(side: Float): Float {
        // Calculate area of a shape (e.g., square, rectangle)
        return side * side
    }

    private fun calculateVolume(side: Float): Float {
        // Calculate volume of a shape (e.g., cube)
        return side * side * side
    }

    private fun displayResult(result: String) {
        // Append the result to the output text view
        binding.tvStaticOutput.append("\n$result")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
