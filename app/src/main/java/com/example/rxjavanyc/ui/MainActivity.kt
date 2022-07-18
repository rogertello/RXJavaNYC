package com.example.rxjavanyc.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.rxjavanyc.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ListenerInterface {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showDisplayFragment()
    }

    private fun showDisplayFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, SchoolDisplay())
            .commit()
    }

    override fun openDetails(dbn: String?, name: String?, loc: String?, email: String?, phone: String?) {
        supportFragmentManager.beginTransaction()
            .replace(
                R.id.fragment_container,
                SchoolDetails.getInstance(dbn, name, loc, email, phone)
            )
            .addToBackStack(null)
            .commit()
    }
}