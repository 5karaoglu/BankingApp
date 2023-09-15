package com.besirkaraoglu.bankingapp.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import androidx.core.text.bold
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.besirkaraoglu.bankingapp.MainViewModel
import com.besirkaraoglu.bankingapp.R
import com.besirkaraoglu.bankingapp.core.viewBinding
import com.besirkaraoglu.bankingapp.databinding.FragmentDetailBinding
import com.besirkaraoglu.bankingapp.navigateUp
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val binding by viewBinding(FragmentDetailBinding::bind)
    private val viewModel by activityViewModels<MainViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        initComponents()
        sendFirebaseLog()
    }

    private fun sendFirebaseLog() {
        val item = viewModel.currentItem.value
        val bundle = Bundle()
        with(item!!){
            bundle.putInt("ID", id!!)
            bundle.putString("sehir", city!!)
            bundle.putString("ilce", city!!)
            bundle.putString("sube", city!!)
            bundle.putString("tip", city!!)
            bundle.putString("banka_kodu", code)
            bundle.putString("adres_adı", addressName)
            bundle.putString("adres", address)
            bundle.putString("posta_kodu", zipCode!!)
            bundle.putString("on_off_line", onOffline!!)
            bundle.putString("on_off_site", onOffSite!!)
            bundle.putString("bolge_koordinatorlugu", regional!!)
            bundle.putString("en_yakın_atm", nearestAtm!!)
        }
        FirebaseAnalytics.getInstance(requireContext())
            .logEvent(FirebaseAnalytics.Event.SELECT_ITEM,bundle)
    }

    private fun initComponents() {
        binding.backButton.setOnClickListener {
            navigateUp()
        }

        binding.openInMapButton.setOnClickListener {
            if (viewModel.currentItem.isInitialized) {
                val gmmIntentUri =
                    Uri.parse("geo:0,0?q=${viewModel.currentItem.value?.address}")
                val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
                mapIntent.setPackage("com.google.android.apps.maps")
                startActivity(mapIntent)
            }
        }
    }

    private fun initData() {
        if (!viewModel.currentItem.isInitialized){
            navigateUp()
        }else{
            val item = viewModel.currentItem.value
            with(binding){
                tvHeader.text = item?.branch
                tvCity.text = SpannableStringBuilder().bold {
                    append("City:")
                }.append(" ").append(item?.city)
                tvDistrict.text = SpannableStringBuilder().bold {
                    append("District:")
                }.append(" ").append(item?.district)
                tvBranch.text = SpannableStringBuilder().bold {
                    append("Branch:")
                }.append(" ").append(item?.branch)
                tvType.text = SpannableStringBuilder().bold {
                    append("Type:")
                }.append(" ").append(item?.type)
                tvAddress.text = SpannableStringBuilder().bold {
                    append("Address:")
                }.append(" ").append(item?.address)
                tvNearestAtm.text = SpannableStringBuilder().bold {
                    append("Nearest Atm:")
                }.append(" ").append(item?.nearestAtm)
            }
        }

    }
}