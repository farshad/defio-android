package io.neoattitude.defio.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import io.neoattitude.defio.R
import io.neoattitude.defio.databinding.FragmentSplashBinding
import io.neoattitude.defio.ui.base.BaseFragment

class SplashFragment : BaseFragment<FragmentSplashBinding>() {

    override fun setViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSplashBinding = FragmentSplashBinding.inflate(inflater, container, false)

    override fun businessLogic() {
        binding.navTo.setOnClickListener {
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                .navigate(R.id.action_splashFragment_to_loginFragment)
        }
    }

}