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
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment() {

    lateinit var sp : SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sp = requireContext().getSharedPreferences("REG", Context.MODE_PRIVATE)

        btn_daftar.setOnClickListener {
            val username = et_register_username.text.toString()
            val email = et_register_email.text.toString()
            val password = et_register_password.text.toString()
            val password2 = et_register_password2.text.toString()

            val edit = sp.edit()
            edit.putString("username", username)
            edit.putString("email", email)
            edit.putString("password", password)
            edit.apply()

            if (password == password2){
                Toast.makeText(requireContext(), "Berhasil", Toast.LENGTH_LONG).show()
                Navigation.findNavController(view).navigate(R.id.action_registerFragment_to_loginFragment)
            } else {
                Toast.makeText(requireContext(), "Password Salah", Toast.LENGTH_LONG).show()
            }
        }
    }
}