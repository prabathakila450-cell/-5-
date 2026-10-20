package com.example

/**
 * Curated, distinct, syllabus-aligned Question & Marking Scheme Generator
 * for Structured & Essay questions across all 25 categories (20 questions each = 500 questions per subject).
 * Completely eliminates repetition: Each category covers its specific curriculum lessons and each question
 * has unique sub-questions, model answers, and official marking schemes.
 */
object StructuredEssayDynamicContentProvider {

  fun buildSubQuestions(
    subject: String,
    cleanTheme: String,
    type: String,
    qIndex: Int,
    setNumber: Int
  ): List<StructuredSubQuestion> {
    return if (type.equals("STRUCTURED", ignoreCase = true)) {
      StructuredQuestionBankTemplates.getStructuredSubQuestions(subject, cleanTheme, qIndex, setNumber)
    } else {
      EssayQuestionBankTemplates.getEssaySubQuestions(subject, cleanTheme, qIndex, setNumber)
    }
  }
}
