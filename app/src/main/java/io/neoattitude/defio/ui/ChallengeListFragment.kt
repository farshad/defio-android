package io.neoattitude.defio.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import io.neoattitude.defio.databinding.FragmentChallengeListBinding
import io.neoattitude.defio.ui.base.BaseFragment

class ChallengeListFragment : BaseFragment<FragmentChallengeListBinding>() {
    override fun setViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentChallengeListBinding = FragmentChallengeListBinding.inflate(inflater, container, false)

    override fun businessLogic() {
    }

    override fun bindView() {
    }
}