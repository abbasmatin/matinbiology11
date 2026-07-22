package ir.matin.biology11.ui

import android.os.Bundle
import android.widget.TextView
import ir.matin.biology11.R

class AboutActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
        setupToolbar(getString(R.string.menu_about))

        findViewById<TextView>(R.id.aboutBody).text = """
            درباره این برنامه:

            این اپلیکیشن برای مرور و یادگیری کامل کتاب زیست‌شناسی (۲) پایه یازدهم رشته تجربی طراحی شده است و شامل بخش‌های زیر می‌باشد:

            • متن کامل و ساختارمند تمام ۹ فصل کتاب
            • شرح تصاویر و شکل‌های کتاب به تفکیک فصل
            • نکات کلیدی و کنکوری هر فصل
            • سوالات تشریحی در سبک امتحان نهایی همراه با پاسخنامه کامل
            • سوالات تستی چهارگزینه‌ای با تحلیل کامل هر گزینه و نمره‌دهی نهایی

            نسخه: ۱.۰
            وضعیت محتوا: تمام ۹ فصل کامل است.
        """.trimIndent()
    }
}
