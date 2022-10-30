package az.khayalsharifli.rickandmorty.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import az.khayalsharifli.rickandmorty.databinding.ItemCharacterBinding
import az.khayalsharifli.rickandmorty.model.CharacterResult
import az.khayalsharifli.rickandmorty.tools.ClickListener
import coil.load

class CharacterAdapter(private val clickListener: ClickListener) : PagingDataAdapter<CharacterResult,
        CharacterAdapter.CharacterViewHolder>(diffUtilCallback) {

    class CharacterViewHolder(
        val binding: ItemCharacterBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(
            ItemCharacterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val currChar = getItem(position)
        holder.binding.tvName.text = "${currChar?.name}"
        holder.binding.tvGender.text = "Gender: ${currChar?.gender}"
        holder.binding.tvStatus.text = "Status: ${currChar?.status}"
        holder.binding.tvType.text = "Type: ${currChar?.type}"

        holder.binding.imageView.load(currChar?.image) {
            crossfade(true)
            crossfade(1000)
        }
        holder.binding.root.setOnClickListener { clickListener.onClick(position, it) }
    }

    companion object {
        val diffUtilCallback = object : DiffUtil.ItemCallback<CharacterResult>() {
            override fun areItemsTheSame(oldItem: CharacterResult, newItem: CharacterResult): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: CharacterResult, newItem: CharacterResult): Boolean {
                return oldItem == newItem
            }
        }
    }

}