package ir.matin.biology11.ui

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.widget.ImageButton
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import ir.matin.biology11.R
import ir.matin.biology11.data.FontHelper
import ir.matin.biology11.data.PrefsHelper

/**
 * فعالیت پایه: منطق مشترک تمام صفحات شامل
 * بازگشت به منوی اصلی، خروج با تأیید، تغییر حالت تاریک/روشن و اندازه فونت.
 */
abstract class BaseActivity : AppCompatActivity() {

    override fun attachBaseContext(newBase: Context) {
        val scale = PrefsHelper.fontScale(newBase)
        val config = Configuration(newBase.resources.configuration)
        config.fontScale = scale
        val newContext = newBase.createConfigurationContext(config)
        super.attachBaseContext(newContext)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(
            if (PrefsHelper.isDarkMode(this)) AppCompatDelegate.MODE_NIGHT_YES
            else AppCompatDelegate.MODE_NIGHT_NO
        )
        super.onCreate(savedInstanceState)
    }

    /** فرزندان باید این متد را بعد از setContentView صدا بزنند */
    protected fun setupToolbar(title: String) {
        applyVazirFont()
        val toolbar = findViewById<Toolbar>(R.id.toolbar) ?: return
        toolbar.findViewById<TextView>(R.id.toolbarTitle)?.text = title

        toolbar.findViewById<ImageButton>(R.id.btnHome)?.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
            finish()
        }

        toolbar.findViewById<ImageButton>(R.id.btnExit)?.setOnClickListener {
            showExitDialog()
        }

        toolbar.findViewById<ImageButton>(R.id.btnDarkMode)?.setOnClickListener {
            val newValue = !PrefsHelper.isDarkMode(this)
            PrefsHelper.setDarkMode(this, newValue)
            recreate()
        }

        toolbar.findViewById<ImageButton>(R.id.btnFontSize)?.setOnClickListener { anchor ->
            showFontSizeMenu(anchor)
        }
    }

    override fun onResume() {
        super.onResume()
        applyVazirFont()
    }

    private fun applyVazirFont() {
        val typeface = FontHelper.getVazirTypeface(this) ?: return
        val root = findViewById<android.view.View>(android.R.id.content)
        FontHelper.applyRecursively(root, typeface)
    }

    private fun showExitDialog() {
        AlertDialog.Builder(this)
            .setTitle(R.string.exit_confirm_title)
            .setMessage(R.string.exit_confirm_message)
            .setPositiveButton(R.string.yes) { _, _ ->
                finishAffinity()
            }
            .setNegativeButton(R.string.no, null)
            .show()
    }

    private fun showFontSizeMenu(anchor: android.view.View) {
        val popup = PopupMenu(this, anchor)
        popup.menu.add(0, 0, 0, R.string.font_small)
        popup.menu.add(0, 1, 1, R.string.font_medium)
        popup.menu.add(0, 2, 2, R.string.font_large)
        popup.setOnMenuItemClickListener { item ->
            PrefsHelper.setFontSizeIndex(this, item.itemId)
            recreate()
            true
        }
        popup.show()
    }
}
