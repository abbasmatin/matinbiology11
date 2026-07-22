# زیست‌شناسی یازدهم (BiologyApp11)

اپلیکیشن اندروید (Kotlin) برای مرور کتاب زیست‌شناسی (۲) پایه یازدهم رشته تجربی.

## وضعیت محتوا
تمام ۹ فصل کامل هستند: متن گفتار‌به‌گفتار، شرح تصاویر، نکات کلیدی، ۲۰ سوال تشریحی و ۲۰ سوال تستی برای هر فصل.

## ساخت خودکار APK با GitHub Actions (ساده‌ترین راه، بدون نصب چیزی)
این پروژه شامل فایل `.github/workflows/build-apk.yml` است.
۱. یک مخزن (repository) جدید در GitHub بسازید و کل پوشه `BiologyApp` را در آن Push کنید.
۲. به تب Actions مخزن بروید؛ اجرای build به‌صورت خودکار شروع می‌شود.
۳. بعد از پایان (چند دقیقه)، از بخش Artifacts فایل `BiologyApp11-debug-apk` را دانلود کنید؛ این فایل zip حاوی `app-debug.apk` واقعی و نصب‌شدنی است.

## نحوه ساخت APK
این پروژه در محیطی بدون اتصال اینترنت تهیه شده و مستقیماً کامپایل نشده است. برای گرفتن خروجی APK:

1. Android Studio (نسخه Hedgehog به بعد) را نصب کنید.
2. پوشه `BiologyApp` را به‌عنوان یک پروژه موجود باز کنید (Open an existing project).
3. صبر کنید Gradle Sync وابستگی‌ها (از جمله کتابخانه AndroidPdfViewer از JitPack) را دانلود کند.
4. از منوی Build گزینه Build Bundle(s) / APK(s) → Build APK(s) را بزنید.
5. فایل خروجی در مسیر `app/build/outputs/apk/debug/app-debug.apk` قرار می‌گیرد.

جایگزین: با نصب Android SDK + Gradle در سیستم خودتان، از خط فرمان اجرا کنید:
```
./gradlew assembleDebug
```

## افزودن فونت وزیر
فایل `Vazir.ttf` را در مسیر `app/src/main/assets/fonts/Vazir.ttf` قرار دهید (توضیح کامل در
`app/src/main/assets/fonts/README_FONT.txt`). بدون این فایل هم برنامه با فونت پیش‌فرض سیستم اجرا می‌شود.

## افزودن PDF واقعی صفحات کتاب (اختیاری)
فایل‌های PDF فصل‌ها را با نام `chapter_<شماره>.pdf` در مسیر `app/src/main/assets/chapters/` قرار دهید.

## ساختار پروژه
```
app/src/main/java/ir/matin/biology11/
  data/     -> مدل‌ها، مخزن محتوا، تنظیمات (SharedPreferences)، فونت
  ui/       -> تمام صفحات (Activity ها)
app/src/main/res/     -> layout ها، رنگ‌ها، تم‌ها، آیکون‌ها
app/src/main/assets/  -> فونت و (اختیاری) PDF فصل‌ها
```
