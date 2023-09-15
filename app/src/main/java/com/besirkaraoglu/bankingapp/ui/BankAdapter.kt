package com.besirkaraoglu.bankingapp.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.besirkaraoglu.bankingapp.R
import com.besirkaraoglu.bankingapp.databinding.RecyclerItemBinding
import com.besirkaraoglu.bankingapp.model.RequestResult

class BankAdapter(
    private val itemClickListener: ItemClickListener): RecyclerView.Adapter<BankAdapter.BankViewHolder>() {

    private var list = mutableListOf<RequestResult>()
    fun setList(mList: MutableList<RequestResult>){
        list = mList
        notifyDataSetChanged()
    }


    inner class BankViewHolder(private val binding: RecyclerItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: RequestResult){
            with(binding){
                ivArrow.setOnClickListener {
                    itemClickListener.onClick(item)
                }
                iv.setImageDrawable(AppCompatResources
                    .getDrawable(binding.root.context,
                    R.drawable.bank))
                tvTitle.text = item.district
                tvDes.text = item.city
                tvSubText.text = item.nearestAtm
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BankViewHolder {
        return BankViewHolder(RecyclerItemBinding
            .inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: BankViewHolder, position: Int) {
        holder.bind(list[position])
    }


    interface ItemClickListener{
        fun onClick(item: RequestResult)
    }
}