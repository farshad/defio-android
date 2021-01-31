package io.neoattitude.defio.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.neoattitude.defio.data.model.Challenge
import io.neoattitude.defio.databinding.ItemChallengeBinding
import io.neoattitude.defio.util.Helper.countToString

class ChallengeAdopter :
    RecyclerView.Adapter<ChallengeAdopter.ChallengeViewHolder>() {
    private lateinit var context: Context

    inner class ChallengeViewHolder(val binding: ItemChallengeBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallback = object : DiffUtil.ItemCallback<Challenge>() {
        override fun areItemsTheSame(oldItem: Challenge, newItem: Challenge): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Challenge, newItem: Challenge): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChallengeViewHolder {
        context = parent.context
        return ChallengeViewHolder(
            ItemChallengeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((Challenge) -> Unit)? = null

    override fun onBindViewHolder(holder: ChallengeViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.binding.apply {
            tvChallengeTitle.text = item.title
            tvParticipant.text = countToString(item.participantCount)
            if (item.id == 54L || item.id == 58L) {
                ivDone.visibility = View.VISIBLE
            }

            item.icon.let {
                ivChallengeIcon.setImageResource(setDrawable(it))
            }
        }

        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(item) }
        }
    }

    private fun setDrawable(icon: String): Int {
        return context.resources.getIdentifier(
            icon,
            "drawable",
            context.packageName
        )
    }

    fun setOnItemClickListener(listener: (Challenge) -> Unit) {
        onItemClickListener = listener
    }
}

