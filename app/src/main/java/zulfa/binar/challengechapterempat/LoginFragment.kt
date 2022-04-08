package zulfa.binar.challengechapterempat

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_register.*

class LoginFragment : Fragment() {

    lateinit var sp : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sp = requireContext().getSharedPreferences("REG", Context.MODE_PRIVATE)

        tv_register.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_registerFragment)
        }

        btn_login.setOnClickListener {

            val email = et_login_email.text.toString()
            val password = et_login_password.text.toString()
            val getemail = sp.getString("email", "")
            val getpassword = sp.getString("password", "")
            val getusername = sp.getString("username", "")
            sp.edit().putString("name", getusername).apply()

            if (email == getemail && password == getpassword){
                Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_homeFragment)
            } else {
                Toast.makeText(requireContext(), "Email / Password Salah", Toast.LENGTH_LONG).show()
            }
        }
    }
}