package com.wang.viewmodel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider

class Fragment2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment2, container, false)
        val seekBar = root.findViewById<SeekBar>(R.id.seekBar)
        val viewModel = ViewModelProvider(
            activity as MainActivity, ViewModelProvider.AndroidViewModelFactory(
                (activity as MainActivity).application
            )
        ).get(MyViewModel::class.java)
        viewModel.getProgress().observe(activity as MainActivity, object : Observer<Int> {
            override fun onChanged(t: Int?) {
                seekBar.progress = t!!
            }
        })

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
                viewModel.getProgress().value = p1
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {
            }

            override fun onStopTrackingTouch(p0: SeekBar?) {
            }

        })

        return root
    }
}