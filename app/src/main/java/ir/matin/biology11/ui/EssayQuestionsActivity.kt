package ir.matin.biology11.ui

import android.graphics.Typeface
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import ir.matin.biology11.R
import ir.matin.biology11.data.ChapterRepository

class EssayQuestionsActivity : BaseActivity() {

    private val answerViews = mutableListOf<View>()
    private val answerButtons = mutableListOf<Button>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_essay)

        val chapterId = intent.getIntExtra(ChapterListActivity.EXTRA_CHAPTER_ID, 1)
        val content = ChapterRepository.getChapterContent(chapterId)
        setupToolbar("سوالات تشریحی: ${content.meta.title}")

        val container = findViewById<LinearLayout>(R.id.essayContainer)
        val scroll = findViewById<ScrollView>(R.id.essayScroll)

        content.essayQuestions.forEachIndexed { index, q ->
            val card = LinearLayout(this)
            card.orientation = LinearLayout.VERTICAL
            card.background = androidx.core.content.ContextCompat.getDrawable(this, R.drawable.bg_card)
            card.setPadding(20, 20, 20, 20)
            val cardParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
            )
            cardParams.bottomMargin = 16
            card.layoutParams = cardParams

            val questionView = TextView(this)
            questionView.text = "${toPersianDigits(index + 1)}. ${q.question}"
            questionView.textSize = 16f
            questionView.setTypeface(questionView.typeface, Typeface.BOLD)
            questionView.setTextColor(getColor(R.color.text_primary))
            questionView.gravity = Gravity.RIGHT
            questionView.setLineSpacing(4f, 1.1f)
            card.addView(questionView)

            val answerView = TextView(this)
            answerView.text = q.answer
            answerView.textSize = 14.5f
            answerView.setTextColor(getColor(R.color.text_secondary))
            answerView.gravity = Gravity.RIGHT
            answerView.setLineSpacing(4f, 1.15f)
            answerView.setPadding(0, 14, 0, 0)
            answerView.visibility = View.GONE
            card.addView(answerView)
            answerViews.add(answerView)

            val toggleButton = Button(this)
            toggleButton.text = getString(R.string.action_show_answer)
            toggleButton.setAllCaps(false)
            toggleButton.setTextColor(getColor(R.color.white))
            toggleButton.background = androidx.core.content.ContextCompat.getDrawable(this, R.drawable.bg_button_rounded)
            val btnParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT
            )
            btnParams.topMargin = 14
            toggleButton.layoutParams = btnParams
            toggleButton.setOnClickListener {
                if (answerView.visibility == View.GONE) {
                    answerView.visibility = View.VISIBLE
                    toggleButton.text = getString(R.string.action_hide_answer)
                } else {
                    answerView.visibility = View.GONE
                    toggleButton.text = getString(R.string.action_show_answer)
                }
            }
            answerButtons.add(toggleButton)
            card.addView(toggleButton)

            container.addView(card)
        }

        findViewById<Button>(R.id.btnRestart).setOnClickListener {
            answerViews.forEach { it.visibility = View.GONE }
            answerButtons.forEach { it.text = getString(R.string.action_show_answer) }
            scroll.smoothScrollTo(0, 0)
        }
    }

    private fun toPersianDigits(number: Int): String {
        val persianDigits = charArrayOf('۰', '۱', '۲', '۳', '۴', '۵', '۶', '۷', '۸', '۹')
        return number.toString().map { c -> if (c.isDigit()) persianDigits[c - '0'] else c }.joinToString("")
    }
}
