package com.knoldus.Nashculator.fragment

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import com.knoldus.Nashculator.R
import com.knoldus.Nashculator.util.ButtonUtil.vibratePhone
import com.knoldus.Nashculator.util.PrefUtil
import kotlinx.android.synthetic.main.fragment_counter.*

/**
 * A fragment that represents a counter with an interval.
 */
class CounterFragment : Fragment() {

    /**
     * The current count value.
     */
    private var count = 0
    /**
     * The interval value for incrementing or decrementing the count.
     */
    private var interval: Int? = null

    /**
     * Called when the fragment is created.
     *
     * @param inflater           The LayoutInflater object that can be used to inflate any views in the fragment.
     * @param container          If non-null, this is the parent view that the fragment's UI should be attached to.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state.
     * @return The View for the fragment's UI, or null.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_counter, container, false)
    }

    /**
     * Called immediately after onCreateView() has returned, but before any saved state has been restored.
     *
     * @param view               The View returned by onCreateView().
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous saved state.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        /**
         * Hides the soft keyboard.
         */
        fun View.hideKeyboard() {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(windowToken, 0)
        }

        /**
         * Handles the key events for the interval TextView.
         *
         * @param v       The view that received the event.
         * @param keyCode The key code of the key event.
         * @param event   The key event.
         * @return true if the event is handled, false otherwise.
         */
        tvInterval.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    tvInterval.clearFocus()
                    tvInterval.isCursorVisible = false
                    view.hideKeyboard()

                    interval = tvInterval.text.toString().toIntOrNull()

                    if (interval == null) {
                        interval = 1
                        tvInterval.setText("1")
                    }
                    return true
                }
                return false
            }
        })

        fabIncrement.setOnClickListener {
            vibratePhone(requireContext())
            count += interval!!
            tvCounter.text = "$count"
        }

        fabDecrement.setOnClickListener {
            vibratePhone(requireContext())
            count -= interval!!
            tvCounter.text = "$count"
        }

        fabReset.setOnClickListener {
            vibratePhone(requireContext())
            count = 0
            tvCounter.text = "0"
        }

        btIncrement.setOnClickListener {
            interval = interval?.plus(1)
            tvInterval.setText(interval.toString())
        }

        btDecrement.setOnClickListener {
            interval = interval?.minus(1)
            tvInterval.setText(interval.toString())
        }
    }
    /**
     * Called when the fragment is visible to the user and actively running.
     */
    override fun onStart() {
        super.onStart()

        count = PrefUtil.getCount(requireContext())
        interval = PrefUtil.getInterval(requireContext())

        tvCounter.text = "$count"
        tvInterval.setText(interval.toString())
    }
    /**
     * Called when the Fragment is no longer started.
     */
    override fun onStop() {
        super.onStop()

        PrefUtil.setCount(requireContext(), count)
        PrefUtil.setInterval(requireContext(), interval!!)
    }
}

