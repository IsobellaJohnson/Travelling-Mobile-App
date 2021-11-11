package op.mobile.app.dev.johnil1.travelling.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.google.firebase.auth.FirebaseAuth
import op.mobile.app.dev.johnil1.travelling.R

class LoginFragment : Fragment() {
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, container, false)

        auth = FirebaseAuth.getInstance()

        val btnLogin: Button = view.findViewById(R.id.btn_login)
        val etEmailAddress: EditText = view.findViewById(R.id.et_email_address)
        val etPassword: EditText = view.findViewById(R.id.et_password)
        val tvSignUp: TextView = view.findViewById(R.id.tv_sign_up)

        btnLogin.setOnClickListener {
            val email = etEmailAddress.text.toString()
            val password = etPassword.text.toString()

            // Set of validation rules
            when {
                email.isEmpty() ->
                    etEmailAddress.error = "Email is required."
                password.isEmpty() ->
                    etPassword.error = "Password is required"
                else -> {
                    firebaseAuthSignInWithEmailAndPassword(email, password) // Call if validation rules pass
                }
            }
        }

        // You can set on click listeners for other Views
        tvSignUp.setOnClickListener {
            // Navigate to the register screen. Check mobile_navigation.xml for this action
            val action =
                LoginFragmentDirections
                    .actionLoginFragmentToRegisterFragment()
            view?.findNavController()?.navigate(action)
        }

        return view
    }

    /**
     * This function signs in a user using Firebase authentication
     *
     * @param email the user's email address
     * @param password the user's password
     */
    private fun firebaseAuthSignInWithEmailAndPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password) // In-built Firebase authentication function
            .addOnCompleteListener(requireActivity()) {
                if (it.isSuccessful) {
                    // Navigate to the home screen. Check mobile_navigation.xml for this action
                    val action =
                        LoginFragmentDirections
                            .actionLoginFragmentToHomeFragment()
                    view?.findNavController()?.navigate(action)
                } else {
                    // Indicate to the application user that their credentials may be incorrect
                    Toast.makeText(
                        activity,
                        "Failed to sign in.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }
}
