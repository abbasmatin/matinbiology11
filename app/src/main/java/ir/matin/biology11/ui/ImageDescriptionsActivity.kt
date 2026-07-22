package ir.matin.biology11.ui

import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import ir.matin.biology11.R
import ir.matin.biology11.data.ChapterRepository

class ImageDescriptionsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_descriptions)

        val chapterId = intent.getIntExtra(ChapterListActivity.EXTRA_CHAPTER_ID, 1)
        val content = ChapterRepository.getChapterContent(chapterId)
        setupToolbar("شرح تصاویر: ${content.meta.title}")

        val container = findViewById<LinearLayout>(R.id.imageContainer)
        content.images.forEach { img ->
            val card = LinearLayout(this)
            card.orientation = LinearLayout.VERTICAL
            card.background = androidx.core.content.ContextCompat.getDrawable(this, R.drawable.bg_card)
            card.setPadding(20, 20, 20, 20)
            val cardParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
            )
            cardParams.bottomMargin = 16
            card.layoutParams = cardParams

            val label = TextView(this)
            label.text = "${img.figureLabel} — ${img.title}"
            label.setTypeface(label.typeface, Typeface.BOLD)
            label.textSize = 16.5f
            label.setTextColor(getColor(R.color.teal_primary_dark))
            label.gravity = Gravity.RIGHT
            card.addView(label)

            val desc = TextView(this)
            desc.text = img.description
            desc.textSize = 14.5f
            desc.setTextColor(getColor(R.color.text_primary))
            desc.setPadding(0, 10, 0, 0)
            desc.setLineSpacing(5f, 1.1f)
            desc.gravity = Gravity.RIGHT
            card.addView(desc)

            container.addView(card)
        }
    }
}
