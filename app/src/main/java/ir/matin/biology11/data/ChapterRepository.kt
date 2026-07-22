package ir.matin.biology11.data

/**
 * فهرست ۹ فصل کتاب زیست‌شناسی (۲) پایه یازدهم و دسترسی به محتوای هر فصل.
 * تمام ۹ فصل (متن، شرح تصاویر، نکات کلیدی، سوالات تشریحی و تستی) کامل پیاده‌سازی شده‌اند.
 */
object ChapterRepository {

    val chapterList = listOf(
        ChapterMeta(1, "تنظیم عصبی"),
        ChapterMeta(2, "حواس"),
        ChapterMeta(3, "دستگاه حرکتی"),
        ChapterMeta(4, "تنظیم شیمیایی"),
        ChapterMeta(5, "ایمنی بدن"),
        ChapterMeta(6, "تقسیم یاخته"),
        ChapterMeta(7, "تولیدمثل"),
        ChapterMeta(8, "تولیدمثل نهاندانگان"),
        ChapterMeta(9, "پاسخ گیاهان به محرک‌ها")
    )

    private val placeholderNotice = "محتوای کامل این فصل در نسخه بعدی برنامه اضافه خواهد شد. در حال حاضر فصل ۱ (تنظیم عصبی) به‌طور کامل در دسترس است."

    fun getChapterContent(chapterId: Int): ChapterContent {
        val meta = chapterList.first { it.id == chapterId }
        return when (chapterId) {
            1 -> ChapterContent(
                meta = Chapter1Content.meta,
                textSections = Chapter1Content.textSections,
                images = Chapter1Content.images,
                keyPoints = Chapter1Content.keyPoints,
                essayQuestions = Chapter1Essay.questions,
                mcqQuestions = Chapter1Mcq.questions
            )
            2 -> ChapterContent(
                meta = Chapter2Content.meta,
                textSections = Chapter2Content.textSections,
                images = Chapter2Content.images,
                keyPoints = Chapter2Content.keyPoints,
                essayQuestions = Chapter2Essay.questions,
                mcqQuestions = Chapter2Mcq.questions
            )
            3 -> ChapterContent(
                meta = Chapter3Content.meta,
                textSections = Chapter3Content.textSections,
                images = Chapter3Content.images,
                keyPoints = Chapter3Content.keyPoints,
                essayQuestions = Chapter3Essay.questions,
                mcqQuestions = Chapter3Mcq.questions
            )
            4 -> ChapterContent(
                meta = Chapter4Content.meta,
                textSections = Chapter4Content.textSections,
                images = Chapter4Content.images,
                keyPoints = Chapter4Content.keyPoints,
                essayQuestions = Chapter4Essay.questions,
                mcqQuestions = Chapter4Mcq.questions
            )
            5 -> ChapterContent(
                meta = Chapter5Content.meta,
                textSections = Chapter5Content.textSections,
                images = Chapter5Content.images,
                keyPoints = Chapter5Content.keyPoints,
                essayQuestions = Chapter5Essay.questions,
                mcqQuestions = Chapter5Mcq.questions
            )
            6 -> ChapterContent(
                meta = Chapter6Content.meta,
                textSections = Chapter6Content.textSections,
                images = Chapter6Content.images,
                keyPoints = Chapter6Content.keyPoints,
                essayQuestions = Chapter6Essay.questions,
                mcqQuestions = Chapter6Mcq.questions
            )
            7 -> ChapterContent(
                meta = Chapter7Content.meta,
                textSections = Chapter7Content.textSections,
                images = Chapter7Content.images,
                keyPoints = Chapter7Content.keyPoints,
                essayQuestions = Chapter7Essay.questions,
                mcqQuestions = Chapter7Mcq.questions
            )
            8 -> ChapterContent(
                meta = Chapter8Content.meta,
                textSections = Chapter8Content.textSections,
                images = Chapter8Content.images,
                keyPoints = Chapter8Content.keyPoints,
                essayQuestions = Chapter8Essay.questions,
                mcqQuestions = Chapter8Mcq.questions
            )
            9 -> ChapterContent(
                meta = Chapter9Content.meta,
                textSections = Chapter9Content.textSections,
                images = Chapter9Content.images,
                keyPoints = Chapter9Content.keyPoints,
                essayQuestions = Chapter9Essay.questions,
                mcqQuestions = Chapter9Mcq.questions
            )
            else -> ChapterContent(
                meta = meta,
                textSections = listOf(TextSection("در دست تکمیل", placeholderNotice)),
                images = listOf(ImageDesc("-", "در دست تکمیل", placeholderNotice)),
                keyPoints = listOf(placeholderNotice),
                essayQuestions = listOf(EssayQuestion(placeholderNotice, placeholderNotice)),
                mcqQuestions = listOf(
                    McqQuestion(
                        placeholderNotice,
                        listOf("باشه", "متوجه شدم", "-", "-"),
                        0,
                        placeholderNotice
                    )
                )
            )
        }
    }
}
