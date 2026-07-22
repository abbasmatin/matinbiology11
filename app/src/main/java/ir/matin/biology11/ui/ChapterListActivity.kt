package ir.matin.biology11.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import ir.matin.biology11.R
import ir.matin.biology11.data.ChapterRepository
import ir.matin.biology11.data.SectionType

class ChapterListActivity : BaseActivity() {

    companion object {
        const val EXTRA_SECTION_TYPE = "extra_section_type"
        const val EXTRA_CHAPTER_ID = "extra_chapter_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chapter_list)

        val typeName = intent.getStringExtra(EXTRA_SECTION_TYPE) ?: SectionType.TEXT.name
        val sectionType = SectionType.valueOf(typeName)

        val title = when (sectionType) {
            SectionType.TEXT -> getString(R.string.menu_text)
            SectionType.IMAGES -> getString(R.string.menu_images)
            SectionType.KEY_POINTS -> getString(R.string.menu_keypoints)
            SectionType.ESSAY -> getString(R.string.menu_essay)
            SectionType.MCQ -> getString(R.string.menu_mcq)
        }
        setupToolbar(title)

        val container = findViewById<LinearLayout>(R.id.chapterContainer)
        ChapterRepository.chapterList.forEach { chapter ->
            val button = Button(this)
            button.text = "فصل ${toPersianDigits(chapter.id)}: ${chapter.title}"
            button.setTextColor(getColor(R.color.white))
            button.background = androidx.core.content.ContextCompat.getDrawable(this, R.drawable.bg_button_rounded)
            button.setPadding(24, 24, 24, 24)
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.bottomMargin = 20
            button.layoutParams = params
            button.setOnClickListener { openChapter(sectionType, chapter.id) }
            container.addView(button)
        }
    }

    private fun openChapter(sectionType: SectionType, chapterId: Int) {
        val targetActivity = when (sectionType) {
            SectionType.TEXT -> TextContentActivity::class.java
            SectionType.IMAGES -> ImageDescriptionsActivity::class.java
            SectionType.KEY_POINTS -> KeyPointsActivity::class.java
            SectionType.ESSAY -> EssayQuestionsActivity::class.java
            SectionType.MCQ -> McqActivity::class.java
        }
        val intent = Intent(this, targetActivity)
        intent.putExtra(EXTRA_CHAPTER_ID, chapterId)
        startActivity(intent)
    }

    private fun toPersianDigits(number: Int): String {
        val persianDigits = charArrayOf('۰', '۱', '۲', '۳', '۴', '۵', '۶', '۷', '۸', '۹')
        return number.toString().map { c -> if (c.isDigit()) persianDigits[c - '0'] else c }.joinToString("")
    }
}
