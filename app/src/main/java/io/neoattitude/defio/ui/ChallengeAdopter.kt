package io.neoattitude.defio.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import io.neoattitude.defio.R
import io.neoattitude.defio.data.model.Challenge

class ChallengeAdopter :
    RecyclerView.Adapter<ChallengeAdopter.ChallengeViewHolder>() {

    inner class ChallengeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    private val differCallback = object : DiffUtil.ItemCallback<Challenge>() {
        override fun areItemsTheSame(oldItem: Challenge, newItem: Challenge): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Challenge, newItem: Challenge): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChallengeViewHolder {
        return ChallengeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_challenge,
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
        holder.itemView.apply {
            val tv : TextView = findViewById(R.id.tvChallengeTitle)
            tv.text = item.title
            setOnClickListener {
                onItemClickListener?.let { it(item) }
            }
        }
    }

    fun setOnItemClickListener(listener: (Challenge) -> Unit) {
        onItemClickListener = listener
    }
}

