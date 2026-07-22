package ir.matin.biology11.ui

import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import ir.matin.biology11.R
import ir.matin.biology11.data.ChapterRepository

class KeyPointsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_key_points)

        val chapterId = intent.getIntExtra(ChapterListActivity.EXTRA_CHAPTER_ID, 1)
        val content = ChapterRepository.getChapterContent(chapterId)
        setupToolbar("نکات کلیدی: ${content.meta.title}")

        val container = findViewById<LinearLayout>(R.id.pointsContainer)
        content.keyPoints.forEachIndexed { index, point ->
            val row = TextView(this)
            row.text = "•  $point"
            row.textSize = 15.5f
            row.setTextColor(getColor(R.color.text_primary))
            row.gravity = Gravity.RIGHT
            row.setLineSpacing(5f, 1.15f)
            row.background = androidx.core.content.ContextCompat.getDrawable(this, R.drawable.bg_card)
            row.setPadding(18, 16, 18, 16)
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.bottomMargin = 12
            row.layoutParams = params
            container.addView(row)
        }
    }
}
