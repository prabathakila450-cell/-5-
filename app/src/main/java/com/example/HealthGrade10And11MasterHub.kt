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
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy
import kotlin.math.ceil
import kotlin.math.log2
import kotlin.math.pow

/**
 * 🌟 100% ACCURATE GRADE 10 & 11 HEALTH & PHYSICAL EDUCATION MASTER HUB
 * Includes:
 * 1. Complete 12 Curriculum Lessons with Core Rules, Summaries, and Exam Tips
 * 2. O/L I MCQ Bank with Detailed Explanations
 * 3. O/L II Structured & Essay Questions with Official Marking Schemes
 * 4. Interactive Live BMI Calculator & Tournament Match/Byes Calculator
 * 5. Sports Injuries & RICE First Aid Action Guides
 * 6. WHO 10 Core Life Skills & Mental Health Management
 */
@Composable
fun HealthGrade10And11MasterHubDialog(
  initialGradeTab: HealthGradeTab = HealthGradeTab.ALL,
  initialSectionCategory: HealthSectionCategory? = null,
  onDismiss: () -> Unit
) {
  var selectedGradeTab by remember { mutableStateOf(initialGradeTab) }
  var selectedCategory by remember { mutableStateOf(initialSectionCategory ?: HealthSectionCategory.LESSONS) }
  var searchQuery by remember { mutableStateOf("") }

  // State for MCQ interactive test
  val userSelectedOptions = remember { mutableStateMapOf<Int, Int>() }

  // Expanded items state
  var expandedLessonId by remember { mutableStateOf<String?>(null) }
  var expandedEssayId by remember { mutableStateOf<String?>(null) }
  var expandedFirstAidId by remember { mutableStateOf<String?>(null) }

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
        .testTag("health_master_hub_dialog"),
      color = Color(0xFF1E0A12)
    ) {
      Column(modifier = Modifier.fillMaxSize()) {
        // TOP APP BAR
        Box(
          modifier = Modifier
            .fillMaxWidth()
            .background(
              Brush.verticalGradient(
                colors = listOf(Color(0xFFBE123C), Color(0xFF9F1239))
              )
            )
            .statusBarsPadding()
            .padding(horizontal = 12.dp, vertical = 10.dp)
        ) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
          ) {
            IconButton(
              onClick = onDismiss,
              modifier = Modifier.testTag("health_hub_back_btn")
            ) {
              Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
              )
            }
            Spacer(modifier = Modifier.width(4.dp))
            Column(modifier = Modifier.weight(1f)) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                Text("🏃‍♂️", fontSize = 18.sp)
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                  text = "සෞඛ්‍ය හා ශාරීරික අධ්‍යාපනය Master Hub",
                  color = Color.White,
                  fontSize = 16.5.sp,
                  fontWeight = FontWeight.Bold,
                  maxLines = 1,
                  overflow = TextOverflow.Ellipsis
                )
              }
              Text(
                text = "10 & 11 ශ්‍රේණි • විෂය නිර්දේශය • MCQs • රචනා • BMI • RICE • තරග සූත්‍ර",
                color = Color(0xFFFFE4E6),
                fontSize = 10.5.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
              )
            }
            Surface(
              color = Color(0xFFF43F5E),
              shape = RoundedCornerShape(12.dp)
            ) {
              Text(
                text = "O/L A+",
                color = Color.White,
                fontSize = 11.sp,
                fontWeight = FontWeight.ExtraBold,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
              )
            }
          }
        }

        // GRADE FILTER ROW
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF3B0B1D))
            .padding(horizontal = 12.dp, vertical = 6.dp),
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          HealthGradeTab.values().forEach { tab ->
            val isSelected = selectedGradeTab == tab
            FilterChip(
              selected = isSelected,
              onClick = { selectedGradeTab = tab },
              label = {
                Text(
                  text = tab.labelSinhala,
                  fontSize = 11.5.sp,
                  fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                )
              },
              colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = Color(0xFFE11D48),
                selectedLabelColor = Color.White,
                containerColor = Color(0xFF4C0519),
                labelColor = Color(0xFFFECDD3)
              ),
              border = BorderStroke(
                width = 1.dp,
                color = if (isSelected) Color(0xFFFB7185) else Color(0xFF881337)
              ),
              modifier = Modifier.weight(1f)
            )
          }
        }

        // SEARCH BAR
        OutlinedTextField(
          value = searchQuery,
          onValueChange = { searchQuery = it },
          placeholder = { Text("පාඩම, ප්‍රශ්නය, රෝගය හෝ ප්‍රථමාධාර සොයන්න...", fontSize = 12.sp, color = Color(0xFFFDA4AF).copy(alpha = 0.7f)) },
          leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Color(0xFFFB7185)) },
          trailingIcon = {
            if (searchQuery.isNotEmpty()) {
              IconButton(onClick = { searchQuery = "" }) {
                Icon(Icons.Default.Close, contentDescription = "Clear", tint = Color(0xFFFDA4AF))
              }
            }
          },
          singleLine = true,
          shape = RoundedCornerShape(10.dp),
          colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFFFB7185),
            unfocusedBorderColor = Color(0xFF881337),
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedContainerColor = Color(0xFF2C0A16),
            unfocusedContainerColor = Color(0xFF2C0A16)
          ),
          modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 6.dp)
            .testTag("health_search_input")
        )

        // CATEGORY TABS HORIZONTAL SCROLL
        Row(
          modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF1E050E))
            .horizontalScroll(rememberScrollState())
            .padding(horizontal = 12.dp, vertical = 6.dp),
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          HealthSectionCategory.values().forEach { cat ->
            val isSelected = selectedCategory == cat
            Surface(
              shape = RoundedCornerShape(16.dp),
              color = if (isSelected) cat.themeColor else Color(0xFF3B0B1D).copy(alpha = 0.6f),
              border = BorderStroke(
                width = 1.dp,
                color = if (isSelected) Color.White.copy(alpha = 0.8f) else Color(0xFF881337)
              ),
              modifier = Modifier
                .clickable { selectedCategory = cat }
                .testTag("health_cat_${cat.id}")
            ) {
              Row(
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp),
                verticalAlignment = Alignment.CenterVertically
              ) {
                Text(cat.emoji, fontSize = 13.sp)
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                  text = cat.titleSinhala,
                  fontSize = 11.sp,
                  fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium,
                  color = if (isSelected) Color.White else Color(0xFFFFE4E6)
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
            .background(Color(0xFF14030A))
        ) {
          when (selectedCategory) {
            HealthSectionCategory.LESSONS -> {
              HealthLessonsSection(
                gradeTab = selectedGradeTab,
                searchQuery = searchQuery,
                expandedLessonId = expandedLessonId,
                onToggleExpand = { id ->
                  expandedLessonId = if (expandedLessonId == id) null else id
                }
              )
            }
            HealthSectionCategory.MCQ_BANK -> {
              HealthMcqSection(
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
            HealthSectionCategory.STRUCTURED_ESSAY -> {
              HealthStructuredEssaySection(
                gradeTab = selectedGradeTab,
                searchQuery = searchQuery,
                expandedEssayId = expandedEssayId,
                onToggleExpand = { id ->
                  expandedEssayId = if (expandedEssayId == id) null else id
                }
              )
            }
            HealthSectionCategory.CALCULATORS -> {
              HealthCalculatorsSection()
            }
            HealthSectionCategory.FIRST_AID -> {
              HealthFirstAidSection(
                searchQuery = searchQuery,
                expandedFirstAidId = expandedFirstAidId,
                onToggleExpand = { id ->
                  expandedFirstAidId = if (expandedFirstAidId == id) null else id
                }
              )
            }
            HealthSectionCategory.LIFE_SKILLS -> {
              HealthLifeSkillsSection(searchQuery = searchQuery)
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
private fun HealthLessonsSection(
  gradeTab: HealthGradeTab,
  searchQuery: String,
  expandedLessonId: String?,
  onToggleExpand: (String) -> Unit
) {
  val filteredLessons = remember(gradeTab, searchQuery) {
    HealthMasterDataProvider.allLessons.filter { lesson ->
      val matchesGrade = when (gradeTab) {
        HealthGradeTab.ALL -> true
        HealthGradeTab.GRADE_10 -> lesson.grade == "10"
        HealthGradeTab.GRADE_11 -> lesson.grade == "11"
      }
      val matchesSearch = searchQuery.isBlank() ||
          lesson.titleSinhala.contains(searchQuery, ignoreCase = true) ||
          lesson.titleEnglish.contains(searchQuery, ignoreCase = true) ||
          lesson.keyConcepts.any { it.contains(searchQuery, ignoreCase = true) } ||
          lesson.fullSummary.contains(searchQuery, ignoreCase = true)
      matchesGrade && matchesSearch
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
        colors = CardDefaults.cardColors(containerColor = Color(0xFF881337).copy(alpha = 0.35f)),
        border = BorderStroke(1.dp, Color(0xFFE11D48))
      ) {
        Row(
          modifier = Modifier.padding(10.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text("🥗", fontSize = 20.sp)
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "O/L සෞඛ්‍ය විෂය නිර්දේශයේ සම්පූර්ණ ඒකක ${filteredLessons.size} ක සාරාංශ, මූලධර්ම සහ විභාග ඉඟි පහතින් අධ්‍යයනය කරන්න.",
            fontSize = 11.5.sp,
            color = Color(0xFFFFE4E6),
            lineHeight = 16.sp
          )
        }
      }
    }

    items(filteredLessons, key = { it.id }) { lesson ->
      val isExpanded = expandedLessonId == lesson.id
      Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2C0A16).copy(alpha = 0.8f)),
        border = BorderStroke(1.dp, if (isExpanded) Color(0xFFFB7185) else Color(0xFF5C0E24)),
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
              color = if (lesson.grade == "10") Color(0xFFE11D48) else Color(0xFF2563EB),
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
              tint = Color(0xFFFB7185)
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
            color = Color(0xFFFDA4AF).copy(alpha = 0.8f)
          )

          Spacer(modifier = Modifier.height(8.dp))
          Surface(
            color = Color(0xFF1E050E),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(0.8.dp, Color(0xFF881337))
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
                color = Color(0xFFFFD1DC),
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
              HorizontalDivider(color = Color(0xFF4C0519), thickness = 0.8.dp)
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
                  Text("•", color = Color(0xFFFB7185), fontWeight = FontWeight.Bold, fontSize = 13.sp)
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
                color = Color(0xFFF1F5F9),
                lineHeight = 17.sp
              )

              Spacer(modifier = Modifier.height(8.dp))
              Surface(
                color = Color(0xFF431407),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, Color(0xFFEA580C))
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
                      color = Color(0xFFFED7AA)
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
private fun HealthMcqSection(
  gradeTab: HealthGradeTab,
  searchQuery: String,
  userSelectedOptions: Map<Int, Int>,
  onSelectOption: (Int, Int) -> Unit,
  onResetQuiz: () -> Unit
) {
  val filteredMcqs = remember(gradeTab, searchQuery) {
    HealthMasterDataProvider.mcqQuestions.filter { q ->
      val matchesGrade = when (gradeTab) {
        HealthGradeTab.ALL -> true
        HealthGradeTab.GRADE_10 -> q.grade == "10"
        HealthGradeTab.GRADE_11 -> q.grade == "11"
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
      Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF31101E)),
        border = BorderStroke(1.dp, Color(0xFFBE123C))
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
                color = Color(0xFFFDA4AF)
              )
            }
            Button(
              onClick = onResetQuiz,
              colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE11D48)),
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
        colors = CardDefaults.cardColors(containerColor = Color(0xFF240713)),
        border = BorderStroke(
          1.dp,
          when {
            !hasAnswered -> Color(0xFF4C0519)
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
              color = Color(0xFFBE123C),
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
              !hasAnswered -> Color(0xFF380C1E)
              isCorrect -> Color(0xFF14532D)
              isThisSelected -> Color(0xFF7F1D1D)
              else -> Color(0xFF380C1E).copy(alpha = 0.5f)
            }

            val borderColor = when {
              !hasAnswered -> Color(0xFF5C0E24)
              isCorrect -> Color(0xFF4ADE80)
              isThisSelected -> Color(0xFFF87171)
              else -> Color(0xFF4C0519)
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

          if (hasAnswered) {
            Spacer(modifier = Modifier.height(8.dp))
            Surface(
              color = Color(0xFF064E3B).copy(alpha = 0.6f),
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
// 3. STRUCTURED ESSAY SECTION (WITH MARKING SCHEMES)
// ============================================================================
@Composable
private fun HealthStructuredEssaySection(
  gradeTab: HealthGradeTab,
  searchQuery: String,
  expandedEssayId: String?,
  onToggleExpand: (String) -> Unit
) {
  val filteredEssays = remember(gradeTab, searchQuery) {
    HealthMasterDataProvider.structuredEssays.filter { essay ->
      val matchesGrade = when (gradeTab) {
        HealthGradeTab.ALL -> true
        HealthGradeTab.GRADE_10 -> essay.grade == "10"
        HealthGradeTab.GRADE_11 -> essay.grade == "11"
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
        colors = CardDefaults.cardColors(containerColor = Color(0xFF0F766E).copy(alpha = 0.35f)),
        border = BorderStroke(1.dp, Color(0xFF14B8A6))
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
            color = Color(0xFFCCFBF1),
            lineHeight = 16.sp
          )
        }
      }
    }

    items(filteredEssays, key = { it.id }) { essay ->
      val isExpanded = expandedEssayId == essay.id
      Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2C0A16)),
        border = BorderStroke(1.dp, if (isExpanded) Color(0xFF2DD4BF) else Color(0xFF4C0519)),
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
              color = Color(0xFF0D9488),
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
              color = Color(0xFF1E050E),
              shape = RoundedCornerShape(4.dp)
            ) {
              Text(
                text = "ලකුණු 15",
                color = Color(0xFF5EEAD4),
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
              )
            }
            Spacer(modifier = Modifier.width(4.dp))
            Icon(
              imageVector = if (isExpanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
              contentDescription = null,
              tint = Color(0xFF2DD4BF)
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
            color = Color(0xFFE2E8F0),
            lineHeight = 16.sp
          )

          AnimatedVisibility(
            visible = isExpanded,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
          ) {
            Column(modifier = Modifier.padding(top = 10.dp)) {
              HorizontalDivider(color = Color(0xFF4C0519), thickness = 0.8.dp)
              Spacer(modifier = Modifier.height(8.dp))

              essay.subQuestions.forEach { sub ->
                Surface(
                  shape = RoundedCornerShape(8.dp),
                  color = Color(0xFF1A050E),
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
// 4. INTERACTIVE CALCULATORS SECTION (BMI & TOURNAMENT FORMULAS)
// ============================================================================
@Composable
private fun HealthCalculatorsSection() {
  var weightInput by remember { mutableStateOf("50") }
  var heightCmInput by remember { mutableStateOf("155") }

  var tournamentTeamsInput by remember { mutableStateOf("11") }

  LazyColumn(
    modifier = Modifier.fillMaxSize(),
    contentPadding = PaddingValues(12.dp),
    verticalArrangement = Arrangement.spacedBy(16.dp)
  ) {
    // 1. BMI CALCULATOR CARD
    item {
      Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2C0A16)),
        border = BorderStroke(1.2.dp, Color(0xFFE11D48)),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(14.dp)) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Text("⚖️", fontSize = 20.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Column {
              Text(
                text = "ශරීර ස්කන්ධ දර්ශක ගණකය (Live BMI Calculator)",
                fontSize = 14.5.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
              )
              Text(
                text = "සූත්‍රය: BMI = බර (kg) / [උස (m) × උස (m)]",
                fontSize = 11.sp,
                color = Color(0xFFFDA4AF)
              )
            }
          }

          Spacer(modifier = Modifier.height(12.dp))
          Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
          ) {
            OutlinedTextField(
              value = weightInput,
              onValueChange = { weightInput = it },
              label = { Text("බර (kg)", color = Color(0xFFFDA4AF), fontSize = 11.sp) },
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
              singleLine = true,
              shape = RoundedCornerShape(8.dp),
              colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFFB7185),
                unfocusedBorderColor = Color(0xFF881337),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
              ),
              modifier = Modifier.weight(1f)
            )

            OutlinedTextField(
              value = heightCmInput,
              onValueChange = { heightCmInput = it },
              label = { Text("උස (cm)", color = Color(0xFFFDA4AF), fontSize = 11.sp) },
              keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
              singleLine = true,
              shape = RoundedCornerShape(8.dp),
              colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFFB7185),
                unfocusedBorderColor = Color(0xFF881337),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
              ),
              modifier = Modifier.weight(1f)
            )
          }

          val weight = weightInput.toDoubleOrNull() ?: 0.0
          val heightCm = heightCmInput.toDoubleOrNull() ?: 0.0
          val heightM = heightCm / 100.0

          val bmiValue = if (heightM > 0 && weight > 0) {
            weight / (heightM * heightM)
          } else 0.0

          val (bmiCategory, categoryColor, adviceText) = when {
            bmiValue <= 0 -> Triple("අගයන් ඇතුළත් කරන්න", Color.Gray, "කරුණාකර නිවැරදි උස හා බර ඇතුළත් කරන්න.")
            bmiValue < 18.5 -> Triple("අඩු බර (Underweight)", Color(0xFF38BDF8), "ප්‍රෝටීන හා පෝෂ්‍ය පදාර්ථ බහුල සමබල ආහාර වේලක් ලබාගන්න.")
            bmiValue <= 24.9 -> Triple("නිරෝගී / ප්‍රශස්ත බර (Normal)", Color(0xFF4ADE80), "ඉතා යහපත්ය! දිනපතා ව්‍යායාම හා සමබල ආහාරය අඛණ්ඩව පවත්වා ගන්න.")
            bmiValue <= 29.9 -> Triple("අධිබර (Overweight)", Color(0xFFFBBF24), "සීනි, තෙල් ආහාර අඩු කර දිනපතා අවම විනාඩි 45ක් ක්‍රියාශීලී ව්‍යායාම කරන්න.")
            else -> Triple("ස්ථුලතාව (Obese)", Color(0xFFF87171), "වහාම වෛද්‍ය උපදෙස් ලබාගෙන බර පාලන ආහාර හා ව්‍යායාම සැලැස්මකට යොමුවන්න.")
          }

          Spacer(modifier = Modifier.height(12.dp))
          Surface(
            shape = RoundedCornerShape(10.dp),
            color = Color(0xFF1E050E),
            border = BorderStroke(1.dp, categoryColor.copy(alpha = 0.6f)),
            modifier = Modifier.fillMaxWidth()
          ) {
            Column(modifier = Modifier.padding(12.dp)) {
              Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
              ) {
                Text(
                  text = "ඔබගේ BMI අගය: ${String.format("%.1f", bmiValue)} kg/m²",
                  fontSize = 14.sp,
                  fontWeight = FontWeight.Bold,
                  color = Color.White
                )
                Surface(
                  color = categoryColor.copy(alpha = 0.2f),
                  border = BorderStroke(1.dp, categoryColor),
                  shape = RoundedCornerShape(6.dp)
                ) {
                  Text(
                    text = bmiCategory,
                    color = categoryColor,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                  )
                }
              }

              Spacer(modifier = Modifier.height(6.dp))
              Text(
                text = "📌 සෞඛ්‍ය උපදෙස: $adviceText",
                fontSize = 11.sp,
                color = Color(0xFFFFD1DC),
                lineHeight = 15.sp
              )
            }
          }
        }
      }
    }

    // 2. TOURNAMENTS FIXTURE CALCULATOR CARD
    item {
      Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1C1917)),
        border = BorderStroke(1.2.dp, Color(0xFFD97706)),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(14.dp)) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Text("🏆", fontSize = 20.sp)
            Spacer(modifier = Modifier.width(8.dp))
            Column {
              Text(
                text = "ක්‍රීඩා තරගාවලි සූත්‍ර ගණකය (Tournament Calculator)",
                fontSize = 14.5.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
              )
              Text(
                text = "Knockout තරග, Byes (වරප්‍රසාද) සහ League තරග ගණනය",
                fontSize = 11.sp,
                color = Color(0xFFFDE68A)
              )
            }
          }

          Spacer(modifier = Modifier.height(12.dp))
          OutlinedTextField(
            value = tournamentTeamsInput,
            onValueChange = { tournamentTeamsInput = it },
            label = { Text("කණ්ඩායම් සංඛ්‍යාව (N)", color = Color(0xFFFDE68A), fontSize = 11.sp) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            shape = RoundedCornerShape(8.dp),
            colors = OutlinedTextFieldDefaults.colors(
              focusedBorderColor = Color(0xFFF59E0B),
              unfocusedBorderColor = Color(0xFF78350F),
              focusedTextColor = Color.White,
              unfocusedTextColor = Color.White
            ),
            modifier = Modifier.fillMaxWidth()
          )

          val teams = tournamentTeamsInput.toIntOrNull() ?: 0

          val knockoutMatches = if (teams > 1) teams - 1 else 0

          // Calculate Byes: 2^n - N where 2^n >= N
          val nextPowerOf2 = if (teams > 0) {
            val power = ceil(log2(teams.toDouble())).toInt()
            2.0.pow(power.toDouble()).toInt()
          } else 0
          val byesCount = if (teams > 0) nextPowerOf2 - teams else 0
          val roundsCount = if (teams > 0) ceil(log2(teams.toDouble())).toInt() else 0

          val leagueMatches = if (teams > 1) (teams * (teams - 1)) / 2 else 0

          Spacer(modifier = Modifier.height(12.dp))
          Surface(
            shape = RoundedCornerShape(10.dp),
            color = Color(0xFF292524),
            border = BorderStroke(1.dp, Color(0xFFF59E0B)),
            modifier = Modifier.fillMaxWidth()
          ) {
            Column(modifier = Modifier.padding(12.dp)) {
              Text(
                text = "⚡ පිළිමලුන් පිටුදැකීමේ ක්‍රමය (Knockout System):",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFFBBF24)
              )
              Text(
                text = "• මුළු තරග ගණන = N - 1 = $teams - 1 = $knockoutMatches තරග",
                fontSize = 11.5.sp,
                color = Color.White
              )
              Text(
                text = "• Byes (වරප්‍රසාද) = 2ⁿ - N = $nextPowerOf2 - $teams = $byesCount Byes",
                fontSize = 11.5.sp,
                color = Color(0xFF86EFAC),
                fontWeight = FontWeight.SemiBold
              )
              Text(
                text = "• මුළු වට සංඛ්‍යාව (Rounds) = $roundsCount වට",
                fontSize = 11.sp,
                color = Color(0xFFE2E8F0)
              )

              Spacer(modifier = Modifier.height(8.dp))
              HorizontalDivider(color = Color(0xFF44403C), thickness = 0.8.dp)
              Spacer(modifier = Modifier.height(8.dp))

              Text(
                text = "⚽ ලීග් ක්‍රමය (League / Round-Robin System):",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF38BDF8)
              )
              Text(
                text = "• මුළු තරග ගණන = N(N - 1) / 2 = ($teams × ${teams - 1}) / 2 = $leagueMatches තරග",
                fontSize = 11.5.sp,
                color = Color.White
              )
            }
          }
        }
      }
    }
  }
}

// ============================================================================
// 5. FIRST AID SECTION
// ============================================================================
@Composable
private fun HealthFirstAidSection(
  searchQuery: String,
  expandedFirstAidId: String?,
  onToggleExpand: (String) -> Unit
) {
  val guides = remember(searchQuery) {
    HealthMasterDataProvider.firstAidGuides.filter { item ->
      searchQuery.isBlank() ||
          item.title.contains(searchQuery, ignoreCase = true) ||
          item.injuryType.contains(searchQuery, ignoreCase = true) ||
          item.immediateSteps.any { it.contains(searchQuery, ignoreCase = true) }
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
        colors = CardDefaults.cardColors(containerColor = Color(0xFF7F1D1D).copy(alpha = 0.4f)),
        border = BorderStroke(1.dp, Color(0xFFEF4444))
      ) {
        Row(
          modifier = Modifier.padding(10.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text("🩹", fontSize = 20.sp)
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "ක්‍රීඩා පිටියේ සහ එදිනෙදා ජීවිතයේ සිදුවන හදිසි අනතුරුවලදී අනුගමනය කළ යුතු විද්‍යාත්මක ප්‍රථමාධාර මාර්ගෝපදේශය.",
            fontSize = 11.5.sp,
            color = Color(0xFFFECACA),
            lineHeight = 16.sp
          )
        }
      }
    }

    items(guides, key = { it.id }) { guide ->
      val isExpanded = expandedFirstAidId == guide.id

      Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF2C0A16)),
        border = BorderStroke(1.dp, if (isExpanded) Color(0xFFF87171) else Color(0xFF4C0519)),
        modifier = Modifier
          .fillMaxWidth()
          .clickable { onToggleExpand(guide.id) }
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Surface(
              color = Color(0xFFDC2626),
              shape = RoundedCornerShape(4.dp)
            ) {
              Text(
                text = guide.injuryType,
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
              tint = Color(0xFFF87171)
            )
          }

          Spacer(modifier = Modifier.height(6.dp))
          Text(
            text = guide.title,
            fontSize = 13.5.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
          )

          Spacer(modifier = Modifier.height(6.dp))
          Text(
            text = "⚠️ ප්‍රධාන රෝග ලක්ෂණ:",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFDE047)
          )
          guide.symptoms.forEach { sym ->
            Row(modifier = Modifier.padding(vertical = 1.dp)) {
              Text("•", color = Color(0xFFFBBF24), fontSize = 11.sp)
              Spacer(modifier = Modifier.width(5.dp))
              Text(text = sym, fontSize = 11.sp, color = Color(0xFFE2E8F0))
            }
          }

          AnimatedVisibility(
            visible = isExpanded,
            enter = fadeIn() + expandVertically(),
            exit = fadeOut() + shrinkVertically()
          ) {
            Column(modifier = Modifier.padding(top = 10.dp)) {
              HorizontalDivider(color = Color(0xFF4C0519), thickness = 0.8.dp)
              Spacer(modifier = Modifier.height(8.dp))

              Text(
                text = "✅ ක්ෂණික ප්‍රථමාධාර පියවර:",
                fontSize = 11.5.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF4ADE80)
              )
              guide.immediateSteps.forEach { step ->
                Row(modifier = Modifier.padding(vertical = 2.dp)) {
                  Text("✓", color = Color(0xFF22C55E), fontSize = 11.sp, fontWeight = FontWeight.Bold)
                  Spacer(modifier = Modifier.width(6.dp))
                  Text(text = step, fontSize = 11.sp, color = Color.White, lineHeight = 15.sp)
                }
              }

              Spacer(modifier = Modifier.height(8.dp))
              Surface(
                color = Color(0xFF7F1D1D).copy(alpha = 0.3f),
                shape = RoundedCornerShape(6.dp),
                border = BorderStroke(0.8.dp, Color(0xFFEF4444))
              ) {
                Row(
                  modifier = Modifier.padding(6.dp),
                  verticalAlignment = Alignment.CenterVertically
                ) {
                  Text("🚫", fontSize = 12.sp)
                  Spacer(modifier = Modifier.width(5.dp))
                  Text(
                    text = guide.warningNote,
                    fontSize = 10.5.sp,
                    color = Color(0xFFFCA5A5),
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

// ============================================================================
// 6. LIFE SKILLS SECTION
// ============================================================================
@Composable
private fun HealthLifeSkillsSection(searchQuery: String) {
  val skills = remember(searchQuery) {
    HealthMasterDataProvider.lifeSkills.filter { s ->
      searchQuery.isBlank() ||
          s.skillNameSinhala.contains(searchQuery, ignoreCase = true) ||
          s.skillNameEnglish.contains(searchQuery, ignoreCase = true) ||
          s.description.contains(searchQuery, ignoreCase = true) ||
          s.practicalApplication.contains(searchQuery, ignoreCase = true)
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
          Text("🧠", fontSize = 20.sp)
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = "ලෝක සෞඛ්‍ය සංවිධානය (WHO) හඳුන්වා දුන් ජීවන කුසලතා 10 සහ එදිනෙදා ජීවිතයේදී ඒවා ප්‍රායෝගිකව යොදාගන්නා ආකාරය.",
            fontSize = 11.5.sp,
            color = Color(0xFFE9D5FF),
            lineHeight = 16.sp
          )
        }
      }
    }

    items(skills, key = { it.id }) { s ->
      Card(
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E102E)),
        border = BorderStroke(1.dp, Color(0xFF4C1D95)),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Surface(
              color = Color(0xFF7C3AED),
              shape = RoundedCornerShape(4.dp)
            ) {
              Text(
                text = s.category,
                color = Color.White,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
              )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = "${s.skillNameSinhala} (${s.skillNameEnglish})",
              fontSize = 13.5.sp,
              fontWeight = FontWeight.Bold,
              color = Color.White
            )
          }

          Spacer(modifier = Modifier.height(6.dp))
          Text(
            text = s.description,
            fontSize = 11.5.sp,
            color = Color(0xFFE2E8F0),
            lineHeight = 16.sp
          )

          Spacer(modifier = Modifier.height(8.dp))
          Surface(
            shape = RoundedCornerShape(8.dp),
            color = Color(0xFF2E1065),
            border = BorderStroke(0.8.dp, Color(0xFF6D28D9)),
            modifier = Modifier.fillMaxWidth()
          ) {
            Row(
              modifier = Modifier.padding(8.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text("🌟", fontSize = 13.sp)
              Spacer(modifier = Modifier.width(6.dp))
              Column {
                Text(
                  text = "ප්‍රායෝගික ජීවිතයට භාවිතය:",
                  fontSize = 10.5.sp,
                  fontWeight = FontWeight.Bold,
                  color = Color(0xFFD8B4FE)
                )
                Text(
                  text = s.practicalApplication,
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
