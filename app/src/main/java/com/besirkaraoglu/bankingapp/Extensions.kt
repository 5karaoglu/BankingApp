package com.besirkaraoglu.bankingapp

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar

fun Fragment.showSnackbar(msg: String) = Snackbar
    .make(requireView(),msg,Snackbar.LENGTH_SHORT).show()

fun Fragment.navigateUp() = findNavController().navigateUp()

fun Fragment.navigateTo(destination: Int) = findNavController().navigate(destination)