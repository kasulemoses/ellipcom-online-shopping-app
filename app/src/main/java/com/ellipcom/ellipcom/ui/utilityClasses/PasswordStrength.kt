package com.ellipcom.ellipcom.ui.utilityClasses

import android.content.Context
import android.graphics.Color
import com.ellipcom.ellipcom.R

enum class PasswordStrength(internal var resId: Int, color: Int) {
    WEAK(R.string.password_strength_weak, Color.RED),
    MEDIUM(R.string.password_strength_medium, Color.argb(255, 220, 185, 0)),
    STRONG(R.string.password_strength_strong, Color.GREEN),
    VERY_STRONG(R.string.password_strength_very_strong, Color.BLUE);

    var color: Int = 0
        internal set

    init {

        this.color = color
    }

    fun getText(context: Context): CharSequence {
        return context.getText(resId)
    }

    companion object {
        //this value defines the minimum length of the password
        internal var REQUIRED_LENGTH = 8

        //this value defines the maximum length of  the password
        internal var MAXIMUM_LENGTH = 15

        /*
        * this value determines whether if the password should contain special characters,  set it
        * to false if you do not require special characters for your password field
        * */
        internal var REQUIRE_SPECIAL_CHARACTERS = true

        /*
        * this value determines whether if the password should contain digits,  set it
        * to false if you do not require digits for your password field
        * */
        internal var REQUIRE_DIGITS = true


        /*
      * this value determines whether if the password should lower case characters,  set it
      * to false if you do not require digits for your password field
      * */
        internal var REQUIRE_LOWER_CASE = true

        /*
     * this value determines whether if the password should upper case characters,  set it
     * to false if you do not require digits for your password field
     * */
        internal var REQUIRE_UPPER_CASE = true

        fun calculatorStrength(password: String): PasswordStrength {

            var currentScore = 0
            var sawUpper = false
            var sawLower = false
            var sawDigit = false
            var sawSpecial = false

            for (i in 0 until password.length) {

                val c = password[i]

                if (!sawSpecial && !Character.isLetterOrDigit(c)) {

                    currentScore += 1
                    sawSpecial = true

                } else {
                    if (!sawDigit && Character.isDigit(c)) {
                        currentScore += 1
                        sawDigit = true
                    } else {
                        if (!sawUpper || !sawLower) {
                            if (Character.isUpperCase(c))
                                sawUpper = true
                            else
                                sawLower = true

                            if (sawUpper && sawLower)
                                currentScore += 1
                        }
                    }
                }


            }
            if (password.length > REQUIRED_LENGTH) {
                if (REQUIRE_SPECIAL_CHARACTERS && !sawSpecial || REQUIRE_UPPER_CASE && !sawUpper
                    || REQUIRE_LOWER_CASE && !sawLower || REQUIRE_DIGITS && !sawDigit
                ) {
                    currentScore = 1

                } else {
                    currentScore = 2

                    if (password.length > MAXIMUM_LENGTH) {
                        currentScore = 3
                    }
                }
            } else {
                currentScore = 0
            }

            when (currentScore) {
                0 -> return WEAK
                1 -> return MEDIUM
                2 -> return STRONG
                3 -> return VERY_STRONG

            }
            return VERY_STRONG
        }

    }


}