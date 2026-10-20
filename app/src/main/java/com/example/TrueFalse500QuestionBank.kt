package com.example

import androidx.compose.ui.graphics.Color

enum class TrueFalseSubject(
  val id: String,
  val displayName: String,
  val iconEmoji: String,
  val primaryColor: Color
) {
  SCIENCE("sci", "විද්‍යාව (Science)", "🧬", Color(0xFF10B981)),
  MATHEMATICS("maths", "ගණිතය (Mathematics)", "📐", Color(0xFF3B82F6)),
  HISTORY("hist", "ඉතිහාසය (History)", "🏛️", Color(0xFFF59E0B)),
  SINHALA("sin", "සිංහල (Sinhala)", "📖", Color(0xFF8B5CF6)),
  BUDDHISM("bud", "බුද්ධ ධර්මය (Buddhism)", "☸️", Color(0xFFEC4899)),
  ENGLISH("eng", "ඉංග්‍රීසි (English)", "🔤", Color(0xFF06B6D4)),
  ICT("ict", "තොරතුරු තාක්ෂණය (ICT)", "💻", Color(0xFF6366F1)),
  GEOGRAPHY("geo", "භූගෝල විද්‍යාව (Geography)", "🌍", Color(0xFF14B8A6)),
  CIVICS("civ", "පුරවැසි අධ්‍යාපනය (Civics)", "⚖️", Color(0xFFE11D48)),
  COMMERCE("comm", "ව්‍යාපාර හා ගිණුම්කරණය", "📊", Color(0xFFD97706))
}

data class TrueFalseQuestionItem(
  val id: String,
  val number: Int,
  val grade: Int,
  val subject: TrueFalseSubject,
  val unitName: String,
  val statement: String,
  val isTrue: Boolean,
  val justification: String,
  val examTrapOrFact: String
)

data class TrueFalseTopicContent(
  val unitName: String,
  val statement: String,
  val isTrue: Boolean,
  val justification: String,
  val examTrap: String
)

object TrueFalse500Repository {
  const val TOTAL_QUESTIONS_PER_SUBJECT = 500
  const val BATCH_SIZE = 30
  const val TOTAL_BATCHES = 17

  fun getBatchRange(batchIndex: Int): Pair<Int, Int> {
    val start = batchIndex * BATCH_SIZE + 1
    val end = minOf((batchIndex + 1) * BATCH_SIZE, TOTAL_QUESTIONS_PER_SUBJECT)
    return Pair(start, end)
  }

  // Cache to ensure fast, deterministic retrieval
  private val cache = mutableMapOf<String, List<TrueFalseQuestionItem>>()

  fun getQuestionsForBatch(grade: Int, subject: TrueFalseSubject, batchIndex: Int): List<TrueFalseQuestionItem> {
    val all = getAllQuestions(grade, subject)
    val (start, end) = getBatchRange(batchIndex)
    return all.subList(start - 1, end)
  }

  fun getAllQuestions(grade: Int, subject: TrueFalseSubject): List<TrueFalseQuestionItem> {
    val key = "${grade}_${subject.id}"
    return cache.getOrPut(key) {
      generate500Questions(grade, subject)
    }
  }

  private fun generate500Questions(grade: Int, subject: TrueFalseSubject): List<TrueFalseQuestionItem> {
    val questions = ArrayList<TrueFalseQuestionItem>(TOTAL_QUESTIONS_PER_SUBJECT)

    for (qNum in 1..TOTAL_QUESTIONS_PER_SUBJECT) {
      val batchIndex = (qNum - 1) / BATCH_SIZE
      val topicIndex = (qNum - 1) % BATCH_SIZE

      val content: TrueFalseTopicContent = when (subject) {
        TrueFalseSubject.SCIENCE -> ScienceTrueFalseBank.getScienceQuestion(grade, qNum, batchIndex, topicIndex)
        TrueFalseSubject.MATHEMATICS -> MathsTrueFalseBank.getMathsQuestion(grade, qNum, batchIndex, topicIndex)
        TrueFalseSubject.HISTORY -> HistoryBuddhismTrueFalseBank.getHistoryQuestion(grade, qNum, batchIndex, topicIndex)
        TrueFalseSubject.BUDDHISM -> HistoryBuddhismTrueFalseBank.getBuddhismQuestion(grade, qNum, batchIndex, topicIndex)
        TrueFalseSubject.SINHALA -> LanguagesTrueFalseBank.getSinhalaQuestion(grade, qNum, batchIndex, topicIndex)
        TrueFalseSubject.ENGLISH -> LanguagesTrueFalseBank.getEnglishQuestion(grade, qNum, batchIndex, topicIndex)
        TrueFalseSubject.ICT -> SocialCommerceTrueFalseBank.getIctQuestion(grade, qNum, batchIndex, topicIndex)
        TrueFalseSubject.GEOGRAPHY -> SocialCommerceTrueFalseBank.getGeographyQuestion(grade, qNum, batchIndex, topicIndex)
        TrueFalseSubject.CIVICS -> SocialCommerceTrueFalseBank.getCivicsQuestion(grade, qNum, batchIndex, topicIndex)
        TrueFalseSubject.COMMERCE -> SocialCommerceTrueFalseBank.getCommerceQuestion(grade, qNum, batchIndex, topicIndex)
      }

      questions.add(
        TrueFalseQuestionItem(
          id = "tf_g${grade}_${subject.id}_q$qNum",
          number = qNum,
          grade = grade,
          subject = subject,
          unitName = content.unitName,
          statement = content.statement,
          isTrue = content.isTrue,
          justification = content.justification,
          examTrapOrFact = content.examTrap
        )
      )
    }
    return questions
  }
}
