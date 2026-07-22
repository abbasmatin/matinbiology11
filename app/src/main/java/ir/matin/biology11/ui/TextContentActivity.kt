package ir.matin.biology11.ui

import android.content.Intent
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import ir.matin.biology11.R
import ir.matin.biology11.data.ChapterRepository

class TextContentActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_content)

        val chapterId = intent.getIntExtra(ChapterListActivity.EXTRA_CHAPTER_ID, 1)
        val content = ChapterRepository.getChapterContent(chapterId)
        setupToolbar("متن فصل: ${content.meta.title}")

        findViewById<android.widget.Button>(R.id.btnOpenPdf).setOnClickListener {
            val assetName = "chapters/chapter_$chapterId.pdf"
            if (assetExists(assetName)) {
                val i = Intent(this, PdfViewActivity::class.java)
                i.putExtra(PdfViewActivity.EXTRA_ASSET_PATH, assetName)
                startActivity(i)
            } else {
                Toast.makeText(
                    this,
                    "فایل PDF این فصل موجود نیست؛ محتوای متنی کامل در پایین صفحه نمایش داده می‌شود.",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

        val container = findViewById<LinearLayout>(R.id.sectionContainer)
        content.textSections.forEach { section ->
            val headingView = TextView(this)
            headingView.text = section.heading
            headingView.textSize = 18f
            headingView.setTypeface(headingView.typeface, android.graphics.Typeface.BOLD)
            headingView.setTextColor(getColor(R.color.teal_primary_dark))
            headingView.setPadding(0, 24, 0, 8)
            headingView.gravity = Gravity.RIGHT
            container.addView(headingView)

            val bodyView = TextView(this)
            bodyView.text = section.body
            bodyView.textSize = 15.5f
            bodyView.setTextColor(getColor(R.color.text_primary))
            bodyView.setLineSpacing(6f, 1.15f)
            bodyView.gravity = Gravity.RIGHT
            bodyView.background = androidx.core.content.ContextCompat.getDrawable(this, R.drawable.bg_card)
            bodyView.setPadding(20, 20, 20, 20)
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.bottomMargin = 16
            bodyView.layoutParams = params
            container.addView(bodyView)
        }
    }

    private fun assetExists(path: String): Boolean {
        return try {
            assets.open(path).close()
            true
        } catch (e: Exception) {
            false
        }
    }
}
