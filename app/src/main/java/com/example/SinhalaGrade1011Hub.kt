package com.example

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
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
 * 🌟 100% ACCURATE GRADE 10 & 11 SINHALA MASTER HUB
 * Strictly adheres to the Sri Lankan G.C.E. O/L Sinhala Language & Literature Syllabus.
 * Designed with compact, low-height collapsible sub-sections, full-screen dialog,
 * smooth scrolling up and down, grade toggles, and instant search.
 */
@Composable
fun SinhalaGrade10And11MasterHub(
  currentGrade: String = "10",
  modifier: Modifier = Modifier
) {
  var showFullScreenModal by remember { mutableStateOf(false) }
  var initialSelectedTab by remember { mutableIntStateOf(0) }
  val activeGrade = if (currentGrade.contains("11")) "11" else "10"

  // Compact Header Card on the Subject Detail Screen
  Card(
    shape = RoundedCornerShape(14.dp),
    colors = CardDefaults.cardColors(
      containerColor = Color(0xFF1E1B4B) // Deep Indigo
    ),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    modifier = modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(14.dp))
      .clickable {
        initialSelectedTab = 0
        showFullScreenModal = true
      }
      .testTag("sinhala_grade_10_11_master_hub_card")
  ) {
    Column(modifier = Modifier.padding(12.dp)) {
      // Top Title Bar
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f, fill = false)) {
          Box(
            modifier = Modifier
              .size(36.dp)
              .clip(CircleShape)
              .background(Brush.linearGradient(listOf(Color(0xFF2563EB), Color(0xFF4F46E5)))),
            contentAlignment = Alignment.Center
          ) {
            Text("✍️", fontSize = 18.sp)
          }
          Spacer(modifier = Modifier.width(10.dp))
          Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Text(
                text = "10 & 11 සිංහල විෂය නිර්දේශ Hub",
                color = Color.White,
                fontSize = 13.5.sp,
                fontWeight = FontWeight.Bold
              )
              Spacer(modifier = Modifier.width(6.dp))
              Surface(
                color = Color(0xFF2563EB),
                shape = RoundedCornerShape(4.dp)
              ) {
                Text(
                  text = "100% O/L",
                  color = Color.White,
                  fontSize = 8.5.sp,
                  fontWeight = FontWeight.ExtraBold,
                  modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                )
              }
            }
            Text(
              text = "ව්‍යාකරණ • 10/11 සාහිත්‍ය විචාර • සාරාංශ • පිරුළු • MCQs",
              color = Color(0xFFC7D2FE),
              fontSize = 10.5.sp,
              maxLines = 1,
              overflow = TextOverflow.Ellipsis
            )
          }
        }

        IconButton(
          onClick = {
            initialSelectedTab = 0
            showFullScreenModal = true
          },
          modifier = Modifier.size(32.dp)
        ) {
          Icon(
            imageVector = Icons.Default.Fullscreen,
            contentDescription = "Full Screen",
            tint = Color(0xFF93C5FD)
          )
        }
      }

      Spacer(modifier = Modifier.height(8.dp))

      // Compact Chips Scroll Row
      LazyRow(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        itemsIndexed(SinhalaSectionType.values()) { index, section ->
          Surface(
            color = section.badgeColor.copy(alpha = 0.2f),
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(0.8.dp, section.badgeColor.copy(alpha = 0.6f)),
            modifier = Modifier
              .clickable {
                initialSelectedTab = index
                showFullScreenModal = true
              }
              .height(28.dp)
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              modifier = Modifier.padding(horizontal = 8.dp)
            ) {
              Text(section.icon, fontSize = 11.sp)
              Spacer(modifier = Modifier.width(4.dp))
              Text(
                text = section.titleSinhala,
                color = Color.White,
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold
              )
            }
          }
        }
      }
    }
  }

  // Full Screen Modal Dialog
  if (showFullScreenModal) {
    SinhalaGrade10And11MasterHubDialog(
      initialGrade = activeGrade,
      initialTab = initialSelectedTab,
      onDismiss = { showFullScreenModal = false }
    )
  }
}

/**
 * 🌟 FULL SCREEN DEDICATED DIALOG FOR SINHALA 10 & 11 SYLLABUS MASTER HUB
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SinhalaGrade10And11MasterHubDialog(
  initialGrade: String,
  initialTab: Int = 0,
  onDismiss: () -> Unit
) {
  var activeGrade by remember { mutableStateOf(initialGrade) }
  var selectedTabIndex by remember { mutableIntStateOf(initialTab) }
  var searchQuery by remember { mutableStateOf("") }

  BackHandler {
    onDismiss()
  }

  Dialog(
    onDismissRequest = onDismiss,
    properties = DialogProperties(
      usePlatformDefaultWidth = false,
      decorFitsSystemWindows = false,
      securePolicy = SecureFlagPolicy.SecureOff
    )
  ) {
    Surface(
      modifier = Modifier
        .fillMaxSize()
        .testTag("sinhala_10_11_fullscreen_dialog"),
      color = Color(0xFFF8FAFC) // Crisp light background
    ) {
      Column(modifier = Modifier.fillMaxSize()) {
        // TOP APP BAR
        Surface(
          color = Color(0xFF581C87), // Royal Purple
          tonalElevation = 4.dp,
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp)) {
            Row(
              modifier = Modifier.fillMaxWidth(),
              horizontalArrangement = Arrangement.SpaceBetween,
              verticalAlignment = Alignment.CenterVertically
            ) {
              Row(verticalAlignment = Alignment.CenterVertically) {
                IconButton(
                  onClick = onDismiss,
                  modifier = Modifier.size(36.dp)
                ) {
                  Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Close",
                    tint = Color.White
                  )
                }
                Spacer(modifier = Modifier.width(6.dp))
                Column {
                  Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                      text = "10 & 11 සිංහල සම්පූර්ණ Hub",
                      color = Color.White,
                      fontSize = 15.sp,
                      fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Surface(
                      color = Color(0xFF2563EB),
                      shape = RoundedCornerShape(4.dp)
                    ) {
                      Text(
                        text = "$activeGrade ශ්‍රේණිය",
                        color = Color.White,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                      )
                    }
                  }
                  Text(
                    text = "G.C.E. O/L විභාග නිර්දේශය 100% ආවරණය",
                    color = Color(0xFF94A3B8),
                    fontSize = 10.5.sp
                  )
                }
              }

              // Grade Switcher Pills (10 ශ්‍රේණිය vs 11 ශ්‍රේණිය)
              Row(
                modifier = Modifier
                  .clip(RoundedCornerShape(8.dp))
                  .background(Color(0xFF334155))
                  .padding(2.dp)
              ) {
                listOf("10", "11").forEach { g ->
                  val isSelected = activeGrade == g
                  Box(
                    modifier = Modifier
                      .clip(RoundedCornerShape(6.dp))
                      .background(if (isSelected) Color(0xFF2563EB) else Color.Transparent)
                      .clickable { activeGrade = g }
                      .padding(horizontal = 8.dp, vertical = 4.dp),
                    contentAlignment = Alignment.Center
                  ) {
                    Text(
                      text = "Grade $g",
                      color = if (isSelected) Color.White else Color(0xFFCBD5E1),
                      fontSize = 11.sp,
                      fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                    )
                  }
                }
              }
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Search Bar
            OutlinedTextField(
              value = searchQuery,
              onValueChange = { searchQuery = it },
              placeholder = {
                Text(
                  "ව්‍යාකරණ, සාහිත්‍ය පාඩම්, සාරාංශ, පිරුළු සොයන්න...",
                  fontSize = 11.5.sp,
                  color = Color(0xFF64748B)
                )
              },
              leadingIcon = {
                Icon(
                  Icons.Default.Search,
                  contentDescription = "Search",
                  tint = Color(0xFF94A3B8),
                  modifier = Modifier.size(18.dp)
                )
              },
              trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                  IconButton(
                    onClick = { searchQuery = "" },
                    modifier = Modifier.size(24.dp)
                  ) {
                    Icon(
                      Icons.Default.Clear,
                      contentDescription = "Clear",
                      tint = Color(0xFF94A3B8),
                      modifier = Modifier.size(16.dp)
                    )
                  }
                }
              },
              singleLine = true,
              shape = RoundedCornerShape(10.dp),
              colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = Color(0xFF0F172A),
                unfocusedContainerColor = Color(0xFF0F172A),
                focusedBorderColor = Color(0xFF38BDF8),
                unfocusedBorderColor = Color(0xFF334155),
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White
              ),
              modifier = Modifier
                .fillMaxWidth()
                .height(44.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Horizontally Scrollable Tabs with Minimal Height & Bright Badges
            ScrollableTabRow(
              selectedTabIndex = selectedTabIndex,
              edgePadding = 0.dp,
              containerColor = Color.Transparent,
              divider = {},
              indicator = {},
              modifier = Modifier.fillMaxWidth()
            ) {
              SinhalaSectionType.values().forEachIndexed { index, section ->
                val isSelected = selectedTabIndex == index
                Surface(
                  color = if (isSelected) section.badgeColor else Color(0xFF1E293B),
                  shape = RoundedCornerShape(8.dp),
                  border = BorderStroke(
                    1.dp,
                    if (isSelected) section.badgeColor else Color(0xFF334155)
                  ),
                  modifier = Modifier
                    .padding(end = 6.dp)
                    .clickable { selectedTabIndex = index }
                ) {
                  Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                  ) {
                    Text(section.icon, fontSize = 12.sp)
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                      text = section.titleSinhala,
                      fontSize = 11.sp,
                      fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                      color = if (isSelected) Color.White else Color(0xFFCBD5E1)
                    )
                  }
                }
              }
            }
          }
        }

        // CONTENT SECTION: Vertically scrollable with compact collapsible cards
        LazyColumn(
          modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp, vertical = 8.dp),
          verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          when (selectedTabIndex) {
            0 -> {
              // ✍️ GRAMMAR & SPELLING RULES
              val items = SinhalaMasterRepository.grammarRulesList.filter {
                (it.grade.contains(activeGrade) || it.grade == "10,11") &&
                    (searchQuery.isBlank() ||
                        it.ruleTitle.contains(searchQuery, true) ||
                        it.ruleCategory.contains(searchQuery, true) ||
                        it.corePrinciple.contains(searchQuery, true))
              }
              if (items.isEmpty()) {
                item { SinhalaEmptySearchResultNotice() }
              } else {
                items(items, key = { it.id }) { rule ->
                  SinhalaGrammarRuleCard(rule)
                }
              }
            }

            1 -> {
              // 📖 GRADE 11 LITERATURE CRITIQUES
              val items = SinhalaMasterRepository.grade11LiteratureList.filter {
                searchQuery.isBlank() ||
                    it.title.contains(searchQuery, true) ||
                    it.author.contains(searchQuery, true) ||
                    it.sourceBook.contains(searchQuery, true)
              }
              if (items.isEmpty()) {
                item { SinhalaEmptySearchResultNotice() }
              } else {
                items(items, key = { it.id }) { lesson ->
                  SinhalaLiteratureLessonCard(lesson)
                }
              }
            }

            2 -> {
              // 📜 GRADE 10 LITERATURE CRITIQUES
              val items = SinhalaMasterRepository.grade10LiteratureList.filter {
                searchQuery.isBlank() ||
                    it.title.contains(searchQuery, true) ||
                    it.author.contains(searchQuery, true) ||
                    it.sourceBook.contains(searchQuery, true)
              }
              if (items.isEmpty()) {
                item { SinhalaEmptySearchResultNotice() }
              } else {
                items(items, key = { it.id }) { lesson ->
                  SinhalaLiteratureLessonCard(lesson)
                }
              }
            }

            3 -> {
              // 📋 WRITING SKILLS & FORMATS
              val items = SinhalaMasterRepository.writingSkillsList.filter {
                searchQuery.isBlank() ||
                    it.title.contains(searchQuery, true) ||
                    it.category.contains(searchQuery, true)
              }
              if (items.isEmpty()) {
                item { SinhalaEmptySearchResultNotice() }
              } else {
                items(items, key = { it.id }) { item ->
                  SinhalaWritingSkillCard(item)
                }
              }
            }

            4 -> {
              // 💡 PROVERBS, IDIOMS & VOCABULARY
              val items = SinhalaMasterRepository.vocabList.filter {
                searchQuery.isBlank() ||
                    it.phrase.contains(searchQuery, true) ||
                    it.contextualMeaning.contains(searchQuery, true) ||
                    it.category.contains(searchQuery, true)
              }
              if (items.isEmpty()) {
                item { SinhalaEmptySearchResultNotice() }
              } else {
                items(items, key = { it.id }) { item ->
                  SinhalaVocabItemCard(item)
                }
              }
            }

            5 -> {
              // ⚡ RAPID MCQS
              val items = SinhalaMasterRepository.rapidMcqList.filter {
                searchQuery.isBlank() ||
                    it.question.contains(searchQuery, true) ||
                    it.topic.contains(searchQuery, true)
              }
              if (items.isEmpty()) {
                item { SinhalaEmptySearchResultNotice() }
              } else {
                items(items, key = { it.id }) { mcq ->
                  SinhalaRapidMcqCard(mcq)
                }
              }
            }

            6 -> {
              // 🎯 MARKING SECRETS & TIPS
              items(SinhalaMasterRepository.examSecretsList) { pair ->
                SinhalaExamSecretCard(title = pair.first, details = pair.second)
              }
            }
          }
        }
      }
    }
  }
}

// -------------------------------------------------------------
// COMPACT COLLAPSIBLE CARD COMPONENTS (MINIMAL HEIGHT BY DEFAULT)
// -------------------------------------------------------------

@Composable
fun SinhalaGrammarRuleCard(rule: SinhalaGrammarRule) {
  var expanded by remember { mutableStateOf(false) }

  Card(
    shape = RoundedCornerShape(10.dp),
    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
    border = BorderStroke(1.dp, Color(0xFF334155)),
    modifier = Modifier
      .fillMaxWidth()
      .animateContentSize(animationSpec = tween(200))
  ) {
    Column(modifier = Modifier.padding(10.dp)) {
      // Compact Header with minimal height (~40dp)
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .clickable { expanded = !expanded },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.weight(1f, fill = false)
        ) {
          Surface(
            color = Color(0xFF2563EB).copy(alpha = 0.25f),
            shape = RoundedCornerShape(6.dp)
          ) {
            Text(
              text = rule.ruleCategory,
              color = Color(0xFF60A5FA),
              fontSize = 9.5.sp,
              fontWeight = FontWeight.Bold,
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            )
          }
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = rule.ruleTitle,
            color = Color.White,
            fontSize = 12.5.sp,
            fontWeight = FontWeight.Bold,
            maxLines = if (expanded) Int.MAX_VALUE else 1,
            overflow = TextOverflow.Ellipsis
          )
        }

        IconButton(
          onClick = { expanded = !expanded },
          modifier = Modifier.size(28.dp)
        ) {
          Icon(
            imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
            contentDescription = "Toggle",
            tint = Color(0xFF94A3B8),
            modifier = Modifier.size(18.dp)
          )
        }
      }

      if (!expanded) {
        // Quick summary line when collapsed
        Text(
          text = rule.corePrinciple,
          color = Color(0xFF94A3B8),
          fontSize = 11.sp,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis,
          modifier = Modifier.padding(top = 2.dp)
        )
      } else {
        // Expanded Details
        Spacer(modifier = Modifier.height(8.dp))
        Divider(color = Color(0xFF334155), thickness = 0.8.dp)
        Spacer(modifier = Modifier.height(8.dp))

        // Core Principle
        Surface(
          color = Color(0xFF0F172A),
          shape = RoundedCornerShape(8.dp),
          modifier = Modifier.fillMaxWidth()
        ) {
          Text(
            text = "📌 මූලික නීතිය: ${rule.corePrinciple}",
            color = Color(0xFFBAE6FD),
            fontSize = 11.5.sp,
            lineHeight = 16.sp,
            modifier = Modifier.padding(8.dp)
          )
        }

        Spacer(modifier = Modifier.height(6.dp))

        // Rule Points
        rule.rulePoints.forEach { point ->
          Row(modifier = Modifier.padding(vertical = 2.dp)) {
            Text("•", color = Color(0xFF38BDF8), fontSize = 12.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(6.dp))
            Text(point, color = Color(0xFFE2E8F0), fontSize = 11.5.sp, lineHeight = 16.sp)
          }
        }

        Spacer(modifier = Modifier.height(6.dp))

        // Correct Examples Chips
        Text("නිවැරදි උදාහරණ:", color = Color(0xFF34D399), fontSize = 11.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Row(
          horizontalArrangement = Arrangement.spacedBy(4.dp),
          modifier = Modifier.fillMaxWidth()
        ) {
          rule.correctExamples.take(4).forEach { ex ->
            Surface(
              color = Color(0xFF065F46).copy(alpha = 0.5f),
              shape = RoundedCornerShape(4.dp)
            ) {
              Text(
                text = "✓ $ex",
                color = Color(0xFFA7F3D0),
                fontSize = 10.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
              )
            }
          }
        }

        if (rule.commonMistakes.isNotEmpty()) {
          Spacer(modifier = Modifier.height(8.dp))
          Text("නිතර සිදුවන වැරදි සහ නිවැරදි කිරීම්:", color = Color(0xFFF87171), fontSize = 11.sp, fontWeight = FontWeight.Bold)
          Spacer(modifier = Modifier.height(4.dp))
          rule.commonMistakes.forEach { pair ->
            Row(
              modifier = Modifier.padding(vertical = 1.dp),
              verticalAlignment = Alignment.CenterVertically
            ) {
              Text("✗ ${pair.first}", color = Color(0xFFFCA5A5), fontSize = 10.5.sp)
              Spacer(modifier = Modifier.width(6.dp))
              Text("➔", color = Color(0xFF94A3B8), fontSize = 10.sp)
              Spacer(modifier = Modifier.width(6.dp))
              Text("✓ ${pair.second}", color = Color(0xFF86EFAC), fontSize = 10.5.sp, fontWeight = FontWeight.Bold)
            }
          }
        }

        Spacer(modifier = Modifier.height(6.dp))
        // Exam Tip
        Surface(
          color = Color(0xFF451A03),
          shape = RoundedCornerShape(6.dp),
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            modifier = Modifier.padding(6.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text("💡 O/L Tip: ", color = Color(0xFFFDE047), fontSize = 10.5.sp, fontWeight = FontWeight.Bold)
            Text(rule.examTip, color = Color(0xFFFEF08A), fontSize = 10.5.sp)
          }
        }
      }
    }
  }
}

@Composable
fun SinhalaLiteratureLessonCard(lesson: SinhalaLiteratureLesson) {
  var expanded by remember { mutableStateOf(false) }

  Card(
    shape = RoundedCornerShape(10.dp),
    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
    border = BorderStroke(1.dp, Color(0xFF475569)),
    modifier = Modifier
      .fillMaxWidth()
      .animateContentSize(animationSpec = tween(200))
  ) {
    Column(modifier = Modifier.padding(10.dp)) {
      // Header Bar (Compact)
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .clickable { expanded = !expanded },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.weight(1f, fill = false)
        ) {
          Surface(
            color = when (lesson.lessonType) {
              "පද්‍ය" -> Color(0xFFDC2626)
              "ගද්‍ය" -> Color(0xFF2563EB)
              else -> Color(0xFF7C3AED)
            }.copy(alpha = 0.25f),
            shape = RoundedCornerShape(6.dp)
          ) {
            Text(
              text = "${lesson.lessonType} • ${lesson.grade} ශ්‍රේණිය",
              color = Color.White,
              fontSize = 9.sp,
              fontWeight = FontWeight.Bold,
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            )
          }
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = lesson.title,
            color = Color.White,
            fontSize = 12.5.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
          )
        }

        IconButton(
          onClick = { expanded = !expanded },
          modifier = Modifier.size(28.dp)
        ) {
          Icon(
            imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
            contentDescription = "Toggle",
            tint = Color(0xFF94A3B8),
            modifier = Modifier.size(18.dp)
          )
        }
      }

      if (!expanded) {
        Text(
          text = "${lesson.sourceBook} • ${lesson.author}",
          color = Color(0xFF94A3B8),
          fontSize = 11.sp,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis,
          modifier = Modifier.padding(top = 2.dp)
        )
      } else {
        Spacer(modifier = Modifier.height(8.dp))
        Divider(color = Color(0xFF334155), thickness = 0.8.dp)
        Spacer(modifier = Modifier.height(8.dp))

        // Source Book, Author, Era
        Surface(
          color = Color(0xFF0F172A),
          shape = RoundedCornerShape(6.dp),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(modifier = Modifier.padding(8.dp)) {
            Text("📚 කෘතිය: ${lesson.sourceBook}", color = Color(0xFFE2E8F0), fontSize = 11.sp, fontWeight = FontWeight.Bold)
            Text("✍️ කතුවරයා: ${lesson.author}", color = Color(0xFFCBD5E1), fontSize = 11.sp)
            Text("🏛️ යුගය: ${lesson.historicalEra}", color = Color(0xFF94A3B8), fontSize = 10.5.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text("🎯 ප්‍රධාන තේමාව: ${lesson.mainTheme}", color = Color(0xFF67E8F9), fontSize = 11.sp, lineHeight = 15.sp)
          }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Appreciation Points
        Text("විචාරාත්මක අගය කිරීම් (Points for Essays):", color = Color(0xFFFBBF24), fontSize = 11.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        lesson.literaryAppreciationPoints.forEach { pt ->
          Row(modifier = Modifier.padding(vertical = 2.dp)) {
            Text("▪", color = Color(0xFFF59E0B), fontSize = 11.sp)
            Spacer(modifier = Modifier.width(6.dp))
            Text(pt, color = Color(0xFFF1F5F9), fontSize = 11.5.sp, lineHeight = 16.sp)
          }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Key Quotations
        Text("අනිවාර්ය සාහිත්‍ය උපුටන & රස විවරණය:", color = Color(0xFF34D399), fontSize = 11.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        lesson.keyQuotationsWithMeaning.forEach { pair ->
          Surface(
            color = Color(0xFF064E3B).copy(alpha = 0.4f),
            shape = RoundedCornerShape(6.dp),
            border = BorderStroke(0.6.dp, Color(0xFF059669)),
            modifier = Modifier
              .fillMaxWidth()
              .padding(vertical = 3.dp)
          ) {
            Column(modifier = Modifier.padding(8.dp)) {
              Text(pair.first, color = Color(0xFFA7F3D0), fontSize = 11.sp, fontWeight = FontWeight.Bold)
              Spacer(modifier = Modifier.height(3.dp))
              Text("➔ ${pair.second}", color = Color(0xFFD1FAE5), fontSize = 10.5.sp, lineHeight = 15.sp)
            }
          }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Essay Outline
        Surface(
          color = Color(0xFF312E81).copy(alpha = 0.35f),
          shape = RoundedCornerShape(6.dp),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(modifier = Modifier.padding(8.dp)) {
            Text("📝 විභාග ආදර්ශ විචාර සැකිල්ල (10/10 Outline):", color = Color(0xFFA5B4FC), fontSize = 11.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(lesson.modelEssayOutline, color = Color(0xFFE0E7FF), fontSize = 10.5.sp, lineHeight = 15.sp)
          }
        }
      }
    }
  }
}

@Composable
fun SinhalaWritingSkillCard(item: SinhalaWritingSkillItem) {
  var expanded by remember { mutableStateOf(false) }

  Card(
    shape = RoundedCornerShape(10.dp),
    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
    border = BorderStroke(1.dp, Color(0xFF0D9488).copy(alpha = 0.5f)),
    modifier = Modifier
      .fillMaxWidth()
      .animateContentSize(animationSpec = tween(200))
  ) {
    Column(modifier = Modifier.padding(10.dp)) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .clickable { expanded = !expanded },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.weight(1f, fill = false)
        ) {
          Surface(
            color = Color(0xFF0D9488).copy(alpha = 0.25f),
            shape = RoundedCornerShape(6.dp)
          ) {
            Text(
              text = item.category,
              color = Color(0xFF2DD4BF),
              fontSize = 9.5.sp,
              fontWeight = FontWeight.Bold,
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            )
          }
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = item.title,
            color = Color.White,
            fontSize = 12.5.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
          )
        }

        IconButton(
          onClick = { expanded = !expanded },
          modifier = Modifier.size(28.dp)
        ) {
          Icon(
            imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
            contentDescription = "Toggle",
            tint = Color(0xFF94A3B8),
            modifier = Modifier.size(18.dp)
          )
        }
      }

      if (!expanded) {
        Text(
          text = "ලකුණු දීමේ නිර්ණායක සහ ආදර්ශ පිළිතුර සහිතයි",
          color = Color(0xFF94A3B8),
          fontSize = 11.sp,
          modifier = Modifier.padding(top = 2.dp)
        )
      } else {
        Spacer(modifier = Modifier.height(8.dp))
        Divider(color = Color(0xFF334155), thickness = 0.8.dp)
        Spacer(modifier = Modifier.height(8.dp))

        // Standard Format Steps
        Text("සම්මත ආකෘතිය හා පියවර:", color = Color(0xFF2DD4BF), fontSize = 11.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        item.standardFormat.forEach { step ->
          Row(modifier = Modifier.padding(vertical = 1.5.dp)) {
            Text("✓", color = Color(0xFF14B8A6), fontSize = 11.sp)
            Spacer(modifier = Modifier.width(6.dp))
            Text(step, color = Color(0xFFE2E8F0), fontSize = 11.sp, lineHeight = 15.sp)
          }
        }

        Spacer(modifier = Modifier.height(6.dp))

        // Marking Criteria
        Text("ලකුණු බෙදී යන ආකාරය:", color = Color(0xFFFBBF24), fontSize = 11.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        item.markingCriteriaMarks.forEach { (criterion, mark) ->
          Row(
            modifier = Modifier.fillMaxWidth().padding(vertical = 1.dp),
            horizontalArrangement = Arrangement.SpaceBetween
          ) {
            Text("• $criterion", color = Color(0xFFCBD5E1), fontSize = 10.5.sp)
            Text("ලකුණු $mark", color = Color(0xFFFDE047), fontSize = 10.5.sp, fontWeight = FontWeight.Bold)
          }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Sample Prompt & Solution
        Surface(
          color = Color(0xFF0F172A),
          shape = RoundedCornerShape(6.dp),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(modifier = Modifier.padding(8.dp)) {
            Text("ආදර්ශ ප්‍රශ්නය:", color = Color(0xFF94A3B8), fontSize = 10.5.sp, fontWeight = FontWeight.Bold)
            Text(item.samplePrompt, color = Color.White, fontSize = 11.sp, fontStyle = androidx.compose.ui.text.font.FontStyle.Italic)
            Spacer(modifier = Modifier.height(6.dp))
            Text("ආදර්ශ නිවැරදි විසඳුම:", color = Color(0xFF34D399), fontSize = 10.5.sp, fontWeight = FontWeight.Bold)
            Text(item.sampleSolution, color = Color(0xFFD1FAE5), fontSize = 10.5.sp, lineHeight = 15.sp)
          }
        }
      }
    }
  }
}

@Composable
fun SinhalaVocabItemCard(item: SinhalaVocabItem) {
  var expanded by remember { mutableStateOf(false) }

  Card(
    shape = RoundedCornerShape(10.dp),
    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
    border = BorderStroke(1.dp, Color(0xFFEA580C).copy(alpha = 0.35f)),
    modifier = Modifier
      .fillMaxWidth()
      .animateContentSize(animationSpec = tween(200))
  ) {
    Column(modifier = Modifier.padding(10.dp)) {
      Row(
        modifier = Modifier
          .fillMaxWidth()
          .clickable { expanded = !expanded },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = Modifier.weight(1f, fill = false)
        ) {
          Surface(
            color = Color(0xFFEA580C).copy(alpha = 0.25f),
            shape = RoundedCornerShape(6.dp)
          ) {
            Text(
              text = item.category,
              color = Color(0xFFFB923C),
              fontSize = 9.sp,
              fontWeight = FontWeight.Bold,
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            )
          }
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = item.phrase,
            color = Color.White,
            fontSize = 12.5.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
          )
        }

        IconButton(
          onClick = { expanded = !expanded },
          modifier = Modifier.size(28.dp)
        ) {
          Icon(
            imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
            contentDescription = "Toggle",
            tint = Color(0xFF94A3B8),
            modifier = Modifier.size(18.dp)
          )
        }
      }

      if (!expanded) {
        Text(
          text = "අර්ථය: ${item.contextualMeaning}",
          color = Color(0xFFCBD5E1),
          fontSize = 11.sp,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis,
          modifier = Modifier.padding(top = 2.dp)
        )
      } else {
        Spacer(modifier = Modifier.height(6.dp))
        Divider(color = Color(0xFF334155), thickness = 0.8.dp)
        Spacer(modifier = Modifier.height(6.dp))

        Text("සැබෑ ගම්‍යමාන අර්ථය:", color = Color(0xFFF97316), fontSize = 10.5.sp, fontWeight = FontWeight.Bold)
        Text(item.contextualMeaning, color = Color(0xFFFFEDD5), fontSize = 11.5.sp, fontWeight = FontWeight.SemiBold)

        Spacer(modifier = Modifier.height(4.dp))
        Text("වාච්‍යාර්ථය: ${item.literalMeaning}", color = Color(0xFF94A3B8), fontSize = 10.5.sp)

        Spacer(modifier = Modifier.height(6.dp))
        Surface(
          color = Color(0xFF0F172A),
          shape = RoundedCornerShape(6.dp),
          modifier = Modifier.fillMaxWidth()
        ) {
          Text(
            text = "උදාහරණ වාක්‍යය: \"${item.exampleSentence}\"",
            color = Color(0xFFBAE6FD),
            fontSize = 11.sp,
            modifier = Modifier.padding(6.dp)
          )
        }
      }
    }
  }
}

@Composable
fun SinhalaRapidMcqCard(mcq: SinhalaRapidMcq) {
  var selectedIndex by remember { mutableStateOf<Int?>(null) }
  val isAnswered = selectedIndex != null

  Card(
    shape = RoundedCornerShape(10.dp),
    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
    border = BorderStroke(1.dp, Color(0xFF4F46E5).copy(alpha = 0.4f)),
    modifier = Modifier.fillMaxWidth()
  ) {
    Column(modifier = Modifier.padding(10.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Surface(
          color = Color(0xFF4F46E5).copy(alpha = 0.25f),
          shape = RoundedCornerShape(6.dp)
        ) {
          Text(
            text = mcq.topic,
            color = Color(0xFFA5B4FC),
            fontSize = 9.5.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
          )
        }
        if (isAnswered) {
          val isCorrect = selectedIndex == mcq.correctIndex
          Text(
            text = if (isCorrect) "✓ නිවැරදියි!" else "✗ වැරදියි",
            color = if (isCorrect) Color(0xFF4ADE80) else Color(0xFFF87171),
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold
          )
        }
      }

      Spacer(modifier = Modifier.height(6.dp))

      Text(
        text = mcq.question,
        color = Color.White,
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
        lineHeight = 16.sp
      )

      Spacer(modifier = Modifier.height(8.dp))

      // Options
      mcq.options.forEachIndexed { idx, opt ->
        val isThisSelected = selectedIndex == idx
        val isThisCorrect = idx == mcq.correctIndex

        val optionBg = when {
          !isAnswered -> Color(0xFF0F172A)
          isThisCorrect -> Color(0xFF065F46)
          isThisSelected -> Color(0xFF7F1D1D)
          else -> Color(0xFF0F172A)
        }

        val borderColor = when {
          !isAnswered -> Color(0xFF334155)
          isThisCorrect -> Color(0xFF10B981)
          isThisSelected -> Color(0xFFEF4444)
          else -> Color(0xFF1E293B)
        }

        Surface(
          color = optionBg,
          shape = RoundedCornerShape(6.dp),
          border = BorderStroke(0.8.dp, borderColor),
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.5.dp)
            .clickable(enabled = !isAnswered) {
              selectedIndex = idx
            }
        ) {
          Row(
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = "(${idx + 1})",
              color = Color(0xFF94A3B8),
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = opt,
              color = Color.White,
              fontSize = 11.sp
            )
          }
        }
      }

      if (isAnswered) {
        Spacer(modifier = Modifier.height(6.dp))
        Surface(
          color = Color(0xFF1E1B4B),
          shape = RoundedCornerShape(6.dp),
          modifier = Modifier.fillMaxWidth()
        ) {
          Text(
            text = "💡 විවරණය: ${mcq.explanation}",
            color = Color(0xFFC7D2FE),
            fontSize = 10.5.sp,
            lineHeight = 15.sp,
            modifier = Modifier.padding(6.dp)
          )
        }
      }
    }
  }
}

@Composable
fun SinhalaExamSecretCard(title: String, details: String) {
  Card(
    shape = RoundedCornerShape(10.dp),
    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
    border = BorderStroke(1.dp, Color(0xFFE11D48).copy(alpha = 0.4f)),
    modifier = Modifier.fillMaxWidth()
  ) {
    Column(modifier = Modifier.padding(10.dp)) {
      Row(verticalAlignment = Alignment.CenterVertically) {
        Text("🎯", fontSize = 14.sp)
        Spacer(modifier = Modifier.width(6.dp))
        Text(
          text = title,
          color = Color(0xFFFDA4AF),
          fontSize = 12.sp,
          fontWeight = FontWeight.Bold
        )
      }
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = details,
        color = Color(0xFFF1F5F9),
        fontSize = 11.sp,
        lineHeight = 16.sp
      )
    }
  }
}

@Composable
fun SinhalaEmptySearchResultNotice() {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .padding(32.dp),
    contentAlignment = Alignment.Center
  ) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
      Text("🔍", fontSize = 28.sp)
      Spacer(modifier = Modifier.height(6.dp))
      Text(
        text = "සෙවුමට ගැළපෙන අංග හමු නොවීය",
        color = Color(0xFF94A3B8),
        fontSize = 12.5.sp
      )
    }
  }
}
