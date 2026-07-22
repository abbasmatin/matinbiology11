package ir.matin.biology11.ui

import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import ir.matin.biology11.R
import java.io.File
import java.io.FileOutputStream

/**
 * نمایش تصویر صفحات کتاب از فایل PDF (در صورت وجود فایل واقعی در assets/chapters/)
 * با استفاده از android.graphics.pdf.PdfRenderer بومی اندروید (بدون کتابخانه خارجی).
 */
class PdfViewActivity : BaseActivity() {

    companion object {
        const val EXTRA_ASSET_PATH = "extra_asset_path"
    }

    private var renderer: PdfRenderer? = null
    private var fileDescriptor: ParcelFileDescriptor? = null
    private var currentPageIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf_view)
        setupToolbar("نمایش صفحات کتاب")

        val assetPath = intent.getStringExtra(EXTRA_ASSET_PATH)
        if (assetPath == null) {
            finish()
            return
        }

        try {
            val localFile = copyAssetToCache(assetPath)
            fileDescriptor = ParcelFileDescriptor.open(localFile, ParcelFileDescriptor.MODE_READ_ONLY)
            renderer = PdfRenderer(fileDescriptor!!)
            currentPageIndex = 0
            showPage(currentPageIndex)
        } catch (e: Exception) {
            Toast.makeText(this, "امکان نمایش این فایل PDF وجود ندارد.", Toast.LENGTH_LONG).show()
            finish()
            return
        }

        findViewById<Button>(R.id.btnPrevPage).setOnClickListener {
            if (currentPageIndex > 0) {
                currentPageIndex--
                showPage(currentPageIndex)
            }
        }
        findViewById<Button>(R.id.btnNextPage).setOnClickListener {
            val count = renderer?.pageCount ?: 0
            if (currentPageIndex < count - 1) {
                currentPageIndex++
                showPage(currentPageIndex)
            }
        }
    }

    private fun copyAssetToCache(assetPath: String): File {
        val outFile = File(cacheDir, "temp_chapter.pdf")
        assets.open(assetPath).use { input ->
            FileOutputStream(outFile).use { output ->
                input.copyTo(output)
            }
        }
        return outFile
    }

    private fun showPage(index: Int) {
        val pdfRenderer = renderer ?: return
        if (index < 0 || index >= pdfRenderer.pageCount) return

        val page = pdfRenderer.openPage(index)
        val bitmap = Bitmap.createBitmap(page.width * 2, page.height * 2, Bitmap.Config.ARGB_8888)
        page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
        page.close()

        findViewById<ImageView>(R.id.pdfPageImage).setImageBitmap(bitmap)
        findViewById<TextView>(R.id.pageIndicator).text =
            "صفحه ${index + 1} از ${pdfRenderer.pageCount}"
    }

    override fun onDestroy() {
        super.onDestroy()
        renderer?.close()
        fileDescriptor?.close()
    }
}
