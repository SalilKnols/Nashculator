package com.knoldus.Nashculator.util

import kotlin.math.*

/**
 * Utility class for evaluating mathematical expressions and trimming the result.
 */
object CalculationUtil {

    /**
     * Evaluates the given mathematical expression and returns the result as a float value.
     *
     * @param input The mathematical expression to evaluate.
     * @return The evaluated result as a float value.
     */
    fun evaluate(input: String): Float {
        return object : Any() {
            var position = -1
            var char = 0
            var x = 0F

            /**
             * Moves to the next character in the input.
             */
            fun moveToNextChar() {
                char = if (++position < input.length) input[position].code else -1
            }

            /**
             * Checks if the current character matches the specified character and removes it if found.
             *
             * @param charToRemove The character to check and remove.
             * @return True if the character was found and removed, false otherwise.
             */
            fun checkAndRemove(charToRemove: Int): Boolean {
                while (char == ' '.code) moveToNextChar()

                if (char == charToRemove) {
                    moveToNextChar()
                    return true
                }
                return false
            }

            /**
             * Parses the mathematical expression.
             *
             * @return The evaluated result as a float value.
             */
            fun parse(): Float {
                moveToNextChar()
                x = parseAddSub()

                if (position < input.length) throw RuntimeException("Unexpected: " + char.toChar())
                return x
            }

            /**
             * Parses the addition and subtraction operations.
             *
             * @return The evaluated result as a float value.
             */
            fun parseAddSub(): Float {
                x = parseMulDiv()

                while (true) {
                    when {
                        checkAndRemove('+'.code) -> x += parseMulDiv()
                        checkAndRemove('-'.code) -> x -= parseMulDiv()
                        else -> return x
                    }
                }
            }

            /**
             * Parses the multiplication and division operations.
             *
             * @return The evaluated result as a float value.
             */
            fun parseMulDiv(): Float {
                x = parseOther()

                while (true) {
                    when {
                        checkAndRemove('*'.code) -> x *= parseOther()
                        checkAndRemove('/'.code) -> x /= parseOther()
                        else -> return x
                    }
                }
            }

            /**
             * Parses other operations such as parentheses and mathematical functions.
             *
             * @return The evaluated result as a float value.
             */
            fun parseOther(): Float {
                if (checkAndRemove('+'.code)) return parseOther()
                if (checkAndRemove('-'.code)) return -parseOther()

                val startPosition = position

                if (checkAndRemove('('.code)) {
                    x = parseAddSub()
                    checkAndRemove(')'.code)
                } else if (char >= '0'.code && char <= '9'.code || char == '.'.code) {
                    while (char >= '0'.code && char <= '9'.code || char == '.'.code) moveToNextChar()
                    x = input.substring(startPosition, position).toFloat()
                } else if (char >= 'a'.code && char <= 'z'.code) {
                    while (char >= 'a'.code && char <= 'z'.code) moveToNextChar()
                    val function = input.substring(startPosition, position)
                    x = parseOther()
                    x = when (function) {
                        "sin" -> sin(Math.toRadians(x.toDouble())).toFloat()
                        "cos" -> cos(Math.toRadians(x.toDouble())).toFloat()
                        "tan" -> tan(Math.toRadians(x.toDouble())).toFloat()
                        "log" -> log10(x)
                        "ln" -> ln(x)
                        else -> throw RuntimeException("Unknown function: $function")
                    }
                } else throw RuntimeException("Unexpected: " + char.toChar())
                if (checkAndRemove('^'.code)) x = x.pow(parseOther())
                return x
            }
        }.parse()
    }

    /**
     * Trims the decimal places of the given result string, removing trailing zeros and the decimal point if necessary.
     *
     * @param result The result string to trim.
     * @return The trimmed result string.
     */
    fun trimResult(result: String?): String? {
        return if (!result.isNullOrEmpty()) {
            if (result.indexOf(".") < 0) result
            else result.replace("0*$".toRegex(), "").replace("\\.$".toRegex(), "")
        } else result
    }
}