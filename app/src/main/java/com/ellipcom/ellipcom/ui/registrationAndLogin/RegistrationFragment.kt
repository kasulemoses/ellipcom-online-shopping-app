package com.ellipcom.ellipcom.ui.registrationAndLogin

import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ellipcom.ellipcom.R
import com.ellipcom.ellipcom.databinding.RegistrationFragmentBinding
import com.ellipcom.ellipcom.ui.utilityClasses.PasswordStrength
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth

class RegistrationFragment : Fragment() {

    private var _binding: RegistrationFragmentBinding? = null
    private val binding get() = _binding!!

    //views
    private lateinit var linearReg: LinearLayout
    private lateinit var loginHaveAccount: TextView
    private lateinit var textEditRegisterUsername: TextInputEditText
    private lateinit var textEditRegisterEmail: TextInputEditText
    private lateinit var textEditRegisterPassword: TextInputEditText
    private lateinit var btnRegister: MaterialButton
    private lateinit var passwordProgressbar: ProgressBar
    private lateinit var tvPasswordStrength: TextView
    private lateinit var progressbarRegister: ProgressBar


    private lateinit var viewModel: RegistrationViewModel

    //firebase
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = RegistrationFragmentBinding.inflate(inflater, container, false)

        auth = FirebaseAuth.getInstance()

        linearRegView()

        return binding.root
    }

    private fun linearRegView() {
        progressbarRegister = binding.progressbarRegister
        passwordProgressbar = binding.passwordProgressbar
        tvPasswordStrength = binding.tvPasswordStrength

        linearReg = binding.linearReg
        loginHaveAccount = binding.loginHaveAccount
        btnRegister = binding.btnRegister
        textEditRegisterEmail = binding.texteditRegisterEmail
        textEditRegisterPassword = binding.texteditRegisterPassword
        textEditRegisterUsername = binding.texteditRegisterUsername

        textEditRegisterPassword.addTextChangedListener {
            updatePasswordStrengthView(it.toString())
        }


        btnRegister.setOnClickListener {

            if (textEditRegisterUsername.text.toString().trim().isEmpty()) {
                Snackbar.make(btnRegister, "fill in username", Snackbar.LENGTH_SHORT).show()

            } else if (!Patterns.EMAIL_ADDRESS.matcher(textEditRegisterEmail.text.toString().trim())
                    .matches()
            ) {
                Snackbar.make(btnRegister, "enter valid email", Snackbar.LENGTH_SHORT).show()
            } else if (textEditRegisterEmail.text.toString().trim().isEmpty()) {
                Snackbar.make(btnRegister, "fill in email", Snackbar.LENGTH_SHORT).show()
            } else if (textEditRegisterPassword.text.toString().trim().isEmpty()) {
                Snackbar.make(btnRegister, "fill in password", Snackbar.LENGTH_SHORT).show()
            } else {

                progressbarRegister.visibility = View.VISIBLE
                btnRegister.visibility = View.GONE

                val loginEmail = textEditRegisterEmail.text.toString().trim()
                val loginPassword = textEditRegisterPassword.text.toString().trim()

                insertUserToFirebaseDatabase(
                    loginEmail,
                    loginPassword
                )
                Toast.makeText(context, textEditRegisterEmail.text.toString(), Toast.LENGTH_SHORT).show()
                Toast.makeText(context, textEditRegisterPassword.text.toString(), Toast.LENGTH_SHORT).show()

            }
        }


        linearReg.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_welcomeFragment)
        }

        loginHaveAccount.setOnClickListener {
            findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
        }
    }

    //verifying the password strength entered by the user
    private fun updatePasswordStrengthView(password: String) {

        if (TextView.VISIBLE != tvPasswordStrength.visibility)
            return
        if (TextUtils.isEmpty(textEditRegisterPassword.text.toString())) {

            tvPasswordStrength.text = ""
            passwordProgressbar.progress = 0
            return
        }

        val pStrength =
            PasswordStrength.calculatorStrength(textEditRegisterPassword.text.toString().trim())
        tvPasswordStrength.text = context?.let { pStrength.getText(it) }

        passwordProgressbar.progressDrawable.colorFilter =
            BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                pStrength.color,
                BlendModeCompat.SRC_IN
            )

        if (context?.let { pStrength.getText(it) } == "weak") {
            passwordProgressbar.progress = 25
        } else if (context?.let { pStrength.getText(it) } == "medium") {
            passwordProgressbar.progress = 50
        } else if (context?.let { pStrength.getText(it) } == "strong") {
            passwordProgressbar.progress = 75
        } else {
            passwordProgressbar.progress = 100
        }

    }

    //registering the user to the firebase databases
    private fun insertUserToFirebaseDatabase(email: String, password: String) {
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Snackbar.make(
                        btnRegister,
                        "sign in successful, verify your email",
                        Snackbar.LENGTH_SHORT
                    )
                        .show()
                    progressbarRegister.visibility = View.GONE
                    btnRegister.visibility = View.VISIBLE

                    findNavController().navigate(R.id.action_registrationFragment_to_navigation_home)

                    //sendUserVerificationEmail()

                }
            }
            .addOnFailureListener {
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
                progressbarRegister.visibility = View.GONE
                btnRegister.visibility = View.VISIBLE
            }

    }

    private fun sendUserVerificationEmail() {
        val user = auth.currentUser

        user!!.sendEmailVerification()
            .addOnCompleteListener {
                if (it.isSuccessful) {
                    Toast.makeText(context, "email sent, verify your email", Toast.LENGTH_SHORT)
                        .show()
                    auth.signOut()
                    progressbarRegister.visibility = View.GONE
                    btnRegister.visibility = View.VISIBLE
                    findNavController().navigate(R.id.action_registrationFragment_to_loginFragment)
                } else {
                    progressbarRegister.visibility = View.GONE
                    btnRegister.visibility = View.VISIBLE

                }
            }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
