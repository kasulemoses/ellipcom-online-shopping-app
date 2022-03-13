package com.ellipcom.ellipcom.ui.registrationAndLogin

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ellipcom.ellipcom.R
import com.ellipcom.ellipcom.databinding.LoginFragmentBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.button.MaterialButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginFragment : Fragment() {
    private lateinit var googleSignInClient: GoogleSignInClient

    private var _binding: LoginFragmentBinding? = null
    private val binding get() = _binding!!

    //views
    private lateinit var linearLogin: LinearLayout
    private lateinit var registerHaveNoAccout: TextView
    private lateinit var textEditloginEmail: TextInputEditText
    private lateinit var textEditloginPassword: TextInputEditText
    private lateinit var btnLogin: MaterialButton
    private lateinit var progressbarLogin: ProgressBar
    private lateinit var forgotPassword: TextView
    private lateinit var loginWithGoogleImg: ImageView

    private lateinit var viewModel: LoginViewModel

    //firebase
    private lateinit var auth: FirebaseAuth


    private val RC_SIGN_IN = 100


    var resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RC_SIGN_IN) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                try {
                    // Google Sign In was successful, authenticate with Firebase
                    val account = task.getResult(ApiException::class.java)!!
                    Toast.makeText(context, "account.email", Toast.LENGTH_SHORT).show()

                    firebaseAuthWithGoogle(account.idToken!!)

                } catch (e: ApiException) {
                    // Google Sign In failed, update UI appropriately
                    Toast.makeText(context, "check your email", Toast.LENGTH_SHORT).show()

                }

            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = LoginFragmentBinding.inflate(inflater, container, false)
        bindingViews()

        auth = FirebaseAuth.getInstance()
        createRequest()
        return binding.root
    }

    private fun bindingViews() {
        loginWithGoogleImg = binding.loginWithGoogleImage
        forgotPassword = binding.forgotPassword
        progressbarLogin = binding.progressbarLogin
        textEditloginEmail = binding.texteditLoginEmail
        textEditloginPassword = binding.texteditLoginPassword
        btnLogin = binding.btnLogin
        linearLogin = binding.linearLogin
        registerHaveNoAccout = binding.tvRegisterHaveNoAccount

        linearLogin.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
        }

        registerHaveNoAccout.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_registrationFragment)
        }

        btnLogin.setOnClickListener {
            if (textEditloginEmail.text.toString().trim().isEmpty()) {
                Snackbar.make(btnLogin, "fill  in email", Snackbar.LENGTH_SHORT).show()

            } else if (!Patterns.EMAIL_ADDRESS.matcher(textEditloginEmail.text.toString().trim())
                    .matches()
            ) {
                Snackbar.make(btnLogin, "fill  in valid email", Snackbar.LENGTH_SHORT).show()
            } else if (textEditloginPassword.text.toString().trim().isEmpty()) {
                Snackbar.make(btnLogin, "fill in password", Snackbar.LENGTH_SHORT).show()
            } else {
                progressbarLogin.visibility = View.VISIBLE
                btnLogin.visibility = View.GONE
                loggingInUserUsingFirebaseAuthentication(
                    textEditloginEmail.text.toString(), textEditloginPassword.text.toString()
                )
            }
        }

        //forgot password
        forgotPassword.setOnClickListener {
            if (textEditloginEmail.text.toString().trim().isEmpty()) {

                Toast.makeText(context, "enter your email", Toast.LENGTH_SHORT).show()
            } else {

                auth.sendPasswordResetEmail(textEditloginEmail.text.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(context, "reset password email sent", Toast.LENGTH_SHORT)
                                .show()
                        } else {
                            Toast.makeText(context, "enter correct email", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }
            }


        }

        loginWithGoogleImg.setOnClickListener {

            signIn()
        }
    }

    //function requesting google sign in
//    private fun createRequest() {
//        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//            .requestIdToken(getString(R.string.default_web_client_id))
//            .requestEmail()
//            .build()
//
//        googleSignInClient = GoogleSignIn.getClient(context, gso)
//        Toast.makeText(context, "createRequest", Toast.LENGTH_SHORT).show()
//
//    }

    private fun signIn() {

        val signInIntent = googleSignInClient.signInIntent
        resultLauncher.launch(signInIntent)

        Toast.makeText(context, "signIn", Toast.LENGTH_SHORT).show()

    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener {
                if (it.isSuccessful) {

                } else {

                }
            }
    }

    private fun loggingInUserUsingFirebaseAuthentication(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener {
                if (it.isSuccessful) {

                    checkIfUserEmailIsVerified()
                } else {
                    progressbarLogin.visibility = View.GONE
                    btnLogin.visibility = View.VISIBLE
                    Snackbar.make(btnLogin, "check connection and try again", Snackbar.LENGTH_SHORT)
                        .show()
                }
            }

    }

    private fun checkIfUserEmailIsVerified() {
        val user = auth.currentUser
        if (user!!.isEmailVerified) {

            progressbarLogin.visibility = View.GONE
            btnLogin.visibility = View.VISIBLE
            Snackbar.make(btnLogin, "login successful", Snackbar.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_loginFragment_to_navigation_home)

        } else {
            progressbarLogin.visibility = View.GONE
            btnLogin.visibility = View.VISIBLE
            Toast.makeText(context, "verify your email", Toast.LENGTH_SHORT).show()
            auth.signOut()
            findNavController().navigate(R.id.action_loginFragment_to_welcomeFragment)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}