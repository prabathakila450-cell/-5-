package com.example

import androidx.compose.ui.graphics.Color

/**
 * Data model for O/L Subject Tools (100 tools per subject, organized in 10 groups of 10).
 */
data class OlSubjectToolItem(
  val id: String,
  val subject: String,
  val toolNumber: Int, // 1 to 100
  val groupNumber: Int, // 1 to 10
  val titleSinhala: String,
  val titleEnglish: String,
  val category: String,
  val icon: String,
  val quickSummary: String,
  val formulaOrRule: String,
  val practicalApplication: String,
  val examTip: String
)

data class OlSubjectToolCategory(
  val subjectName: String,
  val subjectEnglish: String,
  val icon: String,
  val color: Color,
  val totalTools: Int = 100
)
