package ir.matin.biology11.ui

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import ir.matin.biology11.R
import ir.matin.biology11.data.ChapterRepository
import ir.matin.biology11.data.McqQuestion

class McqActivity : BaseActivity() {

    private lateinit var questions: List<McqQuestion>
    private var currentIndex = 0
    private var score = 0
    private var answered = false

    private lateinit var progressText: TextView
    private lateinit var questionText: TextView
    private lateinit var optionsContainer: LinearLayout
    private lateinit var explanationText: TextView
    private lateinit var btnNext: Button
    private lateinit var quizContainer: LinearLayout
    private lateinit var resultContainer: LinearLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mcq)

        val chapterId = intent.getIntExtra(ChapterListActivity.EXTRA_CHAPTER_ID, 1)
        val content = ChapterRepository.getChapterContent(chapterId)
        questions = content.mcqQuestions
        setupToolbar("سوالات تستی: ${content.meta.title}")

        progressText = findViewById(R.id.progressText)
        questionText = findViewById(R.id.questionText)
        optionsContainer = findViewById(R.id.optionsContainer)
        explanationText = findViewById(R.id.explanationText)
        btnNext = findViewById(R.id.btnNext)
        quizContainer = findViewById(R.id.quizContainer)
        resultContainer = findViewById(R.id.resultContainer)

        btnNext.setOnClickListener {
            currentIndex++
            if (currentIndex < questions.size) {
                showQuestion()
            } else {
                showResult()
            }
        }

        findViewById<Button>(R.id.btnRestartMcq).setOnClickListener {
            restartQuiz()
        }

        showQuestion()
    }

    private fun restartQuiz() {
        currentIndex = 0
        score = 0
        quizContainer.visibility = View.VISIBLE
        resultContainer.visibility = View.GONE
        showQuestion()
    }

    private fun showQuestion() {
        answered = false
        val q = questions[currentIndex]
        progressText.text = "سوال ${toPersianDigits(currentIndex + 1)} از ${toPersianDigits(questions.size)}"
        questionText.text = q.question
        explanationText.visibility = View.GONE
        btnNext.visibility = View.GONE

        optionsContainer.removeAllViews()
        q.options.forEachIndexed { idx, optionText ->
            val optionButton = Button(this)
            optionButton.text = optionText
            optionButton.setAllCaps(false)
            optionButton.gravity = Gravity.RIGHT or Gravity.CENTER_VERTICAL
            optionButton.setTextColor(getColor(R.color.text_primary))
            optionButton.background = androidx.core.content.ContextCompat.getDrawable(this, R.drawable.bg_option_default)
            optionButton.setPadding(24, 24, 24, 24)
            val params = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT
            )
            params.bottomMargin = 12
            optionButton.layoutParams = params
            optionButton.setOnClickListener {
                onOptionSelected(idx)
            }
            optionsContainer.addView(optionButton)
        }
    }

    private fun onOptionSelected(selectedIndex: Int) {
        if (answered) return
        answered = true
        val q = questions[currentIndex]
        if (selectedIndex == q.correctIndex) score++

        for (i in 0 until optionsContainer.childCount) {
            val btn = optionsContainer.getChildAt(i) as Button
            btn.isEnabled = false
            when {
                i == q.correctIndex -> btn.background = androidx.core.content.ContextCompat.getDrawable(this, R.drawable.bg_option_correct)
                i == selectedIndex -> btn.background = androidx.core.content.ContextCompat.getDrawable(this, R.drawable.bg_option_wrong)
            }
        }

        explanationText.text = q.explanation
        explanationText.visibility = View.VISIBLE
        btnNext.visibility = View.VISIBLE
        btnNext.text = if (currentIndex == questions.size - 1)
            "مشاهده نتیجه"
        else
            getString(R.string.action_next)
    }

    private fun showResult() {
        quizContainer.visibility = View.GONE
        resultContainer.visibility = View.VISIBLE
        val percent = if (questions.isNotEmpty()) (score * 100) / questions.size else 0
        val resultText = findViewById<TextView>(R.id.resultText)
        resultText.text = getString(R.string.quiz_score_format, score, questions.size, percent)
    }

    private fun toPersianDigits(number: Int): String {
        val persianDigits = charArrayOf('۰', '۱', '۲', '۳', '۴', '۵', '۶', '۷', '۸', '۹')
        return number.toString().map { c -> if (c.isDigit()) persianDigits[c - '0'] else c }.joinToString("")
    }
}
