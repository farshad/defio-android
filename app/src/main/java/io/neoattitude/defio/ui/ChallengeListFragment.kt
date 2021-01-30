package io.neoattitude.defio.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.neoattitude.defio.databinding.FragmentChallengeListBinding
import io.neoattitude.defio.ui.base.BaseFragment
import io.neoattitude.defio.util.Helper.snack
import io.neoattitude.defio.util.Resource
import io.neoattitude.defio.viewmodel.ChallengeViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ChallengeListFragment : BaseFragment<FragmentChallengeListBinding>() {
    private val viewModel: ChallengeViewModel by viewModel()
    private lateinit var challengeAdopter: ChallengeAdopter

    override fun setViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentChallengeListBinding =
        FragmentChallengeListBinding.inflate(inflater, container, false)

    override fun businessLogic() {
        setupRecyclerView()
        viewModel.getChallenges()
        setObserver()
    }

    override fun bindView() {
        challengeAdopter.setOnItemClickListener {
//            val bundle = Bundle().apply {
//                putSerializable("article", it)
//            }
//            findNavController().navigate(
//                R.id.action_breakingNewsFragment_to_articleFragment,
//                bundle
//            )
        }
    }

    private fun setObserver() {
        viewModel.challenges.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Success -> {
                    hideProgressBar()
                    it.data?.let { data ->
                        challengeAdopter.differ.submitList(data)
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

    private fun setupRecyclerView() {
        challengeAdopter = ChallengeAdopter()
        binding.rvChallenge.apply {
            adapter = challengeAdopter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}