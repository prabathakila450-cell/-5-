package com.example

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

/**
 * 100% ACCURATE O/L SYLLABUS HISTORY MASTER HUB FOR GRADES 10 & 11
 * Features:
 * - Compact Overview Card (Zero screen bloat)
 * - Full Screen Scrollable Modal Dialog
 * - Grade 10 and Grade 11 toggles
 * - Instant search filter
 * - Complete coverage of Kingdoms & Kings, Hydraulic Tech, Inscriptions & Sources,
 *   Colonial Struggles (1818 & 1848), Constitutional Reforms, O/L Map Points, World History,
 *   Exam Essay Marking Schemes & Rapid MCQs.
 */

@Composable
fun HistoryGrade10And11MasterHub(
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
      containerColor = Color(0xFF1E1B4B) // Rich Indigo / Deep Navy
    ),
    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
    modifier = modifier
      .fillMaxWidth()
      .clip(RoundedCornerShape(14.dp))
      .clickable {
        initialSelectedTab = 0
        showFullScreenModal = true
      }
      .testTag("history_grade_10_11_master_hub_card")
  ) {
    Column(modifier = Modifier.padding(12.dp)) {
      // Top Title Bar
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Box(
            modifier = Modifier
              .size(36.dp)
              .clip(CircleShape)
              .background(Brush.linearGradient(listOf(Color(0xFFD97706), Color(0xFFB45309)))),
            contentAlignment = Alignment.Center
          ) {
            Text("🏛️", fontSize = 18.sp)
          }
          Spacer(modifier = Modifier.width(10.dp))
          Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
              Text(
                text = "10 & 11 ඉතිහාසය විෂය නිර්දේශ Hub",
                color = Color.White,
                fontSize = 13.5.sp,
                fontWeight = FontWeight.Bold
              )
              Spacer(modifier = Modifier.width(6.dp))
              Surface(
                color = Color(0xFFD97706),
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
              text = "රාජධානි • වාරි ශිෂ්ටාචාරය • මූලාශ්‍ර • නිදහස් අරගල • ආණ්ඩුක්‍රම",
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
        val modules = HistoryModuleType.values()
        items(modules.toList()) { module ->
          Surface(
            color = Color(0xFF312E81),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.clickable {
              initialSelectedTab = module.ordinal
              showFullScreenModal = true
            }
          ) {
            Row(
              verticalAlignment = Alignment.CenterVertically,
              modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
              Text(module.icon, fontSize = 11.sp)
              Spacer(modifier = Modifier.width(4.dp))
              Text(
                text = module.title,
                color = Color(0xFFE0E7FF),
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium
              )
            }
          }
        }
      }

      Spacer(modifier = Modifier.height(10.dp))

      // Quick Access Action Buttons (Low Height)
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(6.dp)
      ) {
        Button(
          onClick = {
            initialSelectedTab = 0
            showFullScreenModal = true
          },
          colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD97706)),
          shape = RoundedCornerShape(8.dp),
          contentPadding = PaddingValues(horizontal = 10.dp, vertical = 5.dp),
          modifier = Modifier.weight(1f)
        ) {
          Text("🏛️ රාජධානි & රජවරු", fontSize = 10.5.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }

        Button(
          onClick = {
            initialSelectedTab = 3
            showFullScreenModal = true
          },
          colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDC2626)),
          shape = RoundedCornerShape(8.dp),
          contentPadding = PaddingValues(horizontal = 10.dp, vertical = 5.dp),
          modifier = Modifier.weight(1f)
        ) {
          Text("⚔️ නිදහස් අරගල", fontSize = 10.5.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }

        Button(
          onClick = {
            initialSelectedTab = 8
            showFullScreenModal = true
          },
          colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4F46E5)),
          shape = RoundedCornerShape(8.dp),
          contentPadding = PaddingValues(horizontal = 10.dp, vertical = 5.dp),
          modifier = Modifier.weight(1f)
        ) {
          Text("⚡ Rapid MCQs", fontSize = 10.5.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }
      }
    }
  }

  // Full Screen Modal Dialog
  if (showFullScreenModal) {
    HistoryFullScreenModal(
      initialGrade = activeGrade,
      initialTab = initialSelectedTab,
      onDismiss = { showFullScreenModal = false }
    )
  }
}

/**
 * FULL SCREEN SCROLLABLE MODAL FOR GRADES 10 & 11 HISTORY
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryFullScreenModal(
  initialGrade: String,
  initialTab: Int = 0,
  onDismiss: () -> Unit
) {
  var activeGrade by remember { mutableStateOf(initialGrade) }
  var selectedTab by remember { mutableIntStateOf(initialTab) }
  var searchQuery by remember { mutableStateOf("") }
  var isSearchExpanded by remember { mutableStateOf(false) }

  Dialog(
    onDismissRequest = onDismiss,
    properties = DialogProperties(
      usePlatformDefaultWidth = false,
      decorFitsSystemWindows = false
    )
  ) {
    Surface(
      modifier = Modifier
        .fillMaxSize()
        .systemBarsPadding()
        .testTag("history_full_screen_modal"),
      color = Color(0xFF0F172A) // Dark Navy
    ) {
      Column(modifier = Modifier.fillMaxSize()) {
        // Top App Bar
        TopAppBar(
          title = {
            Column {
              Text(
                text = "10 & 11 ඉතිහාසය විෂය නිර්දේශ Hub",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
              )
              Text(
                text = "100% O/L විෂය නිර්දේශය • අනුමාන කරුණු සහ සම්පූර්ණ විස්තර",
                fontSize = 11.sp,
                color = Color(0xFF94A3B8)
              )
            }
          },
          navigationIcon = {
            IconButton(onClick = onDismiss) {
              Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = Color.White
              )
            }
          },
          actions = {
            IconButton(onClick = { isSearchExpanded = !isSearchExpanded }) {
              Icon(
                imageVector = if (isSearchExpanded) Icons.Default.Close else Icons.Default.Search,
                contentDescription = "Search",
                tint = Color(0xFFFDE68A)
              )
            }
          },
          colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFF1E1B4B)
          )
        )

        // Search Bar (Expandable)
        AnimatedVisibility(visible = isSearchExpanded) {
          Surface(
            color = Color(0xFF1E1B4B),
            modifier = Modifier.fillMaxWidth()
          ) {
            OutlinedTextField(
              value = searchQuery,
              onValueChange = { searchQuery = it },
              placeholder = { Text("රජුගේ නම, රාජධානිය, වැව, අරගලය හෝ සෙල්ලිපිය සොයන්න...", fontSize = 12.sp, color = Color(0xFF94A3B8)) },
              leadingIcon = { Icon(Icons.Default.Search, contentDescription = null, tint = Color(0xFFFDE68A)) },
              trailingIcon = {
                if (searchQuery.isNotEmpty()) {
                  IconButton(onClick = { searchQuery = "" }) {
                    Icon(Icons.Default.Clear, contentDescription = "Clear", tint = Color.White)
                  }
                }
              },
              singleLine = true,
              colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.White,
                unfocusedTextColor = Color.White,
                focusedBorderColor = Color(0xFFD97706),
                unfocusedBorderColor = Color(0xFF334155)
              ),
              modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp, vertical = 6.dp)
            )
          }
        }

        // Grade Toggle Row
        Surface(
          color = Color(0xFF1E1B4B),
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            modifier = Modifier
              .fillMaxWidth()
              .padding(horizontal = 12.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            val grades = listOf("10" to "10 ශ්‍රේණිය (Grade 10)", "11" to "11 ශ්‍රේණිය (Grade 11)")
            grades.forEach { (gr, label) ->
              val isSelected = activeGrade == gr
              FilterChip(
                selected = isSelected,
                onClick = { activeGrade = gr },
                label = {
                  Text(
                    text = label,
                    fontSize = 11.5.sp,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
                  )
                },
                colors = FilterChipDefaults.filterChipColors(
                  selectedContainerColor = Color(0xFFD97706),
                  selectedLabelColor = Color.White,
                  containerColor = Color(0xFF312E81),
                  labelColor = Color(0xFFCBD5E1)
                ),
                border = null,
                modifier = Modifier.weight(1f)
              )
            }
          }
        }

        // Module Tabs Scroll Row
        ScrollableTabRow(
          selectedTabIndex = selectedTab,
          containerColor = Color(0xFF0F172A),
          contentColor = Color(0xFFD97706),
          edgePadding = 8.dp,
          indicator = { tabPositions ->
            TabRowDefaults.SecondaryIndicator(
              modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTab]),
              color = Color(0xFFD97706),
              height = 3.dp
            )
          },
          divider = { HorizontalDivider(color = Color(0xFF1E293B)) }
        ) {
          val modules = HistoryModuleType.values()
          modules.forEachIndexed { index, module ->
            Tab(
              selected = selectedTab == index,
              onClick = { selectedTab = index },
              text = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                  Text(module.icon, fontSize = 12.sp)
                  Spacer(modifier = Modifier.width(4.dp))
                  Text(
                    text = module.title,
                    fontSize = 11.5.sp,
                    fontWeight = if (selectedTab == index) FontWeight.Bold else FontWeight.Normal,
                    color = if (selectedTab == index) Color(0xFFFDE68A) else Color(0xFF94A3B8)
                  )
                }
              }
            )
          }
        }

        // Main Scrollable Content Area
        LazyColumn(
          modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp),
          contentPadding = PaddingValues(top = 10.dp, bottom = 24.dp),
          verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
          val currentModule = HistoryModuleType.values()[selectedTab]
          when (currentModule) {
            HistoryModuleType.KINGDOMS_RULERS -> {
              // 🏛️ KINGDOMS & RULERS
              val items = HistoryAdvancedSyllabusRepository.rulersList.filter {
                it.grade == activeGrade && (searchQuery.isBlank() || it.rulerName.contains(searchQuery, true) || it.kingdom.contains(searchQuery, true))
              }
              if (items.isEmpty()) {
                item { EmptySearchResultNotice() }
              } else {
                items(items, key = { it.id }) { ruler ->
                  HistoryRulerCard(ruler)
                }
              }
            }

            HistoryModuleType.IRRIGATION_CIVILIZATION -> {
              // 💧 HYDRAULIC CIVILIZATION
              val items = HistoryAdvancedSyllabusRepository.irrigationList.filter {
                it.grade == activeGrade && (searchQuery.isBlank() || it.name.contains(searchQuery, true) || it.builtBy.contains(searchQuery, true))
              }
              if (items.isEmpty()) {
                item { EmptySearchResultNotice() }
              } else {
                items(items, key = { it.id }) { irrigation ->
                  HistoryIrrigationCard(irrigation)
                }
              }
            }

            HistoryModuleType.HISTORICAL_SOURCES -> {
              // 📜 HISTORICAL SOURCES
              val items = HistoryAdvancedSyllabusRepository.sourcesList.filter {
                it.grade == activeGrade && (searchQuery.isBlank() || it.sourceName.contains(searchQuery, true) || it.category.contains(searchQuery, true))
              }
              if (items.isEmpty()) {
                item { EmptySearchResultNotice() }
              } else {
                items(items, key = { it.id }) { source ->
                  HistorySourceCard(source)
                }
              }
            }

            HistoryModuleType.FOREIGN_RULE_STRUGGLES -> {
              // ⚔️ COLONIAL RULE & UPRISINGS
              val items = HistoryAdvancedSyllabusRepository.colonialUprisingsList.filter {
                it.grade == activeGrade && (searchQuery.isBlank() || it.title.contains(searchQuery, true) || it.leaders.any { l -> l.contains(searchQuery, true) })
              }
              if (items.isEmpty()) {
                item { EmptySearchResultNotice() }
              } else {
                items(items, key = { it.id }) { uprising ->
                  HistoryUprisingCard(uprising)
                }
              }
            }

            HistoryModuleType.CONSTITUTIONS -> {
              // ⚖️ CONSTITUTIONAL REFORMS
              val items = HistoryAdvancedSyllabusRepository.constitutionsList.filter {
                it.grade == activeGrade && (searchQuery.isBlank() || it.name.contains(searchQuery, true) || it.year.contains(searchQuery))
              }
              if (items.isEmpty()) {
                item { EmptySearchResultNotice() }
              } else {
                items(items, key = { it.id }) { constitution ->
                  HistoryConstitutionCard(constitution)
                }
              }
            }

            HistoryModuleType.MAP_LOCATIONS -> {
              // 🗺️ O/L MAP LOCATIONS
              val items = HistoryAdvancedSyllabusRepository.mapLocationsList.filter {
                it.grade == activeGrade && (searchQuery.isBlank() || it.locationName.contains(searchQuery, true) || it.category.contains(searchQuery, true))
              }
              if (items.isEmpty()) {
                item { EmptySearchResultNotice() }
              } else {
                items(items, key = { it.id }) { location ->
                  HistoryMapLocationCard(location)
                }
              }
            }

            HistoryModuleType.WORLD_HISTORY -> {
              // 🌍 WORLD HISTORY
              val items = HistoryAdvancedSyllabusRepository.worldHistoryList.filter {
                it.grade == activeGrade && (searchQuery.isBlank() || it.eventTitle.contains(searchQuery, true) || it.region.contains(searchQuery, true))
              }
              if (items.isEmpty()) {
                item { EmptySearchResultNotice() }
              } else {
                items(items, key = { it.id }) { worldEvent ->
                  HistoryWorldEventCard(worldEvent)
                }
              }
            }

            HistoryModuleType.ART_SCULPTURE -> {
              // 🎨 ANCIENT ART & SCULPTURE
              val items = HistoryExpandedRepository.artSculpturesList.filter {
                it.grade == activeGrade && (searchQuery.isBlank() || it.title.contains(searchQuery, true) || it.category.contains(searchQuery, true))
              }
              if (items.isEmpty()) {
                item { EmptySearchResultNotice() }
              } else {
                item {
                  HistoryArtSculptureSection(items)
                }
              }
            }

            HistoryModuleType.HISTORICAL_TREATIES -> {
              // 📜 HISTORICAL TREATIES
              val items = HistoryExpandedRepository.treatiesList.filter {
                it.grade == activeGrade && (searchQuery.isBlank() || it.treatyName.contains(searchQuery, true) || it.signatoryParties.contains(searchQuery, true))
              }
              if (items.isEmpty()) {
                item { EmptySearchResultNotice() }
              } else {
                item {
                  HistoryTreatiesSection(items)
                }
              }
            }

            HistoryModuleType.RENAISSANCE -> {
                // RENAISSANCE - Placeholder or add implementation
            }

            HistoryModuleType.REVOLUTIONS_WORLD_WARS -> {
              // 🌐 WORLD REVOLUTIONS & ERAS
              val items = HistoryExpandedRepository.worldErasList.filter {
                it.grade == activeGrade && (searchQuery.isBlank() || it.eraTitle.contains(searchQuery, true))
              }
              if (items.isEmpty()) {
                item { EmptySearchResultNotice() }
              } else {
                item {
                  HistoryWorldErasSection(items)
                }
              }
            }

            HistoryModuleType.EXAM_SECRETS -> {
              // 📝 EXAM SECRETS & ESSAY SCHEMES
              val items = HistoryAdvancedSyllabusRepository.examTipsList.filter {
                it.grade == activeGrade && (searchQuery.isBlank() || it.questionTopic.contains(searchQuery, true))
              }
              if (items.isEmpty()) {
                item { EmptySearchResultNotice() }
              } else {
                items(items, key = { it.id }) { tip ->
                  HistoryExamTipCard(tip)
                }
              }
            }

            HistoryModuleType.RAPID_MCQS -> {
              // ⚡ RAPID MCQS
              val items = HistoryAdvancedSyllabusRepository.rapidMcqsList.filter {
                it.grade == activeGrade && (searchQuery.isBlank() || it.question.contains(searchQuery, true))
              }
              if (items.isEmpty()) {
                item { EmptySearchResultNotice() }
              } else {
                items(items, key = { it.id }) { mcq ->
                  HistoryRapidMcqCard(mcq)
                }
              }
            }

            HistoryModuleType.CASCADE_TANKS -> {
              // 🌿 CASCADE TANK SYSTEM
              val items = HistoryExpandedRepository.cascadeTanksList.filter {
                it.grade == activeGrade && (searchQuery.isBlank() || it.componentName.contains(searchQuery, true) || it.mainFunction.contains(searchQuery, true))
              }
              if (items.isEmpty()) {
                item { EmptySearchResultNotice() }
              } else {
                item {
                  HistoryCascadeTanksSection(items)
                }
              }
            }

            HistoryModuleType.MAP_QUIZ -> {
                item { HistoryMapQuizSection(onClose = { selectedTab = 0 }) }
            }
            HistoryModuleType.TIMELINE -> {
                item { HistoryTimelineSection(onClose = { selectedTab = 0 }) }
            }
            HistoryModuleType.VISUAL_GALLERY -> {
                item { HistoryVisualGallerySection(onClose = { selectedTab = 0 }) }
            }
            HistoryModuleType.COMPARISON -> {
                item { HistoryComparisonSection(onClose = { selectedTab = 0 }) }
            }
            HistoryModuleType.ESSAY_TEMPLATES -> {
                item { HistoryEssayTemplatesSection(onClose = { selectedTab = 0 }) }
            }
            HistoryModuleType.ESSAY_TEMPLATES -> {
                item { HistoryEssayTemplatesSection(onClose = { selectedTab = 0 }) }
            }
            
            HistoryModuleType.NATIONAL_REVIVAL -> {
              // 🔥 NATIONAL REVIVAL
              val items = HistoryExpandedRepository.revivalList.filter {
                it.grade == activeGrade && (searchQuery.isBlank() || it.leaderOrMovement.contains(searchQuery, true))
              }
              if (items.isEmpty()) {
                item { EmptySearchResultNotice() }
              } else {
                item {
                  HistoryRevivalSection(items)
                }
              }
            }

            HistoryModuleType.TIMELINE_CHALLENGE -> {
              // ⏳ TIMELINE CHALLENGE
              val items = HistoryExpandedRepository.timelineChallengesList.filter {
                it.grade == activeGrade && (searchQuery.isBlank() || it.challengeTitle.contains(searchQuery, true))
              }
              if (items.isEmpty()) {
                item { EmptySearchResultNotice() }
              } else {
                item {
                  HistoryTimelineChallengeSection(items)
                }
              }
            }

            HistoryModuleType.ARTIFACT_IDENTIFIER -> {
              // 🔍 ARTIFACT IDENTIFIER
              val items = HistoryExpandedRepository.artifactsList.filter {
                it.grade == activeGrade && (searchQuery.isBlank() || it.artifactName.contains(searchQuery, true) || it.location.contains(searchQuery, true))
              }
              if (items.isEmpty()) {
                item { EmptySearchResultNotice() }
              } else {
                item {
                  HistoryArtifactSection(items)
                }
              }
            }

            HistoryModuleType.ESSAY_GUIDE -> {
              // 📝 ESSAY GUIDES
              item {
                HistoryEssayGuideSubSection(grade = activeGrade)
              }
            }
            
            HistoryModuleType.AUDIO_LISTENING_LAB, HistoryModuleType.NOTATION_TRAINING, HistoryModuleType.PERFORMANCE_CHECKLIST, HistoryModuleType.MUSIC_EVOLUTION -> {
                // Placeholder
            }
            else -> {
                item { Text("තවම සම්පූර්ණ කර නොමැත.", color = Color.Gray, modifier = Modifier.padding(16.dp)) }
            }
          }
        }
      }
    }
  }
}

// -------------------------------------------------------------
// SUB-COMPONENTS & CARDS
// -------------------------------------------------------------

@Composable
fun HistoryRulerCard(ruler: HistoryRulerItem) {
  var expanded by remember { mutableStateOf(false) }

  Card(
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
    border = BorderStroke(1.dp, Color(0xFF334155)),
    modifier = Modifier.fillMaxWidth()
  ) {
    Column(modifier = Modifier.padding(12.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column(modifier = Modifier.weight(1f)) {
          Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
              text = ruler.rulerName,
              fontSize = 14.sp,
              fontWeight = FontWeight.Bold,
              color = Color(0xFFFDE68A)
            )
            Spacer(modifier = Modifier.width(6.dp))
            Surface(
              color = Color(0xFF374151),
              shape = RoundedCornerShape(4.dp)
            ) {
              Text(
                text = ruler.kingdom,
                color = Color(0xFFE2E8F0),
                fontSize = 9.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
              )
            }
          }
          Text(
            text = ruler.epithet,
            fontSize = 11.5.sp,
            color = Color(0xFF94A3B8),
            fontWeight = FontWeight.Medium
          )
          Text(
            text = "කාලය: ${ruler.period}",
            fontSize = 10.sp,
            color = Color(0xFF64748B)
          )
        }

        IconButton(
          onClick = { expanded = !expanded },
          modifier = Modifier.size(32.dp)
        ) {
          Icon(
            imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
            contentDescription = "Expand",
            tint = Color(0xFFFDE68A)
          )
        }
      }

      AnimatedVisibility(visible = expanded) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
        ) {
          HorizontalDivider(color = Color(0xFF334155), thickness = 0.8.dp)
          Spacer(modifier = Modifier.height(8.dp))

          // Political services
          Text("👑 දේශපාලන මෙහෙවර:", fontSize = 11.5.sp, fontWeight = FontWeight.Bold, color = Color(0xFF67E8F9))
          ruler.politicalServices.forEach { s ->
            Text(" • $s", fontSize = 11.sp, color = Color(0xFFE2E8F0), lineHeight = 16.sp)
          }

          Spacer(modifier = Modifier.height(6.dp))

          // Economic services
          Text("🌾 ආර්ථික & වාරි සේවාවන්:", fontSize = 11.5.sp, fontWeight = FontWeight.Bold, color = Color(0xFF86EFAC))
          ruler.economicServices.forEach { s ->
            Text(" • $s", fontSize = 11.sp, color = Color(0xFFE2E8F0), lineHeight = 16.sp)
          }

          Spacer(modifier = Modifier.height(6.dp))

          // Religious/Cultural services
          Text("🛕 ශාසනික & සංස්කෘතික සේවාවන්:", fontSize = 11.5.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFCA5A5))
          ruler.religiousCulturalServices.forEach { s ->
            Text(" • $s", fontSize = 11.sp, color = Color(0xFFE2E8F0), lineHeight = 16.sp)
          }

          if (ruler.keyExamFacts.isNotEmpty()) {
            Spacer(modifier = Modifier.height(6.dp))
            Surface(
              color = Color(0xFF1E1B4B),
              shape = RoundedCornerShape(6.dp),
              modifier = Modifier.fillMaxWidth()
            ) {
              Row(
                modifier = Modifier.padding(8.dp),
                verticalAlignment = Alignment.Top
              ) {
                Text("💡", fontSize = 12.sp)
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                  text = "විභාග විශේෂ සටහන: ${ruler.keyExamFacts.joinToString(" ")}",
                  fontSize = 10.5.sp,
                  color = Color(0xFFFDE68A),
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

@Composable
fun HistoryIrrigationCard(item: HistoryIrrigationItem) {
  Card(
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
    border = BorderStroke(1.dp, Color(0xFF0284C7).copy(alpha = 0.4f)),
    modifier = Modifier.fillMaxWidth()
  ) {
    Column(modifier = Modifier.padding(12.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text("💧", fontSize = 16.sp)
          Spacer(modifier = Modifier.width(8.dp))
          Text(
            text = item.name,
            fontSize = 13.5.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFBAE6FD)
          )
        }
        Surface(
          color = Color(0xFF0284C7),
          shape = RoundedCornerShape(4.dp)
        ) {
          Text(
            text = item.technologyComponent,
            color = Color.White,
            fontSize = 9.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
          )
        }
      }

      Spacer(modifier = Modifier.height(6.dp))
      Text("නිර්මාතෘ / යුගය: ${item.builtBy}", fontSize = 11.sp, color = Color(0xFF94A3B8), fontWeight = FontWeight.Medium)
      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = "ස්වභාවය සහ ලක්ෂණ: ${item.features}",
        fontSize = 11.sp,
        color = Color(0xFFE2E8F0),
        lineHeight = 16.sp
      )

      Spacer(modifier = Modifier.height(6.dp))
      Surface(
        color = Color(0xFF082F49),
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(8.dp)) {
          Text("තාක්ෂණික වැදගත්කම:", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF7DD3FC))
          Text(
            text = item.syllabusImportance,
            fontSize = 10.5.sp,
            color = Color(0xFFE0F2FE),
            lineHeight = 15.sp
          )
        }
      }
    }
  }
}

@Composable
fun HistorySourceCard(source: HistorySourceItem) {
  var showDetails by remember { mutableStateOf(false) }

  Card(
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
    border = BorderStroke(1.dp, Color(0xFF059669).copy(alpha = 0.4f)),
    modifier = Modifier.fillMaxWidth()
  ) {
    Column(modifier = Modifier.padding(12.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column(modifier = Modifier.weight(1f)) {
          Text(
            text = source.sourceName,
            fontSize = 13.5.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFA7F3D0)
          )
          Text(
            text = source.category,
            fontSize = 10.sp,
            color = Color(0xFF34D399),
            fontWeight = FontWeight.Medium
          )
        }

        Surface(
          color = Color(0xFF064E3B),
          shape = RoundedCornerShape(6.dp),
          modifier = Modifier.clickable { showDetails = !showDetails }
        ) {
          Text(
            text = if (showDetails) "හකුලන්න" else "විස්තර & ගැටලු",
            color = Color(0xFF6EE7B7),
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
          )
        }
      }

      Spacer(modifier = Modifier.height(6.dp))
      Text("කර්තෘ / සම්භවය: ${source.authorOrOrigin}", fontSize = 10.5.sp, color = Color(0xFF94A3B8))
      Spacer(modifier = Modifier.height(4.dp))
      Text("අන්තර්ගතය: ${source.keyContents}", fontSize = 11.sp, color = Color(0xFFE2E8F0), lineHeight = 16.sp)

      AnimatedVisibility(visible = showDetails) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
        ) {
          Surface(
            color = Color(0xFF064E3B).copy(alpha = 0.4f),
            shape = RoundedCornerShape(6.dp),
            modifier = Modifier.fillMaxWidth()
          ) {
            Column(modifier = Modifier.padding(8.dp)) {
              Text("ඓතිහාසික වටිනාකම:", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF6EE7B7))
              Text(source.historicalValue, fontSize = 10.5.sp, color = Color(0xFFD1FAE5), lineHeight = 15.sp)
              Spacer(modifier = Modifier.height(6.dp))
              Text("විභාග ආදර්ශ ප්‍රශ්නය:", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFDE68A))
              Text(source.examSampleQuestion, fontSize = 10.5.sp, color = Color.White, lineHeight = 15.sp)
            }
          }
        }
      }
    }
  }
}

@Composable
fun HistoryUprisingCard(item: HistoryColonialUprisingItem) {
  var expanded by remember { mutableStateOf(false) }

  Card(
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
    border = BorderStroke(1.dp, Color(0xFFDC2626).copy(alpha = 0.4f)),
    modifier = Modifier.fillMaxWidth()
  ) {
    Column(modifier = Modifier.padding(12.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column(modifier = Modifier.weight(1f)) {
          Text(
            text = item.title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFFCA5A5)
          )
          Text(
            text = "වර්ෂය: ${item.periodOrYear} • ${item.powersInvolved}",
            fontSize = 10.sp,
            color = Color(0xFF94A3B8)
          )
        }
        IconButton(
          onClick = { expanded = !expanded },
          modifier = Modifier.size(32.dp)
        ) {
          Icon(
            imageVector = if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore,
            contentDescription = null,
            tint = Color(0xFFFCA5A5)
          )
        }
      }

      Spacer(modifier = Modifier.height(4.dp))
      Text(
        text = "නායකයින්: ${item.leaders.joinToString(", ")}",
        fontSize = 11.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFFFDE68A)
      )

      AnimatedVisibility(visible = expanded) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
        ) {
          Text("🔥 මූලික හේතු:", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFFF87171))
          item.rootCauses.forEach { c ->
            Text(" • $c", fontSize = 10.5.sp, color = Color(0xFFE2E8F0), lineHeight = 15.sp)
          }

          Spacer(modifier = Modifier.height(6.dp))
          Text("⚔️ ප්‍රධාන සිදුවීම්:", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFDE68A))
          item.mainEvents.forEach { e ->
            Text(" • $e", fontSize = 10.5.sp, color = Color(0xFFE2E8F0), lineHeight = 15.sp)
          }

          Spacer(modifier = Modifier.height(6.dp))
          Text("💥 ප්‍රතිඵල සහ බලපෑම:", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFF93C5FD))
          item.consequencesAndImpact.forEach { ci ->
            Text(" • $ci", fontSize = 10.5.sp, color = Color(0xFFE2E8F0), lineHeight = 15.sp)
          }
        }
      }
    }
  }
}

@Composable
fun HistoryConstitutionCard(item: HistoryConstitutionItem) {
  var showDetails by remember { mutableStateOf(false) }

  Card(
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
    border = BorderStroke(1.dp, Color(0xFF7C3AED).copy(alpha = 0.4f)),
    modifier = Modifier.fillMaxWidth()
  ) {
    Column(modifier = Modifier.padding(12.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column(modifier = Modifier.weight(1f)) {
          Text(
            text = item.name,
            fontSize = 13.5.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFC4B5FD)
          )
          Text(
            text = "වර්ෂය: ${item.year} • කොමිසම: ${item.governorOrCommission}",
            fontSize = 10.sp,
            color = Color(0xFF94A3B8)
          )
        }

        Surface(
          color = Color(0xFF4C1D95),
          shape = RoundedCornerShape(6.dp),
          modifier = Modifier.clickable { showDetails = !showDetails }
        ) {
          Text(
            text = if (showDetails) "හකුලන්න" else "ප්‍රතිසංස්කරණ",
            color = Color(0xFFDDD6FE),
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
          )
        }
      }

      Spacer(modifier = Modifier.height(6.dp))
      Text("සභා ව්‍යුහය: ${item.councilStructure}", fontSize = 10.5.sp, color = Color(0xFFE2E8F0))
      Text("ඡන්ද අයිතිය: ${item.votingRights}", fontSize = 10.5.sp, color = Color(0xFFFDE68A), fontWeight = FontWeight.Medium)

      AnimatedVisibility(visible = showDetails) {
        Column(
          modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp)
        ) {
          Text("✅ ප්‍රධාන ප්‍රතිසංස්කරණ:", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFFA7F3D0))
          item.keyReforms.forEach { r ->
            Text(" • $r", fontSize = 10.5.sp, color = Color(0xFFE2E8F0), lineHeight = 15.sp)
          }

          Spacer(modifier = Modifier.height(6.dp))
          Text("⚠️ දුර්වලතා සහ සීමාවන්:", fontSize = 11.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFCA5A5))
          item.shortcomingsOrDrawbacks.forEach { d ->
            Text(" • $d", fontSize = 10.5.sp, color = Color(0xFFE2E8F0), lineHeight = 15.sp)
          }
        }
      }
    }
  }
}

@Composable
fun HistoryMapLocationCard(item: HistoryMapLocationItem) {
  Card(
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
    border = BorderStroke(1.dp, Color(0xFF2563EB).copy(alpha = 0.4f)),
    modifier = Modifier.fillMaxWidth()
  ) {
    Column(modifier = Modifier.padding(12.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
          Text("📍", fontSize = 16.sp)
          Spacer(modifier = Modifier.width(6.dp))
          Text(
            text = item.locationName,
            fontSize = 13.5.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF93C5FD)
          )
        }
        Surface(
          color = Color(0xFF1E3A8A),
          shape = RoundedCornerShape(4.dp)
        ) {
          Text(
            text = item.category,
            color = Color(0xFFBFDBFE),
            fontSize = 8.5.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
          )
        }
      }

      Spacer(modifier = Modifier.height(4.dp))
      Text("පිහිටීම: ${item.provinceOrDistrict}", fontSize = 10.5.sp, color = Color(0xFF94A3B8))
      Spacer(modifier = Modifier.height(4.dp))
      Text("ඓතිහාසික වැදගත්කම: ${item.historicalSignificance}", fontSize = 10.5.sp, color = Color(0xFFE2E8F0), lineHeight = 15.sp)

      Spacer(modifier = Modifier.height(6.dp))
      Surface(
        color = Color(0xFF172554),
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        Row(
          modifier = Modifier.padding(6.dp),
          verticalAlignment = Alignment.CenterVertically
        ) {
          Text("🗺️", fontSize = 11.sp)
          Spacer(modifier = Modifier.width(6.dp))
          Text(
            text = "සිතියමේ ලකුණු කරන අයුරු: ${item.mapMarkingGuide}",
            fontSize = 10.sp,
            color = Color(0xFFBAE6FD),
            lineHeight = 14.sp
          )
        }
      }
    }
  }
}

@Composable
fun HistoryWorldEventCard(item: HistoryWorldEventItem) {
  Card(
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
    border = BorderStroke(1.dp, Color(0xFF0D9488).copy(alpha = 0.4f)),
    modifier = Modifier.fillMaxWidth()
  ) {
    Column(modifier = Modifier.padding(12.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Column(modifier = Modifier.weight(1f)) {
          Text(
            text = item.eventTitle,
            fontSize = 13.5.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFF5EEAD4)
          )
          Text(
            text = "යුගය: ${item.era} • කලාපය: ${item.region}",
            fontSize = 10.sp,
            color = Color(0xFF94A3B8)
          )
        }
      }

      Spacer(modifier = Modifier.height(6.dp))
      Text("ප්‍රමුඛ නායකයන් / පුරෝගාමීන්: ${item.pioneersOrLeaders.joinToString(", ")}", fontSize = 10.5.sp, color = Color(0xFFFDE68A))

      Spacer(modifier = Modifier.height(4.dp))
      Text("මූලික හේතු:", fontSize = 10.5.sp, fontWeight = FontWeight.Bold, color = Color(0xFF2DD4BF))
      item.keyCauses.forEach { c ->
        Text(" • $c", fontSize = 10.sp, color = Color(0xFFE2E8F0))
      }

      Spacer(modifier = Modifier.height(4.dp))
      Text("නව නිපැයුම් / ප්‍රතිඵල:", fontSize = 10.5.sp, fontWeight = FontWeight.Bold, color = Color(0xFF93C5FD))
      item.keyInnovationsOrConsequences.forEach { i ->
        Text(" • $i", fontSize = 10.sp, color = Color(0xFFE2E8F0))
      }

      Spacer(modifier = Modifier.height(6.dp))
      Surface(
        color = Color(0xFF134E4A),
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        Text(
          text = "ලංකාවට ඇතිවූ බලපෑම: ${item.impactOnSriLanka}",
          fontSize = 10.sp,
          color = Color(0xFFCCFBF1),
          modifier = Modifier.padding(6.dp)
        )
      }
    }
  }
}

@Composable
fun HistoryExamTipCard(item: HistoryExamEssayTip) {
  Card(
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
    border = BorderStroke(1.dp, Color(0xFFE11D48).copy(alpha = 0.4f)),
    modifier = Modifier.fillMaxWidth()
  ) {
    Column(modifier = Modifier.padding(12.dp)) {
      Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        Text(
          text = item.questionTopic,
          fontSize = 13.5.sp,
          fontWeight = FontWeight.Bold,
          color = Color(0xFFFDA4AF),
          modifier = Modifier.weight(1f)
        )
        Surface(
          color = Color(0xFF881337),
          shape = RoundedCornerShape(4.dp)
        ) {
          Text(
            text = item.partMarkAllocations,
            color = Color.White,
            fontSize = 9.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
          )
        }
      }

      Spacer(modifier = Modifier.height(8.dp))
      Text("📝 රචනා ප්‍රශ්නයට පිළිතුරු සැලසුම (Full Marks Structure):", fontSize = 10.5.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFDE68A))
      item.structuredParagraphPlan.forEachIndexed { idx, plan ->
        Text(" ${idx + 1}. $plan", fontSize = 10.5.sp, color = Color(0xFFE2E8F0), lineHeight = 15.sp)
      }

      Spacer(modifier = Modifier.height(6.dp))
      Surface(
        color = Color(0xFF4C0519),
        shape = RoundedCornerShape(6.dp),
        modifier = Modifier.fillMaxWidth()
      ) {
        Column(modifier = Modifier.padding(8.dp)) {
          Text("⚠️ සිසුන් නිතර කරන වැරදි:", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFCA5A5))
          Text(item.commonMistakes, fontSize = 10.sp, color = Color(0xFFFFF1F2), lineHeight = 14.sp)
          Spacer(modifier = Modifier.height(4.dp))
          Text("🧠 මතක තබාගැනීමේ කෙටි ක්‍රමය (Mnemonic):", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFDE68A))
          Text(item.mnemonicOrMemoryTrick, fontSize = 10.5.sp, color = Color.White, fontWeight = FontWeight.SemiBold)
        }
      }
    }
  }
}

@Composable
fun HistoryRapidMcqCard(item: HistoryRapidMcqItem) {
  var selectedIndex by remember { mutableStateOf<Int?>(null) }
  val isAnswered = selectedIndex != null

  Card(
    shape = RoundedCornerShape(12.dp),
    colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B)),
    border = BorderStroke(1.dp, Color(0xFF4F46E5).copy(alpha = 0.4f)),
    modifier = Modifier.fillMaxWidth()
  ) {
    Column(modifier = Modifier.padding(12.dp)) {
      Text(
        text = item.question,
        fontSize = 12.5.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        lineHeight = 18.sp
      )

      Spacer(modifier = Modifier.height(8.dp))

      item.options.forEachIndexed { index, option ->
        val isChosen = selectedIndex == index
        val isCorrect = index == item.correctIndex
        val bgColor = when {
          !isAnswered -> Color(0xFF0F172A)
          isCorrect -> Color(0xFF065F46) // Green
          isChosen -> Color(0xFF991B1B) // Red
          else -> Color(0xFF0F172A)
        }
        val borderColor = when {
          !isAnswered -> Color(0xFF334155)
          isCorrect -> Color(0xFF10B981)
          isChosen -> Color(0xFFEF4444)
          else -> Color(0xFF334155)
        }

        Surface(
          color = bgColor,
          shape = RoundedCornerShape(8.dp),
          border = BorderStroke(1.dp, borderColor),
          modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp)
            .clickable(enabled = !isAnswered) {
              selectedIndex = index
            }
        ) {
          Row(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
          ) {
            Text(
              text = "(${index + 1})",
              fontSize = 11.sp,
              fontWeight = FontWeight.Bold,
              color = if (isChosen || isCorrect) Color.White else Color(0xFF94A3B8)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
              text = option,
              fontSize = 11.5.sp,
              color = Color.White,
              modifier = Modifier.weight(1f)
            )
            if (isAnswered) {
              if (isCorrect) {
                Text("✓ නිවැරදියි", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFF6EE7B7))
              } else if (isChosen) {
                Text("✗ වැරදියි", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color(0xFFFCA5A5))
              }
            }
          }
        }
      }

      if (isAnswered) {
        Spacer(modifier = Modifier.height(6.dp))
        Surface(
          color = Color(0xFF312E81),
          shape = RoundedCornerShape(6.dp),
          modifier = Modifier.fillMaxWidth()
        ) {
          Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.Top
          ) {
            Text("💡", fontSize = 12.sp)
            Spacer(modifier = Modifier.width(6.dp))
            Text(
              text = item.explanation,
              fontSize = 10.5.sp,
              color = Color(0xFFE0E7FF),
              lineHeight = 15.sp
            )
          }
        }
      }
    }
  }
}

@Composable
fun EmptySearchResultNotice() {
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .padding(vertical = 32.dp),
    contentAlignment = Alignment.Center
  ) {
    Text(
      text = "සොයන ලද විෂය කොටස හමු නොවීය. කරුණාකර වෙනත් නමක් හෝ අංකයක් යොදන්න.",
      color = Color(0xFF94A3B8),
      fontSize = 12.sp,
      textAlign = TextAlign.Center
    )
  }
}

@Composable
fun HistoryEssayGuideSubSection(grade: String) {
  val essays = remember(grade) {
    ComprehensiveStructuredEssayRepository.getQuestions(
      grade = grade,
      subject = "ඉතිහාසය"
    ).filter { it.type == "ESSAY" }
  }

  Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
    Text("රචනා ලිවීමේ සැකිලි සහ ආදර්ශ පිළිතුරු", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = Color.White)
    Spacer(modifier = Modifier.height(8.dp))
    essays.forEach { essay ->
      Card(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1E293B))
      ) {
        Column(modifier = Modifier.padding(12.dp)) {
          Text(essay.topicSinhala, fontWeight = FontWeight.Bold, color = Color(0xFFFDE68A))
          Spacer(modifier = Modifier.height(4.dp))
          Text(essay.mainScenario, fontSize = 12.sp, color = Color(0xFFE2E8F0))
        }
      }
    }
  }
}

@Composable
private fun HistoryMapQuizSection(onClose: () -> Unit) {
  Column(modifier = Modifier.padding(8.dp).fillMaxWidth().background(Color(0xFF0F172A), RoundedCornerShape(8.dp)).padding(8.dp)) {
    Text("සිතියම් පුහුණුව", color = Color.White, fontWeight = FontWeight.Bold)
    Button(onClick = onClose, modifier = Modifier.padding(top = 8.dp)) { Text("වසන්න") }
  }
}

@Composable
private fun HistoryTimelineSection(onClose: () -> Unit) {
  Column(modifier = Modifier.padding(8.dp).fillMaxWidth().background(Color(0xFF0F172A), RoundedCornerShape(8.dp)).padding(8.dp)) {
    Text("කාලරේඛාව", color = Color.White, fontWeight = FontWeight.Bold)
    Button(onClick = onClose, modifier = Modifier.padding(top = 8.dp)) { Text("වසන්න") }
  }
}

@Composable
private fun HistoryVisualGallerySection(onClose: () -> Unit) {
  Column(modifier = Modifier.padding(8.dp).fillMaxWidth().background(Color(0xFF0F172A), RoundedCornerShape(8.dp)).padding(8.dp)) {
    Text("පුරාවස්තු ගැලරිය", color = Color.White, fontWeight = FontWeight.Bold)
    Button(onClick = onClose, modifier = Modifier.padding(top = 8.dp)) { Text("වසන්න") }
  }
}

@Composable
private fun HistoryComparisonSection(onClose: () -> Unit) {
  Column(modifier = Modifier.padding(8.dp).fillMaxWidth().background(Color(0xFF0F172A), RoundedCornerShape(8.dp)).padding(8.dp)) {
    Text("සංසන්දන වගු", color = Color.White, fontWeight = FontWeight.Bold)
    Button(onClick = onClose, modifier = Modifier.padding(top = 8.dp)) { Text("වසන්න") }
  }
}

@Composable
private fun HistoryEssayTemplatesSection(onClose: () -> Unit) {
  Column(modifier = Modifier.padding(8.dp).fillMaxWidth().background(Color(0xFF0F172A), RoundedCornerShape(8.dp)).padding(8.dp)) {
    Text("රචනා සැකිලි", color = Color.White, fontWeight = FontWeight.Bold)
    Button(onClick = onClose, modifier = Modifier.padding(top = 8.dp)) { Text("වසන්න") }
  }
}
