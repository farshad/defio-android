package io.neoattitude.defio.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import io.neoattitude.defio.databinding.FragmentChallengeBinding
import io.neoattitude.defio.ui.base.BaseFragment

class ChallengeFragment : BaseFragment<FragmentChallengeBinding>() {
    override fun setViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentChallengeBinding = FragmentChallengeBinding.inflate(inflater, container, false)

    override fun businessLogic() {

    }

    override fun bindView() {
    }

}