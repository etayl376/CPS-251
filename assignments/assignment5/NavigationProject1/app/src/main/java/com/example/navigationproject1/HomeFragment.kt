package com.example.navigationproject1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        view.findViewById<Button>(R.id.button_one).setOnClickListener {
            it.findNavController().navigate(R.id.fragmentOne)
        }
        view.findViewById<Button>(R.id.button_two).setOnClickListener {
            it.findNavController().navigate(R.id.fragmentTwo)
        }
        view.findViewById<Button>(R.id.button_three).setOnClickListener {
            it.findNavController().navigate(R.id.fragmentThree)
        }

        return view
    }
}
