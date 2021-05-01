package com.example.chesstimer

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.databinding.DataBindingUtil
import com.example.chesstimer.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
            this,
            R.layout.activity_main
        )
        setSupportActionBar(binding.toolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)

        val nightMode: Int = AppCompatDelegate.getDefaultNightMode()
        if (nightMode != AppCompatDelegate.MODE_NIGHT_YES) {
            menu?.findItem(R.id.day_night_mode)?.setIcon(R.drawable.ic_night_mode)
        } else {
            menu?.findItem(R.id.day_night_mode)?.setIcon(R.drawable.ic_day_mode)
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val nightMode = AppCompatDelegate.getDefaultNightMode()
        when (item.itemId) {
            R.id.day_night_mode ->
                when (nightMode) {
                    AppCompatDelegate.MODE_NIGHT_YES -> AppCompatDelegate.setDefaultNightMode(
                        AppCompatDelegate.MODE_NIGHT_NO
                    )
                    else -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                }
        }
        return super.onOptionsItemSelected(item)
    }

}