package io.neoattitude.defio.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import io.neoattitude.defio.R
import io.neoattitude.defio.databinding.FragmentLoginBinding
import io.neoattitude.defio.ui.base.BaseFragment
import io.neoattitude.defio.util.Helper.snack
import io.neoattitude.defio.util.Resource
import io.neoattitude.defio.viewmodel.AuthViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class LoginFragment : BaseFragment<FragmentLoginBinding>() {
    private val authViewModel: AuthViewModel by viewModel()
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun setViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding = FragmentLoginBinding.inflate(inflater, container, false)

    override fun bindView() {
        super.progressLoading = binding.progressLoading
    }

    override fun businessLogic() {
        setObserver()

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.google_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso);

        binding.signInButton.setOnClickListener { signIn(); }
    }

    private fun signIn() {
        val signInIntent: Intent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, 2)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 2) {
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            account?.idToken.let {
                authViewModel.signIn(it)
            }
        } catch (e: ApiException) {
        }
    }

    private fun setObserver() {
        authViewModel.token.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Success -> {
                    hideProgressBar()
                    it.data?.let { data ->
                        Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)
                            .navigate(R.id.action_loginFragment_to_homeFragment)
                        authViewModel.deleteAll()
                        authViewModel.insertToken(data)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    it.message?.let { message ->
                        view?.snack(message)
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }
}