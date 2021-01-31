package io.neoattitude.defio.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.neoattitude.defio.data.model.Challenge
import io.neoattitude.defio.databinding.ItemChallengeBinding

class ChallengeAdopter :
    RecyclerView.Adapter<ChallengeAdopter.ChallengeViewHolder>() {

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
        holder.binding.tvChallengeTitle.text = item.title
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(item) }
        }
    }

    fun setOnItemClickListener(listener: (Challenge) -> Unit) {
        onItemClickListener = listener
    }
}

