package op.mobile.app.dev.johnil1.travelling.ui.splashscreen

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import op.mobile.app.dev.johnil1.travelling.R

class SplashscreenFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        Handler(Looper.getMainLooper()).postDelayed({
            val action = SplashscreenFragmentDirections
                .actionSplashScreenFragmentToLoginFragment()
            view?.findNavController()?.navigate(action) // Calling the navigation action declared in mobile_navigation.xml
        }, 3000)
        
        return inflater.inflate(R.layout.fragment_splashscreen, container, false)
    }
}
