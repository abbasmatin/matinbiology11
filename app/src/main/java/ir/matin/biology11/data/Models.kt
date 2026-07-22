package ir.matin.biology11.data

/** اطلاعات پایه هر فصل */
data class ChapterMeta(
    val id: Int,
    val title: String
)

/** یک بخش از متن فصل (مطابق گفتارهای کتاب) */
data class TextSection(
    val heading: String,
    val body: String
)

/** شرح یک تصویر یا شکل کتاب */
data class ImageDesc(
    val figureLabel: String,
    val title: String,
    val description: String
)

/** سوال تشریحی امتحان نهایی به همراه پاسخ کامل */
data class EssayQuestion(
    val question: String,
    val answer: String
)

/** سوال چهارگزینه‌ای با پاسخ صحیح و تحلیل تمام گزینه‌ها */
data class McqQuestion(
    val question: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String
)

/** بسته کامل محتوای یک فصل */
data class ChapterContent(
    val meta: ChapterMeta,
    val textSections: List<TextSection>,
    val images: List<ImageDesc>,
    val keyPoints: List<String>,
    val essayQuestions: List<EssayQuestion>,
    val mcqQuestions: List<McqQuestion>
)
