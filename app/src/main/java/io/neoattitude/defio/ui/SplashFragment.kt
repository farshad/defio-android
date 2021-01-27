package io.neoattitude.defio.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import io.neoattitude.defio.R
import io.neoattitude.defio.databinding.FragmentSplashBinding
import io.neoattitude.defio.ui.base.BaseFragment
import io.neoattitude.defio.viewmodel.AuthViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class SplashFragment : BaseFragment<FragmentSplashBinding>() {
    private val authViewModel: AuthViewModel by viewModel()

    override fun setViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSplashBinding = FragmentSplashBinding.inflate(inflater, container, false)

    override fun businessLogic() {
        authViewModel.checkTokenExist()
        authViewModel.isTokenExist.observe(viewLifecycleOwner, {
            when (it) {
                true -> {
                    Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                        .navigate(R.id.action_splashFragment_to_homeFragment)
                }
                false -> {
                    Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                        .navigate(R.id.action_splashFragment_to_loginFragment)
                }
            }
        })
    }

    override fun bindView() {

    }

}