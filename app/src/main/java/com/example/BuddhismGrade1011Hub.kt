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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.SecureFlagPolicy

/**
 * 🌟 100% ACCURATE GRADE 10 & 11 BUDDHISM MASTER HUB
 * Strictly adheres to the Sri Lankan G.C.E. O/L Buddhism Syllabus.
 * Designed with compact, low-height collapsible sub-sections, full-screen dialog,
 * smooth scrolling up and down, grade toggles, and instant search.
 */
@Composable
fun BuddhismGrade10And11MasterHub(
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
      containerColor = Color(0xFF451A03) // Deep Amber / Brown
    ),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    modifier = modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(14.dp))
      .clickable {
        initialSelectedTab = 0
        showFullScreenModal = true
      }
      .testTag("buddhism_grade_10_11_master_hub_card")
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
              .background(Brush.linearGradient(listOf(Color(0xFFF59E0B), Color(0xFFD97706)))),
            contentAlignment = Alignment.Center
          ) {
            Text("☸️", fontSize = 18.sp)
          }
          Spacer(modifier = Modifier.width(10.dp))
          Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Text(
                text = "10 & 11 බුද්ධ ධර්මය විෂය නිර්දේශ Hub",
                color = Color.White,
                fontSize = 13.5.sp,
                fontWeight = FontWeight.Bold
              )
              Spacer(modifier = Modifier.width(6.dp))
              Surface(
                color = Color(0xFFF59E0B),
                shape = RoundedCornerShape(4.dp)
              ) {
                Text(
                  text = "100% O/L",
                  color = Color.Black,
                  fontSize = 8.5.sp,
                  fontWeight = FontWeight.ExtraBold,
                  modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                )
              }
            }
            Text(
              text = "සූත්‍ර • ශාසන ඉතිහාසය • සිව්සස් • ශ්‍රාවක චරිත • ගාථා • MCQs",
              color = Color(0xFFFED7AA),
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
            tint = Color(0xFFFDE68A)
          )
        }
      }

      Spacer(modifier = Modifier.height(8.dp))

      // Compact Chips Scroll Row
      LazyRow(
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        itemsIndexed(BuddhismSectionType.values()) { index, section ->
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
    BuddhismGrade10And11MasterHubDialog(
      initialGrade = activeGrade,
      initialTab = initialSelectedTab,
      onDismiss = { showFullScreenModal = false }
    )
  }
}

/**
 * 🌟 FULL SCREEN DEDICATED DIALOG FOR BUDDHISM 10 & 11 SYLLABUS MASTER HUB
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BuddhismGrade10And11MasterHubDialog(
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
        .testTag("buddhism_10_11_fullscreen_dialog"),
      color = Color(0xFFF8FAFC) // Crisp light background
    ) {
      Column(modifier = Modifier.fillMaxSize()) {
        // TOP APP BAR
        Surface(
          color = Color(0xFF78350F), // Warm Buddhist Saffron
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
                      text = "10 & 11 බුද්ධ ධර්මය සම්පූර්ණ Hub",
                      color = Color.White,
                      fontSize = 15.sp,
                      fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Surface(
                      color = Color(0xFFF59E0B),
                      shape = RoundedCornerShape(4.dp)
                    ) {
                      Text(
                        text = "$activeGrade ශ්‍රේණිය",
                        color = Color.Black,
                        fontSize = 9.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                      )
                    }
                  }
                  Text(
                    text = "G.C.E. O/L විභාග නිර්දේශය 100% ආවරණය",
                    color = Color(0xFFCBD5E1),
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
                      .background(if (isSelected) Color(0xFFF59E0B) else Color.Transparent)
                      .clickable { activeGrade = g }
                      .padding(horizontal = 8.dp, vertical = 4.dp),
                    contentAlignment = Alignment.Center
                  ) {
                    Text(
                      text = "Grade $g",
                      color = if (isSelected) Color.Black else Color(0xFFCBD5E1),
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
                  "සූත්‍ර, ශාසන ඉතිහාසය, සිව්සස්, ගාථා, චරිත සොයන්න...",
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
                focusedBorderColor = Color(0xFFF59E0B),
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
              BuddhismSectionType.values().forEachIndexed { index, section ->
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
                      color = if (isSelected) (if (section.badgeColor == Color(0xFFF59E0B)) Color.Black else Color.White) else Color(0xFFCBD5E1)
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
              // ☸️ SUTTA ANALYSIS
              val items = BuddhismMasterRepository.suttasList.filter {
                (it.grade.contains(activeGrade) || it.grade == "10,11") &&
                    (searchQuery.isBlank() ||
                        it.title.contains(searchQuery, true) ||
                        it.suttaNamePali.contains(searchQuery, true))
              }
              if (items.isEmpty()) {
                item { BuddhismEmptySearchResultNotice() }
              } else {
                items(items, key = { it.id }) { sutta ->
                  BuddhismSuttaCard(sutta)
                }
              }
            }

            1 -> {
              // 🏛️ SASANA HISTORY
              val items = BuddhismMasterRepository.sasanaHistoryList.filter {
                (it.grade.contains(activeGrade) || it.grade == "10,11") &&
                    (searchQuery.isBlank() ||
                        it.title.contains(searchQuery, true) ||
                        it.eraAndRuler.contains(searchQuery, true))
              }
              if (items.isEmpty()) {
                item { BuddhismEmptySearchResultNotice() }
              } else {
                items(items, key = { it.id }) { event ->
                  BuddhismSasanaEventCard(event)
                }
              }
            }

            2 -> {
              // 💡 BUDDHIST PHILOSOPHY & ABHIDHAMMA
              val items = BuddhismMasterRepository.philosophyList.filter {
                (it.grade.contains(activeGrade) || it.grade == "10,11") &&
                    (searchQuery.isBlank() ||
                        it.title.contains(searchQuery, true) ||
                        it.paliConcept.contains(searchQuery, true))
              }
              if (items.isEmpty()) {
                item { BuddhismEmptySearchResultNotice() }
              } else {
                items(items, key = { it.id }) { item ->
                  BuddhismPhilosophyCard(item)
                }
              }
            }

            3 -> {
              // 👑 DISCIPLES & BIOGRAPHIES
              val items = BuddhismMasterRepository.disciplesList.filter {
                (it.grade.contains(activeGrade) || it.grade == "10,11") &&
                    (searchQuery.isBlank() ||
                        it.title.contains(searchQuery, true) ||
                        it.discipleName.contains(searchQuery, true))
              }
              if (items.isEmpty()) {
                item { BuddhismEmptySearchResultNotice() }
              } else {
                items(items, key = { it.id }) { disciple ->
                  BuddhismDiscipleCard(disciple)
                }
              }
            }

            4 -> {
              // 📜 PALI GATHAS & MEANINGS
              val items = BuddhismMasterRepository.gathasList.filter {
                (it.grade.contains(activeGrade) || it.grade == "10,11") &&
                    (searchQuery.isBlank() ||
                        it.gathaTitle.contains(searchQuery, true) ||
                        it.sourceText.contains(searchQuery, true))
              }
              if (items.isEmpty()) {
                item { BuddhismEmptySearchResultNotice() }
              } else {
                items(items, key = { it.id }) { gatha ->
                  BuddhismGathaCard(gatha)
                }
              }
            }

            5 -> {
              // 🌸 CULTURE & RITUALS
              val items = BuddhismMasterRepository.cultureList.filter {
                (it.grade.contains(activeGrade) || it.grade == "10,11") &&
                    (searchQuery.isBlank() ||
                        it.title.contains(searchQuery, true) ||
                        it.category.contains(searchQuery, true))
              }
              if (items.isEmpty()) {
                item { BuddhismEmptySearchResultNotice() }
              } else {
                items(items, key = { it.id }) { cult ->
                  BuddhismCultureCard(cult)
                }
              }
            }

            6 -> {
              // ⚡ RAPID MCQS
              val items = BuddhismMasterRepository.rapidMcqList.filter {
                (it.grade.contains(activeGrade) || it.grade == "10,11") &&
                    (searchQuery.isBlank() ||
                        it.question.contains(searchQuery, true) ||
                        it.topic.contains(searchQuery, true))
              }
              if (items.isEmpty()) {
                item { BuddhismEmptySearchResultNotice() }
              } else {
                items(items, key = { it.id }) { mcq ->
                  BuddhismRapidMcqCard(mcq)
                }
              }
            }

            7 -> {
              // 🎯 MARKING SECRETS & TIPS
              items(BuddhismMasterRepository.examSecretsList) { pair ->
                BuddhismExamSecretCard(title = pair.first, details = pair.second)
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
fun BuddhismSuttaCard(sutta: BuddhismSuttaItem) {
  var expanded by remember { mutableStateOf(false) }

  Card(
    shape = RoundedCornerShape(10.dp),
    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
    border = BorderStroke(1.dp, Color(0xFFF59E0B).copy(alpha = 0.4f)),
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
            color = Color(0xFFF59E0B).copy(alpha = 0.25f),
            shape = RoundedCornerShape(6.dp)
          ) {
            Text(
              text = "සූත්‍ර • ${sutta.grade} ශ්‍රේණිය",
              color = Color(0xFFFDE68A),
              fontSize = 9.sp,
              fontWeight = FontWeight.Bold,
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            )
          }
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = sutta.title,
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
          text = sutta.suttaNamePali,
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

        // Location & Audience
        Surface(
          color = Color(0xFF0F172A),
          shape = RoundedCornerShape(6.dp),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(modifier = Modifier.padding(8.dp)) {
            Text("📍 ස්ථානය & ශ්‍රාවකයින්:", color = Color(0xFFFBBF24), fontSize = 10.5.sp, fontWeight = FontWeight.Bold)
            Text(sutta.locationAndAudience, color = Color(0xFFCBD5E1), fontSize = 11.sp, lineHeight = 15.sp)
          }
        }

        Spacer(modifier = Modifier.height(6.dp))

        // Core Teachings
        Text("ප්‍රධාන ධර්ම කරුණු:", color = Color(0xFF34D399), fontSize = 11.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        sutta.coreTeachings.forEach { pt ->
          Row(modifier = Modifier.padding(vertical = 1.5.dp)) {
            Text("•", color = Color(0xFF10B981), fontSize = 12.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.width(6.dp))
            Text(pt, color = Color(0xFFE2E8F0), fontSize = 11.sp, lineHeight = 15.sp)
          }
        }

        Spacer(modifier = Modifier.height(6.dp))

        // Sub divisions
        sutta.subDivisions.forEach { (subTitle, points) ->
          Surface(
            color = Color(0xFF1E1B4B).copy(alpha = 0.4f),
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier.fillMaxWidth().padding(vertical = 3.dp)
          ) {
            Column(modifier = Modifier.padding(8.dp)) {
              Text(subTitle, color = Color(0xFFA5B4FC), fontSize = 11.sp, fontWeight = FontWeight.Bold)
              Spacer(modifier = Modifier.height(3.dp))
              points.forEach { p ->
                Text("▪ $p", color = Color(0xFFE0E7FF), fontSize = 10.5.sp, lineHeight = 14.sp)
              }
            }
          }
        }

        Spacer(modifier = Modifier.height(6.dp))

        // Exam summary & Essay question
        Surface(
          color = Color(0xFF451A03),
          shape = RoundedCornerShape(6.dp),
          modifier = Modifier.fillMaxWidth()
        ) {
          Column(modifier = Modifier.padding(8.dp)) {
            Text("🎯 විභාග ආදර්ශ ප්‍රශ්නය:", color = Color(0xFFFDE047), fontSize = 10.5.sp, fontWeight = FontWeight.Bold)
            Text(sutta.expectedEssayQuestion, color = Color(0xFFFEF08A), fontSize = 11.sp, lineHeight = 15.sp)
          }
        }
      }
    }
  }
}

@Composable
fun BuddhismSasanaEventCard(event: BuddhismSasanaEventItem) {
  var expanded by remember { mutableStateOf(false) }

  Card(
    shape = RoundedCornerShape(10.dp),
    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
    border = BorderStroke(1.dp, Color(0xFF2563EB).copy(alpha = 0.4f)),
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
            color = Color(0xFF2563EB).copy(alpha = 0.25f),
            shape = RoundedCornerShape(6.dp)
          ) {
            Text(
              text = "ශාසනය • ${event.grade} ශ්‍රේණිය",
              color = Color(0xFF93C5FD),
              fontSize = 9.sp,
              fontWeight = FontWeight.Bold,
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            )
          }
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = event.title,
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
          text = "${event.eraAndRuler} • ${event.primaryLeadership}",
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

        Text("🏛️ කාලය සහ රාජ්‍ය අනුග්‍රහය: ${event.eraAndRuler}", color = Color(0xFFBAE6FD), fontSize = 11.sp, fontWeight = FontWeight.Bold)
        Text("👑 මූලික නායකත්වය: ${event.primaryLeadership}", color = Color(0xFFCBD5E1), fontSize = 10.5.sp)

        Spacer(modifier = Modifier.height(6.dp))

        Text("සිදුවීමට ඓතිහාසික හේතු:", color = Color(0xFFF87171), fontSize = 10.5.sp, fontWeight = FontWeight.Bold)
        event.historicalCauses.forEach { c ->
          Text("• $c", color = Color(0xFFFECACA), fontSize = 10.5.sp, lineHeight = 14.sp)
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text("ප්‍රධාන ප්‍රතිඵල සහ ශාසනික වැදගත්කම:", color = Color(0xFF34D399), fontSize = 10.5.sp, fontWeight = FontWeight.Bold)
        event.keyOutcomesAndImpact.forEach { o ->
          Text("✓ $o", color = Color(0xFFD1FAE5), fontSize = 10.5.sp, lineHeight = 14.sp)
        }

        Spacer(modifier = Modifier.height(6.dp))

        Surface(
          color = Color(0xFF0F172A),
          shape = RoundedCornerShape(6.dp),
          modifier = Modifier.fillMaxWidth()
        ) {
          Text(
            text = "💡 O/L Tip: ${event.examTips}",
            color = Color(0xFFFDE047),
            fontSize = 10.5.sp,
            modifier = Modifier.padding(6.dp)
          )
        }
      }
    }
  }
}

@Composable
fun BuddhismPhilosophyCard(item: BuddhismPhilosophyItem) {
  var expanded by remember { mutableStateOf(false) }

  Card(
    shape = RoundedCornerShape(10.dp),
    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
    border = BorderStroke(1.dp, Color(0xFF0D9488).copy(alpha = 0.4f)),
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
              text = "දර්ශනය • ${item.grade} ශ්‍රේණිය",
              color = Color(0xFF5EEAD4),
              fontSize = 9.sp,
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
          text = item.coreDefinition,
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

        Text(item.coreDefinition, color = Color(0xFF99F6E4), fontSize = 11.sp, lineHeight = 15.sp)
        Spacer(modifier = Modifier.height(6.dp))

        item.analyticalBreakdown.forEach { (comp, desc) ->
          Surface(
            color = Color(0xFF0F172A),
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier.fillMaxWidth().padding(vertical = 2.5.dp)
          ) {
            Column(modifier = Modifier.padding(8.dp)) {
              Text(comp, color = Color(0xFF2DD4BF), fontSize = 11.sp, fontWeight = FontWeight.Bold)
              Spacer(modifier = Modifier.height(2.dp))
              Text(desc, color = Color(0xFFE2E8F0), fontSize = 10.5.sp, lineHeight = 14.sp)
            }
          }
        }

        Spacer(modifier = Modifier.height(4.dp))
        Text("🌿 ප්‍රායෝගික ජීවිතයට: ${item.practicalApplication}", color = Color(0xFF86EFAC), fontSize = 10.5.sp)
      }
    }
  }
}

@Composable
fun BuddhismDiscipleCard(disciple: BuddhismDiscipleItem) {
  var expanded by remember { mutableStateOf(false) }

  Card(
    shape = RoundedCornerShape(10.dp),
    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
    border = BorderStroke(1.dp, Color(0xFF7C3AED).copy(alpha = 0.4f)),
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
            color = Color(0xFF7C3AED).copy(alpha = 0.25f),
            shape = RoundedCornerShape(6.dp)
          ) {
            Text(
              text = "චරිත • ${disciple.grade} ශ්‍රේණිය",
              color = Color(0xFFC4B5FD),
              fontSize = 9.sp,
              fontWeight = FontWeight.Bold,
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            )
          }
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = disciple.title,
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
          text = disciple.supremeTitle,
          color = Color(0xFFCBD5E1),
          fontSize = 11.sp,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis,
          modifier = Modifier.padding(top = 2.dp)
        )
      } else {
        Spacer(modifier = Modifier.height(8.dp))
        Divider(color = Color(0xFF334155), thickness = 0.8.dp)
        Spacer(modifier = Modifier.height(8.dp))

        Text("👑 අග්‍රස්ථානය: ${disciple.supremeTitle}", color = Color(0xFFFDE047), fontSize = 11.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(4.dp))
        Text("📖 පසුබිම: ${disciple.lifeBackground}", color = Color(0xFFE2E8F0), fontSize = 10.5.sp, lineHeight = 14.sp)

        Spacer(modifier = Modifier.height(6.dp))

        Text("ආදර්ශමත් සුවිශේෂී ගුණාංග:", color = Color(0xFFA78BFA), fontSize = 10.5.sp, fontWeight = FontWeight.Bold)
        disciple.exemplaryQualities.forEach { q ->
          Text("✓ $q", color = Color(0xFFEDE9FE), fontSize = 10.5.sp, lineHeight = 14.sp)
        }
      }
    }
  }
}

@Composable
fun BuddhismGathaCard(gatha: BuddhismGathaItem) {
  var expanded by remember { mutableStateOf(false) }

  Card(
    shape = RoundedCornerShape(10.dp),
    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
    border = BorderStroke(1.dp, Color(0xFFDC2626).copy(alpha = 0.4f)),
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
            color = Color(0xFFDC2626).copy(alpha = 0.25f),
            shape = RoundedCornerShape(6.dp)
          ) {
            Text(
              text = "ගාථා • ${gatha.grade} ශ්‍රේණිය",
              color = Color(0xFFFCA5A5),
              fontSize = 9.sp,
              fontWeight = FontWeight.Bold,
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            )
          }
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = gatha.gathaTitle,
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
          text = gatha.paliGathaText.replace("\n", " • "),
          color = Color(0xFFCBD5E1),
          fontSize = 11.sp,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis,
          modifier = Modifier.padding(top = 2.dp)
        )
      } else {
        Spacer(modifier = Modifier.height(8.dp))
        Divider(color = Color(0xFF334155), thickness = 0.8.dp)
        Spacer(modifier = Modifier.height(8.dp))

        // Pali Verse Card
        Surface(
          color = Color(0xFF0F172A),
          shape = RoundedCornerShape(6.dp),
          modifier = Modifier.fillMaxWidth()
        ) {
          Text(
            text = gatha.paliGathaText,
            color = Color(0xFFFEF08A),
            fontSize = 11.5.sp,
            fontWeight = FontWeight.Bold,
            lineHeight = 17.sp,
            modifier = Modifier.padding(8.dp)
          )
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text("පදගත අර්ථ (Word-by-word meaning):", color = Color(0xFFF87171), fontSize = 10.5.sp, fontWeight = FontWeight.Bold)
        gatha.wordByWordMeaning.forEach { (word, meaning) ->
          Row(modifier = Modifier.padding(vertical = 1.dp)) {
            Text(word, color = Color(0xFFFCA5A5), fontSize = 10.5.sp, fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.width(6.dp))
            Text("= $meaning", color = Color(0xFFE2E8F0), fontSize = 10.5.sp)
          }
        }

        Spacer(modifier = Modifier.height(6.dp))
        Text("සම්පූර්ණ අර්ථය: ${gatha.overallMeaningSinhala}", color = Color(0xFF86EFAC), fontSize = 11.sp, lineHeight = 15.sp)
      }
    }
  }
}

@Composable
fun BuddhismCultureCard(cult: BuddhismCultureItem) {
  var expanded by remember { mutableStateOf(false) }

  Card(
    shape = RoundedCornerShape(10.dp),
    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
    border = BorderStroke(1.dp, Color(0xFFEA580C).copy(alpha = 0.4f)),
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
              text = cult.category,
              color = Color(0xFFFDBA74),
              fontSize = 9.sp,
              fontWeight = FontWeight.Bold,
              modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
            )
          }
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = cult.title,
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
          text = cult.historicalOrigin,
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

        Text("ඓතිහාසික පසුබිම: ${cult.historicalOrigin}", color = Color(0xFFFED7AA), fontSize = 11.sp, lineHeight = 15.sp)
        Spacer(modifier = Modifier.height(6.dp))

        Text("ප්‍රධාන චාරිත්‍ර පියවර:", color = Color(0xFFFB923C), fontSize = 10.5.sp, fontWeight = FontWeight.Bold)
        cult.ritualSteps.forEach { step ->
          Text("✓ $step", color = Color(0xFFE2E8F0), fontSize = 10.5.sp, lineHeight = 14.sp)
        }

        Spacer(modifier = Modifier.height(6.dp))
        Text("ආනිසංස: ${cult.spiritualBenefits.joinToString(", ")}", color = Color(0xFF86EFAC), fontSize = 10.5.sp)
      }
    }
  }
}

@Composable
fun BuddhismRapidMcqCard(mcq: BuddhismRapidMcq) {
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
fun BuddhismExamSecretCard(title: String, details: String) {
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
fun BuddhismEmptySearchResultNotice() {
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
        text = "සෙවුමට ගැළපෙන බුද්ධ ධර්මය අංග හමු නොවීය",
        color = Color(0xFF94A3B8),
        fontSize = 12.5.sp
      )
    }
  }
}
