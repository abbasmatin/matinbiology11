package ir.matin.biology11.ui

import android.content.Intent
import android.os.Bundle
import ir.matin.biology11.R
import ir.matin.biology11.data.SectionType

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolbar(getString(R.string.app_name))

        findViewById<android.widget.Button>(R.id.btnText).setOnClickListener {
            openChapterList(SectionType.TEXT)
        }
        findViewById<android.widget.Button>(R.id.btnImages).setOnClickListener {
            openChapterList(SectionType.IMAGES)
        }
        findViewById<android.widget.Button>(R.id.btnKeyPoints).setOnClickListener {
            openChapterList(SectionType.KEY_POINTS)
        }
        findViewById<android.widget.Button>(R.id.btnEssay).setOnClickListener {
            openChapterList(SectionType.ESSAY)
        }
        findViewById<android.widget.Button>(R.id.btnMcq).setOnClickListener {
            openChapterList(SectionType.MCQ)
        }
        findViewById<android.widget.Button>(R.id.btnAbout).setOnClickListener {
            startActivity(Intent(this, AboutActivity::class.java))
        }
    }

    private fun openChapterList(type: SectionType) {
        val intent = Intent(this, ChapterListActivity::class.java)
        intent.putExtra(ChapterListActivity.EXTRA_SECTION_TYPE, type.name)
        startActivity(intent)
    }
}
