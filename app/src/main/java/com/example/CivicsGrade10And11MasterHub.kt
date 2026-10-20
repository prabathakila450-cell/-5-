package com.example

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy

/**
 * 🌟 100% ACCURATE GRADE 10 & 11 CIVIC EDUCATION (පුරවැසි අධ්‍යාපනය) MASTER HUB
 * Includes:
 * 1. All 12 Curriculum Lessons with Core Rules, Summaries, and Exam Tips
 * 2. O/L I MCQ Bank with Detailed Explanations
 * 3. O/L II Structured & Essay Questions with Official Marking Schemes
 * 4. Constitutional Evolution Interactive Timeline (1833 to Modern Amendments)
 * 5. Practical Civic Case Studies & Decision Dilemmas
 * 6. State Structure & Judicial Hierarchy Flowcharts
 */
@Composable
fun CivicsGrade10And11MasterHubDialog(
  initialGradeTab: CivicsGradeTab = CivicsGradeTab.ALL,
  initialSectionCategory: CivicsSectionCategory? = null,
  onDismiss: () -> Unit
) {
  var selectedGradeTab by remember { mutableStateOf(initialGradeTab) }
  var selectedCategory by remember { mutableStateOf(initialSectionCategory ?: CivicsSectionCategory.LESSONS) }
  var searchQuery by remember { mutableStateOf("") }

  // State for MCQ interactive test
  val userSelectedOptions = remember { mutableStateMapOf<Int, Int>() }
  var mcqScore by remember { mutableIntStateOf(0) }

  // Expanded items state
  var expandedLessonId by remember { mutableStateOf<String?>(null) }
  var expandedEssayId by remember { mutableStateOf<String?>(null) }
  var expandedCaseId by remember { mutableStateOf<String?>(null) }

  Dialog(
    onDismissRequest = onDismiss,
    properties = DialogProperties(
      usePlatformDefaultWidth = false,
      securePolicy = SecureFlagPolicy.Inherit
    )
  ) {
    BackHandler { onDismiss() }

    Surface(
      modifier = Modifier
        .fillMaxSize()
        .testTag("civics_master_hub_dialog"),
      color = Color(0xFF041E18)
    ) {
      Column(modifier = Modifier.fillMaxSize()) {
        // TOP APP BAR: Ultra-compact Emerald Theme (Minimizes screen height)
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .background(
              Brush.verticalGradient(
                colors = listOf(Color(0xFF0F766E), Color(0xFF115E59))
              )
            )
            .statusBarsPadding()
            .padding(horizontal = 10.dp, vertical = 3.dp)
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .height(34.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            IconButton(
              onClick = onDismiss,
              modifier = Modifier
                .size(30.dp)
                .testTag("civics_hub_back_btn")
            ) {
              Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.White,
                modifier = Modifier.size(18.dp)
              )
            }
            Spacer(modifier = Modifier.width(4.dp))
            Column(modifier = Modifier.weight(1f)) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Text("⚖️", fontSize = 14.sp)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                  text = "පුරවැසි අධ්‍යාපනය Master Hub",
                  color = Color.White,
                  fontSize = 13.5.sp,
                  fontWeight = FontWeight.Bold,
                  maxLines = 1,
                  overflow = TextOverflow.Ellipsis
                )
              }
              Text(
                text = "10 & 11 ශ්‍රේණි • විෂය නිර්දේශය • MCQs • රචනා",
                color = Color(0xFFCCFBF1),
                fontSize = 9.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
              )
            }
            Surface(
              color = Color(0xFF14B8A6),
              shape = RoundedCornerShape(8.dp)
            ) {
              Text(
                text = "O/L A+",
                color = Color.White,
                fontSize = 9.5.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
              )
            }
          }
        }

        // GRADE FILTER ROW (Ultra-compact)
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF042F2C))
            .padding(horizontal = 10.dp, vertical = 2.dp),
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          CivicsGradeTab.values().forEach { tab ->
            val isSelected = selectedGradeTab == tab
            Surface(
              onClick = { selectedGradeTab = tab },
              shape = RoundedCornerShape(6.dp),
              color = if (isSelected) Color(0xFF0D9488) else Color(0xFF134E4A),
              border = BorderStroke(
                width = 1.dp,
                color = if (isSelected) Color(0xFF2DD4BF) else Color(0xFF115E59)
              ),
              modifier = Modifier
                .weight(1f)
                .height(26.dp)
            ) {
              Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Text(
                  text = tab.labelSinhala,
                  fontSize = 10.5.sp,
                  fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                  color = if (isSelected) Color.White else Color(0xFF99F6E4)
                )
              }
            }
          }
        }

        // SEARCH BAR (Ultra-compact)
        OutlinedTextField(
          value = searchQuery,
          onValueChange = { searchQuery = it },
          placeholder = { Text("පාඩම, ප්‍රශ්නය හෝ ව්‍යවස්ථාව සොයන්න...", fontSize = 11.sp, color = Color(0xFF5EEAD4).copy(alpha = 0.6f)) },
          leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Color(0xFF2DD4BF), modifier = Modifier.size(15.dp)) },
          trailingIcon = {
            if (searchQuery.isNotEmpty()) {
              IconButton(onClick = { searchQuery = "" }, modifier = Modifier.size(20.dp)) {
                Icon(Icons.Default.Close, contentDescription = "Clear", tint = Color(0xFF5EEAD4), modifier = Modifier.size(13.dp))
              }
            }
          },
          singleLine = true,
          shape = RoundedCornerShape(8.dp),
          colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFF2DD4BF),
            unfocusedBorderColor = Color(0xFF115E59),
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedContainerColor = Color(0xFF042F2C),
            unfocusedContainerColor = Color(0xFF042F2C)
          ),
          modifier = Modifier
            .fillMaxWidth()
            .height(36.dp)
            .padding(horizontal = 10.dp, vertical = 2.dp)
            .testTag("civics_search_input")
        )

        // CATEGORY TABS HORIZONTAL SCROLL (Ultra-compact)
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF02201D))
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 8.dp, vertical = 3.dp),
          horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
          CivicsSectionCategory.values().forEach { cat ->
            val isSelected = selectedCategory == cat
            Surface(
              shape = RoundedCornerShape(12.dp),
              color = if (isSelected) cat.themeColor else Color(0xFF134E4A).copy(alpha = 0.5f),
              border = BorderStroke(
                width = 1.dp,
                color = if (isSelected) Color.White.copy(alpha = 0.8f) else Color(0xFF115E59)
              ),
              modifier = Modifier
                .clickable { selectedCategory = cat }
                .testTag("civics_cat_${cat.id}")
            ) {
              Row(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                Text(cat.emoji, fontSize = 11.sp)
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                  text = cat.titleSinhala,
                  fontSize = 10.5.sp,
                  fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                  color = if (isSelected) Color.White else Color(0xFFCCFBF1)
                )
              }
            }
          }
        }

        // MAIN CONTENT AREA BY CATEGORY
        Box(
          modifier = Modifier
            .weight(1f)
            .fillMaxWidth()
            .background(Color(0xFF021714))
        ) {
          when (selectedCategory) {
            CivicsSectionCategory.LESSONS -> {
              CivicsLessonsSection(
                gradeTab = selectedGradeTab,
                searchQuery = searchQuery,
                expandedLessonId = expandedLessonId,
                onToggleExpand = { id ->
                  expandedLessonId = if (expandedLessonId == id) null else id
                }
              )
            }
            CivicsSectionCategory.MCQ_BANK -> {
              CivicsMcqSection(
                gradeTab = selectedGradeTab,
                searchQuery = searchQuery,
                userSelectedOptions = userSelectedOptions,
                onSelectOption = { qId, optIdx ->
                  userSelectedOptions[qId] = optIdx
                },
                onResetQuiz = {
                  userSelectedOptions.clear()
                }
              )
            }
            CivicsSectionCategory.STRUCTURED_ESSAY -> {
              CivicsStructuredEssaySection(
                gradeTab = selectedGradeTab,
                searchQuery = searchQuery,
                expandedEssayId = expandedEssayId,
                onToggleExpand = { id ->
                  expandedEssayId = if (expandedEssayId == id) null else id
                }
              )
            }
            CivicsSectionCategory.TIMELINE -> {
              CivicsTimelineSection(searchQuery = searchQuery)
            }
            CivicsSectionCategory.CASE_STUDIES -> {
              CivicsCaseStudiesSection(
                searchQuery = searchQuery,
                expandedCaseId = expandedCaseId,
                onToggleExpand = { id ->
                  expandedCaseId = if (expandedCaseId == id) null else id
                }
              )
            }
            CivicsSectionCategory.FLOWCHARTS -> {
              CivicsFlowchartsSection(searchQuery = searchQuery)
            }
          }
        }
      }
    }
  }
}

// ============================================================================
// 1. LESSONS SECTION
// ============================================================================
@Composable
private fun CivicsLessonsSection(
  gradeTab: CivicsGradeTab,
  searchQuery: String,
  expandedLessonId: String?,
  onToggleExpand: (String) -> Unit
) {
  val filteredLessons = remember(gradeTab, searchQuery) {
    CivicsMasterDataProvider.allLessons.filter { lesson ->
      val matchesGrade = when (gradeTab) {
        CivicsGradeTab.ALL -> true
        CivicsGradeTab.GRADE_10 -> lesson.grade == "10"
        CivicsGradeTab.GRADE_11 -> lesson.grade == "11"
      }
      val matchesSearch = searchQuery.isBlank() ||
          lesson.titleSinhala.contains(searchQuery, ignoreCase = true) ||
          lesson.titleEnglish.contains(searchQuery, ignoreCase = true) ||
          lesson.keyConcepts.any { it.contains(searchQuery, ignoreCase = true) } ||
          lesson.fullSummary.contains(searchQuery, ignoreCase = true)
      matchesGrade && matchesSearch
    }
  }

  if (filteredLessons.isEmpty()) {
    EmptyResultNotice("සෙවුමට ගැළපෙන පුරවැසි පාඩම් හමු නොවීය.")
    return
  }

  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(12.dp),
    verticalArrangement = Arrangement.spacedBy(10.dp)
  ) {
    item {
      Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0F766E).copy(alpha = 0.25f)),
        border = BorderStroke(1.dp, Color(0xFF14B8A6))
      ) {
        Row(
          modifier = Modifier.padding(10.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text("📖", fontSize = 20.sp)
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "O/L විභාගයට අදාළ සම්පූර්ණ ඒකක ${filteredLessons.size} ක විෂය කරුණු, සූත්‍රිකා සහ විභාග ඉඟි පහතින් අධ්‍යයනය කරන්න.",
            fontSize = 11.5.sp,
            color = Color(0xFFCCFBF1),
            lineHeight = 16.sp
          )
        }
      }
    }

    items(filteredLessons, key = { it.id }) { lesson ->
      val isExpanded = expandedLessonId == lesson.id
      Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF064E3B).copy(alpha = 0.45f)),
        border = BorderStroke(1.dp, if (isExpanded) Color(0xFF2DD4BF) else Color(0xFF115E59)),
        modifier = Modifier
          .fillMaxWidth()
          .clickable { onToggleExpand(lesson.id) }
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Surface(
              color = if (lesson.grade == "10") Color(0xFF0D9488) else Color(0xFF2563EB),
              shape = RoundedCornerShape(6.dp)
            ) {
              Text(
                text = "${lesson.grade} ශ්‍රේණිය • ඒකකය 0${lesson.unitNumber}",
                color = Color.White,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
              )
            }
            Spacer(modifier = Modifier.weight(1f))
            Icon(
              imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
              contentDescription = null,
              tint = Color(0xFF2DD4BF)
            )
          }

          Spacer(modifier = Modifier.height(6.dp))
          Text(
            text = lesson.titleSinhala,
            fontSize = 14.5.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
          )
          Text(
            text = lesson.titleEnglish,
            fontSize = 11.sp,
            color = Color(0xFF5EEAD4).copy(alpha = 0.8f)
          )

          Spacer(modifier = Modifier.height(8.dp))
          // Core Principle quote
          Surface(
            color = Color(0xFF022C22),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(0.8.dp, Color(0xFF047857))
          ) {
            Row(
              modifier = Modifier.padding(8.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text("💡", fontSize = 14.sp)
              Spacer(modifier = Modifier.width(6.dp))
              Text(
                text = lesson.corePrinciples,
                fontSize = 11.sp,
                color = Color(0xFFA7F3D0),
                lineHeight = 15.sp
              )
            }
          }

          AnimatedVisibility(
            visible = isExpanded,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
          ) {
            Column(modifier = Modifier.padding(top = 10.dp)) {
              HorizontalDivider(color = Color(0xFF115E59), thickness = 0.8.dp)
              Spacer(modifier = Modifier.height(8.dp))

              Text(
                text = "📌 ප්‍රධාන විෂය සංකල්ප (Key Concepts):",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFDE047)
              )
              Spacer(modifier = Modifier.height(4.dp))
              lesson.keyConcepts.forEach { concept ->
                Row(modifier = Modifier.padding(vertical = 2.dp)) {
                  Text("•", color = Color(0xFF2DD4BF), fontWeight = FontWeight.Bold, fontSize = 13.sp)
                  Spacer(modifier = Modifier.width(6.dp))
                  Text(text = concept, fontSize = 11.5.sp, color = Color.White, lineHeight = 16.sp)
                }
              }

              Spacer(modifier = Modifier.height(8.dp))
              Text(
                text = "📖 සවිස්තරාත්මක පාඩම් සාරාංශය:",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF67E8F9)
              )
              Spacer(modifier = Modifier.height(4.dp))
              Text(
                text = lesson.fullSummary,
                fontSize = 11.5.sp,
                color = Color(0xFFE2E8F0),
                lineHeight = 17.sp
              )

              Spacer(modifier = Modifier.height(8.dp))
              Surface(
                color = Color(0xFF78350F).copy(alpha = 0.4f),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Color(0xFFD97706))
              ) {
                Row(
                  modifier = Modifier.padding(8.dp),
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Text("🎯", fontSize = 14.sp)
                  Spacer(modifier = Modifier.width(6.dp))
                  Column {
                    Text(
                      text = "O/L විභාග රහස් & ඉඟි:",
                      fontSize = 10.5.sp,
                      fontWeight = FontWeight.Bold,
                      color = Color(0xFFFDE68A)
                    )
                    Text(
                      text = lesson.examTips,
                      fontSize = 11.sp,
                      color = Color.White,
                      lineHeight = 15.sp
                    )
                  }
                }
              }
            }
          }
        }
      }
    }
  }
}

// ============================================================================
// 2. MCQ SECTION (INTERACTIVE WITH EXPLANATIONS)
// ============================================================================
@Composable
private fun CivicsMcqSection(
  gradeTab: CivicsGradeTab,
  searchQuery: String,
  userSelectedOptions: Map<Int, Int>,
  onSelectOption: (Int, Int) -> Unit,
  onResetQuiz: () -> Unit
) {
  val filteredMcqs = remember(gradeTab, searchQuery) {
    CivicsMasterDataProvider.mcqQuestions.filter { q ->
      val matchesGrade = when (gradeTab) {
        CivicsGradeTab.ALL -> true
        CivicsGradeTab.GRADE_10 -> q.grade == "10"
        CivicsGradeTab.GRADE_11 -> q.grade == "11"
      }
      val matchesSearch = searchQuery.isBlank() ||
          q.questionText.contains(searchQuery, ignoreCase = true) ||
          q.unitName.contains(searchQuery, ignoreCase = true) ||
          q.explanation.contains(searchQuery, ignoreCase = true)
      matchesGrade && matchesSearch
    }
  }

  val totalAnswered = userSelectedOptions.keys.count { id -> filteredMcqs.any { it.id == id } }
  val totalCorrect = userSelectedOptions.entries.count { (id, opt) ->
    filteredMcqs.firstOrNull { it.id == id }?.correctOptionIndex == opt
  }

  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(12.dp),
    verticalArrangement = Arrangement.spacedBy(12.dp)
  ) {
    item {
      // Score banner
      Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1B4B)),
        border = BorderStroke(1.dp, Color(0xFF6366F1))
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
          ) {
            Column {
              Text(
                text = "🎯 O/L I පත්‍රය - බහුවරණ පුහුණුව",
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
              )
              Text(
                text = "පිළිතුරු දුන් ගණන: $totalAnswered / ${filteredMcqs.size} | නිවැරදි: $totalCorrect",
                fontSize = 11.5.sp,
                color = Color(0xFFA5B4FC)
              )
            }
            Button(
              onClick = onResetQuiz,
              colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4F46E5)),
              contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
              modifier = Modifier.height(30.dp)
            ) {
              Text("නැවත මුල සිට", fontSize = 10.sp, color = Color.White)
            }
          }
        }
      }
    }

    itemsIndexed(filteredMcqs, key = { _, item -> item.id }) { index, q ->
      val selectedOption = userSelectedOptions[q.id]
      val hasAnswered = selectedOption != null

      Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
        border = BorderStroke(
          1.dp,
          when {
            !hasAnswered -> Color(0xFF334155)
            selectedOption == q.correctOptionIndex -> Color(0xFF22C55E)
            else -> Color(0xFFEF4444)
          }
        ),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Surface(
              color = Color(0xFF0284C7),
              shape = RoundedCornerShape(4.dp)
            ) {
              Text(
                text = "ප්‍රශ්න ${index + 1} (${q.grade} ශ්‍රේණිය - ${q.unitName})",
                color = Color.White,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
              )
            }
          }

          Spacer(modifier = Modifier.height(8.dp))
          Text(
            text = q.questionText,
            fontSize = 13.5.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
            lineHeight = 18.sp
          )

          Spacer(modifier = Modifier.height(10.dp))
          q.options.forEachIndexed { optIndex, optionText ->
            val isThisSelected = selectedOption == optIndex
            val isCorrect = q.correctOptionIndex == optIndex

            val backgroundColor = when {
              !hasAnswered -> Color(0xFF1E293B)
              isCorrect -> Color(0xFF14532D)
              isThisSelected -> Color(0xFF7F1D1D)
              else -> Color(0xFF1E293B).copy(alpha = 0.5f)
            }

            val borderColor = when {
              !hasAnswered -> Color(0xFF475569)
              isCorrect -> Color(0xFF4ADE80)
              isThisSelected -> Color(0xFFF87171)
              else -> Color(0xFF334155)
            }

            Surface(
              shape = RoundedCornerShape(8.dp),
              color = backgroundColor,
              border = BorderStroke(1.dp, borderColor),
              modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 3.dp)
                .clickable { onSelectOption(q.id, optIndex) }
            ) {
              Row(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                Text(
                  text = optionText,
                  fontSize = 11.5.sp,
                  color = if (hasAnswered && isCorrect) Color(0xFF86EFAC) else Color.White,
                  modifier = Modifier.weight(1f),
                  lineHeight = 16.sp
                )
                if (hasAnswered) {
                  if (isCorrect) {
                    Text("✓ නිවැරදියි", color = Color(0xFF4ADE80), fontSize = 11.sp, fontWeight = FontWeight.Bold)
                  } else if (isThisSelected) {
                    Text("✗ වැරදියි", color = Color(0xFFF87171), fontSize = 11.sp, fontWeight = FontWeight.Bold)
                  }
                }
              }
            }
          }

          // EXPLANATION
          if (hasAnswered) {
            Spacer(modifier = Modifier.height(8.dp))
            Surface(
              color = Color(0xFF0F2E23),
              shape = RoundedCornerShape(8.dp),
              border = BorderStroke(1.dp, Color(0xFF10B981))
            ) {
              Column(modifier = Modifier.padding(8.dp)) {
                Text(
                  text = "💡 නිවැරදි කරුණු පැහැදිලි කිරීම (විවරණය):",
                  fontSize = 11.sp,
                  fontWeight = FontWeight.Bold,
                  color = Color(0xFF6EE7B7)
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                  text = q.explanation,
                  fontSize = 11.sp,
                  color = Color.White,
                  lineHeight = 15.sp
                )
              }
            }
          }
        }
      }
    }
  }
}

// ============================================================================
// 3. STRUCTURED ESSAY SECTION (WITH FULL MARKING SCHEMES)
// ============================================================================
@Composable
private fun CivicsStructuredEssaySection(
  gradeTab: CivicsGradeTab,
  searchQuery: String,
  expandedEssayId: String?,
  onToggleExpand: (String) -> Unit
) {
  val filteredEssays = remember(gradeTab, searchQuery) {
    CivicsMasterDataProvider.structuredEssays.filter { essay ->
      val matchesGrade = when (gradeTab) {
        CivicsGradeTab.ALL -> true
        CivicsGradeTab.GRADE_10 -> essay.grade == "10"
        CivicsGradeTab.GRADE_11 -> essay.grade == "11"
      }
      val matchesSearch = searchQuery.isBlank() ||
          essay.title.contains(searchQuery, ignoreCase = true) ||
          essay.unitName.contains(searchQuery, ignoreCase = true) ||
          essay.scenarioText.contains(searchQuery, ignoreCase = true)
      matchesGrade && matchesSearch
    }
  }

  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(12.dp),
    verticalArrangement = Arrangement.spacedBy(12.dp)
  ) {
    item {
      Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E3A8A).copy(alpha = 0.35f)),
        border = BorderStroke(1.dp, Color(0xFF3B82F6))
      ) {
        Row(
          modifier = Modifier.padding(10.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text("📝", fontSize = 20.sp)
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "O/L II පත්‍රයේ සම්මත ලකුණු 15 ප්‍රශ්න ආකෘතිය සහ නිල ලකුණු ලබාදීමේ පටිපාටිය (Marking Schemes) පහතින් බලන්න.",
            fontSize = 11.5.sp,
            color = Color(0xFFBFDBFE),
            lineHeight = 16.sp
          )
        }
      }
    }

    items(filteredEssays, key = { it.id }) { essay ->
      val isExpanded = expandedEssayId == essay.id
      Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0F172A)),
        border = BorderStroke(1.dp, if (isExpanded) Color(0xFF60A5FA) else Color(0xFF1E293B)),
        modifier = Modifier
          .fillMaxWidth()
          .clickable { onToggleExpand(essay.id) }
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Surface(
              color = Color(0xFF2563EB),
              shape = RoundedCornerShape(6.dp)
            ) {
              Text(
                text = "${essay.grade} ශ්‍රේණිය • ${essay.unitName}",
                color = Color.White,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
              )
            }
            Spacer(modifier = Modifier.weight(1f))
            Surface(
              color = Color(0xFF1E293B),
              shape = RoundedCornerShape(4.dp)
            ) {
              Text(
                text = "ලකුණු 15",
                color = Color(0xFF93C5FD),
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
              )
            }
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
              imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
              contentDescription = null,
              tint = Color(0xFF60A5FA)
            )
          }

          Spacer(modifier = Modifier.height(8.dp))
          Text(
            text = essay.title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
          )
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = essay.scenarioText,
            fontSize = 11.5.sp,
            color = Color(0xFF94A3B8),
            lineHeight = 16.sp
          )

          AnimatedVisibility(
            visible = isExpanded,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
          ) {
            Column(modifier = Modifier.padding(top = 10.dp)) {
              HorizontalDivider(color = Color(0xFF334155), thickness = 0.8.dp)
              Spacer(modifier = Modifier.height(8.dp))

              essay.subQuestions.forEach { sub ->
                Surface(
                  shape = RoundedCornerShape(8.dp),
                  color = Color(0xFF1E293B),
                  modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                ) {
                  Column(modifier = Modifier.padding(10.dp)) {
                    Row(
                      modifier = Modifier.fillMaxWidth(),
                      horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                      Text(
                        text = "${sub.numberText} ${sub.question}",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFFF1F5F9),
                        modifier = Modifier.weight(1f),
                        lineHeight = 16.sp
                      )
                      Surface(
                        color = Color(0xFF0284C7),
                        shape = RoundedCornerShape(4.dp)
                      ) {
                        Text(
                          text = "ලකුණු ${sub.marks}",
                          color = Color.White,
                          fontSize = 9.5.sp,
                          fontWeight = FontWeight.Bold,
                          modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                        )
                      }
                    }

                    Spacer(modifier = Modifier.height(6.dp))
                    Text(
                      text = "🎯 ලකුණු දීමේ පටිපාටිය (Marking Scheme):",
                      fontSize = 10.5.sp,
                      fontWeight = FontWeight.Bold,
                      color = Color(0xFF34D399)
                    )
                    sub.markingSchemePoints.forEach { pt ->
                      Row(modifier = Modifier.padding(vertical = 1.5.dp)) {
                        Text("•", color = Color(0xFF10B981), fontSize = 11.sp, fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(text = pt, fontSize = 11.sp, color = Color(0xFFE2E8F0), lineHeight = 15.sp)
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }
}

// ============================================================================
// 4. CONSTITUTIONAL TIMELINE SECTION
// ============================================================================
@Composable
private fun CivicsTimelineSection(searchQuery: String) {
  val timelineItems = remember(searchQuery) {
    CivicsMasterDataProvider.constitutionalTimeline.filter { item ->
      searchQuery.isBlank() ||
          item.year.contains(searchQuery, ignoreCase = true) ||
          item.titleSinhala.contains(searchQuery, ignoreCase = true) ||
          item.description.contains(searchQuery, ignoreCase = true) ||
          item.keyFeatures.any { it.contains(searchQuery, ignoreCase = true) }
    }
  }

  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(12.dp),
    verticalArrangement = Arrangement.spacedBy(10.dp)
  ) {
    item {
      Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF78350F).copy(alpha = 0.35f)),
        border = BorderStroke(1.dp, Color(0xFFD97706))
      ) {
        Row(
          modifier = Modifier.padding(10.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text("⏳", fontSize = 20.sp)
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "1833 කෝල්බෲක් ප්‍රතිසංස්කරණයේ සිට 1978 සහ මෑතකාලීන ආණ්ඩුක්‍රම ව්‍යවස්ථා සංශෝධන දක්වා ඓතිහාසික හැරවුම් ලක්ෂ්‍යයන්.",
            fontSize = 11.5.sp,
            color = Color(0xFFFDE68A),
            lineHeight = 16.sp
          )
        }
      }
    }

    items(timelineItems) { item ->
      Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1C1917)),
        border = BorderStroke(1.dp, Color(0xFF78350F)),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Surface(
              color = Color(0xFFD97706),
              shape = RoundedCornerShape(6.dp)
            ) {
              Text(
                text = item.year,
                color = Color.White,
                fontSize = 12.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp)
              )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = item.titleSinhala,
              fontSize = 14.sp,
              fontWeight = FontWeight.Bold,
              color = Color.White
            )
          }

          Spacer(modifier = Modifier.height(6.dp))
          Text(
            text = item.description,
            fontSize = 11.5.sp,
            color = Color(0xFFE7E5E4),
            lineHeight = 16.sp
          )

          Spacer(modifier = Modifier.height(8.dp))
          Text(
            text = "📌 ප්‍රධාන ප්‍රතිසංස්කරණ ලක්ෂණ:",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFBBF24)
          )
          Spacer(modifier = Modifier.height(4.dp))
          item.keyFeatures.forEach { feat ->
            Row(modifier = Modifier.padding(vertical = 1.5.dp)) {
              Text("•", color = Color(0xFFF59E0B), fontSize = 12.sp, fontWeight = FontWeight.Bold)
              Spacer(modifier = Modifier.width(5.dp))
              Text(text = feat, fontSize = 11.sp, color = Color.White, lineHeight = 15.sp)
            }
          }

          Spacer(modifier = Modifier.height(6.dp))
          Surface(
            color = Color(0xFF292524),
            shape = RoundedCornerShape(6.dp)
          ) {
            Row(
              modifier = Modifier.padding(6.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text("⭐", fontSize = 11.sp)
              Spacer(modifier = Modifier.width(5.dp))
              Text(
                text = item.significance,
                fontSize = 10.5.sp,
                color = Color(0xFFFDE68A),
                lineHeight = 14.sp
              )
            }
          }
        }
      }
    }
  }
}

// ============================================================================
// 5. CIVIC CASE STUDIES SECTION
// ============================================================================
@Composable
private fun CivicsCaseStudiesSection(
  searchQuery: String,
  expandedCaseId: String?,
  onToggleExpand: (String) -> Unit
) {
  val caseItems = remember(searchQuery) {
    CivicsMasterDataProvider.caseStudies.filter { caseItem ->
      searchQuery.isBlank() ||
          caseItem.title.contains(searchQuery, ignoreCase = true) ||
          caseItem.situation.contains(searchQuery, ignoreCase = true) ||
          caseItem.correctCourseOfAction.contains(searchQuery, ignoreCase = true)
    }
  }

  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(12.dp),
    verticalArrangement = Arrangement.spacedBy(10.dp)
  ) {
    item {
      Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF581C87).copy(alpha = 0.35f)),
        border = BorderStroke(1.dp, Color(0xFF9333EA))
      ) {
        Row(
          modifier = Modifier.padding(10.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text("⚖️", fontSize = 20.sp)
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "මූලික අයිතිවාසිකම්, RTI පනත, පළාත් පාලනය සහ ගැටුම් නිරාකරණය පිළිබඳ ප්‍රායෝගික සිද්ධි අධ්‍යයන සහ පුරවැසි ක්‍රියාමාර්ග.",
            fontSize = 11.5.sp,
            color = Color(0xFFE9D5FF),
            lineHeight = 16.sp
          )
        }
      }
    }

    items(caseItems, key = { it.id }) { caseItem ->
      val isExpanded = expandedCaseId == caseItem.id

      Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E1B4B).copy(alpha = 0.6f)),
        border = BorderStroke(1.dp, if (isExpanded) Color(0xFFA855F7) else Color(0xFF3B0764)),
        modifier = Modifier
          .fillMaxWidth()
          .clickable { onToggleExpand(caseItem.id) }
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = caseItem.title,
              fontSize = 13.5.sp,
              fontWeight = FontWeight.Bold,
              color = Color.White,
              modifier = Modifier.weight(1f)
            )
            Icon(
              imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
              contentDescription = null,
              tint = Color(0xFFA855F7)
            )
          }

          Spacer(modifier = Modifier.height(6.dp))
          Text(
            text = "📋 සිදුවීම:",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFC084FC)
          )
          Text(
            text = caseItem.situation,
            fontSize = 11.5.sp,
            color = Color(0xFFE2E8F0),
            lineHeight = 16.sp
          )

          Spacer(modifier = Modifier.height(6.dp))
          Text(
            text = "❓ ගැටලුව / විමසුම:",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFDE047)
          )
          Text(
            text = caseItem.dilemma,
            fontSize = 11.sp,
            color = Color(0xFFFEF08A),
            lineHeight = 15.sp
          )

          AnimatedVisibility(
            visible = isExpanded,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
          ) {
            Column(modifier = Modifier.padding(top = 10.dp)) {
              HorizontalDivider(color = Color(0xFF4C1D95), thickness = 0.8.dp)
              Spacer(modifier = Modifier.height(8.dp))

              Surface(
                color = Color(0xFF14532D).copy(alpha = 0.4f),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Color(0xFF22C55E))
              ) {
                Column(modifier = Modifier.padding(8.dp)) {
                  Text(
                    text = "✅ නිර්දේශිත නිවැරදි පුරවැසි ක්‍රියාමාර්ගය:",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF86EFAC)
                  )
                  Spacer(modifier = Modifier.height(3.dp))
                  Text(
                    text = caseItem.correctCourseOfAction,
                    fontSize = 11.sp,
                    color = Color.White,
                    lineHeight = 16.sp
                  )
                }
              }

              Spacer(modifier = Modifier.height(6.dp))
              Text(
                text = "📜 නීතිමය පදනම / ව්‍යවස්ථාපිත නීතිය: ${caseItem.legalBasisOrRule}",
                fontSize = 10.5.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF94A3B8)
              )
            }
          }
        }
      }
    }
  }
}

// ============================================================================
// 6. FLOWCHARTS SECTION
// ============================================================================
@Composable
private fun CivicsFlowchartsSection(searchQuery: String) {
  val flowchartItems = remember(searchQuery) {
    CivicsMasterDataProvider.flowcharts.filter { flow ->
      searchQuery.isBlank() ||
          flow.title.contains(searchQuery, ignoreCase = true) ||
          flow.category.contains(searchQuery, ignoreCase = true) ||
          flow.hierarchyLevels.any { it.first.contains(searchQuery, ignoreCase = true) || it.second.contains(searchQuery, ignoreCase = true) }
    }
  }

  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(12.dp),
    verticalArrangement = Arrangement.spacedBy(12.dp)
  ) {
    item {
      Card(
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF064E3B).copy(alpha = 0.35f)),
        border = BorderStroke(1.dp, Color(0xFF059669))
      ) {
        Row(
          modifier = Modifier.padding(10.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text("🏛️", fontSize = 20.sp)
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "රාජ්‍ය බලතල බෙදීම, අධිකරණ ධූරාවලිය, පළාත් පාලන ව්‍යුහය සහ එක්සත් ජාතීන්ගේ සංවිධානයේ ප්‍රස්ථාරික සටහන්.",
            fontSize = 11.5.sp,
            color = Color(0xFFA7F3D0),
            lineHeight = 16.sp
          )
        }
      }
    }

    items(flowchartItems, key = { it.id }) { flow ->
      Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF062E25)),
        border = BorderStroke(1.dp, Color(0xFF0D9488)),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          Surface(
            color = Color(0xFF0D9488),
            shape = RoundedCornerShape(4.dp)
          ) {
            Text(
              text = flow.category,
              color = Color.White,
              fontSize = 10.sp,
              fontWeight = FontWeight.Bold,
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            )
          }
          Spacer(modifier = Modifier.height(4.dp))
          Text(
            text = flow.title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
          )
          Spacer(modifier = Modifier.height(2.dp))
          Text(
            text = flow.description,
            fontSize = 11.sp,
            color = Color(0xFF99F6E4)
          )

          Spacer(modifier = Modifier.height(8.dp))
          flow.hierarchyLevels.forEachIndexed { idx, level ->
            Surface(
              shape = RoundedCornerShape(8.dp),
              color = Color(0xFF04241E),
              border = BorderStroke(0.8.dp, Color(0xFF115E59)),
              modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 3.dp)
            ) {
              Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.Top
              ) {
                Surface(
                  shape = CircleShape,
                  color = Color(0xFF0D9488),
                  modifier = Modifier.size(20.dp)
                ) {
                  Box(contentAlignment = Alignment.Center) {
                    Text(
                      text = "${idx + 1}",
                      fontSize = 10.sp,
                      fontWeight = FontWeight.Bold,
                      color = Color.White
                    )
                  }
                }
                Spacer(modifier = Modifier.width(8.dp))
                Column(modifier = Modifier.weight(1f)) {
                  Text(
                    text = level.first,
                    fontSize = 11.5.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFFDE047)
                  )
                  Text(
                    text = level.second,
                    fontSize = 10.5.sp,
                    color = Color.White,
                    lineHeight = 14.sp
                  )
                }
              }
            }
          }
        }
      }
    }
  }
}

@Composable
private fun EmptyResultNotice(message: String) {
  Box(
    modifier = Modifier
      .fillMaxSize()
      .padding(32.dp),
    contentAlignment = Alignment.Center
  ) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
      Text("🔍", fontSize = 32.sp)
      Spacer(modifier = Modifier.height(8.dp))
      Text(
        text = message,
        color = Color(0xFF94A3B8),
        fontSize = 13.sp,
        textAlign = TextAlign.Center
      )
    }
  }
}
