package com.arisurya.jetpackpro.canangbali.ui.information.upakara

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.arisurya.jetpackpro.canangbali.R
import com.arisurya.jetpackpro.canangbali.databinding.ActivityDetailUpakaraBinding
import com.arisurya.jetpackpro.canangbali.databinding.ActivityUpakaraBinding
import com.arisurya.jetpackpro.canangbali.ui.information.upakara.detail.DetailUpakaraViewModel
import com.arisurya.jetpackpro.canangbali.viewmodel.ViewModelFactory

class UpakaraActivity : AppCompatActivity() {

    private lateinit var upakaraBinding: ActivityUpakaraBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        upakaraBinding = ActivityUpakaraBinding.inflate(layoutInflater)
        setContentView(upakaraBinding.root)
        val factory = ViewModelFactory.getInstance(this)
        val viewModel = ViewModelProvider(this, factory)[UpakaraViewModel::class.java]

        val adapter = UpakaraAdapter()
        showProgressBar(true)
        viewModel.getUpakara().observe(this,{upakara->
            if (upakara!=null){
                showProgressBar(false)
                adapter.setUpakara(upakara)
                adapter.notifyDataSetChanged()
            }
        })

        upakaraBinding.apply {
            rvUpakara.layoutManager = LinearLayoutManager(this@UpakaraActivity)
            rvUpakara.setHasFixedSize(true)
            rvUpakara.adapter = adapter
        }
    }

    private fun showProgressBar(state : Boolean){
        if(state){
            upakaraBinding.progressBar.visibility = View.VISIBLE
        }else{
            upakaraBinding.progressBar.visibility = View.GONE
        }
    }
}