package ir.matin.biology11.data

import android.content.Context
import android.content.SharedPreferences

/**
 * ذخیره و بازیابی تنظیمات کاربر: حالت تاریک/روشن و اندازه فونت.
 */
object PrefsHelper {
    private const val PREFS_NAME = "biology11_prefs"
    private const val KEY_DARK_MODE = "dark_mode"
    private const val KEY_FONT_SIZE = "font_size" // 0=small, 1=medium, 2=large

    private fun prefs(context: Context): SharedPreferences =
        context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun isDarkMode(context: Context): Boolean =
        prefs(context).getBoolean(KEY_DARK_MODE, false)

    fun setDarkMode(context: Context, enabled: Boolean) {
        prefs(context).edit().putBoolean(KEY_DARK_MODE, enabled).apply()
    }

    fun getFontSizeIndex(context: Context): Int =
        prefs(context).getInt(KEY_FONT_SIZE, 1)

    fun setFontSizeIndex(context: Context, index: Int) {
        prefs(context).edit().putInt(KEY_FONT_SIZE, index).apply()
    }

    /** ضریب مقیاس متن بر اساس اندازه انتخابی */
    fun fontScale(context: Context): Float = when (getFontSizeIndex(context)) {
        0 -> 0.85f
        2 -> 1.25f
        else -> 1.0f
    }
}
