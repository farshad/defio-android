package io.neoattitude.defio.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding


abstract class BaseFragment<T : ViewBinding> : Fragment() {

    private var layout: T? = null
    protected val binding get() = layout!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        layout = this.setViewBinding(inflater, container)
        return binding.root
    }

    abstract fun setViewBinding(inflater: LayoutInflater, container: ViewGroup?): T

    abstract fun businessLogic()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        businessLogic()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        layout = null
    }
}