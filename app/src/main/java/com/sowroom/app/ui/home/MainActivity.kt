package com.sowroom.app.ui.home

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.sowroom.app.R
import com.sowroom.app.databinding.ActivityMainBinding
import com.sowroom.app.ui.auth.AuthActivity
import com.sowroom.app.ui.auth.sowroomAuth

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        binding.buttonLogout.setOnClickListener {
            sowroomAuth.logout(this){
                startActivity(Intent(this, AuthActivity::class.java))
                finish()
            }
        }
    }
}