package com.anukool.accessibilitytest

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);

        /* Check if enabled of not */
        if (Utils.isAccessibilityEnabled(this, "$packageName/.MyAccessibilityService")) {
            Log.e("MyAccessibilityService ", "Enabled ")
        } else {
            Log.e("MyAccessibilityService ", "Disabled ")
        }

        settingBtn.setOnClickListener {
            openAccessibilitySettings(this)
        }
    }

    private fun openAccessibilitySettings(context: Context) {
        try {
            val dialogIntent = Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)
            dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            dialogIntent.putExtra("EXTRA_SETTINGS_COMPONENT_NAME", getString(R.string.app_name))
            context.startActivity(dialogIntent)
        } catch (e: Exception) {
            // this shouldn't happen ideally
        }

    }
}
