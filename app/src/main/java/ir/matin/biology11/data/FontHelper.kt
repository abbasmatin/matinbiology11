package ir.matin.biology11.data

import android.content.Context
import android.graphics.Typeface
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

/**
 * بارگذاری فونت وزیر از assets/fonts/Vazir.ttf
 * در صورت نبودن فایل فونت (مثلاً در محیط توسعه که فایل واقعی هنوز اضافه نشده)،
 * برنامه بدون خطا با فونت پیش‌فرض سیستم اجرا می‌شود.
 */
object FontHelper {

    private var cachedTypeface: Typeface? = null
    private var attemptedLoad = false

    fun getVazirTypeface(context: Context): Typeface? {
        if (!attemptedLoad) {
            attemptedLoad = true
            cachedTypeface = try {
                Typeface.createFromAsset(context.assets, "fonts/Vazir.ttf")
            } catch (e: Exception) {
                null
            }
        }
        return cachedTypeface
    }

    /** اعمال بازگشتی فونت روی تمام TextView و Button های یک درخت View */
    fun applyRecursively(view: View, typeface: Typeface) {
        when (view) {
            is TextView -> view.typeface = typeface
            is ViewGroup -> {
                for (i in 0 until view.childCount) {
                    applyRecursively(view.getChildAt(i), typeface)
                }
            }
        }
    }
}
