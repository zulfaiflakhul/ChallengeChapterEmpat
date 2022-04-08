package zulfa.binar.challengechapterempat

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

class SplashScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val v = inflater.inflate(R.layout.fragment_splash_screen, container, false)

        Handler().postDelayed({
            Navigation.findNavController(v).navigate(R.id.action_splashScreenFragment_to_loginFragment)
        }, 3000)

        return v
    }
}