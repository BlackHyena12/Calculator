package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

/**
 * Calculator with basic arithmetic operation capacity.
 *
 * @author Bridget Black
 * 2021-03-21
 * Last Updated: 2021-03-21
 */
class MainActivity : AppCompatActivity() {
    /**
     * Create saved state. TODO update description with added functionality
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}