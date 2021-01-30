package io.neoattitude.defio.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import io.neoattitude.defio.databinding.FragmentChallengeListBinding
import io.neoattitude.defio.ui.base.BaseFragment

class ChallengeListFragment : BaseFragment<FragmentChallengeListBinding>() {
    lateinit var challengeAdopter: ChallengeAdopter

    override fun setViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentChallengeListBinding =
        FragmentChallengeListBinding.inflate(inflater, container, false)

    override fun businessLogic() {
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
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        challengeAdopter = ChallengeAdopter()
        binding.rvChallenge.apply {
            adapter = challengeAdopter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}