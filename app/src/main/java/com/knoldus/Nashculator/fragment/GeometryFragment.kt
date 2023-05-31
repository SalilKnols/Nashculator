import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.knoldus.Nashculator.databinding.FregmentGeometryCalculatorBinding
import com.knoldus.Nashculator.util.ButtonUtil.vibratePhone
import kotlin.math.pow
/**
 * A fragment that represents a geometry calculator.
 */
class GeometryFragment : Fragment() {
    private var binding: FregmentGeometryCalculatorBinding? = null

    /**
     * Creates and returns the view associated with the fragment.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container          The parent view that the fragment's UI should be attached to.
     * @param savedInstanceState The previously saved state of the fragment.
     * @return The View for the fragment's UI.
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FregmentGeometryCalculatorBinding.inflate(inflater, container, false)
        return binding!!.root
    }
    /**
     * Called immediately after the view has been created.
     *
     * @param view               The view created by onCreateView.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Populate the conversion options dropdown
        val options = arrayOf("Circle", "Rectangle", "Triangle","Square","Trapezoid",
            "Ellipse","cube","Cuboid","Cylinder","Cone","Sphere","Parallelogram")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, options)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding!!.spinnerOperation2.adapter = adapter
        binding!!.btnCalculate.setOnClickListener { v ->
            vibratePhone(requireContext())
            val input = binding!!.etGeoInput.text.toString()
            val values = extractValues(input)
            if (values.isNotEmpty()) {
                when (binding!!.spinnerOperation2.selectedItemPosition) {
                    0 -> calculateCircle(values)
                    1 -> calculateRectangle(values)
                    2 -> calculateTriangle(values)
                    3 -> calculateSquare(values)
                    4 -> calculateTrapezoid(values)
                    5 -> calculateEllipse(values)
                    6 -> calculateCube(values)
                    7 -> calculateCuboid(values)
                    8 -> calculateCylinder(values)
                    9 -> calculateCone(values)
                    10 -> calculateParallelogram(values)
                    11 -> calculateSphere(values)
                }
                binding!!.etGeoInput.text.clear() // Clear the input field

            } else {
                binding!!.tvGeoOutput.text = "No valid values entered."
            }
            // Clear the output after a delay
            binding!!.tvGeoOutput.postDelayed({
                binding!!.tvGeoOutput.text = ""
            }, 10000) // Adjust the delay time (in milliseconds) as needed
        }
    }
    /**
     * Extracts the values from the input string.
     *
     * @param input The input string containing the values separated by commas.
     * @return An array of floats extracted from the input string.
     */
    private fun extractValues(input: String): FloatArray {
        val tokens = input.split(",".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()
        return try {
            tokens.map { it.trim().toFloat() }.toFloatArray()
        } catch (e: NumberFormatException) {
            FloatArray(0)
        }
    }
    /**
     * Calculates the area, perimeter, and volume of a circle.
     *
     * @param values The array of values containing the radius.
     */
    private fun calculateCircle(values: FloatArray) {
        if (values.size == 1) {
            val radius = values[0]
            val area = (Math.PI * radius * radius).toFloat()
            val perimeter = (2 * Math.PI * radius).toFloat()
            binding!!.tvGeoOutput.text = "Area: $area\nPerimeter: $perimeter"
        } else {
            binding!!.tvGeoOutput.text = "Invalid input for area calculation."
        }
    }
    /**
     * Calculates the area, perimeter, and volume of a rectangle.
     *
     * @param values The array of values containing the length and width.
     */
    private fun calculateRectangle(values: FloatArray) {
        if (values.size == 2) {
            val length = values[0]
            val width = values[1]
            val area = (length * width).toFloat()
            val perimeter = 2 * (length + width)
            binding!!.tvGeoOutput.text = "Area: $area\nPerimeter: $perimeter"
        } else {
            binding!!.tvGeoOutput.text = "Invalid input for perimeter calculation."
        }
    }
    /**
     * Calculates the area and perimeter of a triangle.
     *
     * @param values The array of values containing the lengths of the triangle's sides.
     */
    private fun calculateTriangle(values: FloatArray) {
        if (values.size == 3) {
            val sideA = values[0]
            val sideB = values[1]
            val sideC = values[2]

            val perimeter = sideA + sideB + sideC

            val semiPerimeter = perimeter / 2
            val area = Math.sqrt(
                (semiPerimeter * (semiPerimeter - sideA) *
                        (semiPerimeter - sideB) * (semiPerimeter - sideC)).toDouble()
            ).toFloat()

            val volume = 0.0f // Volume calculation not applicable for triangle

            binding!!.tvGeoOutput.text = "Area: $area\nPerimeter: $perimeter\n"
        } else {
            binding!!.tvGeoOutput.text = "Invalid input for volume calculation."
        }
    }
    /**
     * Calculates the area, perimeter, and volume of a square.
     *
     * @param values The array of values containing the length of the square's side.
     */
      private fun calculateSquare(values: FloatArray) {
            if (values.size == 1) {
                val length = values[0]
                val area = length * length
                val perimeter = 4 * length

                binding!!.tvGeoOutput.text = "Area: $area\nPerimeter: $perimeter"
            } else {
                binding!!.tvGeoOutput.text = "Invalid input for volume calculation."
            }
        }
    /**
     * Calculates the area and perimeter of a trapezoid.
     *
     * @param values The array of values containing the lengths of the bases and the height of the trapezoid.
     */
      private fun calculateTrapezoid(values: FloatArray) {
                if (values.size == 3) {
                    val base1 = values[0]
                    val base2 = values[1]
                    val height = values[2]

                    val area = ((base1 + base2) * height) / 2
                    val perimeter = base1 + base2 + 2 * Math.sqrt((((base2 - base1) / 2).pow(2) + height.pow(2)).toDouble())
                    val volume = 0.0f // Volume calculation not applicable for trapezoid

                    binding!!.tvGeoOutput.text = "Area: $area\nPerimeter: $perimeter"
                } else {
                    binding!!.tvGeoOutput.text = "Invalid input for volume calculation."
                }
        }
    /**
     * Calculates the area and perimeter of an ellipse.
     *
     * @param values The array of values containing the semi-major axis and semi-minor axis of the ellipse.
     */
    private fun calculateEllipse(values: FloatArray) {
        if (values.size == 2) {
            val semiMajorAxis = values[0]
            val semiMinorAxis = values[1]

            val area = Math.PI * semiMajorAxis * semiMinorAxis
            val perimeter = 2 * Math.PI * Math.sqrt(((semiMajorAxis.pow(2) + semiMinorAxis.pow(2)) / 2).toDouble())
            val volume = 0.0f // Volume calculation not applicable for ellipse

            binding!!.tvGeoOutput.text = "Area: $area\nPerimeter: $perimeter"
        } else {
            binding!!.tvGeoOutput.text = "Invalid input for volume calculation."
        }
    }
    /**
     * Calculates the surface area and volume of a cube.
     *
     * @param values The array of values containing the length of the cube's side.
     */
    private fun calculateCube(values: FloatArray) {
        if (values.size == 1) {
            val length = values[0]

            val surfaceArea = 6 * length * length
            val volume = length * length * length

            binding!!.tvGeoOutput.text = "Surface Area: $surfaceArea\nVolume: $volume"
        } else {
            binding!!.tvGeoOutput.text = "Invalid input for volume calculation."
        }
    }

    /**
     * Calculates the surface area and volume of a cuboid.
     *
     * @param values The array of values containing the length, width & height of the cuboids side.
     */
    private fun calculateCuboid(values: FloatArray) {
        if (values.size == 3) {
            val length = values[0]
            val width = values[1]
            val height = values[2]

            val surfaceArea = 2 * (length * width + width * height + height * length)
            val volume = length * width * height

            binding!!.tvGeoOutput.text = "Surface Area: $surfaceArea\nVolume: $volume"
        } else {
            binding!!.tvGeoOutput.text = "Invalid input for volume calculation."
        }
    }
    /**
     * Calculates the surface area and volume of a cuboid.
     *
     * @param values The array of values containing the length, width, and height of the cuboid.
     */
    private fun calculateCylinder(values: FloatArray) {
        if (values.size == 2) {
            val radius = values[0]
            val height = values[1]

            val baseArea = Math.PI * radius * radius
            val lateralArea = 2 * Math.PI * radius * height
            val totalSurfaceArea = 2 * baseArea + lateralArea
            val volume = baseArea * height

            binding!!.tvGeoOutput.text = "Surface Area: $totalSurfaceArea\nVolume: $volume"
        } else {
            binding!!.tvGeoOutput.text = "Invalid input for volume calculation."
        }
    }
    /**
     * Calculates the surface area and volume of a cone.
     *
     * @param values The array of values containing the radius and height of the cone.
     */
    private fun calculateCone(values: FloatArray) {
        if (values.size == 2) {
            val radius = values[0]
            val height = values[1]

            val baseArea = Math.PI * radius * radius
            val slantHeight = Math.sqrt(((radius * radius) + (height * height)).toDouble())
            val lateralArea = Math.PI * radius * slantHeight
            val totalSurfaceArea = baseArea + lateralArea
            val volume = (baseArea * height) / 3

            binding!!.tvGeoOutput.text = "Surface Area: $totalSurfaceArea\nVolume: $volume"
        } else {
            binding!!.tvGeoOutput.text = "Invalid input for volume calculation."
        }
    }
    /**
     * Calculates the area, perimeter, and volume of a parallelogram.
     *
     * @param values The array of values containing the base and height of the parallelogram.
     */
    private fun calculateParallelogram(values: FloatArray) {
        if (values.size == 2) {
            val base = values[0]
            val height = values[1]

            val area = base * height
            val perimeter = 2 * (base + height)
            val volume = area * height

            binding!!.tvGeoOutput.text = "Area: $area\nPerimeter: $perimeter\nVolume: $volume"
        } else {
            binding!!.tvGeoOutput.text = "Invalid input for volume calculation."
        }
    }

    private fun calculateSphere(values: FloatArray) {
        if (values.size == 1) {
            val radius = values[0]
            val volume = (4/3) * Math.PI * Math.pow(radius.toDouble(), 3.0)
            val surfaceArea = 4 * Math.PI * Math.pow(radius.toDouble(), 2.0)
            binding!!.tvGeoOutput.text = "Volume: $volume\nSurface Area: $surfaceArea"
        } else {
            binding!!.tvGeoOutput.text = "Invalid input for volume calculation."
        }
    }
    /**
     * Called when the view associated with the fragment is being destroyed.
     */
    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
